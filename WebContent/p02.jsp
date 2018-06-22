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
	width : 900px;
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
	width: 100px;
	height: 50px;
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
				margin: 10px 0px;
			}
			/* table, tr, th, td {
				border: thin solid lightgray;
				border-collapse: collapse;
				padding: 5px;
				text-align: center;
			}
			th, td {
				width: 100px;
				height: 50px;
			}
			th {
				background-color: #F6F6F6;
			} */
			#container {
				width: 1920px;
			}
			#sub2 {
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
						<th></th>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
					</tr>
					<tr id="1">
						<th>1교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="2">
						<th>2교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="3">
						<th>3교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="4">
						<th>4교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="5">
						<th>5교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="6">
						<th>6교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="7">
						<th>7교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="8">
						<th>8교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
					<tr id="9">
						<th>9교시</th>
						<td class="월"></td>
						<td class="화"></td>
						<td class="수"></td>
						<td class="목"></td>
						<td class="금"></td>
					</tr>
				</table>
			</div>
		</div>
	</body>
	<script>
		$(document).ready(function() {
			// 내정보 메뉴에 색깔 표시
			$("#navi1").css("background-color", "#4375DB");
			
			// 시간ㅠㅛ 가져오기
			$.ajax({
				type: "post",
				url: "./pTimetable",
				dataType: "json",
				data: {
					"loginId": "${sessionScope.loginId}"
				},
				success: function(data) {
					for (var i = 0; i < data.list.length; i++) {
						var str = data.list[i].subject_time;
						var week = str.charAt(0); // 요일
						var time1 = str.substring(1,2); // 시간1
						var time2 = str.substring(2); // 시간2
						var name = data.list[i].subject_name; // 과목이름
						var room = data.list[i].subject_room; // 강의실
						
						$("#" + time1 + " ."+week).html(name+"<br/>"+room);
						$("#" + time2 + " ."+week).html(name+"<br/>"+room);
					}
				}
			});
		});
	</script>
</html>