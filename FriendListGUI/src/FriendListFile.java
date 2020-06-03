import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FriendListFile {
	private FriendList friendlist;
	
	public FriendList readFileToList(String fileName) {//file�� �о� ģ���� ������ friend list�� �������ִ� �޼ҵ�
		Load(fileName);
		//fileName�� �´� file�� ȣ�� Load �޼ҵ�� ���� ȣ��, ������ �а� �����ϴ� ����(parse�޼ҵ�)���� �̾�����
		return friendlist; //friendlist ����
	}


 	public void Load(String fileName) { //file �ε� �Լ�
 		friendlist = new FriendList(); 
		String line = null;  //file�� �� ���� �о�� ������ line string
		try {
			// ���� ��ü ����
			File file = new File(fileName);
			// ��ĳ�ʷ� ���� �б�
			Scanner scan = new Scanner(file);
			int numF = 0; //friendarray�� Fried�� ������� �����ϱ� ���� �ε��� ����

			while (scan.hasNextLine()) { //������ ���� �����Ҷ�����
				line = scan.nextLine(); //line�� �� �� �ҷ�����
				if(parse(line,numF)) { //parse �Լ��� true�� ����(�ùٸ� ���� �Ϸ�) ������ �ε��� ����
					numF++;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Unknown File"); //fileName�� �´� file�� ������ �������
			System.exit(1);
		}

	}
 
//20185947 �����
	public boolean parse(String line, int numF) { //line�� �˻��ϰ� �Ϸ��ϸ�  friendlist�� numF ��ġ�� ����
		if (!line.equals("")) {  //line�� ������ �ƴ� ��
			if(line.charAt(0) == '/') return false; //�ּ��� ������
			int count=0;
			for (int i = 0; i < line.length(); i++) {  // :���� ���� count
				if(line.charAt(i)==':') count++; 
			}
			
			if (count!=4 ) { //: ���� ������ 4���� �ƴϸ� �߸��� �Է�
				System.out.println("Irregular input line");
				return false;
			}
			
			if(count==4) { //���� ���� : �϶� 
			line=line.replaceAll(" ","");  //��� ���� ����
			line=line.replaceAll("\t","");
			String arr[] = line.split(":"); //line �� : ���� �������� split
			for (int i = 0; i < arr.length; i++) {
				if(arr[i].matches("")) { //split �� �� ��ȿ���� ���� ���� �ִٸ�, �ùٸ��� ���� �׸��� �����ϴ°�
					System.out.println("Irregular input line");
					return false;
				}
			}

			if(arr.length <4) { //�׸��� ���� 4�� �����̸�
				System.out.println("Irregular input line");
				return false;
			}
			
			else if (arr.length ==4||arr.length ==5) { //�׸��� ���� 4�� �Ǵ� 5���϶�
				int stringNum = -1;
				while (stringNum < 4) { 
					stringNum++;
					switch (stringNum) { //�׸��� ������� ���İ˻��ϴ� switch��
					case 0:
						if (!arr[stringNum].matches("[A-Z a-z]*")) { //name�˻�
							System.out.println("Name form error");
							arr[stringNum]=null;
							
						}
						else if(isConflict(arr,numF,stringNum)){ //�̸� �浹 �˻��ϴ� �޼ҵ�
							System.out.println("Name Conflict");
							return false;
						}break;
						
					case 1:
						if (!(arr[stringNum].matches("[0-9]*"))) { //group �˻�
							System.out.println("Illegal value");
							arr[stringNum]=null;
						}break;
						
					case 2:
						if (!arr[stringNum].matches("[0-9]*[-][0-9]*[-][0-9]*")) {//phonenumber �˻�
							System.out.println("Illegal Phonenumber");
							arr[stringNum]=null;
							
						}break;
					case 3:
						if (!arr[stringNum].matches("[a-zA-Z0-9]*[@][a-z]*.[a-z]*")){ //mail �˻�
							System.out.println("Illegal email address");
							arr[stringNum]=null;
						}break;
					} //switch ����

				}//while�� ����
				if(arr.length==5) { //�׸��� 5���϶�, ���������Ҷ�
					stringNum=5; 
					if (!arr[4].matches("[a-zA-Z0-9]*.[a-z]*")){ //photo �˻�
						System.out.println("Illegal photo format");
						arr[4]=null;
					}
				}
				
				if(!friendRight(arr,stringNum)) return false;  //null �ϳ��� �ִٸ� �߸��� �׸��� �ִ°���. ����
				
				else{ //������ ���ٸ� friend�� ����  
					if(arr.length==4) { //���� ���� ���
						Friend friend = new Friend(arr[0],arr[1],arr[2],arr[3],null); 
						friendlist.setFriendArray(numF,friend); //friendarray�� numF �ε����� ����
					}
					else { //���� �ִ� ���
						Friend friend = new Friend(arr[0],arr[1],arr[2],arr[3],arr[4]); 
						friendlist.setFriendArray(numF,friend);
					}
					
					return true; //���� �� ����
				}	
			}//��� �˻� ����
		 }//�ùٸ� line �˻� ����
			
	  }
		return false; //line�� �� ���϶� 
	}
	
	
	
	
	public boolean friendRight(String arr[],int stringNum) {//�׸��߿� null�� �ִ°� �˻�(���� ���� �׸� ���� ����)
		for(int i=0; i<stringNum; i++) {
			if(arr[i]==null) return false;
		}
		return true;
	}
	public boolean isConflict(String arr[], int numF,int stringNum) { //�̸� �浹 �˻� 
		if(numF>0) {
		for(int i=0; i<numF; i++) {
			if(arr[stringNum].matches(friendlist.getFriend(i).getName()))) return true;
		}
		}return false;
	}
}
