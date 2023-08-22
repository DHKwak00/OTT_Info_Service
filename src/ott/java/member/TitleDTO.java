package ott.java.member;

import java.time.LocalDate;
import java.util.Date;

/*
TITLE_NO(NUMBER) : 작품 번호(PK)
TITLE_NAME(VARCHER2(20)) : 작품 이름
TITLE_RATING(VARCHER2(20) : 작품 시청등급
TITLE_GENRE(VARCHER2(20) : 작품 장르
TITLE_INFO(VARCHER2(100)) : 작품 정보
TITLE_LIKE(NUMBER) : 좋아요
TITLE_STAR(VARCHER2(20)) : 작품 평점
TITLE_RELEASE(VARCHER2(20)) : 작품 개봉일
TITLE_OTT(VARCHER2(20)) : 시청 가능한 OTT (넷플릭스, 왓챠, 디즈니)
 */
public class TitleDTO {
	private int titleNo;
	private String titleName;
	private String titleRating;
	private String titleGenre;
	private String titleInfo;
	private int titleLike;
	private String titleStar;
	private Date titleRel;
	private String titleott;
	
	public TitleDTO() {}

	public TitleDTO(int titleNo, String titleName, String titleRating, 
			String titleGenre, String titleInfo,int titleLike, 
			String titleStar, Date titleRel, String titleott) {
		super();
		this.titleNo = titleNo;
		this.titleName = titleName;
		this.titleRating = titleRating;
		this.titleGenre = titleGenre;
		this.titleInfo = titleInfo;
		this.titleLike = titleLike;
		this.titleStar = titleStar;
		this.titleRel = titleRel;
		this.titleott = titleott;
	}

	public int getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleRating() {
		return titleRating;
	}

	public void setTitleRating(String titleRating) {
		this.titleRating = titleRating;
	}

	public String getTitleGenre() {
		return titleGenre;
	}

	public void setTitleGenre(String titleGenre) {
		this.titleGenre = titleGenre;
	}

	public String getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(String titleInfo) {
		this.titleInfo = titleInfo;
	}

	public int getTitleLike() {
		return titleLike;
	}

	public void setTitleLike(int titleLike) {
		this.titleLike = titleLike;
	}

	public String getTitleStar() {
		return titleStar;
	}

	public void setTitleStar(String titleStar) {
		this.titleStar = titleStar;
	}

	public Date getTitleRel() {
		return titleRel;
	}

	public void setTitleRel(Date titleRel) {
		this.titleRel = titleRel;
	}

	public String getTitleott() {
		return titleott;
	}

	public void setTitleott(String titleott) {
		this.titleott = titleott;
	}

	@Override
	public String toString() {
		return "TitleDTO [titleNo=" + titleNo + ", titleName=" + titleName + ", titleRating=" + titleRating
				+ ", titleGenre=" + titleGenre + ", titleInfo=" + titleInfo + ", titleLike=" + titleLike
				+ ", titleStar=" + titleStar + ", titleRel=" + titleRel + ", titleott=" + titleott + "]";
	}
	
}
