<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
             /*    border-right:none;
                border-left:none;
                border-top:none;
                border-bottom:none;  */
            }
            td,th{
                padding: 5px 10px;
            }
            #calender{
            	position: absolute;
            	left: 300px;
            }
</style>
</head>
<body>

	<table>
		<tr>
			<th id ="schedule_date" width="520px">
				<input type="text" value="날짜" readonly="readonly" id="date"/>
			</th>	         
		</tr>
		<tr>
		   <th id="schedule_content" height="265px">일정</th>
	  	 	<button id="save">저장</button>
			<input type="button" id="caDel" value="삭제"/>
	    </tr>
	</table>
</body>

</html>