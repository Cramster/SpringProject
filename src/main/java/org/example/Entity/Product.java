package org.example.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
    
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @EqualsAndHashCode
    @ToString
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public int productId;
        public String productTitle;
        public double productPrice;
        @JsonIgnore
        @ManyToOne
        @JsonIgnoreProperties("products")
        public Seller seller;
    }