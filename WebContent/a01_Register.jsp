<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>학생등록</title>
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
		<h2  onclick="location.href='student'">Total Information System</h2>
		<h4 onclick="location.href='student'" >학생 등록</h4>
		<form action="register" method="post">
			<div id="div1">
				<div>
					<input id="std_id" type="text" name="std_id"  placeholder="학번">
				</div>
				<hr/>
				<div>
					<input id="std_pw" type="text" name="std_pw"  placeholder="비밀번호">
				</div>
				<hr/>
				<div>
					<input id="std_name" type="text" name="std_name"  placeholder="이름">
				</div>
				<hr/>
				<div>
					&nbsp;학년 :
					<select id="selectbox1" name="std_year">
	            		<option value="none"  selected="selected" >-선택하세요-</option>
	  					<option value="1">1학년</option>
					  	<option value="2">2학년</option>
					 	<option value="3">3학년</option>
					 	<option value="4">4학년</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상태 :
					<select id="selectbox2" name="std_state">
	            		<option value="none"  selected="selected" >-선택하세요-</option>
	  					<option value="재학">재학</option>
					  	<option value="졸업">졸업</option>
					 	<option value="휴학">휴학</option>
					</select>
				</div>
				<hr/>
				<div>
					<input id="std_birthday" type="text" name="std_birthday"  placeholder="생년월일">
				</div>
				<hr/>
				<div>
					<input id="std_phone" type="text" name="std_phone"  placeholder="연락처">
				</div>
				<hr/>
				<div>
					<input id="std_address" type="text" name="std_address"  placeholder="주소">
				</div>
				<hr/>
				<div>
					<input id="std_email" type="text" name="std_email"  placeholder="이메일">
				</div>
				<hr/>
				<div>
					<input id="major_id" type="text" name="major_id"  placeholder="전공번호">
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
	 	var none = "none";
		$("#ok").click(function(){
			if(!$("#std_id").val()){
				alert("학번을 입력해주세요");
			}else if(!$("#std_pw").val()){
				alert("비밀 번호를 입력해주세요");
			}else if(!$("#std_name").val()){
				alert("이름을 입력해주세요");
			}else if(!$("#std_birthday").val()){
				alert("생년월일을 입력해주세요");
			}else if(!$("#std_phone").val()){
				alert("연락처를 입력해주세요");
			}else if(!$("#std_address").val()){
				alert("주소를 입력해주세요");
			}else if(!$("#std_email").val()){
				alert("이메일을 입력해주세요");
			}else if(!$("#major_id").val()){
				alert("전공번호를 입력해주세요");
			}else if($("#selectbox1").val()==none){
				alert("선택해주세요");
			}else if($("#selectbox2").val()==none){
				alert("선택해주세요");
			}
		});
	</script>
</html>