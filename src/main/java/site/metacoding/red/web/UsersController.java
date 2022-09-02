package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.domain.users.dto.JoinDto;
import site.metacoding.red.domain.users.dto.LoginDto;

@AllArgsConstructor
@Controller
public class UsersController {
	
	private final UsersDao usersDao;
	private final HttpSession session;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return"users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
	
	@PostMapping("/join")
	public String instert(JoinDto joinDto) {
		Integer confirm = usersDao.insert(joinDto);
		if(confirm == null) {
			return "redirect:/joinForm";
		}else {
			return "redirect:/loginForm";
		}
	}
	
	@PostMapping("/login")
	public String login(LoginDto loginDto) {
		
		Users userinfo = usersDao.login(loginDto);
		if (userinfo == null) {
			return "users/loginForm";
		}else {
			session.setAttribute("principal", userinfo);
			return "redirect:/";
		}
	}
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	

}
