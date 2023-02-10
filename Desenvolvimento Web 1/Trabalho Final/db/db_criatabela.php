<?php
require 'db_credentials.php';

// Create connection
$conn = mysqli_connect($servername, $username, $password);
// Check connection
if (!$conn) {
    die("Falha na conexão: " . mysqli_connect_error());
}

// Create database
$sql = "CREATE DATABASE $dbname";
if (mysqli_query($conn, $sql)) {
    echo "<br>Banco de Dados criado com sucesso<br>";
} else {
    echo "<br>Falha na criaçao do banco de dados: " . mysqli_error($conn);
}

// Choose database
$sql = "USE $dbname";
if (mysqli_query($conn, $sql)) {
    echo "<br>Banco de dados alterado!";
} else {
    echo "<br>Erro na criação do banco de dados: " . mysqli_error($conn);
}

// cria tabela de registro

$sql = "CREATE TABLE $table (
    id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    nasc date NOT NULL,
    password varchar(128) NOT NULL

    )";


    if (mysqli_query($conn, $sql)) {
        echo "<br>tabela de registro criada com sucesso!";
    } else {
        echo "<br>Falha na criaçao da tabela: " . mysqli_error($conn);
    }

// cria tabela de produtos    
$sql = "CREATE TABLE $products (
    id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    img varchar(100) NOT NULL,
    price float NOT NULL
    )";


    if (mysqli_query($conn, $sql)) {
        echo "<br>tabela de produtos criada com sucesso!";
    } else {
        echo "<br>Falha na criaçao da tabela: " . mysqli_error($conn);
    }

// insere produtos na tabela

$sql = "INSERT INTO products (id, name, img, price)
    VALUES 
     ('1', 'Jazzmaster', './images/jazzmaster.png', '3999.99'),
     ('2', 'Stratocaster', './images/stratocaster.png', '3699.99'),
     ('3', 'Telecaster', './images/telecaster.png', '2999.99'),
     ('4', 'Les Paul', './images/lespaul.png', '4599.99'),
     ('5', 'ES-335','./images/es335.png', '6899.99'),
     ('6', 'SG', './images/sg.png', '5799.99'),
     ('7', 'Explorer', './images/explorer.png', '3299.99'),
     ('8', 'Jazz Bass', './images/jazzbass.png', '3199.99'),
     ('9', 'Precision Bass', './images/precisionbass.png', '3299.99'),
     ('10', 'White Falcon', './images/whitefalcon.png', '6299.99'),
     ('11', 'Violão', './images/violao.png','2299.99'),
     ('12', 'Violão Nylon', './images/violaonylon.png', '1299.99')";


    if (mysqli_query($conn, $sql)) {
        echo "<br>Tabela preenchida com sucesso!";
    } else {
        echo "<br>Falha no preenchimento do banco de dados: " . mysqli_error($conn);
    }

mysqli_close($conn);

?>
