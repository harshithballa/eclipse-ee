<!DOCTYPE html>
<html>

<head>
	<title>Update Medicine</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Seshadri Hospital</h2>
		</div>
	</div>
	
	<div id="container">
		<h4><a href="index.html">Home Page</a></h4>
		<h3>Update Student</h3>
		
		<form action="medicines" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="medicineId" value="${THE_MEDICINE.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Medicine Name:</label></td>
						<td><input type="text" name="medicineName" 
								   value="${THE_MEDICINE.medicineName}" /></td>
					</tr>

					<tr>
						<td><label>Cost:</label></td>
						<td><input type="text" name="cost" 
								   value="${THE_MEDICINE.cost}" /></td>
					</tr>

					<tr>
						<td><label>Units:</label></td>
						<td><input type="text" name="units" 
								   value="${THE_MEDICINE.units}" /></td>
					</tr>
					
					<tr>
						<td><label>Mfg Date:</label></td>
						<td><input type="text" name="mfgDate" 
								   value="${THE_MEDICINE.mfgDate}" /></td>
					</tr>
					
					<tr>
						<td><label>Exp Date:</label></td>
						<td><input type="text" name="expDate" 
								   value="${THE_MEDICINE.expDate}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="medicines">Back to List</a>
		</p>
	</div>
</body>

</html>











