<!DOCTYPE html>
<html>

<head>
	<title>Add Medicine</title>

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
		<h3>Add Medicine</h3>
		
		<form action="medicines" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Medicine Name:</label></td>
						<td><input type="text" name="medicineName" /></td>
					</tr>

					<tr>
						<td><label>Cost:</label></td>
						<td><input type="text" name="cost" /></td>
					</tr>

					<tr>
						<td><label>Units:</label></td>
						<td><input type="text" name="units" /></td>
					</tr>
					
					<tr>
						<td><label>Mfg Date:</label></td>
						<td><input type="text" name="mfgDate" /></td>
					</tr>
					
					<tr>
						<td><label>Exp Date:</label></td>
						<td><input type="text" name="expDate" /></td>
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











