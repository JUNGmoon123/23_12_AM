package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String cmd;
	private Member loginedMember = null;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String cmd) {
		this.cmd = cmd;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			dologin();
			break;
		case "logout":
			dologout();
			break;
		default:
			System.out.println("명령어 확인해 (actionMethodName 오류)5");
			break;
		}
	}

	private void dologout() {
		if(loginedMember == null) {
			System.out.println("이미 로그아웃상태입니다.");
		}
		String loginid = null;
		String loginpw = null;
		System.out.println("==로그아웃==");
		System.out.println("아이디와 비번확인");
		System.out.print("아이디 : ");
		loginid = sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		loginpw = sc.nextLine().trim();

		Member find_Num = findMember(loginid);
		if(find_Num == null) {
			System.out.println("아이디를 다시 입력하세요.");
			return;
		}
		if (find_Num.getLoginPw().equals(loginpw) == true) {
			System.out.println("로그아웃 성공."+find_Num.getName()+"님");
		} else {
			System.out.println("비밀번호를 다시 입력하세요.");
			return;
		}
	}
	private boolean isLogined() {
		return loginedMember != null;
	}
	private void dologin() {
		
		if (isLogined()) {
			System.out.println("이미 로그인 상태야");
			return;
		}
		
		String loginid = null;
		String loginpw = null;
		System.out.println("==로그인==");
		System.out.print("아이디 : ");
		loginid = sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		loginpw = sc.nextLine().trim();

		Member find_Num = findMember(loginid);
		if(find_Num == null) {
			System.out.println("아이디를 다시 입력하세요.");
			return;
		}
		if (find_Num.getLoginPw().equals(loginpw)) {
			loginedMember = find_Num;
			System.out.println("로그인 성공."+find_Num.getName()+"님");
		} else {
			System.out.println("비밀번호를 다시 입력하세요.");
			return;
		}
		
	}

	private Member findMember(String loginid) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginid) == true) {
				return member;
			}
			
		}
		return null;
	}

	int lastMemberId = 3;

	private void doJoin() {
		System.out.println("==회원 가입==");
		int id = lastMemberId + 1;
		String regDate = Util.getNowDate_TimeStr();
		String loginId = null;
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중이야");
				continue;
			}
			break;
		}
		String loginPw = null;

		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.print("로그인 비밀번호 확인: ");
			String loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호 다시 확인해");
				continue;
			}
			break;
		}

		System.out.print("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입 되었습니다. %s님 환영합니다.\n", id, name);
		lastMemberId++;

	}

	private boolean isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return false;
			}
		}

		return true;
	}

	public void makeTestData() {
		System.out.println("member테스트 데이터 생성합니다.");
		members.add(new Member(1, Util.getNowDate_TimeStr(), "qwe", "qwe", "t1"));
		members.add(new Member(2, Util.getNowDate_TimeStr(), "eee", "eee", "t2"));
		members.add(new Member(3, Util.getNowDate_TimeStr(), "rrr", "rrr", "t3"));
	}

}