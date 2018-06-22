 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
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
	table {border-spacing: 0; } /* IMPORTANT, I REMOVED border-collapse: collapse; FROM THIS LINE IN ORDER TO MAKE THE OUTER BORDER RADIUS WORK */
	
	/*------------------------------------------------------------------ */
	
	/*This is not important*/
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
	} */
	
	Table Style - This is what you want
	------------------------------------------------------------------ */
	table a:link {
		color: #666;
		font-weight: bold;
		text-decoration: none;
	}
	table a:visited {
		color: #999999;
		font-weight: bold;
		text-decoration: none;
	}
	table a:active,
	table a:hover {
		color: #bd5a35;
		text-decoration: underline;
	}
	table {
		font-family: Arial, Helvetica, sans-serif;
		color: #666;
		font-size: 12px;
		text-shadow: 1px 1px 0px #fff;
		background: #eaebec;
		margin: 20px;
		border: #ccc 1px solid;
		position: absolute;
	    top: 150px;
		left: 250px;
		width :1150px;
		-webkit-border-radius: 3px;
		border-radius: 3px;
	
		-webkit-box-shadow: 0 1px 2px #d1d1d1;
		box-shadow: 0 1px 2px #d1d1d1;
	}
	table > thead th {
		padding: 21px 25px 22px 25px;
		border-top: 1px solid #fafafa;
		border-bottom: 1px solid #e0e0e0;
	
		background: #ededed; /* Old browsers */
		background: -moz-linear-gradient(top,  #ededed 0%, #ebebeb 100%); /* FF3.6+ */
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ededed), color-stop(100%,#ebebeb)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(top,  #ededed 0%,#ebebeb 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(top,  #ededed 0%,#ebebeb 100%); /* Opera 11.10+ */
		background: -ms-linear-gradient(top,  #ededed 0%,#ebebeb 100%); /* IE10+ */
		background: linear-gradient(to bottom,  #ededed 0%,#ebebeb 100%); /* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ededed', endColorstr='#ebebeb',GradientType=0 ); /* IE6-9 */
	}
	table > thead th:first-child{
		text-align: left;
		padding-left: 20px;
	}
	table > thead > tr:first-child > th:first-child {
		-webkit-border-top-left-radius: 3px;
		border-top-left-radius: 3px;
	}
	table > thead > tr:first-child > th:last-child {
		-webkit-border-top-right-radius: 3px;
		border-top-right-radius: 3px;
	}
	table > tbody > tr {
		text-align: center;
		padding-left: 20px;
	}
	table > tbody > tr > td:first-child {
		text-align: left;
		padding-left: 20px;
		border-left: 0;
	}
	table > tbody > tr > td {
		padding:18px;
		border-top: 1px solid #ffffff;
		border-bottom: 1px solid #e0e0e0;
		border-left: 1px solid #e0e0e0;
	
		background: #fbfbfb; /* Old browsers */
		background: -moz-linear-gradient(top,  #fbfbfb 0%, #fafafa 100%); /* FF3.6+ */
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#fbfbfb), color-stop(100%,#fafafa)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(top,  #fbfbfb 0%,#fafafa 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(top,  #fbfbfb 0%,#fafafa 100%); /* Opera 11.10+ */
		background: -ms-linear-gradient(top,  #fbfbfb 0%,#fafafa 100%); /* IE10+ */
		background: linear-gradient(to bottom,  #fbfbfb 0%,#fafafa 100%); /* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fbfbfb', endColorstr='#fafafa',GradientType=0 ); /* IE6-9 */
	}
	table > tbody > tr:nth-child(even) > td{
		background: #f8f8f8; /* Old browsers */
		background: -moz-linear-gradient(top,  #f8f8f8 0%, #f6f6f6 100%); /* FF3.6+ */
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f8f8f8), color-stop(100%,#f6f6f6)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(top,  #f8f8f8 0%,#f6f6f6 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(top,  #f8f8f8 0%,#f6f6f6 100%); /* Opera 11.10+ */
		background: -ms-linear-gradient(top,  #f8f8f8 0%,#f6f6f6 100%); /* IE10+ */
		background: linear-gradient(to bottom,  #f8f8f8 0%,#f6f6f6 100%); /* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f8f8f8', endColorstr='#f6f6f6',GradientType=0 ); /* IE6-9 */
	}
	table > tbody > tr:last-child > td{
		border-bottom: 0;
	}
	table > tbody > tr:last-child > td:first-child {
		-webkit-border-bottom-left-radius: 3px;
		border-bottom-left-radius: 3px;
	}
	table > tbody > tr:last-child > td:last-child {
		-webkit-border-bottom-right-radius: 3px;
		border-bottom-right-radius: 3px;
	}
	table > tbody > tr:hover > td {
		background: #f2f2f2; /* Old browsers */
		background: -moz-linear-gradient(top,  #f2f2f2 0%, #f0f0f0 100%); /* FF3.6+ */
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f2f2f2), color-stop(100%,#f0f0f0)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(top,  #f2f2f2 0%,#f0f0f0 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(top,  #f2f2f2 0%,#f0f0f0 100%); /* Opera 11.10+ */
		background: -ms-linear-gradient(top,  #f2f2f2 0%,#f0f0f0 100%); /* IE10+ */
		background: linear-gradient(to bottom,  #f2f2f2 0%,#f0f0f0 100%); /* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f2f2f2', endColorstr='#f0f0f0',GradientType=0 ); /* IE6-9 */
	}
	</style>
</head>
<body>
	<div id="div3">
        <form id="select" action="tSearch">
        	<select name="selectbox">
				<option value="std_id">학번</option>
			  	<option value="std_name">이름</option>
			 	<option value="term_id">학기</option>
			</select>
        <input type="text" name="val"/>
        <input type="submit" value="조회"/>
        <input type="button" onclick="location.href='a02_register.jsp'" value="등록">
		</form>
	</div>
    <table cellspacing='0'> <!-- cellspacing='0' is important, must stay -->
		<thead>
			<tr>
	            <th>학기</th>
	            <th>학번</th>
	            <th>이름</th>
	            <th>장학금 종류</th>
	            <th>장학금 금액</th>
	            <th>등록금</th>
	            <th>실납입액</th>
	            <th>수정</th>
	            <th>삭제</th>
      		</tr>
        </thead><!-- Table Header -->
        <tbody>
       		<c:forEach items="${list}" var="bbs">
       			<form action="tUpdateForm" method="get">
		      		<tr>
		      			<input  type="hidden" name="tuition_id" value="${ bbs.tuition_id }"/>
			            <td>${bbs.term_id}<input  type="hidden" name="term_id" value="${ bbs.term_id }"/></td>
			            <td>${bbs.std_id}<input  type="hidden" name="std_id" value="${ bbs.std_id }"/></td>
			            <td>${bbs.std_name}</td>
			            <td>${bbs.scholar_name}</td>
			            <td>${bbs.scholar_money}<input  type="hidden" name="scholar_money" value="${ bbs.scholar_money }"/></td>
			            <td>${bbs.tuition_money}<input  type="hidden" name="tuition_money" value="${ bbs.tuition_money }"/></td>
			            <td>${bbs.totalMoney}</td>
			            <td>
			           		<input  type="submit" value="수정"/>
			            </td>
			            <td>
			           		<input type="button" onclick="location.href='tDell?tuition_id=${bbs.tuition_id}'" value="삭제"/>
			            </td>
	       			</tr>
       			</form>
        	</c:forEach>
        	<c:forEach items="${search}" var="search">
       			<form action="tUpdateForm" method="get">
		      		<tr >
			            <td>${search.term_id}<input  type="hidden" name="term_id" value="${ search.term_id }"/></td>
			            <td>${search.std_id}<input  type="hidden" name="std_id" value="${ search.std_id }"/></td>
			            <td>${search.std_name}</td>
			            <td>${search.scholar_name}</td>
			            <td>${search.scholar_money}<input  type="hidden" name="scholar_money" value="${ search.scholar_money }"/></td>
			            <td>${search.tuition_money}<input  type="hidden" name="tuition_money" value="${ search.tuition_money }"/></td>
			            <td >${search.totalMoney }</td>
			            <td>
			           		<input  type="submit" value="수정"/>
			            </td>
			            <td>
			           		<input type="button" onclick="location.href='tDell?tuition_id=${bbs.tuition_id}'" value="삭제"/>
			            </td>
	       			</tr>
     			</form>
        	</c:forEach>
		</tbody>
    </table>
</body>
