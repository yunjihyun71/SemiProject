<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			#back{
				position: relative;
				top: -27px;
				left: -100px;
			}
			#bbs{
				margin-left: 22%;
				margin-right: 3%;
			}
			textarea{
				resize: none;
			}
			#bbs button{
				float: right;
				margin-top: 1%;
			}
			#bbs a{
				text-decoration: none;
				background-color: white;
				float: right;
				margin-top: 1%;
				color: black;
				margin-right: 2%;
			}
			#bbs input[type='text']{
				width: 80%;
			}
			#back{
				float: right;
			}
			#lectureNote{
			text-decoration: underline;
			}
			#navi #subBbs {
				background-color: #4375DB;
			}
		</style>
	</head>
	<body>
		<div>
			<jsp:include page="p04-main.jsp"></jsp:include>
		</div>
		<div>
			<jsp:include page="p04-main2.jsp"></jsp:include>
		</div>
		<form name="form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bbs_writer" value="${sessionScope.loginId}"/>
		<input type="hidden" name="bbssort_type" value="<%=request.getParameter("mName")%>"/>
		<input type="hidden" name="subject_id" value="<%=request.getParameter("subject_id")%>"/>
			<div id="bbs">
			<h2>강의 자료 게시판</h2>
				<table width="100%">
				<tr>
					<td width="5%">작성자</td>
					<td>${sessionScope.loginId}</td>
				</tr>
					<tr>
						<td width="5%">제목</td>
						<td><input type="text" name="bbs_title"/></td>
					</tr>
					<tr>
						<p></p>
					</tr>
					<tr>
						<td colspan="3">
							<textarea style="margin: 0px; width: 100%; height: 350px" name="bbs_content"></textarea>
						</td>
					</tr>
					<tr>
						<p></p>
					</tr>
					<tr>
						<td width="8%">첨부파일</td>
						<td>	<input type="file" name="uploadfile"/></td>
					</tr>
					<tr>
						<td colspan="4">
							<button>저장</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<button id="back">취소</button>
	</body>
	<script>
	var obj={};
	obj.type="post";
	obj.dataType="json";
	obj.error=function(e){console.log(e)};
	
	//신청과목 셀렉트 박스에 넣기
	$(document).ready(function(){
		obj.url="./prosubjectTab";
		obj.data={
				"id":'${sessionScope.loginId}'
		}
		obj.success=function(data){
			if(data){
				 selectbox(data.prosublist); 
			}else{
				location.href="index.jsp";
			}
		}
		ajaxCall(obj);
	});
	
	//셀렉트 박스에 넣는 반복문
	function selectbox(prosublist) {
		var content ="";
		console.log(list);
		$("#list").html("<option value='과목선택'>과목선택</option>");
		prosublist.forEach(function(item){
				console.log(item);
				content += "<option value="+item.subject_id+">";
				content += item.subject_name;
				content += "</option>";
			});
			$("#list").append(content);
	}
	
	$("button").click(function(){
		if($("input[name='bbs_title']").val()==""){
			alert("제목을 입력하세요");
			$("input[name='bbs_title']").focus();
			return;
		}else if($("textarea").val()==""){
			alert("내용을 입력하세요");
			$("input[name='bbs_content']").focus();
			return;
		}else if($("input[name='uploadfile']").val()==""){
			alert("파일을 첨부하세요");
			$("input[name='uploadfile']").focus();
			return;
		}
		form.action="prowrite";
	});
	
	$("#back").click(function(){
		alert("과목을 다시 선택해주세요");
		location.href="p04.jsp";
	});
	
	function ajaxCall(param){
		console.log("ajax 호출")
		$.ajax(obj);
	}
	</script>
</html>