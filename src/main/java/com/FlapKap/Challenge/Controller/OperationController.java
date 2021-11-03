package com.FlapKap.Challenge.Controller;

import com.FlapKap.Challenge.Service.OperationService;
import com.FlapKap.Challenge.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/operationApi")
public class OperationController {

    @Autowired
    OperationService operationService;

    @PostMapping("/deposit")
    public String deposit (@RequestParam String username, @RequestParam String password, @RequestParam float amount) {
        return operationService.deposit(username, password, amount);
    }
    @PostMapping("/resetDeposit")
    public String resetDeposit (@RequestParam String username, @RequestParam String password) {
        return operationService.resetDeposit(username, password);
    }

    @PutMapping("/buy")
    public String buy (@RequestParam String username, @RequestParam String password, @RequestParam String productName) {
        return operationService.buy(username, password, productName);
    }

}
