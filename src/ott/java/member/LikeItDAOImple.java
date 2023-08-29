package ott.java.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class LikeItDAOImple implements LikeItDAO, OracleQuery {

	// 싱글톤
	private static LikeItDAOImple instance = null;

	private LikeItDAOImple() {
	}

	public static LikeItDAOImple getInstance() {
		if (instance == null) {
			instance = new LikeItDAOImple();
		}
		return instance;
	}

	// 좋아요
	@Override
	public int insert(LikeItDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결");
			pstmt = conn.prepareStatement(LIKE_IT);

			pstmt.setInt(1, dto.getMemNo());
			pstmt.setInt(2, dto.getTitleNo());
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(LIKE_PLS);
			pstmt.setInt(1, dto.getTitleNo());
			result = pstmt.executeUpdate();
			
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
	}// end insert()

	
	// 촣아요 취소
	@Override
	public int delete(LikeItDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결");
			
			pstmt = conn.prepareStatement(LIKE_CANCEL);
			pstmt.setInt(1, dto.getMemNo());
			result = pstmt.executeUpdate();
			pstmt.close();
			
			
			pstmt = conn.prepareStatement(LIKE_MIN);
			pstmt.setInt(1, dto.getTitleNo());
			result = pstmt.executeUpdate();
			
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
	}// end delete()
	
	// 좋아요 조회
	@Override
	public LikeItDTO select(int memNo) {
		LikeItDTO ldto = new LikeItDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(LIKE_SELECT_ID);
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int likeNo = rs.getInt(1);
				memNo = rs.getInt(2);
				int titleNo = rs.getInt(3);
				
				ldto = new LikeItDTO(likeNo, memNo, titleNo);
				System.out.println(ldto);
				// rs close, pstmt 닫고  pstmt 다시 열고
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
		
		
		return ldto;
	}// end select()

}
