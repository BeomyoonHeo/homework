package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDto {

	private Integer blockCount;
	private Integer currentBlock; // 상수 한페이지에 넘버 개수(5) 1 - 5, 6 - 10
	private Integer startPageNum;// 변수 1 -> 6 -> 11
	private Integer LastPageNum;// 변수 5 -> 10 -> 15
	private Integer totalCount;
	private Integer totalPage;// 23 / 한페이지당 개수(10)
	private Integer currentPage;
	private boolean isLast; // getter가 만들어지면 isLast() 이름으로 만들어짐. -> el에서는 last로 표현
	private boolean isFirst;// getter가 만들어지면 isLast() 이름으로 만들어짐. -> el에서는 last로 표현
	private String keyword;

	public void makeBlockInfo() {
		
		this.keyword = null;
		this.blockCount = 5;
		
		this.currentBlock = this.currentPage / this.blockCount;
		this.startPageNum = 1 + this.blockCount * this.currentBlock;
		this.LastPageNum = 5 + blockCount * currentBlock;

		if (totalPage <= LastPageNum)
			LastPageNum = totalPage;
	}

}
