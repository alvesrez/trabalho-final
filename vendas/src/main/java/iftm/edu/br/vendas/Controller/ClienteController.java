package iftm.edu.br.vendas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;
import iftm.edu.br.vendas.Model.Cliente;

@RestController
@RequestMapping("/vendas")
public class ClienteController {

    @Autowired
    private CrudRepository<Cliente, Long> clienteRepository;

    @GetMapping
    public Cliente getCliente(@RequestParam String field, @RequestParam String value) {
        if (field.equals("cpf")) {
            return clienteRepository.findByCpf(value);
        } else if (field.equals("nome")) {
            return clienteRepository.findByNome(value);
        } else {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

   
}