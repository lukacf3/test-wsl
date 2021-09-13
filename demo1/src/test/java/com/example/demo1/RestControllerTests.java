package com.example.demo1;


import com.example.demo1.db.service.api.request.UpdateProductRequest;
import com.example.demo1.domain.Customer;
import com.example.demo1.domain.Merchant;
import com.example.demo1.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Merchant merchant;

    @Before
    public void createMerchant() throws Exception{
        if(merchant == null){
            merchant= new Merchant("name","email", "address");

            String id = mockMvc.perform(post("/merchant").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(merchant))).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
            merchant.setId(objectMapper.readValue(id,Integer.class));
        }
    }

    @Test
    public void customer() throws Exception {
        Customer customer = new Customer("Fero", "sfdfsdfd", "dfddsfsd", "sdfsdfdsf",23, "051649");

        //Add customer
        String id = mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        customer.setId(objectMapper.readValue(id,Integer.class));

        //get customer
        String customerJson = mockMvc.perform(get("/customer/"+ customer.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Customer returnedCustomer = objectMapper.readValue(customerJson, Customer.class);
        Assert.assertEquals(customer, returnedCustomer);

        //get all customers
        String listJson = mockMvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<Customer> customerList = objectMapper.readValue(listJson, new TypeReference<List<Customer>>(){});
        Assert.assertEquals(1, customerList.size());
        Assert.assertEquals(customer, customerList.get(0));
    }

    @Test
    public void merchant() throws Exception{
        //merchant is already created

        //get merchant
        String merchantJson = mockMvc.perform(get("/merchant/"+ merchant.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Merchant returnedMerchant = objectMapper.readValue(merchantJson, Merchant.class);
        Assert.assertEquals(merchant, returnedMerchant);
        //get all merchants
        String listJson = mockMvc.perform(get("/merchant").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Merchant> merchantList = objectMapper.readValue(listJson, new TypeReference<List<Merchant>>(){});
        Assert.assertEquals(1, merchantList.size());
        Assert.assertEquals(merchant, merchantList.get(0));

    }

    @Test
    public void product() throws Exception{
        Product product = new Product(merchant.getId(),"klavesnica","krasna",153.1,56 );
        //add product
        String id = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        product.setId(objectMapper.readValue(id,Integer.class));
        //get product by id
        String productJson = mockMvc.perform(get("/product/"+ product.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Product returnedProduct = objectMapper.readValue(productJson, Product.class);
        Assert.assertEquals(product, returnedProduct);
        //get all products
        String listJson = mockMvc.perform(get("/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Product> productList = objectMapper.readValue(listJson, new TypeReference<List<Product>>(){});
        Assert.assertEquals(1, productList.size());
        Assert.assertEquals(product, productList.get(0));
        //update product
        double updatePrice = product.getPrice() +10;
        int updateAvailable = product.getAvailable() + 5;
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getName(),product.getDescription(),updatePrice,updateAvailable);

        mockMvc.perform(patch("/product/"+product.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProductRequest))).andExpect(status().isOk());

        String updatedProductJson = mockMvc.perform(get("/product/"+ product.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Product updatedProduct = objectMapper.readValue(updatedProductJson,Product.class);
        assert updateAvailable == updatedProduct.getAvailable();
        assert updatePrice == updatedProduct.getPrice();

        //delete product

        mockMvc.perform(delete("/product/"+product.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
