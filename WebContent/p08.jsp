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
	<jsp:include page="p04-main.jsp"></jsp:include>
</div>
<div>
	<jsp:include page="p04-main2.jsp"></jsp:include>
</div>
	<div id="write">
		<table width="100%">
			<tr>
				<th colspan="4">${detail.bbs_title}</th>
			</tr>
			<tr>
				<td width="7%">작성자 : </td>
				<td>${detail.bbs_writer}</td>
				<td width="7%">작성일 : </td>
				<td>${detail.bbs_date}</td>
			</tr>
			<tr>
				<td colspan="4"><p></p></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="100%" rows="20%" readonly="readonly">${detail.bbs_content}</textarea></td>
			</tr>
			<tr>
				<td colspan="4"><p></p></td>
			</tr>
			<tr>
				<td width="7%">첨부파일</td>
				<td colspan="3">
					<a href="${dto}">${detail.upload_name}</a>&nbsp;&nbsp;다운로드
				</td>
			</tr>
		</table>
		<button id="back">글목록</button>
	</div>
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
				console.log("성공");
				 selectbox(data.prosublist); 
			}else{
				location.href="index.jsp";
			}
		}
		ajaxCall(obj);
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
	});
	
	//셀렉트 박스에 넣는 반복문
	function selectbox(prosublist) {
		var content ="";
		console.log(prosublist);
		$("#list").html("<option value='과목선택'>과목선택</option>");
		prosublist.forEach(function(item){
				console.log(item);
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
		location.href="p07.jsp";
	});
</script>
</html>