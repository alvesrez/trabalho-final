package iftm.edu.br.vendas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
