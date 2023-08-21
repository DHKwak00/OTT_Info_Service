package ott.java.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import oracle.jdbc.driver.OracleDriver;

public class MemberDAOImple implements MemberDAO, OracleQuery {

	// 싱글톤
	private static MemberDAOImple instance = null;

	private MemberDAOImple() {
	}

	public static MemberDAOImple getInstance() {
		if (instance == null) {
			instance = new MemberDAOImple();
		}
		return instance;
	}

	// 회원 가입
	@Override
	public int insert(MemberDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(MEMBER_INSERT);
			if(dto.getMemId().isEmpty() || dto.getMemPw().isEmpty() || dto.getMemPhone().isEmpty() || dto.getMemEmail().isEmpty()) {
				System.out.println("입력값 = null");
				result = -1;
			}else {
				pstmt.setString(1, dto.getMemId());
				pstmt.setString(2, dto.getMemPw());
				pstmt.setString(3, dto.getMemPhone());
				pstmt.setString(4, dto.getMemEmail());
				
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
	} // end insert()

	// 회원 정보 수정
	@Override
	public int update(String memId, MemberDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 완료");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(MEMBER_UPDATE);

			pstmt.setString(1, dto.getMemPw());
			pstmt.setString(2, dto.getMemPhone());
			pstmt.setString(3, dto.getMemEmail());

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
	} // end update()

	// 회원 탈퇴
	@Override
	public int delete(String memId) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			pstmt = conn.prepareStatement(MEMBER_DELETE);

			pstmt.setString(1, memId);

			result = pstmt.executeUpdate();
			System.out.println("결과값 : " + result + " 행이 삭제되었습니다.");

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
	} // end delete()
	
	// 로그인
	@Override
	public int login(String id, String pw) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 로드 성공");
			pstmt = conn.prepareStatement(MEMBER_LOGIN);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					result = 1; // pw = true
					System.out.println(result);
				}else {
					result = 0; // pw = false
					System.out.println(result);
				}
			}else {
				result = -1; // id = false
			}
			
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	// 회원 정보 조회
	@Override
	public MemberDTO getInfo(String memId) {
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 완료");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 완료");
			
			pstmt = conn.prepareStatement(MEMBER_SELECT_ID);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memNo = rs.getInt(1);
				memId = rs.getString(2);
				String memPw = rs.getString(3);
				String memPhone = rs.getString(4);
				String memEmail = rs.getString(5);
				
				dto = new MemberDTO(memNo, memId, memPw, memPhone, memEmail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	

}
