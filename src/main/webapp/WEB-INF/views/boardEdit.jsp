<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<form action="" method="post">
			사원 번호 : ${board.empno} <br>
			<input type="hidden" value="${board.empno}">
			직급 : <input name="job" value="${board.job}" /> <br>
			
			<input type="submit" value="직급 수정">
	</form>
