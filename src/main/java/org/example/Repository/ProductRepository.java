package org.example.Repository;
import java.util.List;

import org.example.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("From Product where productTitle =: productTitle")
    List<Product> findByTitle(@Param("productTitle") String productTitle);
}
