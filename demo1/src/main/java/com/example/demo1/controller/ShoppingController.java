package com.example.demo1.controller;

import com.example.demo1.db.service.api.ShoppingService;
import com.example.demo1.db.service.api.request.BuyProductRequest;
import com.example.demo1.db.service.api.response.BuyProductResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
    @PostMapping("/buy")
    public ResponseEntity buy(@RequestBody BuyProductRequest request){
        BuyProductResponse response= shoppingService.buyProduct(request);
        if(response.isSuccess()){
            return ResponseEntity.ok().build();
        }else{
            return new ResponseEntity<>(response.getErrorMessage(), HttpStatus.PRECONDITION_FAILED);
        }

    }
}
