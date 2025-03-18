package com.bazar_elamigo.elamigo_bazar.repository.Sale;

import com.bazar_elamigo.elamigo_bazar.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

    //Consulta para obtener ventas de un día especifico
    @Query("SELECT s FROM Sale s WHERE s.dateOfSale =:date")
    List<Sale> findByDateOfSale(LocalDate date);
}
