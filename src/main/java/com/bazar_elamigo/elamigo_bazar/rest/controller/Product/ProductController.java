package com.bazar_elamigo.elamigo_bazar.rest.controller.Product;

import com.bazar_elamigo.elamigo_bazar.exception.ProductNotFoundException;
import com.bazar_elamigo.elamigo_bazar.model.Product;
import com.bazar_elamigo.elamigo_bazar.service.Product.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/product")
@RestController
public class ProductController {
    @Autowired
    IProductService productServ;


    @PostMapping("/create")
        public ResponseEntity<String> createProduct(@Valid @RequestBody Product product, BindingResult result){
            if(result.hasErrors()){
                return new ResponseEntity<>("Error en la validación del producto", HttpStatus.BAD_REQUEST);
            }
            productServ.saveProduct(product);
            return new ResponseEntity<>("Producto Creado", HttpStatus.CREATED);
        }

    @GetMapping("/read/{product_id}")
    public ResponseEntity<Product> readProduct(@PathVariable Long product_id){
        Product product = productServ.readProduct(product_id);
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long product_id){
        productServ.deleteProduct(product_id);
        return new ResponseEntity<>("Producto Eliminado", HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{product_id}")
    public ResponseEntity<String> updateProduct(@Valid @PathVariable Long product_id, @RequestBody Product product, BindingResult result){

        productServ.updateProduct(product_id, product);
        return new ResponseEntity<>("Producto Actualizado", HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            List<Product> productList = productServ.bringListOfProducts();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateStock/{product_id}/{quantity}")
    public ResponseEntity<String> updateStock(@PathVariable Long product_id,@PathVariable Long quantity){
        productServ.updateStock(product_id, quantity);
        return new ResponseEntity<>("Stock Actualizado", HttpStatus.OK);
    }


}
