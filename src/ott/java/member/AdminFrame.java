package ott.java.member;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame{

	private JFrame frame;
	private JTable table;
	private String[] colNames = {"No", "작품명", "등급", "장르", "작품설명",
								 "좋아요", "평점", "개봉일", "OTT"};
	private Object[] records = new Object[colNames.length];
	private DefaultTableModel tableModel; // 테이블 모델 변수
	
	private static TitleDAO dao;

	public AdminFrame() {
		dao = TitleDAOImple.getInstance();
		frame = this;
		frame.setBounds(100, 100, 540, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("관리자 계정 로그인 중입니다.");
		lblNewLabel.setBounds(262, 14, 160, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogOut = new JButton("로그 아웃");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnLogOut, "로그아웃 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				FrontMain frontMain = new FrontMain();
				frontMain.mframe.setVisible(true);
			}
		});
		btnLogOut.setMargin(new Insets(2, 5, 2, 5));
		btnLogOut.setBounds(434, 10, 78, 23);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnTitleInsert = new JButton("작품 등록");
		btnTitleInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertTitleFrame insertTitleFrame = new InsertTitleFrame();
				insertTitleFrame.setVisible(true);
			}
		});
		btnTitleInsert.setBounds(12, 10, 120, 40);
		frame.getContentPane().add(btnTitleInsert);
		
		JButton btnSearchAll = new JButton("전체 검색");
		btnSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAllTable();
			}
		});
		btnSearchAll.setBounds(12, 60, 120, 40);
		frame.getContentPane().add(btnSearchAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 110, 500, 241);
		frame.getContentPane().add(scrollPane);
		
		tableModel = new DefaultTableModel(colNames, 0);
		
		table = new JTable(tableModel);
		table.setFont(new Font("Gulim", Font.PLAIN, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumn("No").setPreferredWidth(25);
		table.getColumn("등급").setPreferredWidth(35);
		table.getColumn("좋아요").setPreferredWidth(45);
		table.getColumn("평점").setPreferredWidth(35);
		table.getColumn("개봉일").setPreferredWidth(65);
		table.getColumn("OTT").setPreferredWidth(60);
		scrollPane.setViewportView(table);
		
	} // end AdminFrame()
	
	private void selectAllTable() {
		ArrayList<TitleDTO> list = dao.select();
		System.out.println(list.toString());
		tableModel.setRowCount(0);
		for(int i =0; i<list.size(); i++) {
			records[0] = list.get(i).getTitleNo();
			records[1] = list.get(i).getTitleName();
			records[2] = list.get(i).getTitleRating();
			records[3] = list.get(i).getTitleGenre();
			records[4] = list.get(i).getTitleInfo();
			records[5] = list.get(i).getTitleLike();
			records[6] = list.get(i).getTitleStar();
			records[7] = list.get(i).getTitleRel();
			records[8] = list.get(i).getTitleott();
			tableModel.addRow(records);
		}
		
	} // end selectAllTable()
	
	private void insertTitle() {
		// TODO Auto-generated method stub
		
	}
	

}
