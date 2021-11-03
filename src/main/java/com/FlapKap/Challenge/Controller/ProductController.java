package com.FlapKap.Challenge.Controller;


import com.FlapKap.Challenge.Model.Product;
import com.FlapKap.Challenge.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/productApi")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("getProductById/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @GetMapping("getProductByName/{name}")
    public Product getProductById(@PathVariable("name") String name) {
        return productService.getProductByName(name);
    }

    @PostMapping("/createProduct/{username}/{password}")
    public Product createProduct(@PathVariable("username") String username,
                                 @PathVariable("password") String password,
                                 @RequestParam float amountAvailable,
                                 @RequestParam float cost,
                                 @RequestParam String productName) {

        return productService.createProduct(username,password,amountAvailable, cost, productName);

    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id,
                                @RequestParam String username,
                                @RequestParam String password) {

        return productService.deleteProduct(id, username, password);

    }

    @PutMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") long id,
                                @RequestParam float amountAvailable,
                                @RequestParam float cost,
                                @RequestParam String productName,
                                @RequestParam String username,
                                @RequestParam String password) {

        return productService.updateProduct(id, amountAvailable, cost, productName, username, password);

    }

    @GetMapping("/getProductBySellerId/{id}")
    public List<Product> getProductBySellerId(@PathVariable("id") long id) {
        return productService.getProductsBySellerId(id);
    }

}
