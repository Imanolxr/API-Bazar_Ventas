package com.bazar_elamigo.elamigo_bazar.service.Client;

import com.bazar_elamigo.elamigo_bazar.exception.EmptyListException;
import com.bazar_elamigo.elamigo_bazar.exception.ProductNotFoundException;
import com.bazar_elamigo.elamigo_bazar.exception.ResourceNotFoundException;
import com.bazar_elamigo.elamigo_bazar.model.Client;
import com.bazar_elamigo.elamigo_bazar.repository.Client.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClientService implements IClientService{
    @Autowired
    IClientRepository clientRepo;


    @Override
    @Transactional
    public void newClient(Client client) {
        clientRepo.save(client);

    }

    @Override
    @Transactional
    public void deleteClient(Long client_id) throws ResourceNotFoundException {
        if (!clientRepo.existsById(client_id)){
            throw new ResourceNotFoundException("Cliente con id: " + client_id + " no encontrado", "P-404");
        }
        try{
            clientRepo.deleteById(client_id);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el cliente"+ e.getMessage(), e);
        }
    }

    @Override
    public Client readCliente(Long client_id) throws ResourceNotFoundException {
        return clientRepo.findById(client_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con id: " + client_id + " no encontrado", "P-404"));
    }

    @Override
    public void updateClient(Long client_id, Client updatedClient) throws ResourceNotFoundException{
        if (!clientRepo.existsById(client_id)){
            throw new ResourceNotFoundException("Cliente con id: " + client_id + " no encontrado", "P-404");
        }
        try{
            this.newClient(updatedClient);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el cliente "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Client> bringClientList() throws EmptyListException {
        List <Client> clientList = clientRepo.findAll();
        if (clientList.isEmpty()){
            throw new EmptyListException("No hay clientes disponibles en la base de datos.", "P-404");
        }
        return clientList;
    }


}
