package iftm.edu.br.vendas.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import iftm.edu.br.vendas.Domain.Clientes;

@Component
public class ClientesDao {

    @Autowired
    JdbcTemplate db;

    public List<Clientes> getClientes() {
        String sql = "select cod_cliente, nome, cpf, dataDeNascimento, sexo, cidade, uf from Cliente";
        return db.query(sql, new BeanPropertyRowMapper<>(Clientes.class));
    }

    public Clientes getCliente(String cpf) {
        String sql = "select cod_cliente, nome, cpf, dataDeNascimento, sexo, cidade, uf from Cliente where cpf = ?";
        List<Clientes> clientes = db.query(sql, new BeanPropertyRowMapper<>(Clientes.class), cpf);
        return clientes.isEmpty() ? null : clientes.get(0);
    }

    public void inserirCliente(Clientes cliente) {
        Clientes clienteExistente = getCliente(cliente.getCpf());

        if (clienteExistente == null) {
            // Se o cliente não existir, insira um novo registro
            db.update(
                "INSERT INTO Cliente(nome, cpf, dataDeNascimento, sexo, cidade, uf) VALUES (?, ?, ?, ?, ?, ?)",
                cliente.getNome(), cliente.getCpf(), cliente.getDataDeNascimento(),
                cliente.getSexo(), cliente.getCidade(), cliente.getUf());
        } else {
            // Se o cliente já existir, atualize o registro existente
            db.update("UPDATE Cliente SET nome=?, dataDeNascimento=?, sexo=?, cidade=?, uf=? WHERE cpf=?",
                    cliente.getNome(), cliente.getDataDeNascimento(), cliente.getSexo(), cliente.getCidade(),
                    cliente.getUf(), cliente.getCpf());
        }
    }

    public void updateCliente(Clientes cliente) {
        String sql = "update Cliente set nome=?, dataDeNascimento=?, sexo=?, cidade=?, uf=? where cpf=?";
        db.update(sql, cliente.getNome(), cliente.getDataDeNascimento(), cliente.getSexo(), cliente.getCidade(),
                cliente.getUf(), cliente.getCpf());
    }

    public void deleteCliente(String cpf) {
        String sql = "delete from Cliente where cpf=?";
        db.update(sql, cpf);
    }
}
