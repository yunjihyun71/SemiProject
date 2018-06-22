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

			/*
			table, tr, th, td {
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
			}
			*/
			#termSel {
				width: 150px;
				height: 30px;
			}
			#s04 {
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<select id="termSel" name="subject">
			<option value="default">학기 선택</option>
		</select>
		<table>
			<tr>
				<th colspan="4">등록금 고지서</th>
			</tr>
			<tr>
				<th>학부(과)명</th>
				<td id="major" colspan="3"></td>
			</tr>
			<tr>
				<th>학번</th>
				<td id="id" colspan="3"></td>
			</tr>
			<tr>
				<th>성명</th>
				<td id="name"></td>
				<th>학기</th>
				<td id="term"></td>
			</tr>
			<tr>
				<th colspan="2">납입금 내역</th>
				<th colspan="2">장학금 내역</th>
			</tr>
			<tr>
				<td>수업료</td>
				<td id="tuition"></td>
				<td id="scholar_name"></td>
				<td id="scholar_money"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<th>계(1)</th>
				<td id="sumTuition"></td>
				<th>계(2)</th>
				<td id="sumScholar"></td>
			</tr>
			<tr>
				<th colspan="2">실납입액(1)-(2)</th>
				<td id="sum" colspan="2"></td>
			</tr>
		</table>
	</body>
	<script>
		$(document).ready(function() {
			// 등록금이 있는 학기 select 태그에 추가
			$.ajax({
				type: "post",
				url: "./tuitionTerm",
				dataType: "json",
				data: {
					"loginId": "${sessionScope.loginId}"
				},
				success: function(data) {
					var str = "";
					for (var i = 0; i < data.termList.length; i++) {
						str += "<option value='" + data.termList[i] + "'>" 
							+ data.termList[i] 
							+ "</option>";
					}
					$("#termSel").append(str);
				}
			});
			
			// 학기 선택 시
			$("#termSel").change(function() {
				// 학기 선택이 아닌 실제 학기를 선택한 경우
				if ($(this).val() != "default") {
					$.ajax({
						type: "post",
						url: "./tuition",
						dataType: "json",
						data: {
							"loginId": "${sessionScope.loginId}",
							"term": $(this).val()
						},
						success: function(data) {
							// 태그에 가져온 데이터 넣기
							$("#major").html(data.dto.major_name);
							$("#id").html(data.dto.std_id);
							$("#name").html(data.dto.std_name);
							$("#term").html(data.dto.term_id);
							$("#tuition").html(data.dto.tuition_money.toLocaleString());
							$("#scholar_name").html(data.dto.scholar_name);
							$("#scholar_money").html(data.dto.scholar_money.toLocaleString());
							$("#sumTuition").html(data.dto.tuition_money.toLocaleString());
							$("#sumScholar").html(data.dto.scholar_money.toLocaleString());
							// 등록금 - 장학금이 음수이면 0으로 처리
							var sum;
							if (data.dto.tuition_money - data.dto.scholar_money > 0) {
								sum = data.dto.tuition_money - data.dto.scholar_money;
							} else {
								sum = 0;
							}
							$("#sum").html(sum.toLocaleString());
						}
					});
				}
			});
		});
	</script>
</html>