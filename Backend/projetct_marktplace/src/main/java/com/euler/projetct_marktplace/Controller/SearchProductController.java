package com.euler.projetct_marktplace.Controller;

import com.euler.projetct_marktplace.Service.ProductService;
import com.euler.projetct_marktplace.Util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/produtos")
public class SearchProductController {
    private final ProductService productService;

    public SearchProductController(ProductService productService, JwtUtil jwtUtil) {
        this.productService = productService;

    }

    @GetMapping
    public List<Map<String, String>> searchProduct(@RequestParam(required = false) String productName,
                                                   @RequestParam(required = false) String status,
                                                   @RequestParam(required = false) String categoria){

            return productService.searchProduct(productName, status, categoria);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody int id){
        try{
            Boolean deleted = productService.deleteProduct(id);
            if(deleted){
                return ResponseEntity.status(200).body("Produto removido com sucesso");
            }
            return ResponseEntity.status(400).body("Erro ao tentar remover produto");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Erro ao tentar remover produto");
        }
    }
}
