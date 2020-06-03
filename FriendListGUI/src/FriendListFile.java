import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FriendListFile {
	private FriendList friendlist;
	
	public FriendList readFileToList(String fileName) {//file을 읽어 친구를 저장한 friend list를 리턴해주는 메소드
		Load(fileName);
		//fileName에 맞는 file을 호출 Load 메소드는 파일 호출, 라인을 읽고 저장하는 과정(parse메소드)까지 이어진다
		return friendlist; //friendlist 리턴
	}


 	public void Load(String fileName) { //file 로드 함수
 		friendlist = new FriendList(); 
		String line = null;  //file의 한 줄을 읽어와 저장할 line string
		try {
			// 파일 객체 생성
			File file = new File(fileName);
			// 스캐너로 파일 읽기
			Scanner scan = new Scanner(file);
			int numF = 0; //friendarray에 Fried를 순서대로 저장하기 위한 인덱스 변수

			while (scan.hasNextLine()) { //파일의 끝에 도달할때까지
				line = scan.nextLine(); //line에 한 줄 불러오기
				if(parse(line,numF)) { //parse 함수가 true를 리턴(올바른 저장 완료) 했을때 인덱스 증가
					numF++;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Unknown File"); //fileName에 맞는 file이 없을때 오류출력
			System.exit(1);
		}

	}
 
//20185947 장소정
	public boolean parse(String line, int numF) { //line을 검사하고 완료하면  friendlist의 numF 위치에 저장
		if (!line.equals("")) {  //line이 빈줄이 아닐 때
			if(line.charAt(0) == '/') return false; //주석은 무시함
			int count=0;
			for (int i = 0; i < line.length(); i++) {  // :문자 개수 count
				if(line.charAt(i)==':') count++; 
			}
			
			if (count!=4 ) { //: 문자 개수가 4개가 아니면 잘못된 입력
				System.out.println("Irregular input line");
				return false;
			}
			
			if(count==4) { //문자 개수 : 일때 
			line=line.replaceAll(" ","");  //모든 공백 제거
			line=line.replaceAll("\t","");
			String arr[] = line.split(":"); //line 을 : 문자 기준으로 split
			for (int i = 0; i < arr.length; i++) {
				if(arr[i].matches("")) { //split 한 뒤 유효하지 않은 값이 있다면, 올바르지 않은 항목이 존재하는것
					System.out.println("Irregular input line");
					return false;
				}
			}

			if(arr.length <4) { //항목의 수가 4개 이하이면
				System.out.println("Irregular input line");
				return false;
			}
			
			else if (arr.length ==4||arr.length ==5) { //항목의 수가 4개 또는 5개일때
				int stringNum = -1;
				while (stringNum < 4) { 
					stringNum++;
					switch (stringNum) { //항목의 순서대로 형식검사하는 switch문
					case 0:
						if (!arr[stringNum].matches("[A-Z a-z]*")) { //name검사
							System.out.println("Name form error");
							arr[stringNum]=null;
							
						}
						else if(isConflict(arr,numF,stringNum)){ //이름 충돌 검사하는 메소드
							System.out.println("Name Conflict");
							return false;
						}break;
						
					case 1:
						if (!(arr[stringNum].matches("[0-9]*"))) { //group 검사
							System.out.println("Illegal value");
							arr[stringNum]=null;
						}break;
						
					case 2:
						if (!arr[stringNum].matches("[0-9]*[-][0-9]*[-][0-9]*")) {//phonenumber 검사
							System.out.println("Illegal Phonenumber");
							arr[stringNum]=null;
							
						}break;
					case 3:
						if (!arr[stringNum].matches("[a-zA-Z0-9]*[@][a-z]*.[a-z]*")){ //mail 검사
							System.out.println("Illegal email address");
							arr[stringNum]=null;
						}break;
					} //switch 종료

				}//while문 종료
				if(arr.length==5) { //항목이 5개일때, 사진존재할때
					stringNum=5; 
					if (!arr[4].matches("[a-zA-Z0-9]*.[a-z]*")){ //photo 검사
						System.out.println("Illegal photo format");
						arr[4]=null;
					}
				}
				
				if(!friendRight(arr,stringNum)) return false;  //null 하나라도 있다면 잘못된 항목이 있는거임. 실패
				
				else{ //오류가 없다면 friend에 저장  
					if(arr.length==4) { //사진 없는 경우
						Friend friend = new Friend(arr[0],arr[1],arr[2],arr[3],null); 
						friendlist.setFriendArray(numF,friend); //friendarray의 numF 인덱스에 저장
					}
					else { //사진 있는 경우
						Friend friend = new Friend(arr[0],arr[1],arr[2],arr[3],arr[4]); 
						friendlist.setFriendArray(numF,friend);
					}
					
					return true; //저장 후 성공
				}	
			}//멤버 검사 종료
		 }//올바른 line 검사 종료
			
	  }
		return false; //line이 빈 줄일때 
	}
	
	
	
	
	public boolean friendRight(String arr[],int stringNum) {//항목중에 null이 있는가 검사(형식 오류 항목 존재 여부)
		for(int i=0; i<stringNum; i++) {
			if(arr[i]==null) return false;
		}
		return true;
	}
	public boolean isConflict(String arr[], int numF,int stringNum) { //이름 충돌 검사 
		if(numF>0) {
		for(int i=0; i<numF; i++) {
			if(arr[stringNum].matches(friendlist.getFriend(i).getName()))) return true;
		}
		}return false;
	}
}
