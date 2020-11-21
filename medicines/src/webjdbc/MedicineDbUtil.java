package webjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MedicineDbUtil {

	private DataSource dataSource;

	public MedicineDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Medicine> getMedicines() throws Exception {
		
		List<Medicine> medicines = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from medicines order by medicineName";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String medicineName = myRs.getString("medicineName");
				int units = myRs.getInt("units");
				double cost = myRs.getDouble("cost");
				String mfgDate = myRs.getString("mfgDate");
				String expDate = myRs.getString("expDate");
				
				// create new student object
				Medicine tempMedicine = new Medicine(id, medicineName, units, cost, mfgDate, expDate);
				
				// add it to the list of students
				medicines.add(tempMedicine);				
			}
			
			return medicines;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void deleteMedicine(String theMedicineId) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int medicineId = Integer.parseInt(theMedicineId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from medicines where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, medicineId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
		
	}

	public void updateMedicine(Medicine theMedicine) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update medicines "
						+ "set medicineName=?, units=?, cost=?, mfgDate=?, expDate=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theMedicine.getMedicineName());
			myStmt.setInt(2, theMedicine.getUnits());
			myStmt.setDouble(3, theMedicine.getCost());
			myStmt.setString(4, theMedicine.getMfgDate());
			myStmt.setString(5, theMedicine.getExpDate());
			myStmt.setInt(6, theMedicine.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public Medicine getMedicines(String theMedicineId) throws Exception {
		Medicine theMedicine = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int medicineId;
		
		try {
			// convert student id to int
			medicineId = Integer.parseInt(theMedicineId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from medicines where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, medicineId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String medicineName = myRs.getString("medicineName");
				String unitsStr = myRs.getString("units");
				String costStr = myRs.getString("cost");
				String mfgDate = myRs.getString("mfgDate");
				String expDate = myRs.getString("expDate");
				
				int units = Integer.parseInt(unitsStr);
				double cost = Double.parseDouble(costStr);
				
				// use the studentId during construction
				theMedicine = new Medicine(medicineId, medicineName, units, cost, mfgDate, expDate);
			}
			else {
				throw new Exception("Could not find medicine id: " + medicineId);
			}				
			
			return theMedicine;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addMedicine(Medicine theMedicine) throws Exception{
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create sql for insert
				String sql = "insert into medicines "
						   + "(medicineName, units, cost, mfgDate, expDate) "
						   + "values (?, ?, ?, ?, ?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				// set the param values for the student
				myStmt.setString(1, theMedicine.getMedicineName());
				myStmt.setInt(2, theMedicine.getUnits());
				myStmt.setDouble(3, theMedicine.getCost());
				myStmt.setString(4, theMedicine.getMfgDate());
				myStmt.setString(5, theMedicine.getExpDate());
				
				// execute sql insert
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
		}

}





