package ott.java.member;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrontMain {

	public JFrame mframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontMain window = new FrontMain();
					window.mframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrontMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mframe = new JFrame();
		mframe.setTitle("OTT 정보 공유 서비스");
		mframe.setBounds(100, 100, 450, 300);
		mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mframe.getContentPane().setLayout(null);
		
		
		
		
		JLabel lblNewLabel = new JLabel("OTT 정보 공유 서비스");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 434, 50);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 42));
		mframe.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("로 그 인");
		btnLogin.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				mframe.setVisible(false);
				
			}
		});
		btnLogin.setBounds(155, 82, 120, 50);
		mframe.getContentPane().add(btnLogin);

		JButton btnSignUp = new JButton("회원 가입");
		btnSignUp.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertMemberFrame insertFrame = new InsertMemberFrame();
				insertFrame.setVisible(true);
			}
		});
		btnSignUp.setBounds(155, 142, 120, 50);
		mframe.getContentPane().add(btnSignUp);

		JButton btnAdmin = new JButton("관리자 로그인");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				mframe.setVisible(false);
			}
		});
		btnAdmin.setMargin(new Insets(2, 5, 2, 5));
		btnAdmin.setFont(new Font("굴림", Font.PLAIN, 11));
		btnAdmin.setBounds(330, 228, 92, 23);
		mframe.getContentPane().add(btnAdmin);
		
	} // end initialize()

}
