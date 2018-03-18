<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Output TREE</title>
</head>
<body style="background-color: black;">
	<div style="border-radius: 10px; width: 600px; height: 200px; margin-left: 27%; margin-top: 200px; background:rgba(0, 128, 128, 0.4)">
		<form method="GET" action="create" style="padding-top: 70px; margin-left: 10%;">
			<p style="font-size: 20px; font-family: Times new Roman; color: yellow; text-shadow: 2px 2px 5px #00FF00, -2px -2px 5px #00FF00">Введите количество вершин: 
			<input type="text" name="count">
			<input type="submit" value="Create"></p>
		</form>
		<form method="GET" action="show">
			<center><input type="submit" value="Show tree From DataBase"></center>
		</form>
	</div>			
</body>
</html>	