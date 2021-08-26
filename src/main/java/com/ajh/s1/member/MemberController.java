package com.ajh.s1.member;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController {

	private MemberDTO memberDTO;
	private MemberDAO memberDAO;
	private MemberService memberService;

	public MemberController() {
		memberDTO = new MemberDTO();
		memberDAO = new MemberDAO();
		memberService = new MemberService();
	}

	public void start(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberController 실행");

		String str = request.getRequestURI();

		StringTokenizer st = new StringTokenizer(str, "/");
		String[] arr = new String[st.countTokens()];
		int i = 0;

		while (st.hasMoreTokens()) {
			arr[i] = st.nextToken();
			i++;
		}

		if (arr[2].equals("memberLogin.do")) {
			System.out.println("로그인 진행");
			String id = request.getParameter("id");
			System.out.println(id);

			String pw = request.getParameter("pw");
			System.out.println(pw);

		} else if (arr[2].equals("memberJoin.do")) {

			String method = request.getMethod();

			if (method.equals("POST")) {
				int result = memberService.memberJoin(request, response);
				if (result > 0) {
					response.sendRedirect("../");
				} else {
					response.sendRedirect("./memberJoin.do");
				}

			} else {
				RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/member/memberJoin.jsp");
				view.forward(request, response);

			}

		} else if (arr[2].equals("memberPage.do")) {
			System.out.println("마이페이지 진행");
		} else {
			System.out.println("그 외 나머지");
		}
	}

}
