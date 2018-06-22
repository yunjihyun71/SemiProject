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
</style>
 <body>
 	<h2  onclick="location.href='suManagePage'">Total Information System</h2>
	<h4 onclick="location.href='suManagePage'" >과목 수정</h4>
	<br/><br/><br/>
	 <form action="suUpdate" method="post">
		<div id="div1">
			<div>
				&nbsp; 과목코드 :${ form.subject_id }
				<input  type="hidden" name="subject_id" value="${ form.subject_id }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 학기 :
				<input  id="term_id" type="text" name="term_id" value="${ form.term_id }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 학과 코드 :
				<input  id="major_id" type="text" name="major_id" value="${ form.major_id }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 사번 :
				<input  id="pro_id"  type="text" name="pro_id" value="${ form.pro_id }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 과목명 :
				<input  id="subject_name" type="text" name="subject_name" value="${ form.subject_name }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 강의실 :
				<input  id="subject_room" type="text" name="subject_room" value="${ form.subject_room }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 강의시간 :
				<input  id="subject_time" type="text" name="subject_time" value="${ form.subject_time }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 전공/교양 :
				<input  id="subject_type"  type="text" name="subject_type" value="${ form.subject_type }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 평점 :
				<input id="subject_type" type="text" name="subject_grade" value="${ form.subject_grade }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 학점 :
				<input id="subject_type" type="text" name="subject_credit" value="${ form.subject_credit }"/>
			</div>
			<hr/>
			<div>
				&nbsp; 제한인원 :
				<input id="subject_type" type="text" name="subject_limit" value="${ form.subject_limit }"/>
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
		}else if(!$("#major_id").val()){
			alert("전공 코드를 입력하세요");
		}else if(!$("#pro_id").val()){
			alert("사번을 입력하세요");
		}else if(!$("#subject_name").val()){
			alert("과목명을 입력하세요");
		}else if(!$("#subject_room").val()){
			alert("강의실을 입력하세요");
		}else if(!$("#subject_time").val()){
			alert("강의 시간을 입력하세요");
		}else if(!$("#subject_type").val()){
			alert("전공/교양을 입력하세요");
		}else if(!$("#subject_grade").val()){
			alert("평점을 입력하세요");
		}else if(!$("#subject_credit").val()){
			alert("학점을 입력하세요");
		}else if(!$("#subject_limit").val()){
			alert("제한인원을 입력하세요");
		}
	});
</script>
