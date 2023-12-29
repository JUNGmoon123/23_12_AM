import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 == ");

		Scanner sc = new Scanner(System.in);
		Calendar now = Calendar.getInstance();
		
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();
		
		while (true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.equals("article write")) {
				String article_Date = " ";
				System.out.println("==게시글 작성==");
				int id = lastArticleId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				article_Date = String.format(" %tF %tT", now.getTime(), now.getTime());
				System.out.println(article_Date);
				Article article = new Article(id, title, body, article_Date);
				articles.add(article);
//				System.out.println(title + " / " + body);
				
				System.out.printf("%d번 글이 생성 되었습니다.\n", id);
				lastArticleId++;
			} else if (cmd.equals("article list")) {
				System.out.println("==게시글 목록==");
				if (articles.size() == 0) {
					System.out.println("아무것도 없어");
				} else {
					System.out.println("  번호  /  제목  ");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("  %4d  /   %s  \n", article.getId(), article.getTitle());
					}
				}
				// 
			} else if (cmd.startsWith("article detail")) {
				
				String[] cmdDiv = cmd.split(" ");
//				System.out.println(cmdDiv[0]);
//				System.out.println(cmdDiv[1]);
//				System.out.println(cmdDiv[2]);

				int id = 0;

				// article detail 1 => "1" => 1
				// 숫자대신 abc등 이상한거 올경우 대비
				// trim때문에 공백이 없는상황 예외처리
				//System.out.println(id);
				try {
					id = Integer.parseInt(cmdDiv[2]);
				} catch (Exception e) {
					System.out.println("번호는 정수로 입력해");
					continue;
				}
				
				//flag
				boolean found = false;
				//System.out.println(id);
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.getId() == id) {
						found = true;
						break;
					}
				}
				System.out.println(id);
				if (found == false) {
					System.out.printf("%d번 게시글은 없습니다\n", id);
				} else {
					Article rs = articles.get(id-1);
					//System.out.println("너 찾는거 있더라");
					System.out.println("번호 : " + rs.getId());
					System.out.println("날짜 : " + rs.getDate());
					System.out.println("제목 : " + rs.getTitle());
					System.out.println("내용 : " + rs.getBody());
				}
			} else {
				System.out.println("사용할 수 없는 명령어입니다");
			}
		}

		System.out.println("== 프로그램 끝 == ");

		sc.close();
	}
}

class Article {
	private int id;
	private String title;
	private String body;
	private String date;
	
	public Article(int id, String title, String body, String date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	
	public void setdate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}