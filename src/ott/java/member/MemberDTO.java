package ott.java.member;
/*
MEMBER_NO(NUMBER) : 회원 등록 번호(PK)
MEMBER_ID(VARCHER2(20)) : 회원 아이디(NOT NULL)
MEMBER_PASSWORD(VARCHER2(20)) : 회원 비밀번호(NOT NULL)
MEMBER_PHONE(VARCHER2(20)) : 회원 연락처(NOT NULL)
MEMBER_EMAIL(VARCHER2(100)) : 회원 이메일(NOT NULL) 
*/
public class MemberDTO {
	private int memNo;
	private String memId;
	private String memPw;
	private String memPhone;
	private String memEmail;
	
	public MemberDTO() {}

	public MemberDTO(int memNo, String memId, String memPw, String memPhone, String memEmail) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPw = memPw;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	@Override
	public String toString() {
		return "MemberDTO [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memPhone=" + memPhone
				+ ", memEmail=" + memEmail + "]";
	}
	
}
