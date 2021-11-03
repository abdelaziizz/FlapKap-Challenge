package com.FlapKap.Challenge.Service;

import com.FlapKap.Challenge.Model.Product;
import com.FlapKap.Challenge.Model.User;
import com.FlapKap.Challenge.Repository.ProductRepo;
import com.FlapKap.Challenge.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    public List<Product> getProducts() {
        try {
            List<Product> products = productRepo.findAll();
            return products;
        } catch (Exception e) {
            return null;
        }
    }

    public Product createProduct(String username, String password, float amountAvailable, float cost, String productName) {
        try {
            User user = userRepo.findByUsername(username);
            if (user.getPassword().equals(password) && user.getRole().equals("seller")){
                Product prod = new Product(amountAvailable, cost, productName, user.getId());
               return productRepo.save(prod);
            }
            else {
                System.out.println("Authentication Failed");
                return null;
            }

        }
        catch (Exception e) {
            System.out.println("User Not Found");
            return null;
        }

    }

    public String deleteProduct(long id, String username, String password) {
        try {
            User user = userRepo.findByUsername(username);
            Product prod = productRepo.getById(id);
            if (user.getPassword().equals(password) && user.getRole().equals("seller") && user.getId()==prod.getSellerId()) {
                String name = productRepo.getById(id).getProductName();
                productRepo.deleteById(id);
                return name + " is deleted";
            }
            else {
                return "Failed";
            }
        } catch (Exception e) {
            return "Error while deleting";
        }
    }

    public String updateProduct(long id, float amountAvailable, float cost, String productName, String username, String password) {
        try {
            User seller = userRepo.findByUsername(username);
            Optional<Product> prod = productRepo.findById(id);
            if (prod.isPresent()) {
                Product _prod = prod.get();
                if (seller.getPassword().equals(password)) {
                    if (seller.getId() == _prod.getSellerId()) {
                        _prod.setProductName(productName);
                        _prod.setCost(cost);
                        _prod.setAmountAvailable(amountAvailable);
                        productRepo.save(_prod);
                        return "Product Updated";
                    } else {
                        return "Not Authorized";
                    }
                } else {
                    return "Password not correct";
                }
            } else {
                return "Product not updated";
            }
        } catch (Exception e) {
            return "Error Updating Product";
        }
    }

    public Product getProductById(long id) {
        try {
            return productRepo.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Product getProductByName(String name) {
        try {
            return productRepo.findByProductName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Product> getProductsBySellerId(long id) {
        try {
            return productRepo.findAllBySellerId(id);
        } catch (Exception e) {
            return null;
        }
    }

}
