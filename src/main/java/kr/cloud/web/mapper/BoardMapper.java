package kr.cloud.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import kr.cloud.web.entity.Board;
import kr.cloud.web.entity.SearchCriteria;

@Mapper
public interface BoardMapper {
	public List<Board> selectAll();
	
	public Board goBoardContent(int idx);
	
	public int updateBoard(int idx);

	public List<Board> search(SearchCriteria criteria);
	
	public int boardInsert(Board board);
	
	@Delete("DELETE FROM BOARD WHERE IDX = #{idx}")
	public int boardDelete(int idx);
	
}
