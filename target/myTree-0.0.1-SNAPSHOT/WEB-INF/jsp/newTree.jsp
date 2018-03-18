<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: silver;">
	<center>
		<h1
			style="font-family: Verdana; font-size: 30px; color: yellow; text-shadow: 3px 3px 3px pink, -3px 3px 3px green, 3px -3px 3px red, -3px -3px 3px blue;">МОЕ
			ПРЕКРАСНОЕ ДЕРЕВО!</h1>
	</center>
	<div>
		<div
			style="float: left; min-height: 100px; width: 50%;">
			${message}
		</div>
		<div
			style="float: right; min-height: 100px; width: 50%;">
			<center>
				<form style="margin-top: 50px;">
					<label style="font-size: 20px;">Введите вес добавляемого
						узла: </label><input type="text" name="addNode"><br>
						<label style="font-size: 20px;">Введите вес удаляемого
						узла: </label><input type="text" name="delNode"><br>
					<br> <input type="submit"><br>
					<p><a href="../myTree">Вернуться на главную</a></p>
				</form>
			</center>
		</div>
	</div>
</body>
</html>