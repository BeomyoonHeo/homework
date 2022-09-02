package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.domain.users.dto.JoinDto;
import site.metacoding.red.domain.users.dto.LoginDto;

public interface UsersDao {
	public Integer insert(JoinDto joinDto); // DTO 생각해보기
	public Users findById(Integer id);
	public Users login(LoginDto loginDto);
	public List<Users> findAll();
	public void update(Users users); // DTO 생각해보기
	public void delete(Integer id);
}
