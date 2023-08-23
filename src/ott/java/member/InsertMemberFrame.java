package ott.java.member;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dialog;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class InsertMemberFrame extends JFrame{

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtPw;
	private JTextField txtPhone;
	private JTextField txtEmail;
	
	private MemberDAO dao;
	private MemberDTO dto;

	
	public InsertMemberFrame() {
		dao = MemberDAOImple.getInstance();
		frame = this;
		frame.setTitle("회원가입");
		frame.setBounds(100, 100, 300, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 30, 60, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 60, 60, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("연락처");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(12, 90, 60, 20);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("이메일");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(12, 120, 60, 20);
		getContentPane().add(lblNewLabel_3);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtId.setBounds(84, 30, 116, 21);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBounds(84, 60, 116, 21);
		frame.getContentPane().add(txtPw);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(84, 90, 116, 21);
		frame.getContentPane().add(txtPhone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(84, 120, 116, 21);
		frame.getContentPane().add(txtEmail);
		
		JButton btnInsert = new JButton("가 입");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertMember();
			}
		});
		btnInsert.setFont(new Font("굴림", Font.PLAIN, 14));
		btnInsert.setBounds(175, 150, 97, 23);
		getContentPane().add(btnInsert);
		
		JButton btnNewButton = new JButton("중복 확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idCheck();
			}
		});
		btnNewButton.setMargin(new Insets(2, 7, 2, 7));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 11));
		btnNewButton.setBounds(201, 29, 71, 23);
		getContentPane().add(btnNewButton);
		
	}
	
	private void insertMember() {
		String id = txtId.getText();
		String pw = txtPw.getText();
		String phone = txtPhone.getText();
		String email = txtEmail.getText();
		
		MemberDTO dto = new MemberDTO(0, id, pw, phone, email);
		int result = dao.insert(dto);
		if(result == 1) {
			System.out.println("insertMember() : " + result);
			JOptionPane.showMessageDialog(frame, "가입 성공", "알림", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else {
			System.out.println("가입 정보 부족");
			JOptionPane.showMessageDialog(frame, "가입 정보를 정확히 입력해 주세요.", "알림", JOptionPane.ERROR_MESSAGE);
		}
		
	}// end insertMember()
	
	private void idCheck() {
		String id = txtId.getText();
		dto = dao.getInfo(id);
		if(dto == null) {
			JOptionPane.showMessageDialog(frame, "가입 가능한 아이디입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}else if(id.equals(dto.getMemId())){
			JOptionPane.showMessageDialog(frame, "중복된 아이디입니다.", "알림", JOptionPane.WARNING_MESSAGE);
		}
	}// end idCheck()
}
