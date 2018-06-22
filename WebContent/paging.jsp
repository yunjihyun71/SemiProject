<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
        .paging{
            padding: 10px 0px;
            text-align: center;
        }
        .paging a {
            display: inline-block;
            padding: 4px;
            width: 15px;
            color: black;
            font-size: 12px;
            font-weight: bold;
            border: thin solid lightgray;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
        }
        /* 글자(맨앞, 이전, 다음, 맨뒤) */
        .paging a.text {
            width: 30px;
        }
        /* 현재 페이지, 마우스 올렸을 때 */
        .paging a#curPage, .paging a:hover {  
            color: #fff;
            border: 1px solid orange;
            background-color: orange;
        }
    </style>
</head>
<body>
	<div class="paging"></div>
</body>
<script>
</script>
</html>