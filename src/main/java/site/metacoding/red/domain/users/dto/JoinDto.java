package site.metacoding.red.domain.users.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDto {
	private String username;
	private String password;
	private String email;

}
