package com.Ecommerce_website.Product_catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productmodel {
    @Id
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String image;
    private int quantity;
}
