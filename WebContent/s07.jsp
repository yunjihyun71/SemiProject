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
	padding : 5px;
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
			th {
				background-color: #F6F6F6;
			} */
			#s07 {
				text-decoration: underline;
			}
			table {
				width: 900px;
			}
			input[type="text"], select {
				width: 98%;
				border: thin solid lightgray;
			}
			/* 학점 칸 좁게 */
			.thScore {
				width: 40px;
			}
			/* 학점 입력칸 가운데정렬 */
			.credit {
				text-align: center;
			}
			/* 결과 행 안보이게 */
			#trRsult {
				visibility: hidden;
			}
			/* 결과(이수 학점, 예상 평점) 숫자 */
			#result1, #result2 {
				color: blue;
				font-weight: bold;
			}
		</style>
	</head>
	<body>
		<h3 id="term">현재 학기 신청 과목</h3>
		<table id="tableEnroll">
			<tr>
				<th>과목명</th>
				<th>이수구분</th>
				<th>학점</th>
			</tr>
		</table>
		<h3 id="cal">학점 계산</h3>
		<table id="tableCal">
			<tr>
				<th>과목명</th>
				<th>평점</th>
				<th class="thScore">학점</th>
				<th>과목명</th>
				<th>평점</th>
				<th class="thScore">학점</th>
			</tr>
			<c:forEach var="i" begin="0" end="12" step="2">
				<tr>
					<td><input type="text" id="subject${ i }"/></td>
					<td><select name="score${ i }">
						<option value="default">선택</option>
						<option value="4.5">A+</option>
						<option value="4.0">A0</option>
						<option value="3.5">B+</option>
						<option value="3.0">B0</option>
						<option value="2.5">C+</option>
						<option value="2.0">C0</option>
						<option value="0.0">F</option>
					</select></td>
					<td><input type="text" id="credit${ i }" class="credit"/></td>
					<td><input type="text" id="subject${ i+1 }"/></td>
					<td><select name="score${ i+1 }">
						<option value="default">선택</option>
						<option value="4.5">A+</option>
						<option value="4.0">A0</option>
						<option value="3.5">B+</option>
						<option value="3.0">B0</option>
						<option value="2.5">C+</option>
						<option value="2.0">C0</option>
						<option value="0.0">F</option>
					</select></td>
					<td><input type="text" id="credit${ i+1 }" class="credit"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6"><input type="button" id="btnCal" value="계산"/></td>
			</tr>
			<tr id="trRsult">
				<td colspan="6">이수 학점 <span id="result1"></span> &nbsp;예상 평점 <span id="result2"></span></td>
			</tr>
		</table>
	</body>
	<script>
		$(document).ready(function() {
			// 현재학기 신청 과목 리스트 출력
			$.ajax({
				type: "post",
				url: "./calPage",
				dataType: "json",
				data: {
					"loginId": "${sessionScope.loginId}"
				},
				success: function(data) {
					var str = "";
					for (var i = 0; i < data.subjectList.length; i++) {
						str += "<tr>"
							+ "<td>" + data.subjectList[i].subject_name + "</td>"
							+ "<td>" + data.subjectList[i].subject_type + "</td>"
							+ "<td>" + data.subjectList[i].subject_credit + "</td>"
							+ "</tr>";
						// 학점 계산기에 추가
						$("#subject"+i).val(data.subjectList[i].subject_name);
						$("#credit"+i).val(data.subjectList[i].subject_credit);
					}
					$("#tableEnroll").append(str);
				}
			});
			
			// 계산 버튼 클릭
			$("#btnCal").click(function() {
				var sumCredit = 0; // 학기별 총 신청학점
				var sumScore = 0; // 학기별 성적*학점 합
				for (var i = 0; i < 14; i++) {
					// 평점, 학점 모두 선택한 경우
					if ($("select[name='score"+i+"']").val() != "default" && $("#credit"+i).val() != "") {
						sumCredit += Number($("#credit"+i).val()); // 이수학점
						sumScore += $("select[name='score"+i+"']").val() * $("#credit"+i).val(); // 평점*학점(곱하기하면 자동 숫자로 형변환)
					}
					// 모두 선택하지 않은 경우
					else if ($("select[name='score"+i+"']").val() == "default" && $("#credit"+i).val() == "") {
					}
					// 하나만 선택한 경우
					else {
						alert("값을 정확히 입력해주세요.");
						break;
					}
					
					// 마지막 반복 때 출력
					if (i == 13) {
						$("#trRsult").css("visibility", "visible"); // 보이게 하기
						var result2 = (sumScore/sumCredit).toFixed(2); // 소수점 2째자리까지 반올림
						$("#result1").html(sumCredit); // 이수 학점
						$("#result2").html(result2); // 예상 평점
					}
				}
			});
			
		});
	</script>
</html>