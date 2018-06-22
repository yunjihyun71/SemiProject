<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
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
			#upload{
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<div>
			<jsp:include page="s09-main.jsp"></jsp:include>
		</div>
		<div>
			<jsp:include page="s09-main2.jsp"></jsp:include>
		</div>
		<form name="form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="idx" value="${update.bbs_id}">
			<input type="hidden" name="bbs_writer" value="${sessionScope.loginId}"/>
			<input type="hidden" name="bbssort_type" value="${update.bbssort_type}" />
			<input type="hidden" name="subject_id" value="${update.subject_id}"/>
			<div id="bbs">
				<h2>과제 제출 게시판</h2>
				<table width="100%">
					<tr>
						<td width="5%">작성자</td>
						<td>${sessionScope.loginId}</td>
					</tr>
					<tr>
						<td width="5%">제목</td>
						<td><input type="text" name="bbs_title" value="${update.bbs_title}"/></td>
					</tr>
					<tr>
						<p></p>
					</tr>
					<tr>
						<td colspan="3">
							<textarea style="margin: 0px; width: 100%; height: 350px" name="bbs_content">${update.bbs_content}</textarea>
						</td>
					</tr>
					<tr>
						<p></p>
					</tr>
					<tr>
						<td width="8%">첨부파일</td>
						<td><input type="text" value="${update.upload_name}"/>	
						<input type="file" name="uploadfile"></td>
					</tr>
					<tr>
						<td colspan="4">
							<button>저장</button>
							<a href="s11.jsp">취소</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<button id="back" onclick="location.href='s11.jsp'">취소</button>
	</body>
	<script>
	var obj={};
	obj.type="post";
	obj.dataType="json";
	obj.error=function(e){console.log(e)};

	$(document).ready(function(){
		obj.url="./subjectTab";
		obj.data={
				"id":'${sessionScope.loginId}'
		}
		obj.success=function(data){
			if(data){
				 selectbox(data.sublist); 
			}else{
				location.href="index.jsp";
			}
		}
		ajaxCall(obj);
	});
	
	function selectbox(list) {
		var content ="";
		$("#list").html("<option value='과목선택'>과목선택</option>");
			list.forEach(function(item){
				content += "<option value="+item.subject_id+">";
				content += item.subject_name;
				content += "</option>";
			});
			$("#list").append(content);
	}
	
	function ajaxCall(param){
		console.log("ajax 호출")
		$.ajax(obj);
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
		}
		form.action="sUpdate";
	});
	</script>
</html>