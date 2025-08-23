package com.euler.projetct_marktplace.Repository;


import com.euler.projetct_marktplace.Util.JwtUtil;
import com.euler.projetct_marktplace.Util.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository  {
    private final JdbcTemplate jdbcTemplate;
    private final JwtUtil jwtUtil;

    public UserRepository(JdbcTemplate jdbcTemplate, JwtUtil jwtUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, String> login(String email, String password) {
        String sql = "SELECT * From usuarios WHERE email = ? and password = ?";

        try {
            Map<String, Object> userLogin = jdbcTemplate.queryForMap(sql, email, password);
            Map<String, String> result = new HashMap<>();

            result.put("email", (String) userLogin.get("email"));
            result.put("token", (String) jwtUtil.generateToken(email));
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean register(String nome, String email, String password) {
        String sql = "INSERT INTO usuarios (nome, email, password) VALUES (?, ?, ?)";

        try{
            jdbcTemplate.update(sql,nome,email,password);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
