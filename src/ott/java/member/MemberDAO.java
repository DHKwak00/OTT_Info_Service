package ott.java.member;

public interface MemberDAO {
	// 회원 가입
	public abstract int insert(MemberDTO dto);
	
	// 회원 정보 수정
	public abstract int update(String memId, MemberDTO dto);
	
	// 회원 탈퇴
	public abstract int delete(String memId);
	
	// 회원 전체 검색
	public abstract MemberDTO getInfo(String id);
	
	// 회원 로그인
	public abstract int login(String id, String pw);

	// 관리자 로그인
	public abstract int loginAdm(String id, String pw);

	
}
