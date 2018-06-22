<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>    
		<style>
			select {
				width: 150px;
				height: 30px;
				margin-bottom: 15px;
				margin-top: 15px;
				margin-left: 10px
			}
 			#sub a{
				text-decoration: none;
				color: black;
			}
			#sub{
				margin-top: 1%;
				width: 230px;
				height: 400px;
				border: 1px solid #c0c0c0;
				margin-left: 1%;
				background-color: #F6F6F6;
				float: left;
			}
			#sub div{
				height: 40px;
				padding-top: 15px;
				font-size: 17px;
				font-weight: bold;
				color: black;
				margin-left: 10px;
				background-color: #F6F6F6;
			}
			#sub div:hover {
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<div id="sub">
			<select id="list">
			<option value="" selected="selected">과목선택</option>
			</select>
			<div id="plan"><a href="s08.jsp">강의계획서</a></div>
			<div id="lectureNote"><a href="s09.jsp">강의자료</a></div>
			<div id="upload"><a href="s11.jsp">과제</a></div>
			<div id="grade"><a href="s14.jsp">강의평가</a></div>
		</div>
	</body>
</html>