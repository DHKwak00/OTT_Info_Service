package ott.java.member;

import java.util.ArrayList;

public interface TitleDAO {
	// 작품 등록
	public abstract int insert(TitleDTO dto);
	
	// 작품 전체 검색
	public abstract ArrayList<TitleDTO> select();

	// 작품 전체 검색 (제목순)
	public abstract ArrayList<TitleDTO> selectByName();
	
	// 작품 전체 검색 (좋아요순)
	public abstract ArrayList<TitleDTO> selectByLike();
	
	// 작품 정보 수정
	public abstract int update(int titleNo, TitleDTO dto);
	
	// 작품 제목 검색
	public abstract ArrayList<TitleDTO> selectTitle(String titleName);
	
	// 작품 장르별 검색
	public abstract TitleDTO selectGenre(String titleGenre);
	
	// 작품 삭제
	public abstract int delete(int titleNo);
	
//	// 좋아요
//	public abstract int likeIt(TitleDTO dto);
//	
//	// 좋아요 취소
//	public abstract int likeCancel(TitleDTO dto);

}
