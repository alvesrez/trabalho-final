package iftm.edu.br.vendas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import iftm.edu.br.vendas.Dao.ClientesDao;
import iftm.edu.br.vendas.Domain.Clientes;

@Controller
public class ClientesController {

    @Autowired
    private ClientesDao dao;

    @RequestMapping("clientes")
    public String getClientes(Model model) {
        model.addAttribute("cliente", new Clientes());
        model.addAttribute("clientes", dao.getClientes());
        model.addAttribute("edit", false);
        return "Clientes";
    }

    @PostMapping("addcliente")
    public String inserirCliente(Clientes cliente, Model model) {
        Clientes clienteDb = dao.getCliente(cliente.getCpf());
        if (clienteDb == null) {
            dao.inserirCliente(cliente);
        } else {
            dao.updateCliente(cliente);
        }
        return "redirect:/clientes";
    }

    @GetMapping("/delete")
    public String deleteCliente(@RequestParam(value = "cpf", required = true) String cpf, Model model) {
        dao.deleteCliente(cpf);
        return "redirect:/clientes";
    }

    @RequestMapping("/edit/{cpf}")
    public String editarCliente(@PathVariable String cpf, Model model) {
        Clientes cliente = dao.getCliente(cpf);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", dao.getClientes());
        model.addAttribute("edit", true);
        return "Clientes";
    }

    @GetMapping("/edit/{cpf}")
    public String mostrarEditForm(@PathVariable("cpf") String cpf, Model model) {
        Clientes cliente = dao.getCliente(cpf);
        if (cliente == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "editClientes";
    }
}
