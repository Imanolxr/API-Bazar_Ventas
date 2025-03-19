package com.bazar_elamigo.elamigo_bazar;

import com.bazar_elamigo.elamigo_bazar.exception.EmptyListException;
import com.bazar_elamigo.elamigo_bazar.model.Client;
import com.bazar_elamigo.elamigo_bazar.repository.Client.IClientRepository;
import com.bazar_elamigo.elamigo_bazar.service.Client.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {


    @Mock
    private IClientRepository clientRepo;
    @InjectMocks
    private ClientService clientServ;

    private Client expectedClient;

    @BeforeEach
    public void setUp() {
        // Inicializar un cliente para usar en las pruebas
        expectedClient = new Client(1L, "testName", "testLastName", "testDni", null);
    }

    @Test
    public void testNewClient(){
        when(clientRepo.save(Mockito.any(Client.class))).thenReturn(expectedClient);
        clientServ.newClient(expectedClient);

        verify(clientRepo, times(1)).save(expectedClient);
    }

    @Test
    public void testReadClient(){
        when(clientRepo.findById(1L)).thenReturn(Optional.of(expectedClient));
        final Client resultClient = clientServ.readCliente(1L);
        assertEquals(expectedClient, resultClient);
    }

    @Test
    public void testDeleteClient(){
        Long clientId = expectedClient.getClient_id();

        when(clientRepo.existsById(clientId)).thenReturn(true);
        doNothing().when(clientRepo).deleteById(clientId);
        clientServ.deleteClient(clientId);

        verify(clientRepo,times(1)).deleteById(clientId);
    }

    @Test
    public void testUpdateClient(){
        Long clientId = expectedClient.getClient_id();
        Client updatedClient = new Client(1L, "updatedTestName", "updatedTestLastName", "updatedTestDni", null );

        when(clientRepo.existsById(clientId)).thenReturn(true);


        clientServ.updateClient(clientId, updatedClient);

        verify(clientRepo, times(1)).save(updatedClient);
        verify(clientRepo, times(1)).existsById(clientId);

    }

    @Test
    public void testReadClientList(){

        List<Client> clientList = Arrays.asList(
                new Client(1L, "Client1", "LastName1", "DNI1", null),
                new Client(2L, "Client2", "LastName2", "DNI2", null)
        );
        when(clientRepo.findAll()).thenReturn(clientList);

        List<Client> result = clientServ.bringClientList();

        assertEquals(clientList, result);
        verify(clientRepo, times(1)).findAll();
    }

    @Test
    public void testBringClientList_WhenClientsNotExist(){
        when(clientRepo.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmptyListException.class, () -> {
            clientServ.bringClientList();
        });
        verify(clientRepo, times(1)).findAll();
    }



}
