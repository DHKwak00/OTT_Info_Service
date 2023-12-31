package ott.java.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame{

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtPw;
	private JButton btnLogin;
	
	private MemberDAO dao;
	private MemberDTO dto;
	
	

	public LoginFrame() {
		dao = MemberDAOImple.getInstance();
		frame = this;
		frame.setTitle("로그인");
		frame.setBounds(100, 100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
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
		
		txtPw = new JPasswordField();
		txtPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inId = txtId.getText();
				loginMember(inId);
			}
		});
		txtPw.setColumns(10);
		txtPw.setBounds(104, 67, 116, 21);
		getContentPane().add(txtPw);
		
		
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inId = txtId.getText();
				loginMember(inId);
				
			}
		});
		
		btnLogin.setBounds(123, 98, 97, 23);
		getContentPane().add(btnLogin);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FrontMain frontMain = new FrontMain();
				frontMain.mframe.setVisible(true);
			}
		});
		
	} // end LoginFrame()
	
	private int loginMember(String inId) {
		String id = txtId.getText();
		String pw = txtPw.getText();
		
		int result = dao.login(id, pw);
		if(result == 1 && id.equals("admin")) {
				System.out.println("result = " + result + " 로그인 완료");
				JOptionPane.showMessageDialog(btnLogin, "관리자 계정으로 로그인되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				AdminFrame adminFrame = new AdminFrame(inId);
				adminFrame.setVisible(true);
				dto = dao.getInfo(id);
				System.out.println("dto값: " + dto);
				
				return 1;
				
			}else if(result == 1) {
				System.out.println("result = " + result + " 로그인 완료");
				JOptionPane.showMessageDialog(btnLogin, id +" 님 " +"환영합니다!", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				OttFrame ottFrame = new OttFrame(id);
				ottFrame.setVisible(true);
				dto = dao.getInfo(id);
				System.out.println("dto값: " + dto);
				
				return 1;
				
			}else if(result == 0) {
				System.out.println("result = " + result + " 비밀번호 불일치");
				JOptionPane.showMessageDialog(btnLogin, "비밀번호가 다릅니다.", "알림", JOptionPane.ERROR_MESSAGE);
				return 0;
				
			}else {
				System.out.println("result = " + result + " 아이디 없음");
				JOptionPane.showMessageDialog(btnLogin, "알맞은 아이디가 아닙니다.", "알림", JOptionPane.ERROR_MESSAGE);
				return 0;
		}
		
		
		
	} // end loginMember()
	
} // end LoginFrame
