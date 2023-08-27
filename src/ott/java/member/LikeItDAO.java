package ott.java.member;

public interface LikeItDAO {
	
	// 좋아요 등록
	public abstract int insert(LikeItDTO dto);
	
	// 좋아요 취소
	public abstract int delete(int likeNo);
}
