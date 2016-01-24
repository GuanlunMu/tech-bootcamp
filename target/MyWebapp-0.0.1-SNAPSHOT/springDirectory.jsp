<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>Pet Directory</title>
<style>
body {
	text-align: center;
}
</style>

</head>
<h2>Welcome to the Pet Directory 1.0</h2>
<body>
	<div id="allPets">
		<h3>Click this button to get all pets in our shop:</h3>
		<form action="pet.htm" method="GET">
			<input type="submit" value="Get All Pets" />
		</form>
	</div>

	<div id="search">
		<h3>Or you can search the pets based on criteria you specified:</h3>
		<form action="pet.htm" method="POST">
			<p>Search by:</p>
			<span>ID: </span><input type="text" name="id"></br> </br> <span>Species:
			</span> <select name="species">
				<option value="cat">Cat</option>
				<option value="dog">Dog</option>
				<option value="bird">Bird</option>
				<option value="snake">Snake</option>
			</select> </br> </br> <span>Name: </span><input type="text" name="name"></br> </br> <span>Sex:
			</span><input type="radio" name="sex" value="Male"><span>Male</span>
			<input type="radio" name="sex" value="Female"><span>Female</span><br>
			<br> <input type="submit" value="Search">
		</form>
	</div>
</body>
<script>
	function validate() {
		var idPattern = /^[0-9]*$/g;
		var namePattern = /^[a-zA-Z]*$/g;
		var id = $("input[name='id']").val();
		var name = $("input[name='name']").val();
		var valid = namePattern.test(name) && idPattern.test(id);
		return valid;
	}

	$("#search > form").submit(function(e) {
		if (!validate()) {
			e.preventDefault();
		}
	});
</script>
</html>