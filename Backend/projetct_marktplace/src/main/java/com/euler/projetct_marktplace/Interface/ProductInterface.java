package com.euler.projetct_marktplace.Interface;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductInterface {
    public Boolean registerProduct(MultipartFile imagem, String nomeProduto, Double precoProduto, String descricao,String status, String categoria);
    public List<Map<String, String>> searchProduct(String nomeProduto, String status, String categoria);
    public Boolean deleteProduct(int idProduto);
}
