package com.bazar_elamigo.elamigo_bazar.service.Product;

import com.bazar_elamigo.elamigo_bazar.model.Product;

import java.util.List;

public interface IProductService {

    //create product
    public void saveProduct(Product product);

    //delete product
    public void deleteProduct(Long product_id);

    //read product
    public Product readProduct(Long product_id);

    //update product
    public void updateProduct(Long product_id,Product product);

    //bring list of products
    public List<Product> bringListOfProducts();

    //modify stock after a sale
    public void discountStock(double sale_quantity, Long product_id);

    //update product stock
    public void updateStock(Long product_id, Long quantity);
}
