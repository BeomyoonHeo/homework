package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;

@Getter
public class MainDto {
	private Integer id;
	private String title;
	private String username;
	private PagingDto paging;

}
