<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			body  { height: 100%; }
			body, div, span, applet, object, iframe,
			p, blockquote, pre,
			a, abbr, acronym, address, big, cite, code,
			del, dfn, em, font, img, ins, kbd, q, s, samp,
			small, strike, strong, sub, sup, tt, var,
			b, u, i, center,
			dl, dt, dd, ol, ul, li,
			fieldset, form, label, legend,
			table, caption, tbody, tfoot, thead, tr, th, td {
				margin: 0;
				padding: 0;
				border: 0;
				outline: 0;
				font-size: 100%;
				vertical-align: baseline;
				background: transparent;
			}
			body { line-height: 1; }
			ol, ul { list-style: none; }
			blockquote, q { quotes: none; }
			blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }
			:focus { outline: 0; }
			del { text-decoration: line-through; }
			table {border-spacing: 0; }
			
			 body{
				font-family: Arial, Helvetica, sans-serif;
				background: url(background.jpg);
				margin: 0 auto;
				width: 520px;
			}
		
			a:link {
				color: #666;
				font-weight: bold;
				text-decoration: none;
			}
			a:visited {
				color: #666;
				font-weight: bold;
				text-decoration: none;
			}
			a:active,
			a:hover {
				color: #bd5a35;
				text-decoration: underline;
			} 

			/* table a:link {
				color: #666;
				font-weight: bold;
				text-decoration: none;
			} */
			#schedule {
				position: relative;
                left:600px;
				width: 524px;
				height:395px;
				bottom: 418px;
				border-radius: 5px;
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
            }
            td,th{
                padding: 5px 10px;
            }
            #calender{
            	position: absolute;
            	left: 300px;
            	top: 200px;
            }
           	input[type=text]{
				padding : 10px 5px;
				font-size: 17px;
				width: 50%;
				z-index: 10;
				height: 16px;
				border: none;
				background: #fff;
			}
			table input[type=text]{
				padding : 10px 5px;
				font-size: 17px;
				width: 80%;
				z-index: 10;
				height: 16px;
				border: none;
				background: #fff;
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
		<form id="calender" action="caAdd">
			<div id="datepicker"></div>
			<input type="text" id="getdate" name="getdate">
			<div id="schedule"><h1 id="hacsa">학사일정</h1>	
			<input id="register" type="text" name="content"/>
			<input type="submit" value="등록"/>	
			<table id="listTable">
	            <tr>
	                <th id ="schedule_date" width="520px">일정</th>	         
	            </tr>
       		</table>
				
			</div>
		</form>
		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
	</body>
	<script>
	 	//리스트
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
						}else{
							alert("날짜를 다시 선택해주세요");
						}
					}
			});
		});
	    function listPrint(dateList){
	   		var content ="";
	   		$("#listTable").html("<table id='listTable'><tr><th id ='schedule_date' width='520px'>일정</th></tr></table>");
	   		dateList.forEach(function(item){
	   			content += "<tr>";
	   			content += "<td>"+
	   				"<form action='./caUpdate'>"+
	   				"<input  name='schedule_id' type='hidden' value='"+ item.schedule_id+"'/>"+
	   				"<input class='cont1' name='content' type='text' value='"+item.schedule_content+"'/>"+
	   				"<input  type='submit' value='수정'/>"+ 
	   				"<input class='del' type='button' onclick='location.href=\"./caDell?schedule_id=" + item.schedule_id + "\"' value='삭제'/>"+"</td>"+
	   				"</form>"+
	   				"<input name='schedule_id' type='hidden' value='"+item.schedule_id+"'/>";
	   			content += "</tr>";
	   		});
	   		$("#listTable").append(content);
	   	}
	    $('.ui-datepicker').css('font-size', '30px');
  		//삭제///////////////////////////////////////////////////////////////
  		  $('.del').click(function() {
			$.ajax({
				type: "post",
				url: "./caDell",
				dataType: "json",
				data: {
					"caDell": $(this).val() 
				},
				success: function(data) {
					console.log(data);
				}
			});
		}); 
	</script>
</html>