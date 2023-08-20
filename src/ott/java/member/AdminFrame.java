package ott.java.member;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminFrame extends JFrame{

	private JFrame frame;
	private JTable table;

	public AdminFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 540, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("관리자 계정 로그인 중입니다.");
		lblNewLabel.setBounds(262, 14, 160, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogOut = new JButton("로그 아웃");
		btnLogOut.setMargin(new Insets(2, 5, 2, 5));
		btnLogOut.setBounds(434, 10, 78, 23);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnTitleInsert = new JButton("작품 등록");
		btnTitleInsert.setBounds(12, 10, 120, 40);
		frame.getContentPane().add(btnTitleInsert);
		
		JButton btnSearchAll = new JButton("전체 검색");
		btnSearchAll.setBounds(12, 60, 120, 40);
		frame.getContentPane().add(btnSearchAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 110, 500, 241);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}

}
