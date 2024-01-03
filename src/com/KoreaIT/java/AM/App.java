package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 == ");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		Controller controller = null;

		articleController.makeTestData();
		memberController.makeTestData();
		
		while (true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요1");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			String[] cmdBits = cmd.split(" ");

			String controllerName = cmdBits[0];

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해줘2");
				continue;
			}

			String actionMethodName = cmdBits[1];

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("사용할 수 없는 명령어입니다3");
				continue;
			}
			//controller는 controllerName에 어떤 단어가 들어오냐에 따라
			//article또는 member클래스를 실행한다.
			//추상클래스로 만들어진 Controller를 article과 member가 상속해서
			//2개 클래스가 오버라이딩을 하기때문에 app의 메소드에서 선택해서
			//실행할 수 있다. cmd는 문자열 그대로 전해주기 위해서 전달한다.
			controller.doAction(actionMethodName, cmd);
		}

		System.out.println("== 프로그램 끝 == ");

		sc.close();

	}

}