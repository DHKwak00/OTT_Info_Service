package ott.java.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import oracle.jdbc.driver.OracleDriver;

public class TitleDAOImple implements TitleDAO, OracleQuery {

	// 싱글톤
	private static TitleDAOImple instance = null;

	private TitleDAOImple() {
	}

	public static TitleDAOImple getInstance() {
		if (instance == null) {
			instance = new TitleDAOImple();
		}
		return instance;
	}
	
	// 작품 등록
	@Override
	public int insert(TitleDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(TITLE_INSERT);

			pstmt.setString(1, COL_TITLE_NAME);
			pstmt.setString(2, COL_TITLE_RATING);
			pstmt.setString(3, COL_TITLE_GENRE);
			pstmt.setString(4, COL_TITLE_INFO);
			pstmt.setString(5, COL_TITLE_LIKE);
			pstmt.setString(6, COL_TITLE_STAR);
			pstmt.setString(7, COL_TITLE_RELEASE);
			pstmt.setString(8, COL_TITLE_OTT);

			result = pstmt.executeUpdate();
			System.out.println("값 : " + result + " 행 삽입 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 작품 전체 검색
	@Override
	public ArrayList<TitleDTO> select() {
		ArrayList<TitleDTO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. Oracle JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			
			// 3. DB와 Connection(연결)을 맺음
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			// Connection 객체를 사용하여 Statement 객체를 생성
			pstmt = conn.prepareStatement(TITLE_SELECT);
					
			// SQL 문장 실행(DB 서버로 SQL 전송)
			rs = pstmt.executeQuery();
	
			while(rs.next()) { // 레코드가 존재할 때까지
				int titleNo = rs.getInt(COL_TITLE_NO);
				String titleName = rs.getString(COL_TITLE_NAME);
				String titleRating = rs.getString(COL_TITLE_RATING);
				String titleGenre = rs.getString(COL_TITLE_GENRE);
				String titleInfo = rs.getString(COL_TITLE_INFO);
				int titleLike = rs.getInt(COL_TITLE_LIKE);
				String titleStar = rs.getString(COL_TITLE_STAR);
				Date titleRel = rs.getDate(COL_TITLE_RELEASE);
				String titleott = rs.getString(COL_TITLE_OTT);
				
				TitleDTO dto = new TitleDTO(titleNo, titleName, titleRating, titleGenre, titleInfo, 
						titleLike, titleStar, titleRel, titleott);
				list.add(dto);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 작품 수정
	@Override
	public int update(int TitleNo, TitleDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 완료");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(TITLE_UPDATE);

			pstmt.setString(1, dto.getTitleName());
			pstmt.setString(2, dto.getTitleRating());
			pstmt.setString(3, dto.getTitleGenre());
			pstmt.setString(4, dto.getTitleInfo());
			pstmt.setString(5, dto.getTitleStar());
			pstmt.setDate(6, (java.sql.Date) dto.getTitleRel());
			pstmt.setString(7, dto.getTitleott());

			result = pstmt.executeUpdate();
			System.out.println("결과값 : " + result + " 행이 수정되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 작품 제목 검색
	@Override
	public TitleDTO selectTitle(String titleName) {
		TitleDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 완료");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 완료");
			
			pstmt = conn.prepareStatement(COL_TITLE_NAME);
			
			pstmt.setString(1, titleName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int titleNo = rs.getInt(1);
				titleName = rs.getString(2);
				String titleRating = rs.getString(3);
				String titleGenre = rs.getString(4);
				String titleInfo = rs.getString(5);
				int titleLike = rs.getInt(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);
				
				dto = new TitleDTO(titleNo, titleName, titleRating, titleGenre, titleInfo, 
						titleLike,titleStar, titleRel, titleott);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	// 작품 장르별 검색
	@Override
	public TitleDTO selectGenre(String titleGenre) {
		TitleDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 완료");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 완료");
			
			pstmt = conn.prepareStatement(COL_TITLE_GENRE);
			pstmt.setString(1, titleGenre);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int titleNo = rs.getInt(1);
				String titleName = rs.getString(2);
				String titleRating = rs.getString(3);
				titleGenre = rs.getString(4);
				String titleInfo = rs.getString(5);
				int titleLike = rs.getInt(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);
				
				dto = new TitleDTO(titleNo, titleName, titleRating, titleGenre, titleInfo, 
						titleLike,titleStar, titleRel, titleott);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	// 작품 삭제
	@Override
	public int delete(int titleNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
				
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(TITLE_DELETE);
			
			pstmt.setInt(1, titleNo);

			result = pstmt.executeUpdate();
			
			System.out.println("결과값 : " + result + "행이 삭제됐습니다.");
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}

}
