create database dbVendas;

	use dbVendas;
    

CREATE TABLE Cliente (
    cod_cliente INT PRIMARY KEY,
    CPF VARCHAR(11),
    nome VARCHAR(60),
    dataDeNascimento DATE,
    Sexo VARCHAR(1),
    Cidade VARCHAR(60),
    UF VARCHAR(2)
);

CREATE TABLE produto (
    cod_prod INT PRIMARY KEY,
    descricao VARCHAR(60),
    unidadeMedida VARCHAR(2),
    valorUnitario DECIMAL(10, 2),
    qteComprada INT,
    nomeFornecedor VARCHAR(60)
);


CREATE TABLE Vendas (
    codVenda INT PRIMARY KEY,
    dataVenda DATE,
    ValorTotal DECIMAL(10, 2),
    Cliente_cod_cliente INT,
	Produto_cod_prod INT,
    FOREIGN KEY (Cliente_cod_cliente) REFERENCES Cliente(cod_cliente),	
	FOREIGN KEY (Produto_cod_prod) REFERENCES Produto(cod_prod) 
);


CREATE TABLE itemVenda (
    codVenda INT,
    cod_prod INT,
    qteVendida INT,
    precoVenda DECIMAL(10, 2),
    FOREIGN KEY (codVenda) REFERENCES Vendas(codVenda),
    FOREIGN KEY (cod_prod) REFERENCES produto(cod_prod)
);

-- INSERÇÃO DE CADASTROS DIVERSO DE CLIENTES NO dbVendas
INSERT INTO Cliente (cod_cliente, CPF, nome, dataDeNascimento, Sexo, Cidade, UF) VALUES
(1, '12345678901', 'João Silva', '1990-05-15', 'M', 'São Paulo', 'SP'),
(2, '98765432109', 'Maria Oliveira', '1985-08-22', 'F', 'Rio de Janeiro', 'RJ'),
(3, '45678901234', 'Carlos Souza', '1978-12-03', 'M', 'Belo Horizonte', 'MG'),
(4, '56789012345', 'Ana Pereira', '1995-02-10', 'F', 'Porto Alegre', 'RS'),
(5, '23456789012', 'Lucas Santos', '1980-07-18', 'M', 'Recife', 'PE'),
(6, '89012345678', 'Fernanda Lima', '1992-09-25', 'F', 'Fortaleza', 'CE'),
(7, '34567890123', 'Paulo Oliveira', '1987-04-30', 'M', 'Salvador', 'BA'),
(8, '67890123456', 'Gabriela Silva', '1998-11-08', 'F', 'Curitiba', 'PR'),
(9, '90123456789', 'Mateus Costa', '1983-06-14', 'M', 'Brasília', 'DF'),
(10, '01234567890', 'Mariana Santos', '1991-03-27', 'F', 'Manaus', 'AM'),
(11, '54321098765', 'Felipe Souza', '1975-10-05', 'M', 'Belém', 'PA'),
(12, '43210987654', 'Amanda Lima', '1989-01-12', 'F', 'Florianópolis', 'SC'),
(13, '32109876543', 'Rodrigo Pereira', '1997-07-02', 'M', 'Porto Velho', 'RO'),
(14, '21098765432', 'Juliana Oliveira', '1982-04-20', 'F', 'Natal', 'RN'),
(15, '10987654321', 'Vinícius Costa', '1994-12-15', 'M', 'Maceió', 'AL');

-- INSERCAO DE PRODUTOS NO BANCO DE DADOS DBVENDAS

INSERT INTO produto (cod_prod, descricao, unidadeMedida, valorUnitario, qteComprada, nomeFornecedor) VALUES
(1, 'IPHONE 15', 'UM', 7299.00, 50, 'Apple Computer Brasil Ltda'),
(2, 'IPHONE 15 PRO', 'UM', 9299.00, 50, 'Apple Computer Brasil Ltda'),
(3, 'IPHONE 14', 'UM', 5500.00, 50, 'Apple Computer Brasil Ltda'),
(4, 'IPHONE13', 'UM', 6100.00, 50, 'Apple Computer Brasil Ltda'),
(5, 'Samsung Galaxy S23 Ultra 5G 256GB Tela 6.8', 'UM', 2400.00, 60, 'SAMSUNG S.A'),
(6, 'Xiaomi Pocophone Poco X5 Pro', 'UM', 1400.00, 60, 'XIAOMI S.A');

SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal
FROM Vendas v
INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente
INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod;
