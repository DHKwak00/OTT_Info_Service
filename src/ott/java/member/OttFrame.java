package ott.java.member;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OttFrame {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					OttFrame window = new OttFrame();
					window.frame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OttFrame() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 115, 350, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnLogout = new JButton("로그 아웃");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLogout.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnLogout.setBounds(607, 10, 85, 23);
		frame.getContentPane().add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("@@@님 환영합니다!");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel.setBounds(481, 10, 120, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 160, 680, 270);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("검 색");
		btnSearch.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnSearch.setBounds(375, 115, 85, 35);
		frame.getContentPane().add(btnSearch);
		
		JButton btnSearchAll = new JButton("전체 검색");
		btnSearchAll.setMargin(new Insets(2, 7, 2, 7));
		btnSearchAll.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnSearchAll.setBounds(470, 115, 85, 35);
		frame.getContentPane().add(btnSearchAll);
		
		JRadioButton rdbtnName = new JRadioButton("제목순");
		rdbtnName.setFont(new Font("Gulim", Font.PLAIN, 12));
		rdbtnName.setBounds(560, 120, 62, 23);
		frame.getContentPane().add(rdbtnName);
		
		JRadioButton rdbtnLike = new JRadioButton("좋아요순");
		rdbtnLike.setFont(new Font("Gulim", Font.PLAIN, 12));
		rdbtnLike.setBounds(620, 120, 73, 23);
		frame.getContentPane().add(rdbtnLike);
	}
}
