package iftm.edu.br.vendas.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import iftm.edu.br.vendas.Domain.Produtos;

import java.util.List;

@Component
public class ProdutoDao {

    @Autowired
    JdbcTemplate db;

    public List<Produtos> getProdutos() {
        String sql = "select cod_prod, descricao, unidadeMedida, valorUnitario, qteComprada, nomeFornecedor from produto";
        return db.query(sql, new BeanPropertyRowMapper<>(Produtos.class));
    }

    public Produtos getProduto(long cod_prod) {
        String sql = "select cod_prod, descricao, unidadeMedida, valorUnitario, qteComprada, nomeFornecedor from produto where cod_prod = ?";
        List<Produtos> produtos = db.query(sql, new BeanPropertyRowMapper<>(Produtos.class), cod_prod);
        return produtos.isEmpty() ? null : produtos.get(0);
    }
    

    public void inserirProduto(Produtos produto) {
        Produtos produtoExistente = getProduto(produto.getCod_prod());

        if (produtoExistente == null) {
            // Se o produto não existir, insira um novo registro
            db.update("INSERT INTO produto(cod_prod, descricao, unidadeMedida, valorUnitario, qteComprada, nomeFornecedor) VALUES (?, ?, ?, ?, ?, ?)",
                    produto.getCod_prod(), produto.getDescricao(), produto.getUnidadeMedida(), produto.getValorUnitario(),
                    produto.getQteComprada(), produto.getNomeFornecedor());
        } else {
            // Se o produto já existir, atualize o registro existente
            db.update("UPDATE produto SET descricao=?, unidadeMedida=?, valorUnitario=?, qteComprada=?, nomeFornecedor=? WHERE cod_prod=?",
                    produto.getDescricao(), produto.getUnidadeMedida(), produto.getValorUnitario(), produto.getQteComprada(),
                    produto.getNomeFornecedor(), produto.getCod_prod());
        }
    }

    public void updateProduto(Produtos produto) {
        String sql = "UPDATE produto SET descricao=?, unidadeMedida=?, valorUnitario=?, qteComprada=?, nomeFornecedor=? WHERE cod_prod=?";
        db.update(sql, produto.getDescricao(), produto.getUnidadeMedida(), produto.getValorUnitario(),
                produto.getQteComprada(), produto.getNomeFornecedor(), produto.getCod_prod());
    }

    public void deleteProduto(long cod_prod) {
        String sql = "delete from produto where cod_prod=?";
        db.update(sql, cod_prod);
    }
}