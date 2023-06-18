package com.foodstore.foodstore.service;

import com.foodstore.foodstore.domain.Cliente;
import com.foodstore.foodstore.repository.ClienteRepository;
import com.foodstore.foodstore.service.exception.ClienteMissingValueException;
import com.foodstore.foodstore.service.exception.ClienteNotFoundException;
import com.foodstore.foodstore.service.exception.ClienteRepeatedException;
import com.foodstore.foodstore.service.exception.ClientesEmptyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    Cliente cliente = new Cliente("02721031080", "Lucas", "lucas@outlook.com", "ipiranga 1000", "51997000000");
    Cliente cliente2 = new Cliente("02721031080", "Luke", "lucas@outlook.com", "ipiranga 1000", "51997000000");

    //save Cliente tests
    @Test
    @DisplayName("Cliente Save test")
    public void shouldCreateCliente() {
        when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
        Cliente clienteSaved = clienteService.save(cliente);

        Assert.assertEquals("Lucas", clienteSaved.getNome());
    }

    @Test
    @DisplayName("ClienteMissingValueException test")
    public void shouldThrowClienteMissingValueException() {
        when(clienteRepository.save(Mockito.any(Cliente.class))).thenThrow(ClienteMissingValueException.class);

        Assert.assertThrows(ClienteMissingValueException.class, () -> clienteService.save(cliente));
    }

    @Test
    @DisplayName("ClienteRepeatedException test")
    public void shouldThrowClienteRepeatedException() {
        when(clienteRepository.save(Mockito.any(Cliente.class))).thenThrow(ClienteRepeatedException.class);

        Assert.assertThrows(ClienteRepeatedException.class, () -> clienteService.save(cliente));
    }

    //findAll cliente tests
    @Test
    @DisplayName("Cliente FindAll test")
    public void shouldFindAllCliente() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente, cliente2));

        List<Cliente> clientes = clienteService.findAll();
        Assert.assertEquals(cliente, clientes.get(0));
        Assert.assertEquals(cliente2, clientes.get(1));
        Assert.assertTrue(clientes.size() == 2);
    }

    @Test
    @DisplayName("ClientesEmptyException test")
    public void shouldThrowClienteEmptyException() {
        when(clienteRepository.findAll()).thenThrow(ClientesEmptyException.class);

        Assert.assertThrows(ClientesEmptyException.class, () -> clienteService.findAll());
    }

    //findByCpf cliente tests
    @Test
    @DisplayName("Cliente FindByCpf test")
    public void shouldFindByCpfCliente() {
        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.ofNullable(cliente));

        Cliente clienteNew = clienteService.findByCpf(cliente.getCpf());
        Assert.assertEquals(cliente.getCpf(), clienteNew.getCpf());
        Assert.assertEquals(cliente.getNome(), clienteNew.getNome());
    }

    @Test
    @DisplayName("ClienteNotFoundException test")
    public void shouldThrowClienteNotFoundException() {
        when(clienteRepository.findByCpf("00000000000")).thenThrow(ClienteNotFoundException.class);

        Assert.assertThrows(ClienteNotFoundException.class, () -> clienteService.findByCpf("00000000000"));
    }

    //update cliente testes
    @Test
    @DisplayName("Cliente update test")
    public void shouldUpdateCliente() {
        when(clienteRepository.save(cliente2)).thenReturn(cliente2);
        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.ofNullable(cliente));
        Cliente clienteNew = clienteService.update(cliente.getCpf(), cliente2);
        Assert.assertEquals(cliente2.getCpf(), clienteNew.getCpf());
        Assert.assertEquals(cliente2.getNome(), clienteNew.getNome());
    }

    @Test
    @DisplayName("Cliente deleteByCpf test")
    public void shouldDeleteByCpfCliente() {
        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.ofNullable(cliente));
        doAnswer((invocation) -> {
            when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(null);
            return null;
        }).when(clienteRepository).deleteByCpf(cliente.getCpf());
        clienteRepository.deleteByCpf(cliente.getCpf());
        Assert.assertNull(clienteRepository.findByCpf(cliente.getCpf()));
    }
}
