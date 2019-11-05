<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="java.util.List"%>
<%@page import="com.rs.fer.service.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>
<%


FERService service= new FERServiceImpl();


	String expenseType = request.getParameter("expenseType");
	String fromDate = request.getParameter("FromDate");
	String toDate = request.getParameter("ToDate");
		
	int userId = Integer.parseInt(session.getAttribute("userid").toString());
	
	List<Expense> expenses = service.expenseReport(userId,expenseType, fromDate, toDate);
	
	%>
	
	<jsp:include page="Layout/HeaderNLeftFrame.jsp"/>
	
<%
	if(expenses != null && !expenses.isEmpty()) {
		out.println("<table border='1px' align='center' cellSpacing='0' cellPadding='2px'>");
		out.println("<tr>");
		out.println("	<th>Expense Type</th>");
		out.println("	<th>Date</th>");
		out.println("	<th>Price</th>");
		out.println("	<th>Number of items</th>");
		out.println("	<th>By Whom</th>");
		out.println("	<th>Action</th>");
		out.println("</tr>");
		for(Expense expense : expenses) {
		out.println("<tr>");
		out.println("	<td><input type='text' name='expenseType' value='"+expense.getType()+"'></td>");
		out.println("	<td><input type='text' name='date' value='"+expense.getDate()+"'></td>");
		out.println("	<td><input type='text' name='price' value='"+expense.getPrice()+"'></td>");
		out.println("	<td><input type='text' name='numberOfItems' value='"+expense.getNoOfItems()+"'></td>");
		out.println("	<td><input type='text' name='byWhom' value='"+expense.getByWhom()+"'></td>");
		out.println("	<td><a href='javascript: submitForm(\"displayEditExpense\")'>Edit</a>");
		out.println("	<td><a href='javascript: subitForm(\"deleteExpense\")'>Delete</a></td>");
		out.println("</tr>");
		}
		out.println("</table>");
	}

%>

<jsp:include page="Layout/Footer.jsp"/>