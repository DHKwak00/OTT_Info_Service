package ott.java.member;

public interface OracleQuery {
	public static final String URL =
			"jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "ott";
	public static final String PASSWORD = "1234";
	
	public static final String TABLE_MEMBER = "MEMBER";
	public static final String COL_MEMBER_NO = "MEMBER_NO";
	public static final String COL_MEMBER_ID = "MEMBER_ID";
	public static final String COL_MEMBER_PASSWORD = "MEMBER_PASSWORD";
	public static final String COL_MEMBER_PHONE = "MEMBER_PHONE";
	public static final String COL_MEMBER_EMAIL = "EMAIL";
	
	public static final String TABLE_TITLE = "TITLE";
	public static final String COL_TITLE_NO = "TITLE_NO";
	public static final String COL_TITLE_NAME = "TITLE_NAME";
	public static final String COL_TITLE_RATING = "TITLE_RATING";
	public static final String COL_TITLE_GENRE = "TITLE_GENRE";
	public static final String COL_TITLE_INFO = "TITLE_INFO";
	public static final String COL_TITLE_LIKE = "TITLE_LIKE";
	public static final String COL_TITLE_STAR = "TITLE_STAR";
	public static final String COL_TITLE_RELEASE = "TITLE_RELEASE";
	public static final String COL_TITLE_OTT = "TITLE_OTT";
	
	
	// 회원 가입
	public static final String MEMBER_INSERT = 
			"INSERT INTO " + TABLE_MEMBER +
			" VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?)";
	
	// 회원 전체 검색
	public static final String MEMBER_SELECT = 
			"SELECT * FROM " + TABLE_MEMBER + 
			" ORDER BY " + COL_MEMBER_NO;
	
	// 회원 ID 조회
	public static final String MEMBER_SELECT_ID =
			"SELECT * FROM " + TABLE_MEMBER +
			" WHERE " + COL_MEMBER_ID + " = ?";
	
	
	// 회원 정보 수정
	public static final String MEMBER_UPDATE = 
			"UPDATE " + TABLE_MEMBER + " SET " +
					COL_MEMBER_ID + " = ?, " +
					COL_MEMBER_PASSWORD + " = ?, " +
					COL_MEMBER_PHONE + " = ?, " +
					COL_MEMBER_EMAIL + " = ? " +
					" WHERE " + COL_MEMBER_NO + " = ?";
	
	// 회원 탈퇴
	public static final String MEMBER_DELETE =
			"DELETE " + TABLE_MEMBER +
			" WHERE " + COL_MEMBER_ID + " = ?"; // 탈퇴는 본인 ID 입력으로 삭제
	
	// 로그인
	public static final String MEMBER_LOGIN = 
			"SELECT MEMBER_PASSWORD FROM " + TABLE_MEMBER +
			" WHERE " + COL_MEMBER_ID + " = ?";
	
	// 관리자 로그인
		public static final String ADMIN_LOGIN = 
				"SELECT MEMBER_PASSWORD FROM " + TABLE_MEMBER +
				" WHERE " + COL_MEMBER_ID + " = 'admin'";
	
			
			
	// 작품 등록
	public static final String TITLE_INSERT = 
			"INSERT INTO " + TABLE_TITLE +
			" VALUES(TITLE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	// 작품 전체 검색
	public static final String TITLE_SELECT = 
			"SELECT * FROM " + TABLE_TITLE + 
			" ORDER BY " + COL_TITLE_NO;
	
	// 작품 정보 수정
	public static final String TITLE_UPDATE = 
			"UPDATE " + TABLE_TITLE + " SET " +
					COL_TITLE_NAME + " = ?, " +
					COL_TITLE_RATING + " = ?, " +
					COL_TITLE_GENRE + " = ?, " +
					COL_TITLE_INFO + " = ?, " +
					COL_TITLE_LIKE + " = ?, " +
					COL_TITLE_STAR + " = ?, " +
					COL_TITLE_RELEASE + " = ? " +
					COL_TITLE_OTT + " = ? " +
					" WHERE " + COL_TITLE_NO + " = ?";
	
	// 작품 제목 검색
	public static final String TITLE_SELECT_BY_NAME = 
			"SELECT * FROM " + TABLE_TITLE +
			" WHERE " + COL_TITLE_NAME + " = ?";
	
	// 작품 장르 검색
	// 범죄 밀리터리 판타지 코미디 애니메이션 멜로/로맨스 미스터리 공포(호러) 액션
	public static final String TITLE_SELECT_BY_GENRE = 
			"SELECT * FROM " + TABLE_TITLE +
			" WHERE " + COL_TITLE_GENRE + " = ?";
	
	// 작품 삭제
		public static final String TITLE_DELETE =
				"DELETE " + TABLE_TITLE +
				" WHERE " + COL_TITLE_NO + " = ?";
		
	
	
	
	
	

}
