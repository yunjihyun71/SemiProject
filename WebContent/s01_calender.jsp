<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			#schedule {
				position: relative;
                border: 3px dotted green;
                left:600px;
				width: 524px;
				height:395px;
				bottom: 418px;
				border: 1px solid #c0c0c0;
				border-radius: 5px;
				background-color: white;
			}
			#hacsa {
				text-align: center;			
			}
			table{
                margin-top: 10px;    
            }
            table,td,th{
                border: 1px solid black;
                /*각 라인을 합쳐 준다.*/
                border-collapse: collapse;
                text-align: center;
                 border-right:none;
                border-left:none;
                border-top:none;
                border-bottom:none;  
                background-color: white;
            }
            td,th{
                padding: 5px 10px;
            }
		</style>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	</head>
	<body>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		
		<div id="datepicker"></div>
		<input type="hidden" id="getdate" name="getdate">
		<div id="schedule"><h1 id="hacsa">학사일정</h1>	
			<br/>
			<br/>
			<table id="listTable">
	            <tr>
	                <th id ="schedule_date" width="520px">날짜를 선택하세요</th>	         
	            </tr>
        	</table>
		</div>
		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
	</body>
	<script>
	 	
	    $('#datepicker').datepicker({
	        altField : '#getdate',
	        dateFormat : 'yy-mm-dd'       
		});
	  	    
	    
	    $('#datepicker').change(function() {
          
		  $.ajax({
				type: "post",
				url: "./dateEvent",
				dataType: "json",
				
				data: {
					"schedule": $(this).val() 
				},
				success: function(data) {
					console.log(data);
					if(data){
						listPrint(data.dateList)
						$("#schedule_date").html(document.getElementById("getdate").value);
						}else{
							alert("날짜를 다시 선택해주세요");
						}
					}
		  		});
			});
	    
	   	function listPrint(dateList){
	   		var content ="";
	   		$("#listTable").html("<table id='listTable'><tr><th id ='schedule_date' width='520px'>날짜</th></tr></table>");
	   		dateList.forEach(function(item){
	   			content += "<tr>";
	   			content += "<td>"+item.schedule_content+"</td>";
	   			content += "</tr>";
	   		});
	   		$("#listTable").append(content);
	   	}

	     //-- Font-size를 40px 설정해보니 상단의 년도와 , 월~토요일까지가 글자의 크기가 변경되는 것을 확인하였습니다. 
	    $('.ui-datepicker').css('font-size', '30px');
  
	</script>
</html>