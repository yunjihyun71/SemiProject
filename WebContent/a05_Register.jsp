<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		min-height: 100%;
		margin: 0 auto 0;
		background: #fff;
	}
	#div1{
	 	padding : 10px;
	 	text-align:left;
	    border: solid 1px #dadada;
	 }
	input{
		padding : 10px 5px;
		font-size: 17px;
		width: 80%;
		z-index: 10;
		height: 16px;
		border: none;
		background: #fff;
	}
	#div3{
	    height: 61px;
	    width : 520px;
	   	border: solid 1px none;
	    -webkit-background-size: 108px auto;
	    background: white;	
	}
	#div3 input{
 		width: 100%;
 		height: 100%;
		font-size: 17px;
		border: none;
		background: #fff;
	}
	h2,h4{
		cursor: pointer;
	}
</style>
</head>
<body>
	<h2  onclick="location.href='pManagePage'">Total Information System</h2>
	<h4 onclick="location.href='pManagePage'" >교수 등록</h4>
	<br/><br/><br/>
	<form action="pAdd" method="post">
		<div id="div1">
			<div>
				<input id="pro_id" type="text" name="pro_id"  placeholder="사번">
			</div>
			<hr/>
			<div>
				<input id="pro_pw" type="text" name="pro_pw"   placeholder="비밀번호">
			</div>
			<hr/>
			<div>
				<input id="pro_name" type="text" name="pro_name"   placeholder="이름">
			</div>
			<hr/>
			<div>
				<input id="pro_phone" type="text" name="pro_phone"  placeholder="연락처">
			</div>
			<hr/>
			<div>
				<input id="pro_email" type="text" name="pro_email"   placeholder="이메일">
			</div>
			<hr/>
			<div>
				<input id="pro_room" type="text" name="pro_room"   placeholder="연구실">
			</div>
			<hr/>
			<div>
				<input id="major_id" type="text" name="major_id"  placeholder="전공">
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
		if(!$("#pro_id").val()){
			alert("사번을 입력하세요");
		}else if(!$("#pro_pw").val()){
			alert("비밀번호를 입력하세요");
		}else if(!$("#pro_name").val()){
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
</html>