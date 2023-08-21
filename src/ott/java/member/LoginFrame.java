package ott.java.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame{

//	private JFrame frame;
	private JTextField txtId;
	private JTextField txtPw;
	private JButton btnLogin;
	
	private MemberDAO dao;
	private MemberDTO dto;
	

	
	public LoginFrame() {
		
		
		dao = MemberDAOImple.getInstance();
		
		setTitle("로그인");
		setBounds(100, 100, 300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				FrontMain frontMain = new FrontMain();
				frontMain.mframe.setVisible(false);
			}
		});
		
		btnLogin.setBounds(123, 98, 97, 23);
		getContentPane().add(btnLogin);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				FrontMain frontMain = new FrontMain();
				frontMain.mframe.setVisible(true);
			}
		});
	}
	


	private int loginMember() {
		String id = txtId.getText();
		String pw = txtPw.getText();
		
		
		int result = dao.login(id, pw);
		if(result == 1) {
			System.out.println("result = " + result + " 로그인 완료");
			JOptionPane.showMessageDialog(btnLogin, id +" 님 " +"환영합니다!", "알림", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			OttFrame ottFrame = new OttFrame();
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
	} // end LoginFrame()

} // end LoginFrame
