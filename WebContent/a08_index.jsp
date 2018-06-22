 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="kr">
	<head>
		<meta charset="UTF-8">
		<title>관리자 페이지</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
		<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
		<style type="text/css">
			#bnt{
				position: absolute;
				top: 130px;
				left: 87%;
			}
			html{
           		background-color:#E6E6E6;
            }
			/* 로그아웃 */
			#menu{
				position : absolute;
				left:80%;
				overflow:hidden;
				float: right;
				font-weight: 600;
				font-size:12px;
				margin: 15px 0px 15px 0px;
			}
			#menu a{
				text-decoration: none;
				border-left: 1px solid #c0c0c0;
				padding: 0px 10px 0px 14px;
				color: black;
			}
		 	#div3{
                position: absolute;
                width:auto;
                height: auto;
                border: none solid black;
                top: 130px;
                left: 1050px;
           	}
            /**메뉴 css********************/
			input[type="checkbox"]#menu_state {
		  		display: none;
			}
			input[type="checkbox"]:checked ~ nav {
				width: 250px;
			}
			input[type="checkbox"]:checked ~ nav label[for="menu_state"] i::before {
				content: "\f053";
			}
			input[type="checkbox"]:checked ~ nav ul {
				width: 100%;
			}
			input[type="checkbox"]:checked ~ nav ul li a i {
				border-right: 1px solid #2f343e;
			}
			input[type="checkbox"]:checked ~ nav ul li a span {
				opacity: 1;
				transition: opacity 0.25s ease-in-out;
			}
			input[type="checkbox"]:checked ~ main {
			 	left: 250px;
			}
			nav {
				position: fixed;
				z-index: 9;
				top: 0;
				left: 0;
				bottom: 0;
				background: #383e49;
				color: #9aa3a8;
				width: 50px;
				font-family: 'Montserrat', sans-serif;
				font-weight: lighter;
				transition: all 0.15s ease-in-out;
			}
			nav label[for="menu_state"] i {
				cursor: pointer;
				position: absolute;
				top: 50%;
				right: -8px;
				box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
				background: #fff;
				font-size: 10px;
				display: flex;
				justify-content: center;
				align-items: center;
				height: 15px;
				width: 15px;
				border-radius: 50%;
				border: 1px solid #ddd;
				transition: width 0.15s ease-in-out;
				z-index: 1;
			}
			nav label[for="menu_state"] i::before {
				margin-top: 2px;
				content: "\f054";
			}
			nav label[for="menu_state"] i:hover {
				box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
			}
			nav ul {
				overflow: hidden;
				display: block;
				width: 50px;
				list-style-type: none;
				padding: 0;
				margin: 0;
			}
			nav ul li {
				border: 1px solid #2f343e;
				position: relative;
			}
			nav ul li:not(:last-child) {
				border-bottom: none;
			}
			nav ul li.active a {
				background: #4c515d;
				color: #fff;
			}
			nav ul li a {
				position: relative;
				display: block;
				white-space: nowrap;
				text-decoration: none;
				color: #9aa3a8;
				height: 50px;
				width: 100%;
				transition: all 0.15s ease-in-out;
			}
			nav ul li a:hover {
				background: #4c515d;
				color: #fff;
			}
			nav ul li a * {
				height: 100%;
				display: inline-block;
			}
			nav ul li a i {
				text-align: center;
				width: 50px;
				z-index: 999999;
			}
			nav ul li a i.fa {
				line-height: 50px;
			}
			nav ul li a span {
				padding-left: 25px;
				opacity: 0;
				line-height: 50px;
				transition: opacity 0.1s ease-in-out;
			}
			main {
				position: absolute;
				transition: all 0.15s ease-in-out;
				top: 0;
				left: 50px;
			}
			main header {
				position: absolute;
				z-index: -1;
				top: 0;
				left: 0;
				right: 0;
				height: 400px;
				background: url("http://www.blueb.co.kr/SRC2/_image/01.jpg");
				background-size: cover;
				background-position: 50% 50%;
				background-repeat: no-repeat;
			}
			main section {
				box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
				background: #fff;
				padding: 25px;
				font-family: helvetica;
				font-weight: lighter;
				padding: 50px;
				margin: 150px 75px;
				transition: all 0.15s ease-in-out;
			}
			main section:hover {
				box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
			}
			main section h1 {
				padding-top: 0;
				margin-top: 0;
				font-weight: lighter;
			}
		</style>
	</head>
	<body>
		<div id="menu">
			<span></span><!-- 님 환영합니다 -->
			<script>
				var loginId ="${sessionScope.loginId}";
				if(loginId == ""){
					alert("로그인이 필요한 서비스 입니다.");
					location.href="index.jsp";
				}else{
					var content = loginId+" 님 환영합니다 ";
					document.getElementById("menu").innerHTML = content;
				}
			</script>
			<a href="./logout">LOGOUT</a>
		</div>
		<input type="checkbox" id="menu_state" checked>
		<nav>
		<label for="menu_state"><i class="fa"></i></label>
			<ul>
				<li>
					<a href="student">
						<i class="fa fa-graduation-cap"></i>
						<span>학생 관리</span>
					</a>
				</li>
				<li>
					<a href="aTuition">
						<i class="fa fa-credit-card"></i>
						<span>등록금 관리</span>
					</a>
				</li>
				<li>
					<a href="scScholar">
						<i class="fa fa-pencil"></i>
						<span>장학금 종류 관리</span>
					</a>
				</li>
				<li>
					<a href="ePage">
						<i class="fa fa-credit-card"></i>
						<span>장학금 관리</span>
					</a>
				</li>
				<li>
					<a href="pManagePage">
						<i class="fa fa-user"></i>
						<span>교수 관리</span>
					</a>
				</li>
				<li >
					<a href="suManagePage">
						<i class="fa fa-book"></i>
						<span>과목 관리</span>
					</a>
				</li>
				<li>
					<a href="gPage">
						<i class="fa fa-edit"></i>
						<span>강의평가 질문 관리</span>
					</a>
				</li>
				<li class="active unread">
					<a href="a08_index.jsp">
						<i class="fa fa-calendar"></i>
						<span>학사일정 관리</span>
					</a>
				</li>
			</ul>	
		</nav>
		<main>
			<header></header>
		</main>
		<%@ include file="a08_calender.jsp" %>
	</body>
</html>