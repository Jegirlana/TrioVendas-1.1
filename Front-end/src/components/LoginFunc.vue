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
        <strong>Seja bem vindo funcionário!<br />Vamos trabalhar</strong>
        <p>O primeiro passo, é realizar o login no nosso sistema</p>
      </div>
    </header>

    <header></header>
    <input type="checkbox" id="chk" />
    <label for="chk" class="menu-icon">&#9776;</label>

    <div class="bg"></div>
    <nav class="menu" id="principal1">
      <ul id="me">
        <li><a href="" class="voltar">Fechar</a></li>
        <li><router-link to="/">Tela Inicial</router-link></li>
        <li><router-link to="produtos">Catálogo</router-link></li>
        <li><a href="/stock">Ver estoque</a></li>
        <li><router-link to="/clientes">Clientes cadastrados</router-link></li>
        <li><router-link to="/funcionarios">Funcionários cadastrados</router-link></li>
        <li><a href="/cadastrarFuncionario">Cadastrar funcionário</a></li>
        <li><a href="/furnitures">Cadastrar móveis</a></li>
      </ul>
    </nav>

    <h2 align="center">
      <b> <font color="#191970"> Logue-se Aqui! </font></b>
      <img
        id="funcionario"
        src="/../assets/funcionario.png"
        alt="funcionario"
      />
    </h2>
    <br />
    <div class="container" align="center">
      <form id="create-registration" onsubmit="return getEmployeeByCpfAndSenha()">
        <table>
          <tr>
            <td id="cpf">CPF:</td>
            <td>
              <input
                id="campo1"
                type="text"
                name="cpf"
                v-model="cpf"
                required
              />
            </td>
          </tr>
          <tr>
            <td id="senha">Senha:</td>
            <td>
              <input
                id="campo2"
                type="password"
                name="senha"
                v-model="senha"
                required
              />
            </td>
          </tr>
          <tr id="campo3">
            <td id="vazio"></td>
            <td><input id="botao" type="button" @click="getEmployeeByCpfAndSenha()" form="create-registration" value="Login" /></td>
          </tr>
        </table>
      </form>
    </div>
    
  </div>

</template>

<script>
export default {
  name: "LoginFunc",
  data() {
    return {
      id: 0,
      login: "",
      cpf: "",
      senha: "",
      employee: {},
      employees: [],
      baseURI: "http://localhost:8080/api/employees",
    };
  },
  methods: {
    fetchEmployees: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.employees = result.data;
      });
    },
    getEmployeeByCpfAndSenha: function () {
      this.$http
        .get(this.baseURI + "/loginfun?cpf=" + this.cpf + "&senha=" + this.senha)
        .then((result) => {
          this.employee = result.data;
          if (result.data == "") {
            alert("Dados incorretos!");
          } else {
            alert("Login realizado com sucesso!");
             window.location = "/stock";
          }
        })
        .catch(function (error) {
          alert("Não foi encontrado registro");
          console.log(error);
        });
    },
  },
};
</script>

<style>
img {
  max-width: 100%;
}

#principal1 {
  width: 200px;
  left: -300px;
}
#chk:checked ~ .bg {
  display: block;
}

#chk:checked ~ #principal1 {
  transform: translateX(300px);
}

body {
  font-family: Arial, Helvetica, sans-serif;
  width: 100%;
}

* {
  margin: 0;
  padding: 0;
}

.menu-icon:hover {
  background-color: #9c98a6;
  color: #fff;
}

.menu {
  height: 100%;
  position: fixed;
  background-color: #fff;
  overflow: hidden;
  transition: all 0.2s;
}


#me {
  list-style: none;
  width: 205px;
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
  width: -300px;
 
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
h2 {
  font-size: 1.4rem;
  color: var(--color-text-complement);
}

h2 {
  font: 1.6rem Archivo;
  margin: 17px 0px 0px 100px;
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

#funcionario {
  width: 40px;
  margin: 0px 0px -15px 20px;
}
</style>