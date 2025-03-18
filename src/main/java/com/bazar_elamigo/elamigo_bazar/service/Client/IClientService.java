package com.bazar_elamigo.elamigo_bazar.service.Client;

import com.bazar_elamigo.elamigo_bazar.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IClientService {

    //crear nuevo cliente
    public void newClient(Client client);

    //borrar cliente
    public void deleteClient(Long client_id);

    //mostrar cliente
    public Client readCliente(Long client_id);

    //modificar cliente
    public void updateClient(Long client_id);

    //lista completa de clientes
    public List<Client> bringClientList();

}
