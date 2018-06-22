<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
			img {
				cursor: pointer;
			}
			body{
				margin: 0px; /* body와 div 사이 공백 제거 */
				background-image: url('/img/back.jpg') ;
				background-size: cover;
			}
			#menu{
				width: 100%;
				height:50px;
				margin-top: 0px;
				background-color: #9DCFFF;
				overflow:hidden;
				float: right;
				font-weight: 600;
				font-size:16px;
				color: white;
				
			}
			#menu a{
				text-decoration: none;
				border-left: 1px solid #c0c0c0;
				padding: 0px 10px 0px 14px;
				color: white;
			}
			#menu1{
				position : relative;
				top: 10px;
				left:70%;
				color: white;
			}
			#navi{
				width: 100%;
				height: 90px;
				margin-top: 20px;
				overflow:hidden;
				background-color:white;
				border-bottom: 1px solid #ccc;
			}
			#navi div{
				float: left;
				position : relative;
				top : 0px;
				left: 80px;
			}
			.title{
				position : relative;
				top : 0px;
				left: 150px;
				display: inline;
				height: 100%;
				padding: 17px 50px 13px 50px;
				float: left;
				text-align: center;
			}
				#main1{
				position: absolute;
				top:145px;
				left:220px;
				height: 80%;
				width : 100%;
				vertical-align: text-bottom;
			
			}
			#img1{
				opacity: 0.3;
			}
			#navi a{
				position :relative;
				top : 15px;
				left:0px;
				text-decoration: none;
				color: #333;
				font-size: large;
				font-weight: bold;
			}	
		</style>
	</head>
	<body>
		<div id="menu">
			<div id="menu1">
				<span>${ sessionScope.loginId }</span>님 환영합니다
				<a href="./pmain">HOME</a>
				<a href="./m02.jsp">비밀번호변경</a>
				<a href="./logout">LOGOUT</a>
			</div>
		</div>
		<div id="navi">
			<div>
				<img src="/img/logo.png" onclick="location.href='p01_main.jsp'" width="130" height="90" alt="로고,,,"/>
			</div>
			<div class="title"><a href="pProfile">내 정 보</a></div>
			<div class="title"><a href="p03.jsp">과목게시판</a></div>
			<div class="title"><a href="studentSearchPage?subject_id=default">강 의</a></div>
		</div>
	</body>
	<script>
	</script>
</html>