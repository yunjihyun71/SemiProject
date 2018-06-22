<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>    
<style>
	th{
		text-align: left;
		font-size: x-large;
	}
	td{
		font-size: small;
	}
	p{
		border-top: 1px solid #D5D5D5;
	}
	#write{
		width: 75%;
		margin-top: 3%;
		margin-left: 22%;
		margin-right: 20%;
		font-size: small;
	}
	#write button{
		float: right;
		margin-right: 1%;
	}
	#write textarea{
		resize: none;
		border: none;
	}
	#upload{
		text-decoration: underline;
	}
</style>
<body>
<div>
	<jsp:include page="s09-main.jsp"></jsp:include>
</div>
<div>
	<jsp:include page="s09-main2.jsp"></jsp:include>
</div>
	<div id="write">
		<table width="100%">
			<tr>
				<th colspan="4">${uploaddetail.bbs_title}</th>
			</tr>
			<tr>
				<td width="2%">작성자 : </td>
				<td width="10%">${uploaddetail.bbs_writer}</td>
				<td width="2%">작성일 : </td>
				<td width="10%">${uploaddetail.bbs_date}</td>
			</tr>
			<tr>
				<td colspan="4"><p></p></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="100%" rows="20%" readonly="readonly">${uploaddetail.bbs_content}</textarea></td>
			</tr>
			<tr>
				<td colspan="4"><p></p></td>
			</tr>
			<tr>
				<td width="2%">첨부파일</td>
				<td colspan="3">
					<a href="#">${uploaddetail.upload_name}</a>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<button id="del">삭제</button>
		<button id="update">수정</button>
		<button id="back">글목록</button>
	</div>
</body>
<script>
	var obj={};
	obj.type="post";
	obj.dataType="json";
	obj.error=function(e){console.log(e)};
	
	var sNum=1;
	var eNum=10;
	var idx = "${lecturebbs.bbs_id}";
	
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

	$("#back").click(function(){
		location.href="s11.jsp";
	});
	var loginId = "${sessionScope.loginId}";
	if(loginId.indexOf("p") != -1){
		$(".btn").css("display","none");
	}
	
	$("#update").click(function(){
		var loginId = "${sessionScope.loginId}";
		if(loginId != "${uploaddetail.bbs_writer}" ){
			alert("수정 권한이 없습니다");
			location.href="s09.jsp";
		}else{
			var bbs_id = "${uploaddetail.bbs_id}";
			console.log(bbs_id);
			location.href="updatePage?idx="+bbs_id+"&mName=과제&selected="+${uploaddetail.subject_id};
		}		
	});
		
	$("#del").click(function(){
		var loginId = "${sessionScope.loginId}";
		if(loginId != "${uploaddetail.bbs_writer}"){
			alert("삭제 권한이 없습니다");
			location.href="s09.jsp";
		}else{
			var bbs_id = "${uploaddetail.bbs_id}";
			console.log(bbs_id);
			location.href="sDel?idx="+bbs_id+"&mName=과제";
		}
	});
</script>
</html>