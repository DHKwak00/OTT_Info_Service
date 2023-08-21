package ott.java.member;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OttFrame extends JFrame {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	private MemberDAO dao;
	private MemberDTO dto;
	

	public OttFrame() {
		
		dao = MemberDAOImple.getInstance();
		setTitle("OTT 정보 프로그램");
		setBounds(100, 100, 720, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		LoginFrame loginFrame = new LoginFrame();
//		dto = dao.getInfo(getName()); // LoginFrame에서 입력값이 넘어와야함.
//		System.out.println(dto);
		
		JLabel lblNewLabel = new JLabel(dto + " 님 환영합니다.");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel.setBounds(475, 10, 120, 23);
		getContentPane().add(lblNewLabel);
		
		JButton btnLogOut = new JButton("로그 아웃");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnLogOut, "로그아웃 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				FrontMain frontMain = new FrontMain();
				frontMain.mframe.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnLogOut.setBounds(607, 10, 85, 23);
		getContentPane().add(btnLogOut);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtSearch.setBounds(12, 115, 350, 35);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검 색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnSearch.setBounds(375, 115, 85, 35);
		getContentPane().add(btnSearch);
		
		JButton btnSearchAll = new JButton("전체 검색");
		btnSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchAll.setMargin(new Insets(2, 7, 2, 7));
		btnSearchAll.setFont(new Font("굴림", Font.PLAIN, 12));
		btnSearchAll.setBounds(475, 115, 85, 35);
		getContentPane().add(btnSearchAll);
		
		JRadioButton rdbtnName = new JRadioButton("제목순");
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rdbtnName.setFont(new Font("굴림", Font.PLAIN, 12));
		rdbtnName.setBounds(560, 120, 62, 23);
		getContentPane().add(rdbtnName);
		
		JRadioButton rdbtnLike = new JRadioButton("좋아요순");
		rdbtnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rdbtnLike.setFont(new Font("굴림", Font.PLAIN, 12));
		rdbtnLike.setBounds(620, 120, 73, 23);
		getContentPane().add(rdbtnLike);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 160, 680, 270);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Gulim", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
	}
//	public void setDto(MemberDTO dto) {
//		this.dto = dto;
//	}
} // end OttFrame
