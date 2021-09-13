package com.example.demo1.db.service.api;

import com.example.demo1.domain.Merchant;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MerchantService {
    List<Merchant> getMerchant();

    @NonNull
    Merchant get(int id);

    @NonNull
    Integer add(Merchant merchant); // returns generated id

    void delete(int id);

}
