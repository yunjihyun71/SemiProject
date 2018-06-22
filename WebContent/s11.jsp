<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>|||과제제출|||</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>    
<style>
	table,th,td{
		background-color: white;
		border-top: 2px solid #D5D5D5;
		border-bottom: 2px solid #D5D5D5;
		color: #4C4C4C;
		border-collapse: collapse;
		text-align: center;
		padding: 10px;
	}
	#bbs th{
		font-size: 15px;
	}
	#bbs button{
		float: right;
		margin-top: 10px;
	}
	#bbs{
		width: 75%;
		margin-top: 3%;
		margin-left: 22%;
		margin-right: 20%;
		font-size: small;
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
	<jsp:include page="s09-main2.jsp"/>
</div>
<div id="bbs">
	<table id="listTable" width="100%">
		<tr>
			<th width="15%">글번호</th>
			<th width="50%">제목</th>
			<th width="20%">작성자</th>
			<th width="15%">작성일</th>
		</tr>
	</table>
	<div id="page">
			<jsp:include page="paging.jsp"></jsp:include>
	</div>
	<button onclick="move()">글작성</button>
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
	
	$(document).ready(function(){
		obj.url="./listback?bbssort_type=과제";
		obj.success=function(data){
			$("#list").val("${sessionScope.selected}").prop("selected", true);
			console.log(data);
			if(data){
				mainPrint(data.main);
				paging(data.pageInfo);
			}else{
				alert("과목을 다시 선택해주세요");
			}
		}
		ajaxCall(obj);
	}); 
	
	function selectbox(list) {
		var content ="";
		console.log(list);
		$("#list").html("<option value='과목선택'>과목선택</option>");
			list.forEach(function(item){
				content += "<option value="+item.subject_id+">";
				content += item.subject_name;
				content += "</option>";
			});
			$("#list").append(content);
	}
	
	//셀렉트 박스 선택시 리스트 출력
	$("#list").change(function(){
		obj.url="./uploadlist?mName=과제&sNum=1&eNum=10";
		obj.data={selected:$("#list option:selected").val()};
		obj.success=function(data){
			if(data){
				mainPrint(data.main);
				paging(data.pageInfo);
			}else{
				alert("과목을 다시 선택해주세요");
			}
		}
		ajaxCall(obj);
	});
	
	function mainPrint(main){
		var content="";
		$("#listTable").html("<table id='listTable' width='100%'><tr>"+"<th width='15%''>글번호</th>"
		+"<th width='50%''>제목</th>"
		+"<th width='20%''>작성자</th>"
		+"<th width='15%'>작성일</th></tr></table>"); //테이블 초기화
		main.forEach(function(item){
			content += "<tr>";
			content += "<td>"+item.bbs_id+"</td>"
			content += "<td><a href='uploaddetail?idx="+item.bbs_id+"&mName=과제&selected="+item.subject_id+"'>"+item.bbs_title+"</td>"
			content += "<td>"+item.bbs_writer+"</td>"
			content += "<td>"+item.bbs_date+"</td>"
		});
		$("#listTable").append(content);
	}
	
	 function move(){
			var selected = $("#list option:selected").val();
			if(selected == "과목선택"){
				alert("과목을 다시 선택하세요");
				return;
			}
			location.href="s13.jsp?subject_id="+selected+"&mName=과제";
		}
	 
	// 페이지 매기기
	function paging(pageInfo) {
		// 초기화
		$(".paging").html("");
		// 맨앞
	    if (pageInfo.startPage > 1) {
	        $(".paging").append("<a class='text' onclick='list(1)'>맨앞</a>");
	    }
	    // 이전
	    if (pageInfo.startPage > 1) {
	        $(".paging").append("<a class='text' onclick='list("+pageInfo.prevPage+")'>이전</a>");
	    }
	    // 페이지 번호
	    for (var i = pageInfo.startPage; i <= pageInfo.endPage; i++) {
	        if (i == pageInfo.page) {
	            $(".paging").append("<a id='curPage' onclick='list("+i+")'>" + i + "</a>");
	        } else {
	            $(".paging").append("<a onclick='list("+i+")'>" + i + "</a>");
	        }
	    }
	    // 다음
	    if (pageInfo.endPage != pageInfo.totalPage) {
	        $(".paging").append("<a class='text' onclick='list(" + pageInfo.nextPage  + ")'>다음</a>");
	    }
	    // 맨뒤
	    if (pageInfo.endPage != pageInfo.totalPage) {
	        $(".paging").append("<a class='text' onclick='list(" + pageInfo.totalPage + ")'>맨뒤</a>");
	    }
	}
	
	// 특정 페이지의 리스트 불러오는 함수
	function list(pageNum) {
		obj.url="./uploadlist?mName=과제&sNum=1&eNum=10";
		obj.data={"selected":$("#list option:selected").val(),
				"page": pageNum
		};
		obj.success=function(data){
			if(data){
				mainPrint(data.main);
				paging(data.pageInfo);
			}else{
				alert("과목을 다시 선택해주세요");
			}
		}
		ajaxCall(obj);
	}
	
	function ajaxCall(param){
		console.log("ajax 호출")
		$.ajax(obj);
	}
</script>
</html>