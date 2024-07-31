package com.Ecommerce_website.Product_catalog.controller;

import com.Ecommerce_website.Product_catalog.config.Ordermodel;
import com.Ecommerce_website.Product_catalog.model.Productmodel;
import com.Ecommerce_website.Product_catalog.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class Productcontroller {
    @Autowired
    private Productservice productservice;
    @PostMapping("/admin/addproduct")
    public String addproduct(@RequestBody Productmodel product){
        productservice.addproduct(product);
        return "";
    }
    @PutMapping("/admin/update-price/{id}")
    public String updateprice(@PathVariable("id") Integer id, @RequestParam Long price){
        productservice.updateprice(id,price);
        return "";
    }
    @PutMapping("/admin/update-quantity/{id}")
    public String updatequantity(@PathVariable("id") Integer id, @RequestParam Integer quantity){
        productservice.updatequantity(id, quantity);
        return "";
    }
    @DeleteMapping("/admin/delete-product/{id}")
    public String deleteproduct(@PathVariable("id") Integer id){
        productservice.deleteproduct(id);
        return "";
    }
    @PutMapping("/admin/orders/updateorder/{orderid}/{status}")
    public String updateorderstatus(@PathVariable("orderid") Integer orderid, @PathVariable("status") String status){
        productservice.updateorderstatus(orderid, status);
        return "";
    }
    @GetMapping("/orders/getallorders")
    public Iterable<Ordermodel> getallorders(){
        return productservice.getallorders();
    }
}
