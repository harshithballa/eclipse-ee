package webjdbc;
//services
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/medicines")
public class MedicinesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MedicineDbUtil medicineDbUtil;
	
	@Resource(name="jdbc/medicines")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			medicineDbUtil = new MedicineDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listMedicines(request, response);
				break;
				
			case "ADD":
				addMedicine(request, response);
				break;
				
			case "LOAD":
				loadMedicine(request, response);
				break;
				
			case "UPDATE":
				updateMedicine(request, response);
				break;
				
			case "DELETE":
				deleteMedicine(request, response);
				break;
				
			default:
				listMedicines(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	
	

	private void addMedicine(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String medicineName = request.getParameter("medicineName");
		String costStr = request.getParameter("cost");
		String unitsStr = request.getParameter("units");
		String mfgDate = request.getParameter("mfgDate");
		String expDate = request.getParameter("expDate");
		
		double cost = Double.parseDouble(costStr);
		int units = Integer.parseInt(unitsStr);
		
		// create a new student object
		Medicine theMedicine = new Medicine(medicineName, units, cost, mfgDate, expDate);
		
		// add the student to the database
		medicineDbUtil.addMedicine(theMedicine);
				
		// send back to main page (the student list)
		listMedicines(request, response);
	}

	private void deleteMedicine(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theMedicineId = request.getParameter("medicineId");
			
			// delete student from database
			medicineDbUtil.deleteMedicine(theMedicineId);
			
			// send them back to "list students" page
			listMedicines(request, response);
		}
	
	
	private void updateMedicine(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			int id = Integer.parseInt(request.getParameter("medicineId"));
			String medicineName = request.getParameter("medicineName");
			int units = Integer.parseInt(request.getParameter("units"));
			double cost = Double.parseDouble(request.getParameter("cost"));
			String mfgDate = request.getParameter("mfgDate");
			String expDate = request.getParameter("expDate");
			
			// create a new student object
			Medicine theMedicine = new Medicine(id, medicineName, units, cost, mfgDate, expDate);
			
			// perform update on database
			medicineDbUtil.updateMedicine(theMedicine);
			
			// send them back to the "list students" page
			listMedicines(request, response);
			
		}

	private void loadMedicine(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theMedicineId = request.getParameter("medicineId");
			
			// get student from database (db util)
			Medicine theMedicine = medicineDbUtil.getMedicines(theMedicineId);
			
			// place student in the request attribute
			request.setAttribute("THE_MEDICINE", theMedicine);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-medicine-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void listMedicines(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Medicine> medicines = medicineDbUtil.getMedicines();
		
		// add students to the request
		request.setAttribute("MEDICINE_LIST", medicines);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-medicines.jsp");
		dispatcher.forward(request, response);
	}

}





