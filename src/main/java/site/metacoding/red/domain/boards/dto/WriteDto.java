package site.metacoding.red.domain.boards.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WriteDto {
	private String title;
	private String content;
	private Integer usersId;

}
