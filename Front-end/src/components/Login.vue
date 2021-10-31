<template>
  <div id="app">
    <header class="page-header">
      <div class="top-bar-container">
        <a href="/">
          <img src="/../assets/back.svg" alt="Voltar" />
        </a>
        <img src="/../assets/logo.png" alt="Teixeira Móveis" />
      </div>

      <div class="header-content">
        <strong>Seja bem vindo!<br />Vamos as compras</strong>
        <p>O primeiro passo, é realizar o login no nosso sistema</p>
      </div>
    </header>

    <h2 align="center">
      <b> <font color="#191970"> Logue-se Aqui! </font> </b>
    </h2>
    <br />
    <div class="container" align="center">
      <form id="create-registration" onsubmit="return getUserByCpfAndSenha()">
        <table>
          <tr>
            <td id="cpf">CPF:</td>
            <td>
              <input
                id="campo1"
                type="text"
                name="cpf"
                v-model="cpf"
                required="required"
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
                required="required"
              />
            </td>
          </tr>
          <tr id="campo3">
            <td id="vazio"></td>
            <td><input id="botao" type="button" @click="getUserByCpfAndSenha()" form="create-registration" value="Login" /></td>
          </tr>
        </table>
      </form>
      <div class="container">
        <br />
        <h6 align="center">
          <router-link to="/loginFunc" id="soufuncionario"
            >Sou Funcionário</router-link
          >
          <img
            id="funcionario"
            src="/../assets/funcionario.png"
            alt="funcionario"
          />
        </h6>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      id: 0,
      login: "",
      cpf: "",
      senha: "",
      user: {},
      users: [],
      baseURI: "http://localhost:8080/api/users",
    };
  },
  methods: {
    fetchUsers: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.users = result.data;
      });
    },
    getUserByCpfAndSenha: function () {
      this.$http
        .get(this.baseURI + "/login?cpf=" + this.cpf + "&senha=" + this.senha)
        .then((result) => {
          this.user = result.data;
          if (result.data == "") {
            alert("Dados incorretos!");
          } else {
            alert("Login realizado com sucesso!");
             window.location = "/produtos";
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
* {
  margin: 0;
  padding: 0;
}

.menu-icon {
  font-size: 25px;
  font-weight: bold;
  padding: 5px;
  width: 100px;
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

ul li a {
  display: block;
  font: 1.6rem Archivo;

  border-bottom: solid 1px #000;
  color: var(--color-text-title);
  text-decoration: none;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;

  margin-top: -10px;
  height: 45px;
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

#funcionario {
  width: 30px;
  margin: 5px 0px -20px 10px;
}
</style>