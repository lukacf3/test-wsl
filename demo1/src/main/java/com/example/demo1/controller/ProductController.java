package com.example.demo1.controller;

import com.example.demo1.db.service.api.ProductService;
import com.example.demo1.db.service.api.request.UpdateProductRequest;
import com.example.demo1.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity add(@RequestBody Product product){
        Integer id = productService.add(product);
        if(id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity getAll(){
        List<Product> products = productService.getProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Product product = productService.get(id);
        if (product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @RequestBody UpdateProductRequest update){
        if(productService.get(id) != null){
            productService.update(id,update);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product with id: "+id+" does not exist");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        Product product = productService.get(id);
        if (product != null){
            productService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product with id: "+id+" does not exist");
    }
}
