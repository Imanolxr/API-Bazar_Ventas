package com.bazar_elamigo.elamigo_bazar.repository.Client;

import com.bazar_elamigo.elamigo_bazar.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
}
