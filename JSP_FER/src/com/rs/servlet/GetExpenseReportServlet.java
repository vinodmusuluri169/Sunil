package com.rs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.FERServiceImpl;
import com.rs.fer.util.HTMLutil;

public class GetExpenseReportServlet extends HttpServlet{
	
	FERService service = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new FERServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String expenseType = request.getParameter("expenseType");
		String fromDate = request.getParameter("FromDate");
		String toDate = request.getParameter("ToDate");
		
		
		HttpSession session = request.getSession();
		
		int userId = Integer.parseInt(session.getAttribute("userid").toString());
		
		List<Expense> expenses = service.expenseReport(userId,expenseType, fromDate, toDate);
		
		PrintWriter out = response.getWriter();
		
        HTMLutil.displayHeaderAndLeftFrame(request, response, out, session.getAttribute("username").toString());
		
		//if(expenses != null && !expenses.isEmpty()) {
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
			out.println("		<a href='javascript: subitForm(\"deleteExpense\")'>Delete</a></td>");
			out.println("</tr>");
			}
			out.println("</table>");
		//}
		
		HTMLutil.displayFooter(request, response);
	}
	
	@Override
	public void destroy() {
		service = null;
	}


}
