package com.euler.projetct_marktplace;

import com.euler.projetct_marktplace.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	@DisplayName("usuário cadastrado com sucesso")
	public void registerSucess() {
		String nome = "Fulano";
		String email = "fulano@teste.com";
		String password = "fulano12345";

		Boolean result = userRepository.register(nome, email, password);
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("usuário não foi cadastrado com sucesso")
	public void registerNotSucess() {
		String nome = null;
		String email = null;
		String password = null;

		Boolean result = userRepository.register(nome, email, password);
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("usuário entrou com sucesso no sistema")
	public void loginSucess() {
		String email = "fulano@teste.com";
		String password = "fulano12345";

		Optional<Map<String, String>> result = Optional.ofNullable(this.userRepository.login(email, password));
		assertThat(result.isPresent()).isTrue();
	}

	@Test
	@DisplayName("usuário não entrou com sucesso no sistema")
	public void loginNotSucess() {
		String email = "sicrano@teste.com";
		String password = "sicrano12345";

		Optional<Map<String, String>> result = Optional.ofNullable(this.userRepository.login(email, password));
		assertThat(result.isPresent()).isFalse();
	}

}
