package site.metacoding.red.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.boards.dto.BoardColors;
import site.metacoding.red.domain.boards.dto.BoardDto;
import site.metacoding.red.domain.boards.dto.BoardList;
import site.metacoding.red.domain.boards.dto.UpdateDto;
import site.metacoding.red.domain.boards.dto.WriteDto;
import site.metacoding.red.domain.users.Users;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsDao boardsDao;
	private final HttpSession session;
	private Users userinfo;

	@GetMapping("/boards/writeForm")
	public String writeForm() {

		userinfo = (Users) session.getAttribute("principal");

		if (userinfo == null) {
			return "redirect:/";
		} else {
			return "/boards/writeForm";
		}
	}

	@PostMapping("/write")
	public String insert(WriteDto writeDto) {
		userinfo = (Users) session.getAttribute("principal");

		writeDto.setUsersId(userinfo.getId());
		if (writeDto.getTitle().equals(null) || writeDto.getTitle().equals("")) {
			return "redirect:/boards/writeForm";
		} else {
			boardsDao.insert(writeDto);
			return "redirect:/";
		}
	}

	@GetMapping({ "/", "/boards" })
	public String mainPage(Model model) {

		BoardColors boardcolor = new BoardColors();
		List<BoardList> blist = new ArrayList<>();
		blist.addAll(boardsDao.findAll());
		model.addAttribute("boards", blist);
		model.addAttribute("boardcolors", boardcolor);

		return "boards/main";
	}

	@GetMapping("boards/{id}")
	public String board(@PathVariable Integer id, Model model) {
		BoardDto board = boardsDao.findById(id);
		model.addAttribute("board", board);
		return "boards/detail";
	}

	@GetMapping("/boards/update")
	public String updateList(Model model) {
		userinfo = (Users) session.getAttribute("principal");
		if (userinfo.getId() == null) {
			return "redirect:/";
		} else {
			List<BoardList> blist = new ArrayList<>();
			blist.addAll(boardsDao.findAllById(userinfo.getId()));
			model.addAttribute("boards", blist);
			return "/boards/updateList";
		}
	}

	@PostMapping("/boards/update/{id}")
	public String updateboard(@PathVariable Integer id, Model model) {
		BoardDto updateDto = boardsDao.showBoard(id);
		model.addAttribute("updateDto", updateDto);
		model.addAttribute("id", id);
		return "boards/updateForm";
	}

	@PostMapping("/write/{id}")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		userinfo = (Users) session.getAttribute("principal");
		if (userinfo.getId() == null) {
			return "redirct:/loginForm";
		} else {
			updateDto.setId(id);
			boardsDao.update(updateDto);
			return "redirect:/";
		}
	}
	
	@GetMapping("/boards/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		userinfo = (Users)session.getAttribute("principal");
		if (userinfo.getId() == null) {
			return "redirct:/loginForm";
		} else {
			boardsDao.delete(id);
			return "redirect:/";
		}
	}

}
