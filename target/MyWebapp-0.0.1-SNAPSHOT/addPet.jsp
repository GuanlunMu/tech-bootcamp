<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Pet</title>
<style>
h2, #add {
	text-align: center;
}

</style>
</head>
<body>
<h2>Add a Pet into Our System</h2>
	<div id = "add">	
		<form id="add" action = "addPet.htm" method = "POST">
			<label for="name">Name: </label><input type="text" name="name"
				id="name"><br> <br> <label for="owner">Owner's
				Name: </label><input type="text" name="owner" id="owner"><br> <br>
			<label for="species">Species: </label><input type="text"
				name="species" id="species"><br> <br> <span>Sex:
			</span> <input type="radio" name="sex" value="male" id="male"><label
				for="male">Male</label> <input type="radio" name="sex"
				value="female" id="female"><label for="female">Female</label>
			<br>
			<br>
			<label for="birth">Birthday: </span><input type="date" name="birth"
				id="birth"><br> <br>
				<input type = "submit" value = "Add">
		</form>
	</div>

</body>
</html>