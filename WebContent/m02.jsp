<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>비밀번호 변경</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			body {
				margin: 0 ;
				padding: 0 ;
			}
			#main{
				position:relative;
				left :25%;
				background-color: white;
			    height: 100%;
				width : 800px;	
				text-align: center;
			  	vertical-align: text-bottom;
				  			}
			html{
				background-color: #A9BCF5;
				text-align: center;
			}
			#div1{
				position: absolute;
				top: 430px;
				left: 100px;
				width: 650px;
				text-align: center	;
			}
			input[type="password"]{
           		line-height: 18px;
			    margin-bottom: 10px;
			    padding: 10px;
			    font-size: 14px;
			    border: 1px solid #ccc;
		        font: 400 13.3333px Arial;
		        width: 311px;
            }	
            #save{
           		margin:5px;
             	height: 40px;
	            width: 311px;
			    color: #4d4d4d;
            }
            table{
            	width: 650px;
            }
            img {
            	position: relative;
				top: 50px;
				cursor: pointer;
			}
		</style>
	</head>
	<body>
	
		<div id="main">
			<img onclick="location.href='index.jsp'" alt="로고" src="/img/logo.png" width="450" height="350">
			<div id="div1">
				<form action="passChange" method="post">
					<table>
						<tr>
							<td>
								현재 비밀번호  
							</td>
							<td>
								<input id="writePass" type="password" name="Pass" placeholder="현재 비밀 번호를 입력해 주세요.">
							</td>
							<td>
								<input type ="button" id="passCheck" value="현재 비밀번호 확인">
							</td>
						</tr>
						<tr>
							<td>
								새로운 비밀번호
							</td>
							<td>
								 <input id ="writeNewPass" type="password" name="newPw" placeholder="새로운 비밀 번호를 입력해 주세요.">
							</td>
						</tr>
						<tr>
							<td>
								새로운 비밀번호 확인
							</td>
							<td>
								 <input id = "newPassCheck" type="password" name="newPwCheck" placeholder="새로운 비밀 번호를 한번더 입력 해주세요.">
							</td>
						</tr>
					</table>
				
					<!-- <input type="submit" value="저장">  -->
					<button id = "save">비밀번호 변경</button>
					<input type ="hidden" name="loginId" value="${sessionScope.loginId}">
			
				</form>
			</div>
		</div>
	</body>
	<script>
	var loginPw ="${sessionScope.loginPw}";
	
	$("#passCheck").click(function(){
		if(loginPw==document.getElementById("writePass").value){
			alert("현재의 비밀번호가 일치합니다.");
		}else{
			alert("현재의 비밀번호가 일치하지 않습니다. 다시 확인 해 주세요.");
		}
		//console.log(loginPw);
		//console.log(document.getElementById("writePass").value);
		//console.log(document.getElementById("writeNewPass").value);
		//console.log(document.getElementById("newPassCheck").value);
    });
	
	$("#save").click(function(){
		if(document.getElementById("writeNewPass").value!=document.getElementById("newPassCheck").value){
			alert("새 비밀번호가 일치하지 않습니다. 다시 확인 해 주세요.");
		}else{
			alert("저장이 완료 되었습니다. 새로운 비밀번호로 다시 로그인 해 주세요.");
			//location.href="index.jsp";
		}
		//console.log(loginPw);
		//console.log(document.getElementById("writePass").value);
		//console.log(document.getElementById("writeNewPass").value);
		
    });
	
	</script>
</html>