package com.bazar_elamigo.elamigo_bazar.rest.controller.Client;

import com.bazar_elamigo.elamigo_bazar.model.Client;
import com.bazar_elamigo.elamigo_bazar.service.Client.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@RestController
public class ClientController {
    @Autowired
    IClientService clientServ;

    @PostMapping("/new")
    public ResponseEntity<String> createClient(@Valid @RequestBody Client client, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>("Error en la validación del cliente ", HttpStatus.BAD_REQUEST);
        }
        clientServ.newClient(client);
        return new ResponseEntity<>("Cliente creado", HttpStatus.CREATED);
    }
    @GetMapping("/read/{client_id}")
    public ResponseEntity<Client> readClient(@PathVariable Long client_id){
        Client client = clientServ.readCliente(client_id);
        return new ResponseEntity<>(client, HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{client_id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long client_id){
        clientServ.deleteClient(client_id);
        return new ResponseEntity<>("Cliente eliminado ", HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{client_id}")
    public ResponseEntity<String> updateClient(@PathVariable Long client_id, @RequestBody Client updatedClient){
        clientServ.updateClient(client_id, updatedClient);
        return new ResponseEntity<>("Cliente actualizado", HttpStatus.OK);
    }

    @GetMapping("/readAllClients")
    public ResponseEntity<List<Client>> clientList(){
        try{
            List <Client> clients = clientServ.bringClientList();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
