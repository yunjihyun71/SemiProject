<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
/************************ 테이블 ************************/
table {
	font-family:Arial, Helvetica, sans-serif;
	color:#666;
	font-size:14px;
	text-shadow: 1px 1px 0px #fff;
	background:#eaebec;
	border:#ccc 1px solid;

	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	border-radius:3px;

	-moz-box-shadow: 0 1px 2px #d1d1d1;
	-webkit-box-shadow: 0 1px 2px #d1d1d1;
	box-shadow: 0 1px 2px #d1d1d1;
}
/* 크기 지정 */
table th, table td {
	width: 150px;
	height: 40px;
}
table th {
	/* padding:15px; */
	border-top:1px solid #fafafa;
	border-bottom:1px solid #e0e0e0;
	border-left: 1px solid #e0e0e0;

	background: #ededed;
	background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
	background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
}
table tr {
	text-align: center;
	padding-left:20px;
}
table td {
	/* padding:10px; */
	border-top: 1px solid #ffffff;
	border-bottom:1px solid #e0e0e0;
	border-left: 1px solid #e0e0e0;

	background: #fafafa;
	background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
	background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
}
/************************ 테이블 ************************/
			#content {
				float: left;
				margin-top: 10px;
			}
			/* table, tr, th, td {
				border: thin solid lightgray;
				border-collapse: collapse;
				padding: 5px;
				text-align: center;
			}
			th, td {
				width: 150px;
				height: 40px;
			}
			th {
				background-color: #F6F6F6;
			} */
			#container {
				width: 1920px;
			}
			#sub1 {
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<jsp:include page="p_top_menu.jsp"/>
		<div id="container">
			<jsp:include page="p_sub_menu1.jsp"/>
			<div id="content">
				<table>
					<tr>
						<th>사번</th>
						<td>${ dto.pro_id }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${ dto.pro_name }</td>
					</tr>
					<tr>
						<th>학과</th>
						<td>${ dto.major_name }</td>
					</tr>
					<tr>
						<th>연구실</th>
						<td>${ dto.pro_room }</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>${ dto.pro_phone }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${ dto.pro_email }</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
	<script>
		// 내정보 메뉴에 색깔 표시
		$("#navi1").css("background-color", "#4375DB");
	</script>
</html>