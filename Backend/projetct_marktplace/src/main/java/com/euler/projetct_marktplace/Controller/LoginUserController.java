package com.euler.projetct_marktplace.Controller;

import com.euler.projetct_marktplace.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.euler.projetct_marktplace.Util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/")
public class LoginUserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public LoginUserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> user) {
        try {
            String email = user.get("email");
            String password = user.get("password");

            Map<String, String> result = userService.login(email, password);

            if (result != null && result.get("email").equals(email)) {
                String token = jwtUtil.generateToken(email);

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login efetuado com sucesso!");
                response.put("token", token);

                return ResponseEntity.status(200).body(result);
            }
            return ResponseEntity.status(401).body("Credenciais inválidas!");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Erro no login: " + e.getMessage());
        }
    }
}
