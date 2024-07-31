package com.Ecommerce_website.Product_catalog.feignclient;


import com.Ecommerce_website.Product_catalog.config.Ordermodel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name="orders",url="http://localhost:8083")
public interface Ordersclient {
    @PostMapping("/orders/placeorder")
    public Ordermodel placeorder(@RequestBody Ordermodel ordermodel);
    @GetMapping("/orders/getorder/{iD}")
    public Optional<Ordermodel> getorderbyid(@PathVariable("iD") Integer orderid);
    @PutMapping("/orders/updateorder/{orderid}/{status}")
    public Ordermodel updateorderstatus(@PathVariable("orderid") Integer orderid,@PathVariable("status") String order_status);
    @DeleteMapping("/orders/cancelorder/{orderid}")
    public void deleteorder(@PathVariable("orderid") Integer orderid);
    @GetMapping("/orders/getallorders")
    public Iterable<Ordermodel> getallorders();
}
