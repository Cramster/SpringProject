
package org.example.Controller;
import org.example.Entity.Seller;
import org.example.Entity.Product;
import org.example.Exception.ProductNotFoundException;
import org.example.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value="/product", params = "productTitle")
    public ResponseEntity<List<Product>> getAllProductByTitle(@RequestParam String productTitle){
        List<Product> products;
        if (productTitle == null) {
            products = productService.getAllProducts();
        }else{
            products = productService.getAllProductsByTitle(productTitle);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "product")
    public ResponseEntity<List<Product>> getAllPProduct(){
        List<Product> products;
        products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//    @PostMapping("seller/{sellerId}/product")
//    public ResponseEntity<Product> addProduct(@RequestBody Product p, @PathVariable int productId) throws Exception {
//        Product product = productService.saveProduct(productId, p);
//        return new ResponseEntity<>(product, HttpStatus.CREATED);
//    }

    @PostMapping("seller/{sellerId}/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product p, @PathVariable int sellerId) throws Exception {
        Product product = productService.saveProduct(sellerId, p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId){
        try{
            Product p = productService.getById(productId);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("product/{productId}")
    public Product deleteProduct(@PathVariable int productId){
        return productService.deleteProduct(productId);
    }

    @PatchMapping("product/{productId}")
    public Product deleteProduct(@PathVariable int productId, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(productId, product);
    }
}
