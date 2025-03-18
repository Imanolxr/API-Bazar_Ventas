package com.bazar_elamigo.elamigo_bazar.service.Sale;

import com.bazar_elamigo.elamigo_bazar.exception.EmptyListException;
import com.bazar_elamigo.elamigo_bazar.exception.InsufficientStockException;
import com.bazar_elamigo.elamigo_bazar.exception.ProductNotFoundException;
import com.bazar_elamigo.elamigo_bazar.exception.ResourceNotFoundException;
import com.bazar_elamigo.elamigo_bazar.model.Product;
import com.bazar_elamigo.elamigo_bazar.model.Sale;
import com.bazar_elamigo.elamigo_bazar.model.SaleProduct;
import com.bazar_elamigo.elamigo_bazar.repository.Client.IClientRepository;
import com.bazar_elamigo.elamigo_bazar.repository.Product.IProductRepository;
import com.bazar_elamigo.elamigo_bazar.repository.Sale.ISaleRepository;
import com.bazar_elamigo.elamigo_bazar.repository.SaleProduct.ISaleProductRepository;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleDTO;
import com.bazar_elamigo.elamigo_bazar.rest.mapper.SaleDTOMapper;
import com.bazar_elamigo.elamigo_bazar.service.Product.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements ISaleService{
    @Autowired
    private ISaleRepository saleRepo;
    @Autowired
    private ISaleProductRepository saleProductRepo;
    @Autowired
    IClientRepository clientRepo;
    @Autowired
    IProductRepository productRepo;
    @Autowired
    IProductService prodServ;
    @Autowired
    private SaleDTOMapper saleDTOMapper;


    @Override
    @Transactional
    public void newSale(Sale sale) {

        sale.setDateOfSale(LocalDate.now());

        if(sale.getClient().getClient_id() == null || !clientRepo.existsById(sale.getClient().getClient_id())){

            clientRepo.save(sale.getClient());
            System.out.println("El cliente no existe en la base de datos, se creara un nuevo cliente");
        }
        //se calcula el total de la venta
        double total = 0;
        for(SaleProduct saleProduct: sale.getProductList()){
            System.out.println("id producto: " + saleProduct.getId());
            System.out.println("Cantidad: " + saleProduct.getQuantity());
            System.out.println("precio: " + saleProduct.getPrice());
            System.out.println("subtotal producto: " + saleProduct.getQuantity() * saleProduct.getPrice());

            total+= saleProduct.getPrice() * saleProduct.getQuantity();
            System.out.println("total:" + total);
        }
        sale.setTotal(total);

        Sale saleToSave = saleRepo.save(sale);


        for(SaleProduct saleProduct : sale.getProductList()){

            // 🔹 Verifica si realmente hay un objeto 'Product'
            if (saleProduct.getProduct() == null || saleProduct.getProduct().getProduct_id() == null) {
                throw new RuntimeException("El product_id en SaleProduct es nulo");
            }

            Product product = productRepo.findById(saleProduct.getProduct().getProduct_id())
                    .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado en la base de datos"));
            if (saleProduct.getQuantity() > product.getAvailableQuantity()){
                throw new InsufficientStockException("Stock Insuficiente para el producto: " + product.getName()+ "\n" +
                                                     "Stock disponible: " + product.getAvailableQuantity() + "\n"+
                                                     "Cantidad Solicitada: " + saleProduct.getQuantity());
            }

            prodServ.discountStock(saleProduct.getQuantity(), product.getProduct_id());

            saleProduct.setProduct(product);
            saleProduct.setSale(saleToSave);

            saleProductRepo.save(saleProduct);
        }
    }

    @Override
    public void deleteSale(Long sale_id) throws ResourceNotFoundException {
        if (!saleRepo.existsById(sale_id)){
            throw new ResourceNotFoundException("Venta no encontrada con el ID: " + sale_id, "P_404");
        }
        try{
            saleRepo.deleteById(sale_id);
        } catch (Exception e){
            throw new RuntimeException("Error al eliminar la venta: " + e.getMessage());
        }
    }

    @Override
    public Sale readSale(Long sale_id) throws ResourceNotFoundException{
        return saleRepo.findById(sale_id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con el ID: "+ sale_id, "P-404"));
    }

    @Override
    public void modifySale(Long sale_id) {
        if (!saleRepo.existsById(sale_id)){
            throw new ResourceNotFoundException("Venta no encontrada con el ID: " + sale_id, "P-404");
        }
        try{
            Sale updateSale = readSale(sale_id);
            this.newSale(updateSale);
        } catch (Exception e){
            throw new RuntimeException("Error al actualizar la venta: " + e.getMessage());
        }
    }

    @Override
    public List<Sale> saleList() throws EmptyListException {
        List<Sale> listOfSales = saleRepo.findAll();
        if (listOfSales.isEmpty()){
            throw new EmptyListException("No hay ventas disponibles en la base de datos.", "P-404");
        }
        return listOfSales;
    }

    @Override
    public List<Sale> saleListInADay(LocalDate date) {
        List<Sale> listOfSales = saleRepo.findByDateOfSale(date);
        if (listOfSales.isEmpty()){
            throw new EmptyListException("No hay clientes ventas disponibles en la base de datos.", "P-404");
        }
        return listOfSales;
    }

    @Override
    public SaleDTO readSaleToDTO(Long sale_id) {
        Sale sale = saleRepo.findById(sale_id).orElseThrow(() -> new EntityNotFoundException("Sale not found"));
        return saleDTOMapper.saleToSaleDTO(sale);
    }
}
