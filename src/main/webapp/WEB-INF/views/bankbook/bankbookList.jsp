<%@page import="java.util.ArrayList"%>
<%@page import="com.ajh.s1.bankbook.BankBookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BankBook List Page</h1>
	<%
	Object obj = request.getAttribute("list");
	ArrayList<BankBookDTO> ar = (ArrayList<BankBookDTO>) obj;
	for (BankBookDTO dto : ar) {
	%>
	
	<h2>Name : <%=dto.getBookName() %></h2>
	<h2>Rate : <%=dto.getBookRate() %></h2>
	
	<%
	}
	%>
</body>
</html>