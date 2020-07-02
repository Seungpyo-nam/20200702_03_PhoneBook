package codes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import codes.datas.User;

public class MainDrive {
	
	public static void main(String[] args) {
		
//		Git과 연동해서 전화번호부 만들 예정
		
		printMenu();
		
		
	}
	
	//메뉴출력할거 만들기
	public static void printMenu() {
		
//		메뉴1, 2, 0 번 > 0번을 누르면 프로그램 종료.
//		0번을 누를때까지 무한반복
		
		Scanner myScan = new Scanner(System.in);
		
		while(true) {
			
//			어떤 메뉴가 있는지 표기
			System.out.println("*******전화번호부*******");
			System.out.println("1) 전화번호 추가 등록");
			System.out.println("2) 전체 전화번호 목록 조회");
			System.out.println("0) 프로그램 종료");
			System.out.println("=====================");
			
//			실제로 메뉴 입력 받기
			System.out.print("메뉴 선택 : ");
			int inputMenu = myScan.nextInt();
			
//			입력값 확인
			if(inputMenu ==0) {
//				프로그램 종료시켜야함 > 무한반복 탈출!
				
				System.out.println("전화번호부를 종료합니다.");
				break;
				
			}else if (inputMenu ==1) {
				
//				전화번호 등록 기능 실행
				addPhoneNumInfoToFile();
				
			}else if( inputMenu == 2) {
				
//				전화번호 전체 조회 기능 실행
				readAllPhoneNum();
				
			}else {
				
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
			}
			
			
			
		}
		
		
		
		
		
	}
	
	//파일에 저장된 전화번호 목록 출력
	public static void readAllPhoneNum() {
//		파일에 저장된 데이터 > 자바 프로그램에서 활용. ( File INPUT)
//		FileReader / BufferedReader 활용.
		
//		불러올 파일의 위치 지정. > 저장할 때 사용하는 파일명과 동일하게.
		File file = new File("phoneBook.txt");
		

		try {
//			파일을 실제로 불러오는 클래스
			FileReader fr = new FileReader(file);
			
//			한줄씩 한꺼번에 불러오게 하는 클래스 > fr은 한글짜씩. fr을 보조해서 한문장 로드.
			BufferedReader br = new BufferedReader(fr);
			
//			모든 연락처를 불러올때까지 반복
			while (true) {
				
//				한 줄을 통째로 불러오기 > IOException 처리 필요
				String line = br.readLine();
				
//				불러온 내용을 검사. null 인지.
				if(line ==null ) {
					
//					더이상 불러올 내용이 없어서 null이 들어옴.
//					다 읽었다! 라는 뜻  > 무한반복 탈출.
					System.out.println("연락처를 모두 읽어왔습니다.");
					break;
				}
				
//				이 줄의 코드가 실행된다 : break를 안만남  > 불러온 내용이 null이 아님.
//				실제로 파일에 적혀있던 한줄이 line에 담겨있다.
				
//				System.out.println(line);
				
//				사용자 정보를 가공해서 출력.
//				ex ) 조경진(33세) : 010-5112-3237 양식
				
//				사용자의 이름과 폰번과 나이를 분리해서 변수로 저장하자.
//				string 클래스의 split 기능으로 정보항목들을 ","기준으로 분리.
				String[] userInfos = line.split(",");
				
//				이름/폰번/나이 저장
				String userName = userInfos[0];
				String userPhoneNum = userInfos[1];
//				나이 : 생년을 저장하고 계산 ? 생년 int
//				string을 int로 변환. > wrapper 클래서 > integer클래스 활용.
				int userBirthYear = Integer.parseInt(userInfos[2]);
				
//				이름/폰번/나이를 가지고 user객체로 만들자.
				User user = new User(userName, userPhoneNum, userBirthYear);
				
//				만들어낸 user 출력 > 유저 클래스의 toString 오버라이딩
//				양식으로 가공하자.
				System.out.println(user);
				
			}
			
//			while을 빠져나옴 : 파일을 다 읽었으므로 빠져나왔다.
//			파일 사용을 종료. br부터 닫고 fr 닫자
			
			br.close();
			fr.close();
			
			
		} catch (FileNotFoundException e) {
			
//			읽어올 타입이 없는 경우(삭제 or 아직 안만든 경우)
			System.out.println("불러올 파일이 없습니다.");
			System.out.println("연락처를 저장하고 다시 시도해주세요.");

		} catch (IOException e) {
			
			System.out.println("연락처를 읽어오는 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	//전화번호 + 이름 + 생년정보 추가 메소드
	public static void addPhoneNumInfoToFile() {
		
//		저장할 데이터를 입력받자
		Scanner myScan = new Scanner(System.in);
		
//		이름 폰번 생년 순으로 저장 > string string int 순서
		System.out.print("이름 입력 : ");
		String name = myScan.nextLine();
		
		System.out.print("전화번호 입력 : ");
		String phoneNum = myScan.nextLine();
		
		System.out.print("생년 입력 : ");
		int birthYear = myScan.nextInt();
		
		
//		변수에 저장한 데이터를 묶어서 파일로 저장
//		java > 보조기억장치 내보내기 > 파일출력 (save)
		
		
//		어느 파일을 이용할지 파일명 설정
		File phoneBookFile = new File("phoneBook.txt");
		
//		파일 save > 파일 쓰기
//		파일에 사용자 정보는 (추가)지정 > 기존 내용에서 이어붙이기. (true의 역할)
		
		try {
			
			FileWriter fw = new FileWriter(phoneBookFile, true);
//			fw는 개발자가 다루기 불편함. > 보조도구 : bw (buffered witer)
			
			BufferedWriter bw  = new BufferedWriter(fw);
			
//			실제로 bw를 이용해서 연락처 정보 저장
			
//			저장내용 : 3가지 정보를 한 줄로 묶어서
//			ex. 남승표,0103736125,1991 > 한줄짜리 string으로 저장.
			
			String infoStr = String.format("%s,%s,%d", name, phoneNum, birthYear);
			
//			묶인 한 줄을 파일에 기록
			bw.append(infoStr);
//			파일에 기록하고 나면, 줄이 바뀌지 않는다.
//			한줄에 한명씩만 저장 예정 > 줄을 바꿔주자
			bw.newLine();
			
			
//			작업 완료 > 열어둔 bw, fw를 닫아주자.
			bw.close();
			fw.close();
			
			System.out.println("연락처 저장이 끝났습니다.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
