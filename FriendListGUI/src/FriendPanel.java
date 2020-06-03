import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FriendPanel extends JPanel{
	public FriendPanel(String name, String group, String phone, String mail, String photo) {
		this.setLayout(new GridLayout(1,5));
		JLabel nameL = new JLabel("name");
		JTextField groupF = new JTextField("group");
		JTextField phoneF = new JTextField("phone");
		JTextField mailF = new JTextField("mail");
		JTextField photoF = new JTextField("photo");
		
	}
}
