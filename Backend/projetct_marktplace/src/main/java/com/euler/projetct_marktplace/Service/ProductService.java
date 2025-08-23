package com.euler.projetct_marktplace.Service;

import com.euler.projetct_marktplace.Interface.ProductInterface;
import com.euler.projetct_marktplace.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ProductService implements ProductInterface {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean registerProduct(MultipartFile imagem, String nomeProduto, Double precoProduto, String descricao,String status, String categoria){
        try {
            return productRepository.registerProduct(imagem.getBytes(), nomeProduto, precoProduto, descricao,status, categoria);
        }catch (Exception e){
            throw new RuntimeException("Erro ao registrar produto!");
        }
    }

    @Override
    public List<Map<String, String>> searchProduct(String nomeProduto, String status, String categoria){
        return productRepository.searchProduct(nomeProduto, status, categoria);
    }

    @Override
    public Boolean deleteProduct(int idProduto){
        return productRepository.deleteProduct(idProduto);
    }

}
