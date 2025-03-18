package com.bazar_elamigo.elamigo_bazar.repository.SaleProduct;

import com.bazar_elamigo.elamigo_bazar.model.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISaleProductRepository extends JpaRepository<SaleProduct, Long> {
}
