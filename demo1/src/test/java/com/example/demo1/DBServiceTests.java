package com.example.demo1;

import com.example.demo1.db.service.api.CustomerService;
import com.example.demo1.db.service.api.MerchantService;
import com.example.demo1.db.service.api.ProductService;
import com.example.demo1.db.service.api.request.UpdateProductRequest;
import com.example.demo1.domain.Customer;
import com.example.demo1.domain.Merchant;
import com.example.demo1.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBServiceTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    private Merchant merchant;

    @Before
    public void createMerchant(){
        if (merchant == null){
            merchant = new Merchant("name","email","address");
            Integer id = merchantService.add(merchant);
            assert id != null;
            merchant.setId(id);
        }
    }


    @Test
    public void customer(){
        Customer customer = new Customer("ferko","Mrkvicka", "email","test", 22, "xxx");
        Integer id = customerService.add(customer);
        assert id != null;
        customer.setId(id);
        Customer formDB = customerService.get(id);
        Assert.assertEquals(customer,formDB);

        List<Customer> customers = customerService.getCustomers();
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals(customer, customers.get(0));
    }

    @Test
    public void merchant(){

        Merchant fromDB = merchantService.get(merchant.getId());
        Assert.assertEquals(merchant,fromDB);

        List<Merchant> merchants = merchantService.getMerchant();
        Assert.assertEquals(1,merchants.size());
        Assert.assertEquals(merchant,merchants.get(0));

    }
    @Test
    public void product(){
        Product product = new Product(merchant.getId(),"klavesnica","pekna",15.5,100);
        Integer id = productService.add(product);
        assert id != null;
        product.setId(id);

        Product fromDB = productService.get(id);
        assert fromDB != null;

        Assert.assertEquals(product, fromDB);
        List<Product> products = productService.getProduct();
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(product,products.get(0));

        product.setAvailable(10);
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getName(), product.getDescription(),product.getPrice(),product.getAvailable());
        productService.update(id, updateProductRequest);
        Product fromDBAfterUpdate = productService.get(id);
        Assert.assertEquals(product, fromDBAfterUpdate);

    }

}
