package com.Ecommerce_website.Product_catalog.service;

import com.Ecommerce_website.Product_catalog.config.Ordermodel;
import com.Ecommerce_website.Product_catalog.feignclient.Ordersclient;
import com.Ecommerce_website.Product_catalog.model.Productmodel;
import com.Ecommerce_website.Product_catalog.repository.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Productservice {
    @Autowired
    private Productrepo productrepo;

    public String addproduct(Productmodel product) {
        productrepo.save(product);
        return "Product added";
    }

    public String updateprice(Integer id, Long price) {
        Optional<Productmodel> product = productrepo.findById(id);
        product.get().setPrice(price);
        productrepo.save(product.get());
        return "Price updated";
    }

    public String updatequantity(Integer id, Integer quantity) {
        Optional<Productmodel> product = productrepo.findById(id);
        product.get().setQuantity(quantity);
        productrepo.save(product.get());
        return "Quantity updated";
    }

    public String deleteproduct(Integer id) {
        productrepo.deleteById(id);
        return "Product deleted";
    }

    public List<Productmodel> getallproducts() {
        return productrepo.findAll();
    }

    public List<Productmodel> getproductbycategory(String category) {
        return productrepo.findByCategory(category);
    }

    public List<Productmodel> getproductunderprice(Long price) {
        return productrepo.findByPriceLessThan(price);
    }

    public Productmodel getproductbyid(Integer id) {
        return productrepo.getById(id);
    }

    @Autowired
    private Ordersclient ordersclient;
    public Ordermodel placeorder(Integer id, Ordermodel ordermodel) {
        Optional<Productmodel> productOpt = productrepo.findById(id);
        if (productOpt.isPresent()) {
            Productmodel product = productOpt.get();
            if (product.getQuantity() >= ordermodel.getOrder_quantity()) {
                Long unitPrice = (long) product.getPrice();
                ordermodel.calculateOrderPrice(unitPrice);
                System.out.println("Calculated Order Price: " + ordermodel.getOrder_price());
                product.setQuantity(product.getQuantity() - ordermodel.getOrder_quantity());
                productrepo.save(product);
                return ordersclient.placeorder(ordermodel);
            } else {
                throw new IllegalArgumentException("Not enough product quantity available");
            }
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void updateorderstatus(Integer orderid, String status) {
        ordersclient.updateorderstatus(orderid,status);
    }

    public Iterable<Ordermodel> getallorders() {
        return ordersclient.getallorders();
    }
}
