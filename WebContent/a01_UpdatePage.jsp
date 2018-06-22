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
		width: 79%;
		z-index: 10;
		height: 35px;
		border: none;
		background: #fff;
		border: 1px solid #dadada;
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
 <body>
 	<h2  onclick="location.href='student'">Total Information System</h2>
	<h4 onclick="location.href='student'" >학생 수정</h4>
	    <form action="update" method="post">
			<div id="div1">
				<div>
					&nbsp; 학번 : ${form.std_id}
				<input type="hidden" name="std_id" value="${form.std_id}"/>
				</div>
				<hr/>
				<div>
					&nbsp; 이름 : <input id="std_name" type="text" name="std_name"  value="${form.std_name}">
				</div>
				<hr/>
				<div>
					&nbsp; 학년 :
					<select name="std_year">
	  					<option value="1">1학년</option>
					  	<option value="2">2학년</option>
					 	<option value="3">3학년</option>
					 	<option value="4">4학년	</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상태 :
					<select name="std_state">
	  					<option value="재학">재학</option>
					  	<option value="졸업">졸업</option>
					 	<option value="휴학">휴학</option>
					</select>
				</div>
				<hr/>
				<div>
					&nbsp; 생년월일 : <input id="std_birthday" type="text" name="std_birthday"  value="${form.std_birthday}">
				</div>
				<hr/>
				<div>
					&nbsp; 연락처 : <input id="std_phone" type="text" name="std_phone"  value="${form.std_phone}">
				</div>
				<hr/>
				<div>
					&nbsp; 주소 : <input id="std_address" type="text" name="std_address"  value="${form.std_address}">
				</div>
				<hr/>
				<div>
					&nbsp; 이메일 : <input id="std_email" type="text" name="std_email"  value="${form.std_email}">
				</div>
				<hr/>
			</div>
			<br/>
			<div id="div3">
				<input  id="ok" type="submit" value="완료"/> 
			</div>
		</form>
</body>
<script>
$("#ok").click(function(){
	if(!$("#std_name").val()){
		alert("이름을 입력해주세요");
	}else if(!$("#std_birthday").val()){
		alert("생년월일을 입력해주세요");
	}else if(!$("#std_phone").val()){
		alert("연락처를 입력해주세요");
	}else if(!$("#std_address").val()){
		alert("주소를 입력해주세요");
	}else if(!$("#std_email").val()){
		alert("이메일을 입력해주세요");
	}else if($(".selectbox").val()==none){
		alert("선택해주세요");
	}
});
</script>