package com.ajh.s1.bankbook;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BankBookController {

	private BankBookDAO bankBookDAO;
	private BankBookDTO bankBookDTO;

	public BankBookController() {
		bankBookDAO = new BankBookDAO();
		bankBookDTO = new BankBookDTO();
	}

	public void start(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BankBookController 실행");

		String str = request.getRequestURI();

		StringTokenizer st = new StringTokenizer(str, "/");
		String[] arr = new String[st.countTokens()];
		int i = 0;

		while (st.hasMoreTokens()) {
			arr[i] = st.nextToken();
			i++;
		}

		if (arr[2].equals("bankbookList.do")) {
			System.out.println("상품목록");
			ArrayList<BankBookDTO> ar = bankBookDAO.getList();
//			for (int j = 0; j < ar.size(); j++) {
//				System.out.println(ar.get(j).getBookNumber());
//				System.out.println(ar.get(j).getBookName());
//				System.out.println(ar.get(j).getBookRate());
//				System.out.println(ar.get(j).getBookSale());
//
//			}
			for (BankBookDTO bankBookDTO : ar) {
				System.out.println(bankBookDTO.getBookNumber());
				System.out.println(bankBookDTO.getBookName());
				System.out.println(bankBookDTO.getBookRate());
				System.out.println(bankBookDTO.getBookSale());
				System.out.println("=================================");
			}

			request.setAttribute("list", ar);

			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/bankbook/bankbookList.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (arr[2].equals("bankbookInsert.do")) {
			System.out.println("상품등록");

			String method = request.getMethod();
			System.out.println("Method : " + method);

			if (method.equals("POST")) {
				String bookName = request.getParameter("bookName");
				String bookRate = request.getParameter("bookRate");
				String bookSale = request.getParameter("bookSale");

				BankBookDTO bankBookDTO = new BankBookDTO();
				bankBookDTO.setBookName(bookName);
				bankBookDTO.setBookRate(Double.parseDouble(bookRate));
				bankBookDTO.setBookSale(Integer.parseInt(bookSale));

				int result = bankBookDAO.setInsert(bankBookDTO);
//				ArrayList<BankBookDTO> ar = bankBookDAO.getList();
//				request.setAttribute("list", ar);
				
				try {
					response.sendRedirect("./bankbookList.do");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/bankbook/bankbookInsert.jsp");
				try {
					view.forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else if (arr[2].equals("bankbookSelect.do")) {
			System.out.println("상품상세조회");
			String num = request.getParameter("bookNumber");
			long num2 = Long.parseLong(num);

			bankBookDTO.setBookNumber(num2);
			bankBookDTO = bankBookDAO.getSelect(bankBookDTO);

			System.out.println(bankBookDTO.getBookNumber());
			System.out.println(bankBookDTO.getBookName());
			System.out.println(bankBookDTO.getBookRate());
			System.out.println(bankBookDTO.getBookSale());

			request.setAttribute("dto", bankBookDTO);
			request.setAttribute("count", 123);
			request.setAttribute("name", "iu");
			
			HttpSession session = request.getSession();
			session.setAttribute("se", "session");
			request.setAttribute("se", "request");

			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/bankbook/bankbookSelect.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("그 외 나머지");
		}
	}

}
