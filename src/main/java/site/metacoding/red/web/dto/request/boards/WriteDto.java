package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Getter
@Setter
public class WriteDto {
	private String title;
	private String content;
	
	public Boards toEntity(Integer usersId) {
		Boards boards = new Boards(this.title, this.content, usersId);
		//Wrapping class를 사용하면 null을 받지 않기 때문에 0을 넣음
		return boards;
	}

}
