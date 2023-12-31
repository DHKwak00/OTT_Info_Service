package ott.java.member;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;

public class TitleInsertFrame extends JFrame {

	private JFrame frame;

	private JTextField txtName;
	private JTextField txtLike;
	private JTextField txtRating;
	private JTextArea txtInfo;
	private JTextField txtStar;
	private JTextField txtRel;

	private TitleDAO dao;
	
	String[] genre = {"", "액션", "모험", "판타지", "전쟁", "공포", "스릴러", "멜로", "코미디", "애니메이션"};
	String[] ott = {"", "넷플릭스", "디즈니+", "왓챠"};
	DefaultComboBoxModel<String> model;
	JComboBox<String> genCombo;
	JComboBox<String> ottCombo;

	public TitleInsertFrame() {
		dao = TitleDAOImple.getInstance();
		frame = this;
		frame.setTitle("작품 등록");
		frame.setBounds(100, 100, 275, 515);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setFont(new Font("Gulim", Font.PLAIN, 12));
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("작품명*");
		lblName.setFont(new Font("Gulim", Font.PLAIN, 12)); // (27, 285, 75, 30);
		lblName.setBounds(27, 10, 75, 30);
		getContentPane().add(lblName);

		JLabel lblLike = new JLabel("좋아요수");
		lblLike.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblLike.setBounds(27, 50, 75, 30);
		getContentPane().add(lblLike);

		JLabel lblRating = new JLabel("시청 등급*");
		lblRating.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblRating.setBounds(27, 90, 75, 30);
		getContentPane().add(lblRating);

		JLabel lblGenre = new JLabel("장르*");
		lblGenre.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblGenre.setBounds(27, 130, 75, 30);
		getContentPane().add(lblGenre);

		JLabel lblInfo = new JLabel("작품 설명");
		lblInfo.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblInfo.setBounds(27, 170, 75, 30);
		getContentPane().add(lblInfo);

		JLabel lblStar = new JLabel("평점");
		lblStar.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblStar.setBounds(27, 325, 75, 30);
		getContentPane().add(lblStar);

		JLabel lblRel = new JLabel("<html>개봉일<br>(yyyy-mm-dd)</html>");
		lblRel.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblRel.setBounds(12, 360, 90, 47);
		getContentPane().add(lblRel);

		JLabel lblOtt = new JLabel("시청 가능 OTT");
		lblOtt.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblOtt.setBounds(12, 405, 85, 30);
		getContentPane().add(lblOtt);

		txtName = new JTextField();
		txtName.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtName.setBounds(114, 15, 116, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		txtLike = new JTextField();
		txtLike.setEditable(false);
		txtLike.setFont(new Font("Gulim", Font.PLAIN, 12)); // (114, 290, 116, 21)
		txtLike.setColumns(10);
		txtLike.setBounds(114, 55, 116, 21);
		getContentPane().add(txtLike);

		txtRating = new JTextField();
		txtRating.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtRating.setColumns(10);
		txtRating.setBounds(114, 95, 116, 21);
		getContentPane().add(txtRating);
		
		model = new DefaultComboBoxModel<String>(genre);
		genCombo = new JComboBox<String>(model);
		genCombo.setFont(new Font("Gulim", Font.PLAIN, 12));
			
		genCombo.setBounds(114, 134, 116, 23);
		getContentPane().add(genCombo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(114, 170, 116, 145);
		getContentPane().add(scrollPane);

		txtInfo = new JTextArea();
		scrollPane.setViewportView(txtInfo);
		txtInfo.setLineWrap(true);
		txtInfo.setFont(new Font("굴림", Font.PLAIN, 12));
		txtInfo.setColumns(10);

		txtStar = new JTextField();
		txtStar.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtStar.setColumns(10);
		txtStar.setBounds(114, 330, 116, 21);
		getContentPane().add(txtStar);

		txtRel = new JTextField();
		txtRel.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtRel.setColumns(10);
		txtRel.setBounds(114, 370, 116, 21);
		getContentPane().add(txtRel);

		JButton btnInsert = new JButton("등 록");
		btnInsert.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertTitle();
			}
		});
		btnInsert.setBounds(150, 443, 97, 23);
		getContentPane().add(btnInsert);
		
		model = new DefaultComboBoxModel<String>(ott);
		ottCombo = new JComboBox<String>(model);
		ottCombo.setFont(new Font("Gulim", Font.PLAIN, 12));
		ottCombo.setBounds(114, 409, 116, 23);
		getContentPane().add(ottCombo);
		
		
	}

	private void insertTitle() {
		
		String name = txtName.getText();
//		int like = Integer.parseInt(txtLike.getText());
		String rate = txtRating.getText();
		String genre = (String)genCombo.getSelectedItem();
		System.out.println("장르값:" + genre);
//		System.out.println(txtInfo.getText());
		String info = txtInfo.getText();
//		System.out.println(like);
		String star = txtStar.getText();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String rrel = txtRel.getText();
		java.sql.Date rel = null;
		
		try {
			if(!txtRel.getText().isEmpty()) {
				rel = java.sql.Date.valueOf(txtRel.getText());
			}else {
				rel = java.sql.Date.valueOf("1900-01-01");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "날짜를 정확히 입력해 주세요.", "알림", JOptionPane.ERROR_MESSAGE);
		}
		String ott = (String)ottCombo.getSelectedItem();
		
//		try {
//			String rrel;
//			if(txtRel.getText().isEmpty()) {
//				rrel = "";			
//			}else {
//				rrel = txtRel.getText();
//			}
//			System.out.println("널?" + rrel);
//			rel = java.sql.Date.valueOf(rrel);// * 문자열을 DB에 date로 날려주기
//			
//			if(rel == null) {
//				System.out.println("널이다");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
//		java.util.Date rel = null;
//		try {
//			rel = Date.v;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		TitleDTO dto = new TitleDTO(0, name, 0, rate, genre, info, star, rel, ott);
		
		int result;
		if(dto.getTitleRel()==null) {
			result = -1;
		}else {
			result = dao.insert(dto);
		}
//		System.out.println(result);
		if (result == 1) {
			System.out.println("insertTitle()값 : " + result);
			JOptionPane.showMessageDialog(frame, "등록되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			System.out.println("가입 정보 부족");
			JOptionPane.showMessageDialog(frame, "<html>정보를 정확히 입력해 주세요. <br /> (*표시는 반드시 기입해 주세요.)</html>", "알림", JOptionPane.ERROR_MESSAGE);
		}

	}// end insertTitle()
}// end InsertTitleFrame
