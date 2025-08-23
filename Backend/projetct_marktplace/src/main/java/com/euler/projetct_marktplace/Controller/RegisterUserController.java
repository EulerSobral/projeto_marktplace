package com.euler.projetct_marktplace.Controller;


import com.euler.projetct_marktplace.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/cadastro")
public class RegisterUserController {
    private final UserService  userService;


    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> user) {
        try{
            String nome = user.get("nome");
            String email = user.get("email");
            String password = user.get("password");

            Boolean resulst = userService.register(nome, email, password);
            if(resulst){
                return ResponseEntity.status(200).body("Usuário registrado com sucesso!");
            }
            return ResponseEntity.status(400).body("Erro ao registrar o usuário!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body("Erro ao registrar o usuário!");
        }
    }
}
