package iftm.edu.br.vendas.Controller;

Import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public Produto getProduto(@RequestParam String field, @RequestParam String value) {
        if (field.equals("cod_prod")) {
            return produtoRepository.findByCodProd(value);
        } else if (field.equals("descricaoProduto")) {
            return produtoRepository.findByDescricaoProduto(value);
        } else {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }