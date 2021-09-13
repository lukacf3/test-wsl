package com.example.demo1.db.service.impl;

import com.example.demo1.db.repository.MerchantRepository;
import com.example.demo1.db.service.api.MerchantService;
import com.example.demo1.domain.Merchant;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MerchantServiceImpl implements MerchantService {

    private MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository){
        this.merchantRepository = merchantRepository;
    }
    @Override
    public List<Merchant> getMerchant() {
        return merchantRepository.getAll();
    }

    @Override
    public Merchant get(int id) {
        return merchantRepository.get(id);
    }

    @Override
    public Integer add(Merchant merchant) {
        return merchantRepository.add(merchant);
    }

    @Override
    public void delete(int id) {
        merchantRepository.delete(id);
    }
}
