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
			#selSubject {
				width: 150px;
				height: 30px;
				margin-bottom: 15px;
				margin-top: 15px;
				margin-left: 10px
			}
		</style>
	</head>
	<body>
		<div id="sub">
			<select id="selSubject" name="selSubject">
				<option selected="selected" value="default">과목선택</option>
				<c:forEach var="temp" items="${ subjectList }">
				<option value="${ temp.subject_id }">${ temp.subject_name }</option>
				</c:forEach>
			</select>
			<div id="sub1"><a>수강생조회</a></div>
			<div id="sub2"><a>성적등록</a></div>
		</div>
	</body>
	<script>
		// 수강생조회
		$("#sub1").click(function() {
			location.href="studentSearchPage?subject_id="+$("#selSubject").val();
		});
		// 성적등록
		$("#sub2").click(function() {
			location.href="scoreRegistPage?subject_id="+$("#selSubject").val();
		});
	</script>
</html>