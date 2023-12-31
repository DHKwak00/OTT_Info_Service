package ott.java.member;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class TitleUpdateFrame extends JFrame{
	
	private JFrame frame;
	
	private JTextField txtName;
	private JTextField txtLike;
	private JTextField txtRating;
	private JTextField txtGenre;
	private JTextArea txtInfo;
	private JTextField txtStar;
	private JTextField txtRel;
	private JTextField txtOtt;
	
	private TitleDAO dao;
	private int no;
	
	
	public TitleUpdateFrame(TitleDTO dto) {
		dao = TitleDAOImple.getInstance();
		frame = this;
		frame.setTitle("작품 수정");
		frame.setBounds(100, 100, 275, 515);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setFont(new Font("Gulim", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("작품명");
		lblName.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblName.setBounds(27, 10, 75, 30);
		getContentPane().add(lblName);
		
		JLabel lblLike = new JLabel("좋아요수");
		lblLike.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblLike.setBounds(27, 50, 75, 30);
		getContentPane().add(lblLike);
		
		JLabel lblRating = new JLabel("시청 등급");
		lblRating.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblRating.setBounds(27, 90, 75, 30);
		getContentPane().add(lblRating);
		
		JLabel lblGenre = new JLabel("장르");
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
		lblRel.setBounds(12, 365, 90, 30);
		getContentPane().add(lblRel);
		
		JLabel lblOtt = new JLabel("시청 가능 OTT");
		lblOtt.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblOtt.setBounds(12, 405, 85, 30);
		getContentPane().add(lblOtt);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtName.setBounds(114, 15, 116, 21);
		txtName.setColumns(10);
		txtName.setText(dto.getTitleName());
		getContentPane().add(txtName);
		
		txtLike = new JTextField();
		txtLike.setEditable(false);
		txtLike.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtLike.setColumns(10);
		txtLike.setBounds(114, 55, 116, 21);
		txtLike.setText(String.valueOf(dto.getTitleLike()));
		getContentPane().add(txtLike);
		
		txtRating = new JTextField();
		txtRating.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtRating.setBounds(114, 95, 116, 21);
		txtRating.setColumns(10);
		txtRating.setText(dto.getTitleRating());
		getContentPane().add(txtRating);
		
		txtGenre = new JTextField();
		txtGenre.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtGenre.setBounds(114, 135, 116, 21);
		txtGenre.setColumns(10);
		txtGenre.setText(dto.getTitleGenre());
		getContentPane().add(txtGenre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(114, 170, 116, 145);
		getContentPane().add(scrollPane);
		
		txtInfo = new JTextArea();
		scrollPane.setViewportView(txtInfo);
		txtInfo.setLineWrap(true);
		txtInfo.setFont(new Font("굴림", Font.PLAIN, 12));
		txtInfo.setColumns(10);
		txtInfo.setText(dto.getTitleInfo());
		
		
		
		txtStar = new JTextField();
		txtStar.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtStar.setColumns(10);
		txtStar.setBounds(114, 330, 116, 21);
		txtStar.setText(dto.getTitleStar());
		getContentPane().add(txtStar);
		
		txtRel = new JTextField();
		txtRel.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtRel.setColumns(10);
		txtRel.setBounds(114, 370, 116, 21);
		txtRel.setText(String.valueOf(dto.getTitleRel()));
		getContentPane().add(txtRel);
		
		txtOtt = new JTextField();
		txtOtt.setFont(new Font("Gulim", Font.PLAIN, 12));
		txtOtt.setColumns(10);
		txtOtt.setBounds(114, 410, 116, 21);
		txtOtt.setText(dto.getTitleott());
		getContentPane().add(txtOtt);
		
		JButton btnInsert = new JButton("수 정");
		btnInsert.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleUpdate();
			}
		});
		btnInsert.setBounds(150, 443, 97, 23);
		getContentPane().add(btnInsert);
		
		JButton btnDelete = new JButton("삭 제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleDelete();
			}

		});
		btnDelete.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnDelete.setBounds(12, 445, 97, 23);
		getContentPane().add(btnDelete);
		
		no = dto.getTitleNo();
		
	}// end TitleUpdateFrame()
	
	private void titleUpdate() {
		int tNo = no;
//		System.out.println(tNo);
		String name = txtName.getText();
		int like = Integer.parseInt(txtLike.getText());
		String rate = txtRating.getText();
		String genre = txtGenre.getText();
		String info = txtInfo.getText();
//		System.out.println(like);
		String star = txtStar.getText();
		String rrel = txtRel.getText();
		java.sql.Date rel = java.sql.Date.valueOf(rrel); // * 문자열을 DB에 date로 날려주기
		String ott = txtOtt.getText();
		
		TitleDTO dto = new TitleDTO(tNo, name, like, rate, genre, 
				info, star, rel, ott);
		
		int result = dao.update(tNo, dto);
		System.out.println(result);
		if(result == 1) {
			System.out.println("titleUpdate()값 : " + result);
			JOptionPane.showMessageDialog(frame, "수정되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else {
			System.out.println("가입 정보 부족");
			JOptionPane.showMessageDialog(frame, "정보를 정확히 입력해 주세요.", "알림", JOptionPane.ERROR_MESSAGE);
		}
		
	}// end titleUpdate()
	
	private void titleDelete() {
		int tno = no;
		int result = dao.delete(tno);
		if(result == 1) {
			System.out.println("titleDelete : " + result);
			JOptionPane.showMessageDialog(frame, "삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
		
	}// end titleDelete()
	
}// end TitleUpdateFrame
