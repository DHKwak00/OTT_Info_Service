package ott.java.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

			if(dto.getTitleName().isEmpty() || dto.getTitleRating().isEmpty() || dto.getTitleGenre().isEmpty()) {
				result = -1;
			}else {
				pstmt.setString(1, dto.getTitleName());
				pstmt.setInt(2, dto.getTitleLike());
				pstmt.setString(3, dto.getTitleRating());
				pstmt.setString(4, dto.getTitleGenre());
				pstmt.setString(5, dto.getTitleInfo());
				pstmt.setString(6, dto.getTitleStar());
				pstmt.setDate(7, (java.sql.Date) dto.getTitleRel());
				pstmt.setString(8, dto.getTitleott());
				
				result = pstmt.executeUpdate();
				System.out.println("값 : " + result + " 행 삽입 완료");
			}

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
		ArrayList<TitleDTO> list = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			// DB와 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			// Connection 객체를 사용하여 Statement 객체를 생성
			pstmt = conn.prepareStatement(TITLE_SELECT);
			System.out.println(TITLE_SELECT);

			// SQL 문장 실행(DB 서버로 SQL 전송)
			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) { // 레코드가 존재할 때까지
				int titleNo = rs.getInt(1);
				String titleName = rs.getString(2);
				int titleLike = rs.getInt(3);
				String titleRating = rs.getString(4);
				String titleGenre = rs.getString(5);
				String titleInfo = rs.getString(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);

				TitleDTO dto = new TitleDTO(titleNo, titleName,  titleLike, titleRating, titleGenre, titleInfo,
						titleStar, titleRel, titleott);
				list.add(dto);
			}
//			for(TitleDTO dto:list) {
//				System.out.println(dto);
//			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	// 작품 전체 검색 (제목순)
	@Override
	public ArrayList<TitleDTO> selectByName() {
		ArrayList<TitleDTO> list = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			// DB와 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			// Connection 객체를 사용하여 Statement 객체를 생성
			pstmt = conn.prepareStatement(TITLE_SELECT_NAME);
			System.out.println(TITLE_SELECT_NAME);

			// SQL 문장 실행(DB 서버로 SQL 전송)
			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) { // 레코드가 존재할 때까지
				int titleNo = rs.getInt(1);
				String titleName = rs.getString(2);
				int titleLike = rs.getInt(3);
				String titleRating = rs.getString(4);
				String titleGenre = rs.getString(5);
				String titleInfo = rs.getString(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);

				TitleDTO dto = new TitleDTO(titleNo, titleName, titleLike, titleRating, titleGenre, titleInfo, 
						titleStar, titleRel, titleott);
				list.add(dto);
			}
//				for(TitleDTO dto:list) {
//					System.out.println(dto);
//				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	// 작품 전체 검색 (좋아요순)
	@Override
	public ArrayList<TitleDTO> selectByLike() {
		ArrayList<TitleDTO> list = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// JDBC 드라이버를 메모리에 로드
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			// DB와 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			// Connection 객체를 사용하여 Statement 객체를 생성
			pstmt = conn.prepareStatement(TITLE_SELECT_LIKE);
			System.out.println(TITLE_SELECT_LIKE);

			// SQL 문장 실행(DB 서버로 SQL 전송)
			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) { // 레코드가 존재할 때까지
				int titleNo = rs.getInt(1);
				String titleName = rs.getString(2);
				int titleLike = rs.getInt(3);
				String titleRating = rs.getString(4);
				String titleGenre = rs.getString(5);
				String titleInfo = rs.getString(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);

				TitleDTO dto = new TitleDTO(titleNo, titleName,  titleLike, titleRating, titleGenre,
						 titleInfo, titleStar, titleRel, titleott);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}// end selectByLike()

	// 작품 제목 검색
	@Override
	public ArrayList<TitleDTO> selectTitle(String searchName) {
		ArrayList<TitleDTO> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 완료");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 완료");

			pstmt = conn.prepareStatement(TITLE_SELECT_BY_NAME);
			System.out.println(TITLE_SELECT_BY_NAME);
			System.out.println(searchName);
			pstmt.setString(1, searchName);

			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) { // 레코드가 존재할 때까지
				int titleNo = rs.getInt(1);
				searchName = rs.getString(2);
				int titleLike = rs.getInt(3);
				String titleRating = rs.getString(4);
				String titleGenre = rs.getString(5);
				String titleInfo = rs.getString(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);

				TitleDTO dto = new TitleDTO(titleNo, searchName, titleLike, titleRating, titleGenre,
						 titleInfo, titleStar, titleRel, titleott);
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
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

			if (rs.next()) {
				int titleNo = rs.getInt(1);
				String titleName = rs.getString(2);
				int titleLike = rs.getInt(3);
				String titleRating = rs.getString(4);
				titleGenre = rs.getString(5);
				String titleInfo = rs.getString(6);
				String titleStar = rs.getString(7);
				Date titleRel = rs.getDate(8);
				String titleott = rs.getString(9);

				dto = new TitleDTO(titleNo, titleName,  titleLike, titleRating, titleGenre, titleInfo,
							titleStar, titleRel, titleott);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dto;
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
			pstmt.setInt(2, dto.getTitleLike());
			pstmt.setString(3, dto.getTitleRating());
			pstmt.setString(4, dto.getTitleGenre());
			pstmt.setString(5, dto.getTitleInfo());
			pstmt.setString(6, dto.getTitleStar());
			pstmt.setDate(7, (java.sql.Date) dto.getTitleRel());
			pstmt.setString(8, dto.getTitleott());
			pstmt.setInt(9, dto.getTitleNo());

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

	} // end delete()

} // end TitleDAOImple
