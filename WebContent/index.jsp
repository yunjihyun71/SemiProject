<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>로그인 화면</title>
        <style>
            table, tr, th, td {
            	border: thin solid black;
            	border-collapse: collapse;
            	padding: 5px;
            	text-align: center;
            }
            input [type="text"]{
			    line-height: 18px;
			    margin-bottom: 10px;
			    padding: 10px;
			    font-size: 14px;
			    border: 1px solid #ccc;
		        font: 400 13.3333px Arial;
            }
            input{
           		margin:5px;
             	height: 40px;
	            width: 311px;
			    color: #4d4d4d;
            }
           #one{
           		font-size: 14px;
           		color: red;	
           } 
           #two{
           		font-size: 14px;
         		color: #737373;	
           }
           	body{
           		background-color:#E6E6E6;
            }
            #main{
           		position: absolute;
            	top: 150px;
            	left: 24%;
            	background-color:white;
            	width: 800px;
            	height: 400px;
            }
            #sub{
            position:absolute;
            	left: 460px;
            	top: 70px;
            }
            img{
            	position: absolute;
            	top: 70px;
            	left: 50px;
            }
        </style>
        <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    </head>
    <body>
    	
    	<div id="main">
        <form action="login" method="post">
        		<img src="/img/logo.png" width="350" height="250" alt="로고,,,"/>
    		<div id="sub">
	        	<div>
	        		<input type="text" name="userId"  placeholder="학번,사번(ID No.)"/>
	        	</div>
	        	<div>
	        		<input type="password" name="userPw" placeholder="비밀번호(Password)"/>
	        	</div>
	        	<p id="one">
	        		원하는 서비스를 이용하시려면 로그인이 필요합니다.<br/>
	        		Login is required for the service you want.
	        	</p>
	        	<div>
	        		 <input type="submit" value="로그인(Login)">
	     		</div>
	     		<p id="two">
	        		이용후 반드시 로그아웃 해주세요!<br/>
					Please be sure to log out after use.
	        	</p>
     		</div>
        </form>
        </div>
    </body>
    <script>
    	var msg = "${msg}";
    	if (msg != "") {
    		alert(msg);
    	}
    
    </script>
</html>
