<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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
	width: 200px;
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
			/* table, tr, th, td {
				border: thin solid lightgray;
				border-collapse: collapse;
				padding: 5px;
				text-align: center;
			}
			th, td {
				width: 200px;
				height: 40px;
			}
			th {
				background-color: #F6F6F6;
			} */
			#s05 {
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<table id="scholarTable">
			<tr>
				<th>학기</th>
				<th>장학금 종류</th>
				<th>장학금 금액</th>
			</tr>
		</table>
	</body>
	<script>
		$(document).ready(function() {
			// 장학금 정보 가져오기
			$.ajax({
				type: "post",
				url: "./scholar",
				dataType: "json",
				data: {
					"loginId": "${sessionScope.loginId}"
				},
				success: function(data) {
					var str = "";
					for (var i = 0; i < data.scholarList.length; i++) {
						// 장학금이 있는 경우만 출력(0원이 아닌 경우)
						if (data.scholarList[i].scholar_money != 0) {
							str += "<tr>"
								+ "<td>" + data.scholarList[i].term_id + "</td>"
								+ "<td>" + data.scholarList[i].scholar_name + "</td>"
								+ "<td>" + data.scholarList[i].scholar_money.toLocaleString() + "</td>"
								+ "</tr>";
						}
					}
					$("#scholarTable").append(str);
				}
			});
		});
	</script>
</html>