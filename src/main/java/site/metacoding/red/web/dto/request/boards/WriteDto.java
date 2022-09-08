package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Getter
@Setter
//update와 insert로 함께 사용 할 수 있다 - saveDto
public class WriteDto {
	private String title;
	private String content;
	
	public Boards toEntity(Integer id) {
		Boards boards = new Boards(this.title, this.content, id);
		//Wrapping class를 사용하면 null을 받지 않기 때문에 0을 넣음
		return boards;
	}

}
