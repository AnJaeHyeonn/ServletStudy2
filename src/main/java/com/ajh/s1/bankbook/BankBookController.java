package com.ajh.s1.bankbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/bankbook/bankbookList.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (arr[2].equals("bankbookInsert.do")) {
			System.out.println("상품등록");
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
