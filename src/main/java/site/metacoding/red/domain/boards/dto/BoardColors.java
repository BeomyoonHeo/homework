package site.metacoding.red.domain.boards.dto;

import java.util.ArrayList;



public class BoardColors {
	public String boardColors(Integer id){
		String[] color = {
				"",
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
		return colors.get(id);
	}

}
