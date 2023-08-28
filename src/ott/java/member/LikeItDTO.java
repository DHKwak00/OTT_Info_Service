package ott.java.member;

public class LikeItDTO {
	
	private int likeNo;
	private int memNo;
	private int titleNo;
	
	public LikeItDTO() {}

	public LikeItDTO(int likeNo, int memNo, int titleNo) {
		super();
		this.likeNo = likeNo;
		this.memNo = memNo;
		this.titleNo = titleNo;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	@Override
	public String toString() {
		return "LikeItDTO [likeNo=" + likeNo + ", memNo=" + memNo + ", titleNo=" + titleNo + "]";
	}

	
}
