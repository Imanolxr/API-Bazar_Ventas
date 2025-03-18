package com.bazar_elamigo.elamigo_bazar.rest.controller.Sale;

import com.bazar_elamigo.elamigo_bazar.exception.ProductNotFoundException;
import com.bazar_elamigo.elamigo_bazar.model.Sale;
import com.bazar_elamigo.elamigo_bazar.rest.Dto.SaleDTO;
import com.bazar_elamigo.elamigo_bazar.service.Sale.ISaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {
    @Autowired
    ISaleService saleServ;

    @PostMapping("/newSale")
    public ResponseEntity<String> newSale(@Valid @RequestBody Sale sale, BindingResult result){
        System.out.println("Venta recibida: " + sale);  // Para verificar el objeto sale
        System.out.println("Productos de la venta: " + sale.getProductList());  // Para verificar la lista de productos
        if (result.hasErrors()){
            return new ResponseEntity<>("Error en la validación", HttpStatus.BAD_REQUEST);
        }
        saleServ.newSale(sale);
        return new ResponseEntity<>("Venta Realizada con exito", HttpStatus.CREATED);
    }

    @GetMapping("/read/{sale_id}")
    public ResponseEntity<SaleDTO> readSale(@PathVariable Long sale_id){
        SaleDTO saleDTO = saleServ.readSaleToDTO(sale_id);
        return new ResponseEntity<>(saleDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sale_id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long sale_id){
        saleServ.deleteSale(sale_id);
        return new ResponseEntity<>("Venta Eliminada" , HttpStatus.ACCEPTED);
    }

    @PutMapping("/modify/{sale_id}")
    public ResponseEntity<String> modifySale(@Valid @PathVariable Long sale_id, BindingResult result){
        saleServ.modifySale(sale_id);
        return new ResponseEntity<>("Venta Actualizada" , HttpStatus.OK);
    }

    @GetMapping("/getAllSales")
    public ResponseEntity<List<Sale>> getAllSales(){
        try{
            List <Sale> listOfSales = saleServ.saleList();
            return new ResponseEntity<>(listOfSales, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/salesInDay/{date}")
    public ResponseEntity<List<Sale>> saleListInADay(@PathVariable LocalDate date){
        List<Sale> sales = saleServ.saleListInADay(date);
        if (sales.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sales);
    }
}
