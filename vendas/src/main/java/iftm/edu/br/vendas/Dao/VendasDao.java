package iftm.edu.br.vendas.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iftm.edu.br.vendas.Domain.Vendas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class VendasDao {

    @Autowired
    private JdbcTemplate db;

    public List<Vendas> getVendas() {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
        "FROM Vendas v " +
        "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
        "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod";
        return db.query(sql, new VendasRowMapper());
    }
    

    public Vendas getVenda(Long codVenda) {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.cod_prodProd = p.cod_prod " +
                     "WHERE v.codVenda = ?";
        return db.queryForObject(sql, new VendasRowMapper(), codVenda);
    }
    
    public void inserirVenda(Vendas venda) {
        if (venda != null) {
            db.update("INSERT INTO Vendas(Cliente_cod_cliente, Produto_cod_prod, valorTotal, dataVenda) VALUES (?, ?, ?, ?)",
                    venda.getCodVenda(), venda.getCod_prodProduto(), venda.getValorTotal(), venda.getDataVenda());
        }
    }
    

    public void updateVenda(Vendas venda) {
        if (venda != null) {
            db.update("UPDATE Vendas SET Cliente_cod_cliente=?, cod_prodProduto=?, valorTotal=?, dataVenda=? WHERE codVenda=?", 
                    venda.getCodVenda(), venda.getCod_prodProduto(), venda.getValorTotal(), venda.getDataVenda(), venda.getCodVenda());
        }
    }

    public void deleteVenda(Long codVenda) {
        db.update("DELETE FROM Vendas WHERE codVenda=?", codVenda);
    }

    private static class VendasRowMapper implements RowMapper<Vendas> {
        @Override
        public Vendas mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vendas venda = new Vendas();
            venda.setCodVenda(rs.getLong("codVenda"));
            venda.setDataVenda(rs.getString("dataVenda"));
            venda.setCpfCliente(rs.getString("cpfCliente"));
            venda.setNomeCliente(rs.getString("nomeCliente"));
            venda.setCod_prodProduto(rs.getLong("cod_prodProduto"));
            venda.setDescricao(rs.getString("descricao"));
            venda.setValorTotal(rs.getDouble("valorTotal"));
            return venda;
        }
    }

    public List<Vendas> getVendasByCliente(String cpfCliente, String nomeCliente) {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod " +
                     "WHERE c.CPF = ? AND c.nome = ?";
        return db.query(sql, new VendasRowMapper(), cpfCliente, nomeCliente);
    }

    public List<Vendas> getVendasByProduto(Long codProd, String descricaoProduto) {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod " +
                     "WHERE p.cod_prod = ? AND p.descricao = ?";
        return db.query(sql, new VendasRowMapper(), codProd, descricaoProduto);
    }

    public List<String> getAllCpfs() {
        String sql = "SELECT DISTINCT CPF FROM Cliente";
        return db.queryForList(sql, String.class);
    }

    public List<Long> getAllCodProdutos() {
        String sql = "SELECT DISTINCT cod_prod FROM Produto";
        return db.queryForList(sql, Long.class);
    }


    public Vendas getVendaByCpf(String cpfCliente) {
        return null;
    }
    
    @GetMapping("/getClienteByCpf")
@ResponseBody
public ResponseEntity<?> getClienteByCpf(@RequestParam("cpfCliente") String cpfCliente) {
    Vendas venda = this.getVendaByCpf(cpfCliente);
    if (venda != null) {
        return ResponseEntity.ok(venda);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
