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
		width: 80%;
		z-index: 10;
		height: 16px;
		border: none;
		background: #fff;
	}
	select{
		text-align:center;
		width: 150px;
		height: 30px;
		padding-left: 10px;
		font-size: 18px;
		color: black;
		border: 1px solid #dadada;
		border-radius: 3px;
	}
	#div3{
	    height: 55px;
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
	<h2  onclick="location.href='aTuition'">Total Information System</h2>
	<h4 onclick="location.href='aTuition'" >등록금 등록</h4>
	<form action="tAdd" method="post">
		<div id="div1">
			<div>
				<input id="term_id" type="text" name="term_id"  placeholder="학기">
			</div>
			<hr/>
			<div>
				<input id="std_id" type="text" name="std_id"  placeholder="학번">
			</div>
			<hr/>
			<div>
				<input id="scholar_id" type="text" name="scholar_id"  placeholder="장학금 코드번호">
			</div>
			<hr/>
			<div>
				<input id="tuition_money" type="text" name="tuition_money"  placeholder="등록금">
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
		if(!$("#term_id").val()){
			alert("학기를 입력하세요");
		}else if(!$("#std_id").val()){
			alert("학번을 입력하세요");
		}else if(!$("#scholar_id").val()){
			alert("장학금 코드 번호를 입력하세요");
		}else if(!$("#tuition_money").val()){
			alert("등록금 금액을 입력하세요");
		}
	});
</script>
</html>