package com.bazar_elamigo.elamigo_bazar.service.Product;

import com.bazar_elamigo.elamigo_bazar.exception.EmptyListException;
import com.bazar_elamigo.elamigo_bazar.exception.ProductNotFoundException;
import com.bazar_elamigo.elamigo_bazar.model.Product;
import com.bazar_elamigo.elamigo_bazar.repository.Product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepo;


    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long product_id) {
        if (!productRepo.existsById(product_id)){
            throw new ProductNotFoundException("Producto no encontrado con el ID: " + product_id);
        }
        try{
            productRepo.deleteById(product_id);
        } catch (Exception e){
            throw new RuntimeException("Error al eliminar el producto: " + e.getMessage());
        }

    }

    @Override
    public Product readProduct(Long product_id) {
        return productRepo.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con el id: " + product_id));
    }

    @Override
    public void updateProduct(Long product_id, Product product) {
        if(!productRepo.existsById(product_id)){
            throw new ProductNotFoundException("Producto no encontrado con el ID: " + product_id);
        }
        try{
            product.setProduct_id(product_id);
            productRepo.save(product);

        } catch (Exception e){
            throw new RuntimeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public List<Product> bringListOfProducts() {
        List<Product> products = productRepo.findAll();
        if(products.isEmpty()){
            throw new EmptyListException("No hay productos disponibles en la base de datos.", "P-404");
        }
        return products;
    }

    @Override
    public void discountStock(double sale_quantity, Long product_id) {
        Product updatedProductStock = productRepo.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con el id: " + product_id));
        updatedProductStock.setAvailableQuantity(updatedProductStock.getAvailableQuantity() - sale_quantity);
        updateProduct(product_id, updatedProductStock);
    }

    @Override
    public void updateStock(Long product_id, Long quantity) {
        if (quantity < 0){
            throw new IllegalArgumentException("La cantidad on puede ser negativa");
        }
        Product updatedProductStock = productRepo.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con el id: " + product_id));
        updatedProductStock.setAvailableQuantity(updatedProductStock.getAvailableQuantity() + quantity);
        updateProduct(product_id, updatedProductStock);
    }
}
