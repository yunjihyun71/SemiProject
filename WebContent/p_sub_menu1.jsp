<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
 			#sub a{
				text-decoration: none;
				font-size: 17px;
				font-weight: bold;
				color: black;
				margin-left: 10px;
			}
			#sub{
				margin: 10px;
				width: 230px;
				height: 400px;
				border: 1px solid #c0c0c0;
				background-color: #F6F6F6;
				float: left;
			}
			#sub div{
				height: 40px;
				padding-top: 15px;
				cursor: pointer;
			}
			#sub div:hover {
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<div id="sub">
			<div id="sub1"><a>신상조회</a></div>
			<div id="sub2"><a>시간표조회</a></div>
		</div>
	</body>
	<script>
		// 신상조회
		$("#sub1").click(function() {
			location.href="pProfile";
		});
		// 시간표조회
		$("#sub2").click(function() {
			location.href="p02.jsp";
		});
	</script>
</html>