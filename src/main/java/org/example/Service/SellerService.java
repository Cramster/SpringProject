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

    public List<Seller> getAllSellers(){
        Main.log.info("Getting all sellers");
        return sellerRepository.findAll();
    }

//    public Seller saveSeller(Seller seller) throws SellerException {
//        List<Seller> sellerList = getAllSellers();
//        if (seller.getSellerId() == 0 ) {
//            Main.log.warn("Seller's exception due to sellers name cannot be zero");
//            throw new SellerException("Seller's id cannot be zero.");
//        }
//        if (seller.getSellerName().isEmpty()) {
//            Main.log.warn("Seller's exception due to sellers name cannot be Null");
//            throw new SellerException("Seller's name cannot be Null.");
//        }
//        if (sellerList.contains(seller)){
//            Main.log.warn("Seller's exception due to duplicate sellers name.");
//            throw new SellerException("Duplicate sellers name: " + seller);
//        }
//        Main.log.info("Adding a seller" + seller);
//        return sellerRepository.save(seller);
//    }
public Seller saveSeller(Seller seller) throws SellerException {
    List<Seller> sellerList = getAllSellers();


    if (seller.getSellerName().isEmpty()) {
        Main.log.warn("Seller's exception due to seller's name cannot be null");
        throw new SellerException("Seller's name cannot be null.");
    }

    // Check for duplicate seller names
    boolean isDuplicateName = sellerList.stream()
            .anyMatch(existingSeller -> existingSeller.getSellerName().equalsIgnoreCase(seller.getSellerName()));

    if (isDuplicateName) {
        Main.log.warn("Seller's exception due to duplicate seller's name.");
        throw new SellerException("Duplicate seller's name: " + seller.getSellerName());
    }

    Main.log.info("Adding a seller: " + seller);
    return sellerRepository.save(seller);
}


    public boolean isValidSeller(int sellerId) throws SellerException {
        List<Seller> sellerList = getAllSellers();
        return sellerList.stream().anyMatch(seller -> seller.getSellerId() == sellerId);
    }
    
}
