package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.boards.mapper.ShowDetail;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsDao boardsDao;
	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")

	
	
	//http://localhost:8000/?page=0
	@GetMapping({ "/", "/boards", "boards/main",})
	public String getBoardList(Model model, Integer page) {// 0->0, 1->10, 2->20
		if(page == null) page = 0; //offset은 서버쪽에서 처리해야된다 - 클라이언트가 처리하면 불편하다.
		int startNum = page * 10;
		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto pagingDto = boardsDao.findPage(page);
		System.out.println(pagingDto.getIsFirst()); 
		System.out.println(pagingDto.getIsLast()); 
		
		model.addAttribute("boardsList", boardsList);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}

	@PostMapping("/boards")
	public String writeBoard(WriteDto writeDto) {
		//1번 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		Users principal = (Users)session.getAttribute("principal");
		//2번 principal null인지 확인하고 null이면 loginForm 리다이렉션해준다.
		if(principal == null) {
			return "redirect:/loginForm";
		}
		//3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		//조건 : dto를 entity로 변환해서 인수로 담아준다.
		//조건 : entity에는 세션의 principal에 getId가 필요하다.
		return "redirect:/";
	}

	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		ShowDetail detail = boardsDao.findById(id);
		model.addAttribute("detail", detail);
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		
		if (principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
}
