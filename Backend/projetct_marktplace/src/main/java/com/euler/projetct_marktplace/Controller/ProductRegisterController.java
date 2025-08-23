package com.euler.projetct_marktplace.Controller;


import com.euler.projetct_marktplace.Service.ProductService;
import com.euler.projetct_marktplace.Util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cadastro-produtos")
public class ProductRegisterController {
    private final ProductService productService;
    public ProductRegisterController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestParam("imagem") MultipartFile imagem,
                                             @RequestParam("nome-produto") String nomeProduto,
                                             @RequestParam("preco-produto") Double precoProduto,
                                             @RequestParam("descricao") String descricao,
                                             @RequestParam("status") String status,
                                             @RequestParam("categoria") String categoria){
        try {
            Boolean result = productService.registerProduct(imagem, nomeProduto, precoProduto, descricao,status, categoria);
            if(result) {
                return ResponseEntity.status(200).body("Produto registrado com sucesso!");
            }
            return  ResponseEntity.status(400).body("Erro ao registrar produto!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Erro ao registrar produto!");
        }
    }
}
