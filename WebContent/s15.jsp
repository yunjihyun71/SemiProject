 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	<style>
 		#s15{
			text-decoration: underline;
		}
		#divSel{
			position : absolute;
			top :  160px;
			left: 350px;
			width:99%;
			margin : 10px;
		}
		#divSel #optSelect{
			width: 170px;
			height:50px;
		}
		#divSel #inp{
			width: 500px;
			height:44px;
		}
		#divSel #btn{
			width: 300px;
			height:50px;
			cursor: pointer;
		}
		table{
			width: 987px;
		}
		table,th,td{
			background-color : white;
			border-collapse: collapse;
			text-align: center;
			padding:17px;
			margin-top:110px;
			margin-left:360px;
		}
		#page{
			width: 1000px;
		}
		#page .paging{
			margin-left:700px;
		}
		#trAppend th{
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
	</style>
</head>
<body>
		<div id="divSel">
				<select id="optSelect">
					<option value="entry" selected>전체</option>
					<option value="term">학기별</option>
					<option value="pro">교수별</option>
					<option value="maj">학과명</option>
					<option value="sub">과목명</option>
				</select>
				<input type="text" id ="inp"  placeholder="조회 버튼을 클릭해주세요"/>
				<input type="button" id="btn" value="조회"/>
		</div>
			<table>
					<tr id="trAppend">
						<th>학기</th>
						<th>학과명</th>
						<th>과목명</th>
						<th>교수명</th>
						<th>강의실</th>
						<th>강의시간</th>
						<th>이수구분</th>
						<th>학점</th>
						<th>제한인원</th>
						<th>평점</th>
					</tr>
			</table>
			<div id="page">
				<jsp:include page="paging.jsp"></jsp:include>
			</div>
</body>
<script>
	$(document).ready(function(){
		//ready 되자마자 이전 과목 리스트 요청
		var obj ={};
		obj.type="POST";
		obj.url="./subjectSearch" ; 
		obj.optValue = $("#optSelect option:selected").val();
		obj.inpValue = $("#inp").val();
		//obj.page;
		/*이전 학기 평점조회 와 
		신학기 수강신청 과목 분류 하기위해 분류할수있는 데이터 함께 전송*/
		obj.term_id = " <= '2018-2' "
		obj.data={
				//getParameter()메서드 : name 을 통해서 value를 얻을 수 있음
				//optValue를 기준으로 sql 분류
				"optValue"  :obj.optValue,
				"inpValue" : obj.inpValue ,
				"term_id" : obj.term_id,
		};
		obj.dataType="JSON";
		obj.success=function(data){
			console.log("성공!");
			console.log(data);
			pagingView(data.paging);
			append(data.searchList);
		};
		obj.error=function(error){
				console.log("error");
		};
		ajaxCall(obj);
		
		/* 조회 버튼 클릭 시 text의 value 가져오기 */
		$("#btn").click(function(){
 			console.log("조회 버튼 클릭");
 			obj.optValue = $("#optSelect option:selected").val();
 			obj.inpValue = $("#inp").val();
 			obj.data={
 					//getParameter()메서드 : name 을 통해서 value를 얻을 수 있음
 					//optValue를 기준으로 sql 분류
 					"optValue"  :obj.optValue,
 					"inpValue" : obj.inpValue ,
 					"term_id" : obj.term_id,
 			};
			ajaxCall(obj);
		}); 
	});		
		/* 과목 list 출력 */
 		function append(searchList){
			//부모 , 자식 요소 삭제 - tr을 삭제 후 새로운 tr 생성
				if($(".trRemove")!=null){
				$(".trRemove").remove();
				console.log("tr제거");
			} 
			var listAppend;
			for(var i =0; i<searchList.length; i++){
				listAppend+="<tr class='trRemove'>"
				listAppend+="<td>"+searchList[i].term_id+"</td>"
				listAppend+="<td>"+searchList[i].major_name+"</td>"
				listAppend+="<td>"+searchList[i].subject_name+"</td>"
				listAppend+="<td>"+searchList[i].pro_name+"</td>"
				listAppend+="<td>"+searchList[i].subject_room+"</td>"
				listAppend+="<td>"+searchList[i].subject_time+"</td>"
				listAppend+="<td>"+searchList[i].subject_type+"</td>"
				listAppend+="<td>"+searchList[i].subject_credit+"</td>"
				listAppend+="<td>"+searchList[i].subject_limit+"</td>"
				listAppend+="<td>"+searchList[i].subject_grade+"</td>"
				listAppend+="</tr>"
			}
				$("#trAppend").after(listAppend);
 		}
		/* 페이징 뷰 */
 		function pagingView(paging){
 			console.log("pagingView 호출");
 			console.log(paging);
 			// 초기화
 			$(".paging").html("");
 			// 맨앞
 		    if (paging.startPage > 1) {
 		        $(".paging").append("<a class='text' onclick='list(1)'>맨앞</a>");
 		    }
 		    // 이전
 		    if (paging.startPage > 1) {
 		        $(".paging").append("<a class='text' onclick='list("+paging.prevPage+")'>이전</a>");
 		    }
 		    // 페이지 번호
 		    for (var i = paging.startPage; i <= paging.endPage; i++) {
 		        if (i == paging.page) {
 		            $(".paging").append("<a id='curPage' onclick='list("+i+")'>" + i + "</a>");
 		        } else {
 		            $(".paging").append("<a onclick='list("+i+")'>" + i + "</a>");
 		        }
 		    }
 		    // 다음
 		    if (paging.endPage != paging.totalPage) {
 		        $(".paging").append("<a class='text' onclick='list(" + paging.nextPage  + ")'>다음</a>");
 		    }
 		    // 맨뒤
 		    if (paging.endPage != paging.totalPage) {
 		        $(".paging").append("<a class='text' onclick='list(" + paging.totalPage + ")'>맨뒤</a>");
 		    }
 		}
	/* 페이징 뷰 이벤트 처리  - 페이징 처리하는 ajax를 만들어보자.*/
	function list(page){
			console.log(page);
			var optValue = $("#optSelect option:selected").val();
			var inpValue = $("#inp").val();
			var term_id = " <= '2018-2' ";
			$.ajax({
				type:"POST",
				url:"./subjectSearch",
				dataType:"JSON",
				data:{
					"optValue":optValue , 
					"inpValue": inpValue,
					"term_id": term_id ,
					"page" :page
				},
				success:function(data){
					console.log(data);
					append(data.searchList)
					pagingView(data.paging)
				},
				error:function(error){
					console.log(error);
				}
			});
	}
 		 /* ajax실행 */
		function ajaxCall(obj){
			console.log(obj);
			console.log("ajax호출");
			$.ajax(obj);
		}

</script>
</html>