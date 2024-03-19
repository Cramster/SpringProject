package org.example.Service;

import org.example.Entity.Seller;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

// Get All Sellers
    public List<Seller> getAllSellers(){
        Main.log.info("Getting all sellers");
        return sellerRepository.findAll();
    }

// Add a Seller to SellerList
public Seller saveSeller(Seller seller) throws SellerException {
    List<Seller> sellerList = getAllSellers();

// Logic to check to make sure Seller Name is not Empty/Blank
    if (seller.getSellerName().isEmpty()) {
        Main.log.warn("Seller's exception due to seller's name cannot be null");
        throw new SellerException("Seller's name cannot be null.");
    }

    // Logic to check for  duplicate seller names
    boolean isDuplicateName = sellerList.stream()
            .anyMatch(existingSeller -> existingSeller.getSellerName().equalsIgnoreCase(seller.getSellerName()));

    if (isDuplicateName) {
        Main.log.warn("Seller's exception due to duplicate seller's name.");
        throw new SellerException("Duplicate seller's name: " + seller.getSellerName());
    }

    Main.log.info("Adding a seller: " + seller);
    return sellerRepository.save(seller);
    }
}
