package model;

import java.sql.SQLException;
import java.util.List;

public interface BoardDAO {

	// 전체 게시물 갯수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// 전체 게시물
	public List<BoardDTO> getBoard(int page, String field, String query) throws ClassNotFoundException, SQLException;

	// 게시물 삭제
	public int delete(int empno) throws ClassNotFoundException, SQLException;

	// 게시물 수정
	public int update(int empno, String job) throws ClassNotFoundException, SQLException;

	// 게시물 상세
	public BoardDTO getDetail(int num) throws ClassNotFoundException, SQLException;

	// 게시물 입력
	public int insert(BoardDTO b) throws ClassNotFoundException, SQLException;
}
