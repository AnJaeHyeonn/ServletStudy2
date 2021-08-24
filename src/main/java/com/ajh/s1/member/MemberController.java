package com.ajh.s1.member;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

public class MemberController {

	public void start(HttpServletRequest request) {
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
			System.out.println("회원가입 진행");
		} else if (arr[2].equals("memberPage.do")) {
			System.out.println("마이페이지 진행");
		} else {
			System.out.println("그 외 나머지");
		}
	}

}
