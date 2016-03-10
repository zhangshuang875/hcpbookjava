package com.shuang.javawebprojtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class REadingListServlet
 */
@WebServlet("/REadingListServlet")
public class REadingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public REadingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Retrieve the current list from the user's session
		List<String> readingList = null;
		HttpSession session = request.getSession();
		Object o = session.getAttribute("readingList");
		if(o==null)
		{
			readingList = new ArrayList<String>();
			session.setAttribute("readingList", readingList);
		}
		else
		{
			readingList = (List<String>) o;
		}
		
		//add the selected item to the list
		String book = request.getParameter("book");
		if(book != null)
			readingList.add(book);
		//also keep track of the number of items in the list
		session.setAttribute("itemCount", readingList.size());
		//reroute the user back to the main reading list form
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
		//doGet(request, response);
	}

}
