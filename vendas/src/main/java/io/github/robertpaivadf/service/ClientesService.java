package io.github.robertpaivadf.service;

import io.github.robertpaivadf.model.Cliente;
import io.github.robertpaivadf.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        clientesRepository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){
        //Aplica Validações
    }


}
