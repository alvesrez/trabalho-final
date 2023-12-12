package iftm.edu.br.vendas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import iftm.edu.br.vendas.Dao.ProdutoDao;
import iftm.edu.br.vendas.Domain.Produtos;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoDao dao;

    @RequestMapping("/produtos")
    public String getProdutos(Model model) {
        model.addAttribute("produto", new Produtos());
        model.addAttribute("produtos", dao.getProdutos());
        model.addAttribute("edit", false);
        return "Produtos";
    }

    @PostMapping("/addproduto")
    public String inserirProduto(Produtos produto, Model model) {
        Produtos produtoDb = dao.getProduto(produto.getCod_prod());
        if (produtoDb == null) {
            dao.inserirProduto(produto);
        } else {
            dao.updateProduto(produto);
        }
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/delete/{cod_produto}")
    public String deleteProduto(@PathVariable("cod_produto") long cod_produto, Model model) {
        dao.deleteProduto(cod_produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editProduto/{cod_prod}")
public String editarProduto(@PathVariable long cod_prod, Model model) {
    Produtos produto = dao.getProduto(cod_prod);
    model.addAttribute("produto", produto);
    model.addAttribute("produtos", dao.getProdutos());
    model.addAttribute("edit", true);
    return "Produtos";
}

@GetMapping("/produtos/edit/{cod_prod}")
public String mostrarEditForm(@PathVariable("cod_prod") long cod_prod, Model model) {
    Produtos produto = dao.getProduto(cod_prod);
    if (produto == null) {
        return "redirect:/produtos";
    }
    model.addAttribute("produto", produto);
    return "editProdutos";
}

@PostMapping("/produtos/edit/{cod_prod}")
public String salvarAlteracoes(@PathVariable("cod_prod") String codProd, @ModelAttribute("produto") Produtos produto) {
    // Converta o código do produto para long
    long codProduto = Long.parseLong(codProd);
    
    // Configure o código do produto no objeto produto
    produto.setCod_prod(codProduto);

    // Lógica para salvar as alterações no banco de dados
    dao.updateProduto(produto);
    
    // Redireciona para a página de lista de produtos após salvar as alterações
    return "redirect:/produtos";
}

}
