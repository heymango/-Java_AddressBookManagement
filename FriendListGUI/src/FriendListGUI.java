import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FriendListGUI extends JFrame {
	FriendList friendList = new FriendList();
	private JButton button1,button2,button3,button4;
	String[] lavel = {"이름","그룹","전화번호","Email","사진"};
	public FriendListGUI(String title) {
		JFrame FriendListFrame = new JFrame(title);
		FriendListFrame.setSize(500,1000);
		FriendListFrame.setVisible(true);
		JPanel FriendlistPanel = new JPanel();
		
		FriendlistPanel.setLayout(new FlowLayout());
		for(int i=0; i<friendList.numFriends(); i++) {
			Friend friend = new Friend();
			friend = friendList.getFriend(i);
			FriendlistPanel.add(new FriendPanel(friend.getName(),friend.getGroup(),friend.getPnum(),friend.getMail(),friend.getPhoto()));
		}
		
		JPanel rButton = new JPanel();
		rButton.setLayout(new GridLayout(4,1,2,1));
		button1 = new JButton("Add");
		button2 = new JButton("Delete");
		button3 = new JButton("Modify");
		button4 = new JButton("SaveFile");
		
		
	}
}

