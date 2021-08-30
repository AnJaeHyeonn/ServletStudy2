<%@page import="java.util.ArrayList"%>
<%@page import="com.ajh.s1.bankbook.BankBookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="x" uri ="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href = "../css/list.css" rel="stylesheet">
</head>
<body>
	
	<h1 id="title">BankBook List Page BY JSTL</h1>
	<table class = "tbl">
	<tr>
		<th class = "tbl_tr">일련번호</th> <th class = "tbl_tr">제품명</th> <th class = "tbl_tr">이자율</th>	<th class = "tbl_tr">Status</th>	
	</tr>
	
	<c:forEach items="${list}" var="dto" varStatus="i">
	<tr>
		<td class = "tbl_tr">${dto.bookNumber}</td>
		<td class = "tbl_tr"><a href="bankbookSelect.do?bookNumber=${dto.bookNumber}">${dto.bookName}</td>		
		<td class = "tbl_tr">${dto.bookRate}</td>
		
		<td class = "tbl_tr">
			<p>현재 아이템 : ${i.current}</p>
			<p>인덱스 번호 : ${i.index}</p>
			<p>1부터 순서 번호 : ${i.count}</p>
			<p>처음 ? : ${i.first}</p>
			<p>마지막 ? : ${i.last}</p>
		</td>
	</tr>
	</c:forEach>
	</table>
	
	<div> 
		<c:forEach begin="1" end = "10" step="1" var = "num" varStatus="i">
			<button>${num} : ${i.begin} : ${i.end} : ${i.step }</button>		
		</c:forEach>
	</div>
	
	<a href="./bankbookInsert.do">Write</a>
	
</body>
</html>