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
	textarea{
		padding : 10px 5px;
		font-size: 17px;
		width: 97%;
		z-index: 10;
		height: 300px;
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
	<h2  onclick="location.href='gPage'">Total Information System</h2>
	<h4 onclick="location.href='gPage'" >강의평가 질문 등록</h4>
	<br/><br/><br/>
	<form action="gAdd" method="post">
		<div id="div1">
			<div>
				&nbsp; 질문
			</div>
			<hr/>
			<div>
				<textarea id="subject_room" rows="5" cols="30" name="question_question" placeholder="내용을 입력하세요."></textarea>
			</div>
			<hr/>
		</div>
		<br/>
		<div id="div3">
			<input id="ok" type="submit" value="완료"/> 
		</div>
	</form>
</body>
<script>
	$("#ok").click(function(){
		if(!$("#subject_room").val()){
			alert("질문 내용을 입력하세요");
		}
	});
</script>
</html>