<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
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
				position : absolute;
				top: 10px;
				left:65%;
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
			/* #navi div:hover{
				background-color: #4375DB;
			} */
			#navi a{
				position :relative;
				top : 15px;
				left:0px;
				text-decoration: none;
				color: #333;
				font-size: large;
				font-weight: bold;
			}
			/* ///////////////// */
			select {
				width: 150px;
				height: 30px;
				margin-bottom: 15px;
				margin-top: 15px;
				margin-left: 10px
			}
 			#sub a{
				text-decoration: none;
				font-size: 17px;
				font-weight: bold;
				color: black;
				margin-left: 10px;
				display: none;
			}
			#sub{
				margin-top: 1%;
				width: 230px;
				height: 400px;
				border: 1px solid #c0c0c0;
				margin-left: 1%;
				background-color: #F6F6F6;
				float: left;
				display: none;
			}
			#sub div{
				height: 40px;
				padding-top: 15px;
				display: none;
			}
			#sub div:hover {
				text-decoration: underline;
				display: none;
			}
			img {
				cursor: pointer;
			}
			#main1{
				position: absolute;
				top:145px;
				left:220px;
				height: 80%;
				vertical-align: text-bottom;
			
			}
		</style>
	</head>
	<body>
		<div id="menu">
			<div id="menu1">
				<span></span>
				<script>
					var loginId ="${sessionScope.loginId}";
					if(loginId == ""){
						alert("로그인이 필요한 서비스 입니다.");
						location.href="index.jsp";
					}else{
						var content = loginId+" 님 환영합니다 ";
						document.getElementById("menu1").innerHTML = content;
					}
				</script>
				<a href="./s01.jsp">HOME</a>
				<a href="./m02.jsp">비밀번호변경</a>
				<a href="./logout">LOGOUT</a>
			</div>
		</div>
		<div id="navi">
			<div>
				<img src="/img/logo.png" onclick="location.href='s01.jsp'" width="130" height="90" alt="로고,,,"/>
			</div>
			<div class="title"><a href="./s02-main.jsp">학적</a></div>
			<div class="title"><a href="./s08.jsp">과목게시판</a></div>
			<div class="title"><a href="./s15-main.jsp">수강신청</a></div>
		</div>
		<div id="main1">
			<jsp:include page="s01_calender.jsp"></jsp:include>
		</div>
	</body>
</html>