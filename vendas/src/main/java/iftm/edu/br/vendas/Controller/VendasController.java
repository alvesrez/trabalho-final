package iftm.edu.br.vendas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import iftm.edu.br.vendas.Dao.VendasDao;
import iftm.edu.br.vendas.Domain.Vendas;

@Controller
public class VendasController {

    @Autowired
    private VendasDao vendasDao;

    @RequestMapping("/vendas")
    public String getVendas(Model model) {
        model.addAttribute("venda", new Vendas());
        model.addAttribute("vendasList", vendasDao.getVendas());
        model.addAttribute("edit", false);
        return "Vendas";
    }

    @PostMapping("/addvenda")
    public String inserirVenda(@ModelAttribute("venda") Vendas venda, Model model) {
        Vendas vendaDb = vendasDao.getVenda(venda.getCodVenda());

        if (vendaDb == null) {
            vendasDao.inserirVenda(venda);
        } else {
            vendasDao.updateVenda(venda);
        }

        model.addAttribute("venda", new Vendas());
        model.addAttribute("vendasList", vendasDao.getVendas());
        model.addAttribute("edit", false);

        return "redirect:/vendas";
    }

    @GetMapping("/deletevenda")
    public String deleteVenda(@RequestParam(value = "codVenda", required = true) Long codVenda, Model model) {
        vendasDao.deleteVenda(codVenda);
        return "redirect:/vendas";
    }

    @GetMapping("/editvenda/{codVenda}")
    public String editarVenda(@PathVariable Long codVenda, Model model) {
        Vendas venda = vendasDao.getVenda(codVenda);
        model.addAttribute("venda", venda);
        model.addAttribute("vendasList", vendasDao.getVendas());
        model.addAttribute("edit", true);
        return "Vendas";
    }

    @GetMapping("/vendasByProduto")
    public String getVendasByProduto(@RequestParam(value = "codProd", required = true) Long codProd,
            @RequestParam(value = "descricaoProduto", required = true) String descricaoProduto,
            Model model) {
        List<Vendas> vendasByProduto = vendasDao.getVendasByProduto(codProd, descricaoProduto);
        model.addAttribute("vendasList", vendasByProduto);

        // Mantenha o nome da página para exibição dos resultados
        return "Vendas";
    }

    @GetMapping("/vendasByCliente")
    public String getVendasByCliente(@RequestParam(value = "cpfCliente", required = true) String cpfCliente,
            @RequestParam(value = "nomeCliente", required = true) String nomeCliente,
            Model model) {
        List<Vendas> vendasByCliente = vendasDao.getVendasByCliente(cpfCliente, nomeCliente);
        model.addAttribute("vendasList", vendasByCliente);

        // Mantenha o nome da página para exibição dos resultados
        return "Vendas";
    }

    @ModelAttribute("allCpfs")
    public List<String> getAllCpfs() {
        // Use o seu VendasDao para buscar todos os CPFs do banco de dados
        return vendasDao.getAllCpfs();
    }

    @ModelAttribute("allCodProdutos")
    public List<Long> getAllCodProdutos() {
        return vendasDao.getAllCodProdutos();
    }

    @GetMapping("/getClienteByCpf")
@ResponseBody
public ResponseEntity<?> getClienteByCpf(@RequestParam("cpfCliente") String cpfCliente) {
    Vendas venda = vendasDao.getVendaByCpf(cpfCliente);
    if (venda != null) {
        return ResponseEntity.ok(venda);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
