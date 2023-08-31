package ott.java.member;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class OttFrame extends JFrame {

	private JFrame frame;
	private JTextField txtSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JTable table;
	private String[] colNames = { "No", "작품명", "좋아요", "등급", "장르", "작품설명", "평점", "개봉일", "OTT" };
	private Object[] records = new Object[colNames.length];
	private DefaultTableModel tableModel; // 테이블 모델 변수

	private MemberDAO mdao;
	private TitleDAO tdao;
	private LikeItDAO ldao;

	public OttFrame(String inId) {
		// LoginFrame에서 입력값이 넘어와야함.
		mdao = MemberDAOImple.getInstance();
		tdao = TitleDAOImple.getInstance();
		ldao = LikeItDAOImple.getInstance();
		frame = this;
		frame.setTitle("OTT 정보 프로그램");
		frame.setBounds(100, 100, 1366, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

//		System.out.println(dto);
		JLabel lblNewLabel = new JLabel(inId + " 님 환영합니다.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel.setBounds(1105, 10, 130, 23);
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
		btnLogOut.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnLogOut.setBounds(1247, 10, 90, 23);
		frame.getContentPane().add(btnLogOut);

		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectNameTable();
			}
		});
		txtSearch.setFont(new Font("Gulim", Font.PLAIN, 16));
		txtSearch.setBounds(12, 115, 350, 35);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("검 색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectNameTable();
			}
		});
		btnSearch.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnSearch.setBounds(421, 116, 85, 35);
		frame.getContentPane().add(btnSearch);

		JButton btnSearchAll = new JButton("전체 검색");
		btnSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
				selectAllTable();
			}
		});
		btnSearchAll.setMargin(new Insets(2, 7, 2, 7));
		btnSearchAll.setFont(new Font("굴림", Font.PLAIN, 12));
		btnSearchAll.setBounds(518, 116, 85, 35);
		frame.getContentPane().add(btnSearchAll);

		JRadioButton rdbtnName = new JRadioButton("제목순");
		buttonGroup.add(rdbtnName);
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectTableByName();
			}
		});
		rdbtnName.setFont(new Font("굴림", Font.PLAIN, 12));
		rdbtnName.setBounds(611, 122, 62, 23);
		frame.getContentPane().add(rdbtnName);

		JRadioButton rdbtnLike = new JRadioButton("좋아요순");
		buttonGroup.add(rdbtnLike);
		rdbtnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAllTableByLike();
			}
		});
		rdbtnLike.setFont(new Font("굴림", Font.PLAIN, 12));
		rdbtnLike.setBounds(677, 122, 73, 23);
		frame.getContentPane().add(rdbtnLike);

		// 좋아요 버튼
		JButton btnNewButton = new JButton("❤");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iLikeIt(inId);
				
			}

		});
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setMargin(new Insets(2, 0, 0, 0));
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 30));
		btnNewButton.setBounds(374, 115, 35, 35);
		getContentPane().add(btnNewButton);

		// 좋아요 리스트
		JButton btnLike = new JButton("나의 좋아요 목록");
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				likeList(inId);
			}

		});
		btnLike.setMargin(new Insets(2, 7, 2, 7));
		btnLike.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnLike.setBounds(1207, 127, 130, 23);
		getContentPane().add(btnLike);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBounds(12, 160, 1325, 270);
		frame.getContentPane().add(scrollPane);

		tableModel = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int culmn) {
				return false;
			}// 변경 불가
		};

		// 컬럼 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		// 컬럼 우측 정렬
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

//		// 컬럼 좋아요 버튼
//		DefaultTableCellRenderer btnLk = new DefaultTableCellRenderer();

		table = new JTable(tableModel) {
			// 컬럼값 길이 자동 조절
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}

		};

//		table.getColumn("좋아요").setCellRenderer(btnLk);
//		JButton btn = new JButton();
//		btn.setHorizontalAlignment(JLabel.RIGHT);
//		table.getColumn("좋아요").setCellEditor((TableCellEditor) btn);

		table.setFont(new Font("Gulim", Font.PLAIN, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumn("No").setPreferredWidth(30);
		table.getColumn("등급").setPreferredWidth(35);
		table.getColumn("등급").setCellRenderer(celAlignCenter);
		table.getColumn("좋아요").setPreferredWidth(45);
		table.getColumn("좋아요").setCellRenderer(celAlignCenter);
		table.getColumn("평점").setPreferredWidth(35);
		table.getColumn("평점").setCellRenderer(celAlignRight);
		table.getColumn("작품설명").setPreferredWidth(900);
		table.getColumn("개봉일").setPreferredWidth(65);
		table.getColumn("OTT").setPreferredWidth(62);
		table.getColumn("OTT").setCellRenderer(celAlignCenter);

	}// end OttFrame()

	// 전체 검색
	private void selectAllTable() {
		ArrayList<TitleDTO> list = tdao.select();
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

//	// 제목순
//	private void selectAllTableByName() {
//		ArrayList<TitleDTO> list = tdao.selectByName();
////		System.out.println(list.toString());
//		System.out.println(Arrays.toString(records));
//		tableModel.setRowCount(0);
//
//		for (int i = 0; i < list.size(); i++) {
//			records[0] = list.get(i).getTitleNo();
//			records[1] = list.get(i).getTitleName();
//			records[2] = list.get(i).getTitleLike();
//			records[3] = list.get(i).getTitleRating();
//			records[4] = list.get(i).getTitleGenre();
//			records[5] = list.get(i).getTitleInfo();
//			records[6] = list.get(i).getTitleStar();
//			records[7] = list.get(i).getTitleRel();
//			records[8] = list.get(i).getTitleott();
//			tableModel.addRow(records);
//
//		}
//
//	} // end selectAllTableByName()

	// 검색 제목순
	private void selectTableByName() { // list.get~
		String searchName = ("%" + txtSearch.getText() + "%");
		searchName = searchName.toLowerCase();

		tableModel.setRowCount(0);

		if (txtSearch.getText().isEmpty()) {
			ArrayList<TitleDTO> list = tdao.selectByAllName();
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
		} else {
			ArrayList<TitleDTO> list = tdao.selectByName(searchName);
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

		}

	} // end selectAllTableByName()

	
	// 좋아요순
	private void selectAllTableByLike() {
		String searchName = ("%" + txtSearch.getText() + "%");
		searchName = searchName.toLowerCase();
//		System.out.println(list.toString());
		tableModel.setRowCount(0);
		if(txtSearch.getText().isEmpty()) {
			ArrayList<TitleDTO> list = tdao.selectByAllLike();
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
		}else {
			ArrayList<TitleDTO> list = tdao.selectByLike(searchName);
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
			
		}

	} // end selectAllTable()

	// 검색
	private void selectNameTable() {
		// 텍스트필드에 입력값이 검색버튼 -> selectNameTable() 메소드에서
		// String titleName에 담기고 dao.selectTitle(titleName)로 보내져,
		// return 값이 dto (TITLE_NAME 컬럼에 titleName의 값)
		String searchName = ("%" + txtSearch.getText() + "%");
		searchName = searchName.toLowerCase();
		ArrayList<TitleDTO> list = tdao.selectTitle(searchName);
//		System.out.println(list.toString());
		tableModel.setRowCount(0);

		if (txtSearch.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "검색어를 입력해 주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
		} else if (!list.isEmpty()) {
			System.out.println("dao로 넘어온 값은" + list);
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
		} else {
			JOptionPane.showMessageDialog(null, "검색어와 일치하는 정보가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}
		// titleName이 DB의 TITLE_NAME 컬럼에 있으면 ~

	}// end selectNameTable()

	// 좋아요
	private void iLikeIt(String inId) {
		// 선택된 셀의 행 번호
		int row = table.getSelectedRow();

		// 모델 객체 담기
		TableModel tableModel = table.getModel();
		
		LikeItDTO ldto = null;
		try {
			int mNo = mdao.getInfo(inId).getMemNo();
			System.out.println("로그인 아이디 값 : " + mNo);
			
			int tNo = (int) tableModel.getValueAt(row, 0);
			
			System.out.println("선택한 작품 아이디 값 : " +tNo);
			
			ldto = new LikeItDTO(0, mNo, tNo);
			
			// 선택한 row의 회원no와 좋아요테이블의 회원no가 같고 작품no와 좋테 작품no가 같으면 삭제
			if (ldto.getMemNo() == ldao.select(mNo).getMemNo() &&
					ldto.getTitleNo() == ldao.select(mNo).getTitleNo()) {
				ldao.delete(ldto);
				
				
				System.out.println("좋아요 취소");
			} else {
				ldao.insert(ldto);
				System.out.println("좋아요 등록");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "작품을 선택해 주세요", "알림", JOptionPane.WARNING_MESSAGE);
		}

	}// end iLikeThis()
	
	private void likeList(String inId) {
		int mNo = mdao.getInfo(inId).getMemNo();
		ArrayList<TitleDTO> list = tdao.selectMyLike(mNo);
		System.out.println("좋아요리스트 값:"+list);
		tableModel.setRowCount(0);
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "좋아요한 작품이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}else {
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
			System.out.println("로그인 아이디 값 : " + mNo);
		}
		
	}// end likeList()
	
}// end OttFrame
