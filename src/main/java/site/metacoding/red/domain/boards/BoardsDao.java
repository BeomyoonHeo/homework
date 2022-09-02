package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.boards.dto.BoardDto;
import site.metacoding.red.domain.boards.dto.BoardList;
import site.metacoding.red.domain.boards.dto.UpdateDto;
import site.metacoding.red.domain.boards.dto.WriteDto;

public interface BoardsDao {
	public Integer insert(WriteDto wirteDto); // DTO 생각해보기
	public BoardDto findById(Integer id);
	public List<BoardList> findAll();
	public List<BoardList> findAllById(Integer id);
	public void update(UpdateDto updateDto); // DTO 생각해보기
	public BoardDto showBoard(Integer id);
	public void delete(Integer id);
}
