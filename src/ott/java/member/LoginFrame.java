package ott.java.member;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame{

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtPw;
	private JButton btnLogin;
	
	private static MemberDAO dao;

	
	public LoginFrame() {
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(35, 35, 57, 15);
		getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(104, 32, 116, 21);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀 번호");
		lblNewLabel_1.setBounds(35, 70, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBounds(104, 67, 116, 21);
		getContentPane().add(txtPw);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginMember();
				
			}
		});
		
		btnLogin.setBounds(123, 98, 97, 23);
		getContentPane().add(btnLogin);
	}


	private void loginMember() {
		String id = txtId.getText();
		String pw = txtPw.getText();
		
		int result = dao.login(id, pw);//
		if(result == 1) {
			System.out.println("result = " + result + "로그인 완료");
		}
	} // end LoginFrame()

} // end LoginFrame
