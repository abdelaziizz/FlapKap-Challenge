package com.FlapKap.Challenge.Service;

import com.FlapKap.Challenge.Model.Product;
import com.FlapKap.Challenge.Model.User;
import com.FlapKap.Challenge.Repository.ProductRepo;
import com.FlapKap.Challenge.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    public String deposit ( String username, String password, float amount ) {
        try {
            User user = userRepo.findByUsername(username);
            if (user.getPassword().equals(password) && user.getRole().equals("buyer")) {
                if ( amount == 5 || amount == 10 || amount == 20 || amount == 50 || amount == 100 ) {
                    user.setDeposit(user.getDeposit()+amount);
                    userRepo.save(user);
                    return "Successfully Charged";
                }
                else {
                    return "Amount Not Correct, Please insert one of the following ( 5, 10, 20, 50, 100 )";
                }
            }
            else {
                return "Authentication Failed";
            }
        }
        catch (Exception e) {
            return "Something went wrong";
        }
    }

    public String resetDeposit ( String username, String password) {
        try {
            User user = userRepo.findByUsername(username);
            if (user.getPassword().equals(password) && user.getRole().equals("buyer")) {
                    user.setDeposit(0);
                    userRepo.save(user);
                    return "Successfully Reset";
                }
            else {
                return "Authentication Failed";
            }
        }
        catch (Exception e) {
            return "Something went wrong";
        }
    }

    public String buy (String username, String password, String productName) {
        try {
            User user = userRepo.findByUsername(username);
            Product prod = productRepo.findByProductName(productName);
            if (user.getPassword().equals(password) &&
                    user.getRole().equals("buyer") &&
                    prod.getCost()<=user.getDeposit() &&
                    prod.getAmountAvailable()>0) {
                user.setDeposit(user.getDeposit()-prod.getCost());
                prod.setAmountAvailable(prod.getAmountAvailable()-1);
                userRepo.save(user);
                productRepo.save(prod);
                float deposit = user.getDeposit();
                String remaining = "";
                int hunds = (int) (deposit/100);
                if(hunds>0){
                    deposit=deposit-(hunds*100);
                }
                remaining+= "Hundreds : "+hunds+" ;";
                int fifties = (int) (deposit/50);
                if(fifties>0){
                    deposit=deposit-(fifties*50);
                }
                remaining+= "Fifties : "+fifties+" ;";
                int twenties = (int) (deposit/20);
                if(twenties>0){
                    deposit=deposit-(twenties*20);
                }
                remaining+= "Twenties : "+twenties+" ;";
                int tens = (int) (deposit/10);
                if(tens>0){
                    deposit=deposit-(tens*10);
                }
                remaining+= "Tens : "+tens+" ;";
                int fives = (int) (deposit/5);
                if(fives>0){
                    deposit=deposit-(fives*5);
                }
                remaining+= "Fives : "+fives +" ;";

                String response = "Transaction Done ";
                if (!remaining.equals("")){
                    response+="and remaining is ===> "+remaining;
                }
                return response;
            }
            else {
                return "Not Eligible";
            }
        }
        catch (Exception e) {
            return "Something went wrong";
        }
    }

}
