package com.euler.projetct_marktplace;

import com.euler.projetct_marktplace.Repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Produto registrado com sucesso")
    public void productRegisterSuccess(){
        String base64Imagem = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAA"
                + "AAC0lEQVR42mP8/x8AAwMCAO9pYQAAAABJRU5ErkJggg==";
        byte[] imagemBytes = Base64.getDecoder().decode(base64Imagem);

        String produto = "camiseta";
        Double preco = 50.00;
        String descricao = "descricao camiseta";
        String status = "disponível";
        String categoria = "Roupas";

        Boolean result = productRepository.registerProduct(imagemBytes, produto, preco, descricao, status, categoria);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Produto não foi registrado com sucesso")
    public void productNotRegisterSuccess(){
        String base64Imagem = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAA"
                + "AAC0lEQVR42mP8/x8AAwMCAO9pYQAAAABJRU5ErkJggg==";
        byte[] imagemBytes = Base64.getDecoder().decode(base64Imagem);

        String produto = null;
        Double preco = 50.00;
        String descricao = "descricao camiseta";
        String status = null;
        String categoria = "Roupas";

        Boolean result = productRepository.registerProduct(imagemBytes, produto, preco, descricao, status, categoria);
        assertThat(result).isFalse();
    }

    @DisplayName("Produto foi encontrado no sistema")
    @Test
    public void searchProduct(){
        String produto = "camiseta";
        String status = "disponível";
        String categoria = "Roupas";

        Optional<List<Map<String, String>>> result =   Optional.ofNullable(this.productRepository.searchProduct(produto, status, categoria));
        assertThat(result.isPresent()).isTrue();
    }

    @DisplayName("Produto não foi encontrado no sistema")
    @Test
    public void searchNotProduct(){
        String produto = "camiseta";
        String status = "indisponível";
        String categoria = "";

        Optional<List<Map<String, String>>> result =   Optional.ofNullable(this.productRepository.searchProduct(produto, status, categoria));
        assertThat(result.isEmpty()).isTrue();
    }


}