
public class Friend {
	//������ �׸��� ��Ÿ���� String ���� 
	private String name;
	private String group;
	private String pnum;
	private String mail;
	private String photo;
	//������ ����
	public Friend() {
		
	}
	public Friend(String name,String group,String pnum,String mail,String photo) {
		this.setName(name);
		this.setGroup(group);
		this.setPnum(pnum);
		this.setMail(mail);
		this.setPhoto(photo);
	}
	
	public void print () // Friend ��ü �ϳ��� ������ ���
	   {
		if(this.photo==null) //������ ������, ������ ����
	      System.out.println("Name: "+ this.name+ " Group: " + this.group+ " PhoneNumber: "+this.pnum+ " Mail: "+ this.mail);
		else {
			System.out.println("Name: "+ this.name+ " Group: " + this.group+ " PhoneNumber: "+this.pnum+ 
					" Mail: "+ this.mail+" Photo: "+ this.photo);
		}
	   }
	
	//5�� �׸�鿡 ���� getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group=group;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum=pnum;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail=mail;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo=photo;
	}
	
}


