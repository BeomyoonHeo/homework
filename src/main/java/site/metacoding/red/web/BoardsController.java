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
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
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

	//비정상 요청 체크, 인증 체크, 권한 체크
	//Restful api:자원을 요청하는 주소를 설계하는 api(protocol) - 읽어지게만 설계하면 된다
	   @GetMapping("/boards/{id}/updateForm")
	   public String updateForm(@PathVariable Integer id, Model model) {
		  //영속화
	      Boards boardsPS = boardsDao.findById(id);
	      Users principal = (Users) session.getAttribute("principal");
	      // 비정상 요청 체크
	      if (boardsPS == null) {
	         return "errors/badPage";
	      }
	      // 인증 체크
	      if (principal == null) {
	         return "redirect:/loginForm";
	      }
	      // 권한 체크 ( 세션 principal.getId() 와 boardsPS의 userId를 비교)
	      if (principal.getId() != boardsPS.getUsersId()) {
	         return "errors/badPage";
	      }   
	      model.addAttribute("boards", boardsPS);
	      return "boards/updateForm";
	   }
	
	
	@PostMapping("/boards/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto){
		Users principal = (Users)session.getAttribute("principal");
		//1.영속화
		Boards boardsPS = boardsDao.findById(id);
	      // 비정상 요청 체크
	      if (boardsPS == null) {
	         return "errors/badPage";
	      }
	      // 인증 체크
	      if (principal == null) {
	         return "redirect:/loginForm";
	      }
	      // 권한 체크 ( 세션 principal.getId() 와 boardsPS의 userId를 비교)
	      if (principal.getId() != boardsPS.getUsersId()) {
	         return "errors/badPage";
	      }
	      
	    //2. 변경
	    boardsPS.글수정(updateDto);
	    
	    //3. 수행
		boardsDao.update(boardsPS);
		return "redirect:/boards/"+ id;
		//사용자의 편의를 위해서 수정한 페이지를 보여준다. - 사용자 입장을 생각하기
	}
	
	
	
	
	
	// http://localhost:8000/?page=0
	@GetMapping({ "/", "/boards", "boards/main" })
	public String getBoardList(Model model, Integer page) {// 0->0, 1->10, 2->20
		if (page == null)
			page = 0; // offset은 서버쪽에서 처리해야된다 - 클라이언트가 처리하면 불편하다.
		int startNum = page * 3;
		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto pagingDto = boardsDao.paging(page);
		pagingDto.makeBlockInfo();
		model.addAttribute("boardsList", boardsList);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	@PostMapping("/boards/{id}/delete")
	public String deleteBoards(@PathVariable Integer id) {
		Boards boardsPS = boardsDao.findById(id);
		Users principal = (Users) session.getAttribute("principal");
		if(boardsPS == null || boardsPS.getUsersId() != principal.getId()) { //if는 비정상 로직을 타게 해서 걸러내는 필터 역할을 하는게 좋다.
			return "redirect:/boards/"+id;
		}
		//핵심로직보다 공통로직을 만드는것이 시간이 많이 든다.
		//인증 체크
		//권한 체크(세션 principal.getId() 와 boardsPS userId()를 비교
		boardsDao.delete(id); //핵심로직
		return "redirect:/";
	}
	
	@PostMapping("/boards/write")
	public String writeBoard(WriteDto writeDto) {
		// 1번 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");
		// 2번 principal null인지 확인하고 null이면 loginForm 리다이렉션해준다.
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// 조건 : entity에는 세션의 principal에 getId가 필요하다.

		return "redirect:/";
	}
	

	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		Boards detail = boardsDao.findById(id);
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
