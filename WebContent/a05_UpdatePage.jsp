 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
 	html {
		box-sizing: inherit;
		background: -webkit-linear-gradient(right, #8e9eab, #eef2f3);
		background: linear-gradient(to left, #8e9eab, #eef2f3);
	}
 	div{
		position: relative;
		width: 500px;
		min-height: 5%;
		margin: 0 auto 0;
		background: #fff;
		font-size: 17px;	
		z-index: 10;
	}
	#div1{
	 	padding : 10px;
	 	text-align:left;
	    border: solid 1px #dadada;
	 }
	input{
		padding : 10px 5px;
		font-size: 17px;
		width: 75%;
		z-index: 10;
		height: 35px;
		border: none;
		background: #fff;
		border: 1px solid #dadada;
	}
	#div3{
	    height: 55px;
	    width : 520px;
	   	border: solid 1px none;
	    -webkit-background-size: 108px auto;
	    background: white;	
	  }
	  #div3 input{
	  		border: 1px solid #dadada;
	 		width: 100%;
	 		height: 100%;
			font-size: 17px;
			border: none;
			background: #fff;
	  }
	  h2,h4{
	  		cursor: pointer;
	  }
	  #code1{
	   	width: 60%;
	  }
</style>
 <body>
 	<h2  onclick="location.href='pManagePage'">Total Information System</h2>
	<h4 onclick="location.href='pManagePage'" >교수 수정</h4>
	<br/><br/><br/>
	<form action="pUpdate" method="post">
		<div id="div1">
			<div>
				&nbsp; 사번 :${form.pro_id}
				<input type="hidden" name="pro_id" value="${form.pro_id}"/>
			</div>
			<hr/>
			<div>
				&nbsp; 이름 :
				<input id="pro_name" type="text" name="pro_name" value="${form.pro_name}"/>
			</div>
			<hr/>
			<div>
				&nbsp; 연락처 :
				<input  id="pro_phone"  type="text" name="pro_phone" value="${form.pro_phone}"/>
			</div>
			<hr/>
			<div>
				&nbsp; 이메일 :
				<input id="pro_email" type="text" name="pro_email" value="${form.pro_email}"/>
			</div>
			<hr/>
			<div>
				&nbsp; 연구실 :
				<input id="pro_room" type="text" name="pro_room" value="${form.pro_room}"/>
			</div>
			<hr/>
			<div>
				&nbsp; 전공 코드 번호 :
				<input id="major_id" type="text" name="major_id" value="${form.major_id}"/>
			</div>
			<hr/>
		</div>
		<br/>
		<div id="div3">
			<input  type="submit" value="완료"/> 
		</div>
	</form>
</body>
<script>
$("#ok").click(function(){
	if(!$("#pro_name").val()){
		alert("교수 이름을 입력하세요");
	}else if(!$("#pro_phone").val()){
		alert("연락처를 입력하세요");
	}else if(!$("#pro_email").val()){
		alert("이메일을 입력하세요");
	}else if(!$("#pro_room").val()){
		alert("연구실 입력하세요");
	}else if(!$("#major_id").val()){
		alert("전공번호를 입력하세요");
	}
});
</script>