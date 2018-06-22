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
	padding: 5px 20px;
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
				padding: 5px 10px;
				text-align: center;
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
			/* 최초에는 form이 안보임 */
			#form1 {
				visibility: hidden;
			}
		</style>
	</head>
	<body>
		<jsp:include page="p_top_menu.jsp"/>
		<div id="container">
			<jsp:include page="p_sub_menu3.jsp"/>
			<div id="content">
				<div id="msg">과목을 선택하세요.</div>
				
				<form action="./scoreRegist" method="post" id="form1">
					<input type="hidden" name="subject_id"/>
					<table id="tableList">
					</table>
					<input type="submit" value="저장"/>
				</form>
			</div>
		</div>
	</body>
	<script>
		// 강의 메뉴에 색깔 표시
		$("#navi3").css("background-color", "#4375DB");
	
		// 성공, 실패 메시지 출력
		if (<%= request.getAttribute("success") %> > 0) {
			alert("성적이 등록되었습니다.");
		} else if (<%= request.getAttribute("success") %> == -1) {
			alert("성적 등록에 실패하였습니다.");
		} 
		
		// 파라미터를 통해 과목 선택
		$("#selSubject").val("${ param.subject_id }").prop("selected", true);
		// 리스트 출력
		loadScore();
		
		// 과목 변경 시 loadScore 함수 호출
		$("#selSubject").change(loadScore);
	
		// 선택된 과목의 리스트를 불러오는 함수
		function loadScore() {
			// input에 과목 ID를 지정(파라미터로 보내기 위해)
			$("input[name='subject_id']").val($("#selSubject").val());
			
			// 과목을 선택하지 않았을 경우
			if ($("#selSubject").val() == "default") {
				$("#msg").show(); // 과목 선택 메시지 보여줌
				$("#form1").css("visibility", "hidden"); // form 숨김
			}
			// 과목을 선택했을 경우 학생 리스트 가져오기
			else {
				$("#msg").hide(); // 과목 선택 메시지 숨김
				
				// 과목 아이디를 보내고 수강하는 학생 리스트 받아옴
				$.ajax({
					url: "./studentSearch",
					type: "post",
					data: {"subjectId": $("#selSubject").val()},
					dataType: "json",
					success: function(data) {
						// 수강생이 없는 경우
						if (data.studentList.length == 0) {
							$("#form1").css("visibility", "hidden"); // form 숨김
							alert("수강생이 없습니다.");
						}
						// 수강생이 있는 경우
						else {
							$("#form1").css("visibility", "visible"); // form 보여줌
							// 수강생 리스트 가져오기
							var str = "<tr><th></th><th>학번</th><th>이름</th><th>성적</th></tr>";
							for (var i = 0; i < data.studentList.length; i++) {
								var dto = data.studentList[i];
								var index = i+1;
								str += "<tr><td>"+index+"</td>"
									+"<td>"+dto.std_id+"</td>"
									+"<td>"+dto.std_name+"</td>"
									+"<td>"
									+"<input type='radio' name='"+dto.std_id+"' value='A+'>A+ &nbsp;&nbsp;"
									+"<input type='radio' name='"+dto.std_id+"' value='A0'>A0 &nbsp;&nbsp;"
									+"<input type='radio' name='"+dto.std_id+"' value='B+'>B+ &nbsp;&nbsp;"
									+"<input type='radio' name='"+dto.std_id+"' value='B0'>B0 &nbsp;&nbsp;"
									+"<input type='radio' name='"+dto.std_id+"' value='C+'>C+ &nbsp;&nbsp;"
									+"<input type='radio' name='"+dto.std_id+"' value='C0'>C0 &nbsp;&nbsp;"
									+"<input type='radio' name='"+dto.std_id+"' value='F'>F "
									+"</form>"
									+"</td></tr>";
							}
							$("#tableList").html(str);
							
							// 성적 가져오기
							for (var i = 0; i < data.scoreList.length; i++) {
								var dto = data.scoreList[i];
								$("input[name='"+dto.std_id+"'][value='"+dto.score_score+"']").prop("checked", true);
							}
						}
						
					}
				});
			}
		}
		
	</script>
</html>