package org.example.Service;
import org.example.Entity.Seller;
import org.example.Entity.Product;
import org.example.Exception.ProductNotFoundException;
import org.example.Main;
import org.example.Repository.SellerRepository;
import org.example.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductService {
    ProductRepository productRepository;
    SellerRepository sellerRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository){
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    //Return all products
    public List<Product> getAllProducts(){
        Main.log.info("Getting all products");
        return productRepository.findAll();
    }

    //Add a product and return it
    public Product saveProduct(int sellerId, Product p) throws ProductNotFoundException {
        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
        Seller s;

        // Seller name logic check
        if (optionalSeller.isEmpty()) {
            throw new ProductNotFoundException("Seller was not found, please try again.");
        } else {
            s = optionalSeller.get();
        }

        // Product price and title logic check
        if (p.getProductPrice() == 0 || p.getProductTitle().trim().isEmpty()) {
            throw new ProductNotFoundException("Product must have a name and price greater than zero.");
        } else {

            int randomProductId = generateRandomProductId();
            p.setProductId(randomProductId);

            Product savedProduct = productRepository.save(p);
            s.getProducts().add(savedProduct);
            sellerRepository.save(s);
            return savedProduct;
        }
    }

    private int generateRandomProductId() {
        Random random = new Random();
        return random.nextInt(9999) + 1;
    }

    //Return all products by productTitle
    public List<Product> getAllProductsByTitle(String productTitle) {
        return productRepository.findByTitle(productTitle);
    }

    //Return products by productId
    public Product getById(int productId) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if(p.isEmpty()){
            throw new ProductNotFoundException("Product was not found, please try again..");
        }else{
            return p.get();
        }
    }

    //Delete product by Id
    public Product deleteProduct(int productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product was not found, please try again..");
        }else{
        Product product = productOptional.get();
        productRepository.delete(product);
        Main.log.info("ProductService: deleting product for ID: "+productId+" named: "+product);
        return product;}
    }

    //Update product and return the update
    public Product updateProduct(int productId, Product newProduct) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.get();

        //Set updated product name
        product.setProductTitle(newProduct.getProductTitle());
        //Set updated product price
        product.setProductPrice(newProduct.getProductPrice());

        //validate updated product price and name
        if (product.getProductPrice() == 0 || product.getProductTitle().isEmpty()) {
            throw new ProductNotFoundException("Product must have a name and price greater than zero.");
        } else {
            productRepository.save(product);
            return product;
        }
    }
}