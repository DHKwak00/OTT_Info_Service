package ott.java.member;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminFrame extends JFrame {

	private JFrame frame;
	private JTable table;
	private String[] colNames = { "No", "작품명", "좋아요", "등급", "장르", "작품설명", "평점", "개봉일", "OTT" };
	private Object[] records = new Object[colNames.length];
	private DefaultTableModel tableModel; // 테이블 모델 변수

	private static TitleDAO dao;

	public AdminFrame(String inId) {
		dao = TitleDAOImple.getInstance();
		frame = this;
		frame.setBounds(100, 100, 540, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("관리자 계정 로그인 중입니다.");
		lblNewLabel.setBounds(262, 14, 170, 15);
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
				TitleInsertFrame insertTitleFrame = new TitleInsertFrame();
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

		// 여기서부터 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 110, 500, 241);
		frame.getContentPane().add(scrollPane);

		// 컬럼 우측 정렬
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

		tableModel = new DefaultTableModel(colNames, 0);

		table = new JTable(tableModel);

		// 테이블 클릭 수정 이벤트
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titleUpdate();
			}
		});

		table.setFont(new Font("Gulim", Font.PLAIN, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumn("No").setPreferredWidth(25);
		table.getColumn("등급").setPreferredWidth(35);
		table.getColumn("좋아요").setPreferredWidth(45);
		table.getColumn("평점").setPreferredWidth(35);
		table.getColumn("평점").setCellRenderer(celAlignRight);
		table.getColumn("개봉일").setPreferredWidth(65);
		table.getColumn("OTT").setPreferredWidth(60);
		scrollPane.setViewportView(table);

	} // end AdminFrame()

	private void selectAllTable() {
		ArrayList<TitleDTO> list = dao.select();
//		System.out.println(list.toString());
		tableModel.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			records[0] = list.get(i).getTitleNo();
			records[1] = list.get(i).getTitleName();
			records[2] = list.get(i).getTitleLike();
			records[3] = list.get(i).getTitleRating();
			records[4] = list.get(i).getTitleGenre();
			records[5] = list.get(i).getTitleInfo();
			records[6] = list.get(i).getTitleStar();
			records[7] = list.get(i).getTitleRel();
			records[8] = list.get(i).getTitleott();
			tableModel.addRow(records);
		}

	} // end selectAllTable()

	private void titleUpdate() {
		// 선택된 셀의 행 번호
		int row = table.getSelectedRow();

		// 모델 객체 담기
		TableModel tableModel = table.getModel();

		// 선택한 셀의 행의 값을 DTO로 포장하기
		int no = (int) tableModel.getValueAt(row, 0);
		String name = (String) tableModel.getValueAt(row, 1);
		int like = (int) tableModel.getValueAt(row, 2);
		String rate = (String) tableModel.getValueAt(row, 3);
		String genre = (String) tableModel.getValueAt(row, 4);
		String info = (String) tableModel.getValueAt(row, 5);
		String star = (String) tableModel.getValueAt(row, 6);
		Date rel = (Date) tableModel.getValueAt(row, 7);
		String ott = (String) tableModel.getValueAt(row, 8);

		TitleDTO dto = new TitleDTO(no, name, like, rate, genre, info, star, rel, ott);
		System.out.println(dto.getTitleNo());

		TitleUpdateFrame updateFrame = new TitleUpdateFrame(dto);
		updateFrame.setVisible(true);

	}// end titleUpdate()

}// end AdminFrame
