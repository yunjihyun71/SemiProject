<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	var loginId = "${sessionScope.loginId}";
	if (loginId == "") {
		alert("로그인이 필요합니다.");
		location.href = "index.jsp";
	} else if (loginId.charAt(0) == 'p') {
		alert("권한이 없습니다.");
		location.href = "pmain";
	} else if (loginId.charAt(0) == 'a') {
		alert("권한이 없습니다.");
		location.href = "amain";
	}
</script>