package site.metacoding.red.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;

@RequiredArgsConstructor
@Controller
public class UsersController {
	private final UsersDao usersDao;
	private final HttpSession session; //스프링이 서버시작시에 IOC 컨테이너에 보관함.
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	
	@PostMapping("/login")// 로그인만예외로 select인데 post로 함
	public String login(LoginDto logindto, HttpServletRequest request){
		Users usersPs = usersDao.login(logindto);
		if(usersPs != null) {
			session.setAttribute("principal", usersPs); //인증된 유저
			return "redirect:/";
		}else {
			return "redirect:/loginForm";
		}
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
	
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm";
	}
}
