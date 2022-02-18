package com.example.demo2.elkproduct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/elk/product")
@Slf4j
public class ProductController {

    private List<Product> getProducts(){
        return Stream.of(new Product(1,"phone"),
                         new Product(2,"lap"),
                         new Product(3,"comp")).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        List<Product> products=getProducts();
        Product product=products.stream().filter(u->u.getId()==id).findAny().orElse(null);
        if(product!=null){
            log.info("product found :{}",product);
            return product;
        }
        else{
            try{
                throw new Exception();
            } catch (Exception e){
                //e.printStackTrace();
                log.error("User not found with id :{}",id);
            }
            return new Product();
        }
    }
}
