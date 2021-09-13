package com.example.demo1.controller;

import com.example.demo1.db.service.api.MerchantService;
import com.example.demo1.domain.Customer;
import com.example.demo1.domain.Merchant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity add(@RequestBody Merchant merchant){
        Integer id = merchantService.add(merchant);
        if (id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Merchant merchant = merchantService.get(id);
        if (merchant != null){
            return new ResponseEntity<>(merchant, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity getAll(){
        List<Merchant> merchants = merchantService.getMerchant();
        return new ResponseEntity<>(merchants , HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        Merchant merchant = merchantService.get(id);
        if (merchant != null){
            merchantService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product with id: "+id+" does not exist");
    }
}
