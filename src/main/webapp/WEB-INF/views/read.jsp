<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function board_update(){
	//alert("수정");
	location.href="empEdit.htm?empno=${b.empno}&pg=${pg}";
}
function board_delete(){
	//alert("삭제");
	location.href="deleteform.htm?empno=${b.empno}";
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 정보</title>
</head>
<body>
<h1>사원 정보</h1>

<a href="list.htm?pg=${pg}"> 사원 목록 </a>
<table width="500">
	<tr>
		<td width="20%">${b.empno}</td>
		<td width="20%">${b.ename}</td>
		<td width="20%">${b.job}</td>
		<td width="20%">${b.sal}</td>
		<td width="20%">${b.deptno}</td>
	</tr>
	<tr>
		<td colspan="4" align="right">
			<input type="button" value="수정" onclick="board_update()"/>
			<input type="button" value="삭제" onclick="board_delete()"/>
		</td>
	</tr>
	
</table>



</body>
</html>