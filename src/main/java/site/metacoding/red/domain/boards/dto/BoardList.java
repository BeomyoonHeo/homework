package site.metacoding.red.domain.boards.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardList {
	private Integer id;
	private String title;
	private String usersName;
	
	public String boardColors(Integer id){
		String[] color = {
				"primary",
				"success",
				"danger",
				"info",
				"warning",
				"active",
				"light"
				};
		ArrayList<String> colors = new ArrayList<>();
		for (String string : color) {
			colors.add(string);
		}
		if(colors.size() <= id) {
			for(;id>colors.size(); ) {
				id -= colors.size();
			}
		}

		return colors.get(id+1);
	}

}
