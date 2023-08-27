package ott.java.member;

public class LikeItDTO {
	
	private int likeNo;
	private String memId;
	private String titleName;
	
	public LikeItDTO() {}

	public LikeItDTO(int likeNo, String memId, String titleName) {
		super();
		this.likeNo = likeNo;
		this.memId = memId;
		this.titleName = titleName;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return "LikeItDTO [likeNo=" + likeNo + ", memId=" + memId + ", titleName=" + titleName + "]";
	}
	
	
	
}
