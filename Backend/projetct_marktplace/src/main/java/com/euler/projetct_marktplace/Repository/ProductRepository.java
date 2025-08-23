package com.euler.projetct_marktplace.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, String>> searchProduct(String nomeProduto, String status, String categoria) {
        StringBuilder sql = new StringBuilder("SELECT * FROM produto WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            sql.append(" AND nome_produto ILIKE ?");
            params.add("%" + nomeProduto + "%");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = ?");
            params.add(status);
        }
        if (categoria != null && !categoria.isEmpty()) {
            sql.append(" AND categoria = ?");
            params.add(categoria);
        }
        try{
            return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {
                Map<String, String> result = new HashMap<>();
                result.put("nome_produto", rs.getString("nome_produto"));
                result.put("status", rs.getString("status"));
                result.put("categoria", rs.getString("categoria"));
                result.put("preco", String.valueOf(rs.getDouble("preco_produto")));
                result.put("descricao", rs.getString("descricao"));
                 result.put("imagem", Base64.getEncoder().encodeToString(rs.getBytes("imagem")));
                return result;
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public Boolean registerProduct(byte[] imagem, String nomeProduto, Double precoProduto, String descricao,String status, String categoria){
        String sql = "INSERT INTO produto (imagem, nome_produto,  preco_produto, descricao, status, categoria) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, imagem, nomeProduto, precoProduto, descricao,status, categoria);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean deleteProduct(int idProduto){
        String sql = "DELETE FROM produto WHERE id_produto = ?";

        try {
            jdbcTemplate.update(sql, idProduto);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
