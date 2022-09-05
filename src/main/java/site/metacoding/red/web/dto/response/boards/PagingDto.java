package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
	private Integer startNum;
	private Integer totalCount;
	private Integer totalPage;// 23 / 한페이지당 개수(10)
	private Integer currentPage;
	private Boolean isLast;
	private Boolean isFirst;
}
