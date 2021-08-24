package com.ajh.s1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajh.s1.bankbook.BankBookController;
import com.ajh.s1.member.MemberController;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberController memberController;
	private BankBookController bankBookController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
		memberController = new MemberController();
		bankBookController = new BankBookController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Front Controller 실행");

		String str = request.getRequestURI();

//		StringTokenizer st = new StringTokenizer(str, "/");
//		String[] arr = new String[st.countTokens()];
//		int i = 0;
//
//		while (st.hasMoreTokens()) {
//			arr[i] = st.nextToken();
//			i++;
//		}
//		
//		String path = "";
//
//		if (arr[1].equals("member")) {
//			path = "member";
//		} else if (arr[1].equals("bankbook")) {
//			path = "bankbook";
//		}
//		
//		System.out.println(path);

		String path = "";
		int startIndex = request.getContextPath().length();
		int lastIndex = str.lastIndexOf("/");
		path = str.substring(startIndex, lastIndex);

		if (path.equals("/member")) {
			memberController.start(request);
		} else if (path.equals("/bankbook")) {
			bankBookController.start(request, response);
		} else {
			System.out.println("없는 URL");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
