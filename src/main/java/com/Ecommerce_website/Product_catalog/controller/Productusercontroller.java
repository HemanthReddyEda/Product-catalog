package com.Ecommerce_website.Product_catalog.controller;

import com.Ecommerce_website.Product_catalog.config.Ordermodel;
import com.Ecommerce_website.Product_catalog.model.Productmodel;
import com.Ecommerce_website.Product_catalog.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class Productusercontroller {
    @Autowired
    private Productservice productservice;
    @GetMapping("/getallproducts")
    public List<Productmodel> getallproducts(){
        return productservice.getallproducts();
    }
    @GetMapping("/getproduct/{category}")
    public List<Productmodel> getproductbycategory(@PathVariable("category") String category){
        return productservice.getproductbycategory(category);
    }
    @GetMapping("/getproduct/under-price/{price}")
    public List<Productmodel> getproductunderprice(@PathVariable("price") Long price){
        return productservice.getproductunderprice(price);
    }
    @GetMapping("/getproduct/{id}")
    public Productmodel getproductbyid(@PathVariable("id") Integer id){
        return productservice.getproductbyid(id);
    }
    @PostMapping("/getproduct/{id}/orders/placeorder")
    public Ordermodel placeorder(@PathVariable("id") Integer id, @RequestBody Ordermodel ordermodel){
        return productservice.placeorder(id,ordermodel);
    }

}
