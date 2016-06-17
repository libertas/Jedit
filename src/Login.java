import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login {
	public Login() {
		JFrame app = new JFrame("JEdit");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ĭ�ϵĹرղ���
		app.setSize(400, 200);
		
		Container c = app.getContentPane(); // ȡ���������
		c.setLayout(new FlowLayout());//���񲼾�  һ���һ��??????
		
		c.add(new JLabel("Please login"));
		

		JTextField tf1 = new JTextField(35);
		JTextField tf2 = new JTextField(35);
		c.add(tf1);
		c.add(tf2);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = tf1.getText();
				String password = tf2.getText();
				
				if(username.compareTo("Jedit") == 0
						&& password.compareTo("123456") == 0) {
					app.setVisible(false);
					new Editor();
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong username or password",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		c.add(button);
		
		app.setVisible(true);//�ɼ�
	}
}
