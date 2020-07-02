package codes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

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
			System.out.println("메뉴 선택 : ");
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
				
//				전화번호 전체 조회기능
				
			}else {
				
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
			}
			
			
			
		}
		
		
		
		
		
	}
	
	//전화번호 + 이름 + 생년정보 추가 메소드
	public static void addPhoneNumInfoToFile() {
		
//		저장할 데이터를 입력받자
		Scanner myScan = new Scanner(System.in);
		
//		이름 폰번 생년 순으로 저장 > string string int 순서
		System.out.println("이름 입력 : ");
		String name = myScan.nextLine();
		
		System.out.println("전화번호 입력 : ");
		String phoneNum = myScan.nextLine();
		
		System.out.println("생년 입력 : ");
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
