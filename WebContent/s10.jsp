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
		margin: 0px;
	}
	#write textarea{
		resize: none;
		border: none;
	}
	#lectureNote{
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
				<th colspan="4">${detail.bbs_title}</th>
			</tr>
			<tr>
				<td width="2%">작성자 : </td>
				<td width="10%">${detail.bbs_writer}</td>
				<td width="2%">작성일 : </td>
				<td width="10%">${detail.bbs_date}</td>
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
				<td width="2%">첨부파일</td>
				<td colspan="3">${detail.upload_name}</td>
			</tr>
		</table>
		<button>글목록</button>
	</div>
</body>
<script>
	var obj={};
	obj.type="post";
	obj.dataType="json";
	obj.error=function(e){console.log(e)};
	
	//신청과목 셀렉트 박스에 넣기
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
	
	//셀렉트 박스에 넣는 반복문
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
		location.href="s09.jsp";
	});
</script>
</html>