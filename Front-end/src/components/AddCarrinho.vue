<template>
  <div id="app">
    <header class="page-header">
      <div class="top-bar-container">
        <router-link to="/login">
          <img src="/../assets/back.svg" alt="Voltar" />
        </router-link>
        <img src="/../assets/logo.png" alt="Teixeira Móveis" />
      </div>

      <div class="header-content">
        <strong>Seja bem vindo!<br />Vamos as compras</strong>
        <p>Esses são os itens do seu carrinho</p>
      </div>
    </header>

    <input type="checkbox" id="chk" />
    <label for="chk" class="menu-icon">&#9776;</label>

    <div class="bg"></div>
    <nav class="menu" id="principal">
      <ul>
        <li><router-link to="/" class="voltar">Fechar</router-link></li>
        <li><router-link to="/">Tela Inicial</router-link></li>
        <li><router-link to="/produtos">Catálogo</router-link></li>
        <li><a href="customer-registration.html">Cadastro</a></li>
      </ul>
    </nav>

    <h2 align="center">
      <b>
        <font color="#191970"> Você ainda não selecionou nenhum item! </font>
        <img id="carinha" src="/../assets/triste.png" alt="carinha triste"
      /></b>
    </h2>
    <br />

    <div class="container" align="center">
      <form action="/produtos" onsubmit="return enviarDados();">
        <table>
          <tr id="campo3">
            <td id="vazio"></td>
            <td><input id="botao" type="submit" value="Ver Catálogo" /></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddCarrinho",
};
</script>

<style>
img {
  max-width: 100%;
}
#topo {
  width: 100%;
  height: 26%;
  border-bottom-width: 2px;
  border-bottom-style: solid;
  border-bottom-color: #0a246a;
  background-position: center center;
  background-color: skyblue;
}

body {
  font-family: Arial, Helvetica, sans-serif;
  width: 100%;
}

* {
  margin: 0;
  padding: 0;
}

.menu-icon {
  font-size: 25px;
  font-weight: bold;
  padding: 5px;
  width: 40px;
  height: 40px;
  text-align: center;
  background-color: #696969;
  color: #fff;
  cursor: pointer;
  transition: all 0.4s;
  left: 300px;
}

.menu-icon:hover {
  background-color: #9c98a6;
  color: #fff;
}

#chk {
  display: none;
}

.menu {
  height: 100%;
  position: fixed;
  background-color: #fff;
  overflow: hidden;
  transition: all 0.2s;
}

#principal {
  width: 200px;
  left: -300px;
}

ul {
  list-style: none;
}

ul li a {
  display: block;
  font: 1.6rem Archivo;
  padding: 10px;
  border-bottom: solid 1px #000;
  color: var(--color-text-title);
  text-decoration: none;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

ul li span {
  float: right;
  padding-right: 10px;
}

ul li a:hover {
  background-color: #9c98a6;
}

.voltar {
  background-color: #c0c0c0;
  border-left: solid 5px #444;
}

.bg {
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  position: fixed;
  background-color: rgba(0, 0, 0, 0.6);
  display: none;
}

#chk:checked ~ .bg {
  display: block;
}

#chk:checked ~ #principal {
  transform: translateX(300px);
}

#interacao,
#celula {
  width: 250px;
  left: -250px;
}

#interacao:target,
#celula:target {
  transform: translateX(250px);
  box-shadow: 2px 2px 5px 2px rgba(0, 0, 0, 0.5);
}

#rodape {
  position: absolute;
  bottom: 0px;
}

#botao {
  width: 150px;
  height: 5.6rem;
  background: var(--color-secondary);
  color: var(--color-button-text);
  border: 0;
  border-radius: 0.8rem;
  cursor: pointer;
  font: 700 1.6rem Archivo;

  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;

  transition: 0.2s;
  margin: 17px 0px 0px 50px;
}

#botao:hover {
  background: var(--color-secondary-dark);
}

#campo1,
#campo2,
h2,
#soufuncionario {
  font-size: 1.4rem;
  color: var(--color-text-complement);
}

h2 {
  font: 1.6rem Archivo;
  margin: 17px 0px 0px 100px;
}

#soufuncionario {
  margin: 17px 0px 0px 50px;
}
#campo1,
#campo2 {
  width: 100%;
  height: 5.6rem;
  margin-top: 0.8rem;
  border-radius: 0.8rem;
  background: var(--color-input-background);
  border: 1px solid var(--color-line-in-write);
  outline: 0;
  padding: 0 1.6rem;
  font: 1.6rem Archivo;
  margin-left: 22px;
}

#campo1,
#campo2 {
  position: relative;
}

#cpf,
#senha {
  font: 700 14px Archivo;
  color: var(--color-text-title);
}

#carinha {
  width: 45px;
  margin: 5px 0px -20px 10px;
}
</style>