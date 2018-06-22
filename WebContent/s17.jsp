 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<script src =" https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<style>
			img{
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
			#navi a{
				position :relative;
				top : 15px;
				left:0px;
				text-decoration: none;
				color: #333;
				font-size: large;
				font-weight: bold;
			}
			#navi #navi3{
				background-color: #4375DB;
			}
 			#sub a{
				text-decoration: none;
				font-size: 17px;
				font-weight: bold;
				color: black;
				margin-left: 10px;
			}
			#sub{
				margin-top: 1%;
				width: 230px;
				height: 400px;
				border: 1px solid #c0c0c0;
				margin-left: 1%;
				background-color: #F6F6F6;
				float: left; 
			}
			 #sub div{
				height: 40px;
				padding-top: 50px;
			} 
			#sub div:hover {
				text-decoration: underline; 
			}
		#s17{
			text-decoration: underline;
		}
		table{
			width: 1000px;
			position:absolute;
			top:200px;
			left:360px;
			}
		table,th,td{
			background-color : white;
			border-collapse: collapse;
			border : 1px solid black;
			padding :15px;
		}
		#stdEnroll th{
		    border-right: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;
		    border-top: 1px solid #fff;
		    border-left: 1px solid #fff;
			background: #eee;
		}
		.trRemove td{
			border-left: 1px solid #ccc;
		 	border-right: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;
		}
		#span{
			width:1000px;
			height:30px;
			background-color :#e2e2e2;
			position : absolute;
			left :360px;
			top:155px;
		}
	</style>
</head>
<body>
			<div id="menu">
				<div id="menu1">
					<span>${sessionScope.loginId}</span>님 환영합니다.
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
				<div class="title"><a href="./s15-main.jsp" style="text-decoration: underline">수강신청</a></div>
			</div>
			<div id="sub">
				<div><a href="./s15-main.jsp">과목조회</a></div>
				<div ><a href="./s16.jsp">수강신청</a></div>
				<div id="s17"><a href="./s17.jsp">신청과목조회</a></div>
			</div>
			<div id=span>
				<span  style="font-weight:bold">${sessionScope.loginId}</span>학생의 수강 신청 과목 조회 내역
			</div>
			<table> 
			<tr id="stdEnroll">
				<th>학기</th>
				<th>학과명</th>
				<th>과목명</th>
				<th>교수명</th>
				<th>강의실</th>
				<th>강의시간</th>
				<th>이수구분</th>
				<th>학점</th>
				<th>제한인원</th>
			</tr>
			</table>
</body>
<script>
	$(document).ready(function(){

			/* ready 되면서 특정학생이 수강 신청한 과목 조회 */
			$.ajax({
				type:"POST",
				url:"./stdEnroll",
				data:{
					"loginId" :"${sessionScope.loginId}",
					"term_id":"2018-2"
				},
				dataType:"JSON",
				success:function(data){
					console.log(data);
					if(".trRemove"!=null){
						//tr 제거
						console.log("tr제거");
						$(".trRemove2").remove();
					}
					// 수강신청 과목 리스트 담을 변수 선언
					var listAppend;
					for(var i =0; i<data.searchList.length; i++){
						listAppend+="<tr class='trRemove'>"
						listAppend+="<td>"+data.searchList[i].term_id+"</td>"
						listAppend+="<td>"+data.searchList[i].major_name+"</td>"
						listAppend+="<td>"+data.searchList[i].subject_name+"</td>"
						listAppend+="<td>"+data.searchList[i].pro_name+"</td>"
						listAppend+="<td>"+data.searchList[i].subject_room+"</td>"
						listAppend+="<td>"+data.searchList[i].subject_time+"</td>"
						listAppend+="<td>"+data.searchList[i].subject_type+"</td>"
						listAppend+="<td>"+data.searchList[i].subject_credit+"</td>"
						listAppend+="<td>"+data.searchList[i].subject_limit+"</td>"
						listAppend+="</tr>"
					}
						$("#stdEnroll").after(listAppend);
				},
				error:function(error){
					console.log(error);
				}
			});
	});
	


</script>
</html>