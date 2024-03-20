package org.example.Controller;

import org.example.Entity.Product;
import org.example.Entity.Seller;
import org.example.Exception.ProductNotFoundException;
import org.example.Exception.SellerException;
import org.example.Service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {
    SellerService sellerService;
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }
    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllArtist(){
        List<Seller> artists = sellerService.getAllSellers();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }
    @PostMapping("/seller")
    public ResponseEntity<Seller> addArtist(@RequestBody Seller s) throws SellerException {
        try {
            Seller seller = sellerService.saveSeller(s);
            return new ResponseEntity<>(seller, HttpStatus.CREATED);
        } catch (SellerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("seller/{sellerId}")
    public ResponseEntity<Seller> getSellerById(@PathVariable int sellerId){
        try{
            Seller s = sellerService.getById(sellerId);
            return new ResponseEntity<>(s, HttpStatus.OK);
        }catch (SellerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
