<template>
  <div class="users">
    <div id="container">
      <header class="page-header">
        <div class="top-bar-container">
          <router-link to="/loginFunc">
            <img src="/../assets/back.svg" alt="Voltar" />
          </router-link>
          <img src="/../assets/logo.png" alt="Teixeira Móveis" />
        </div>

        <div class="header-content">
          <strong>Seja bem vindo!<br />Cresça conosco</strong>
          <p>Preencha esse formulário de inscrição</p>
        </div>
      </header>
      <main>
        <form id="create-registration" onsubmit="return validaCadastro()">
          <fieldset>
            <legend>Insira os Dados do Funcionário</legend>
            <div class="input-block">
              <label for="name">Nome completo</label>
              <input
                type="text"
                nome=""
                id="nome"
                v-model="nome"
                required="required"
              />
              <span class="msg-erro msg-nome"></span>
            </div>
            <div class="input-block">
              <label for="email">E-mail</label>
              <input
                type="email"
                id="email"
                v-model="email"
                required="required"
              />
            </div>
            <div class="input-block">
              <label for="cpf">CPF <small>(somente números)</small></label>
              <input
                id="cpf"
                type="number"
                v-model="cpf"
                placeholder="xxx.xxx.xxx-xx"
                required="required"
              />
            </div>
            <div class="input-block">
              <label for="cargo">Cargo</label>
              <input
                type="text"
                nome=""
                id="cargo"
                v-model="cargo"
                required="required"
              />
              <span class="msg-erro msg-nome"></span>
            </div>
          </fieldset>
          <fieldset>
            <legend>Estamos quase lá</legend>
            <div class="input-block" id="input-block3">
              <label for="senha">Digite uma senha</label>
              <input
                name="senha"
                id="senha"
                v-model="senha"
                type="password"
                required="required"
              />
              <label for="senha">Digite novamente</label>
              <input
                name="confirmeSenha"
                v-model="confirmeSenha"
                id="confirmeSenha"
                type="password"
                required="required"
              />
              <span class="eye2">
                <img
                  @click="mostrarSenha"
                  src="/../assets/eye.png"
                  alt="Mostrar senha"
                />
              </span>
              <span class="eye">
                <img
                  @click="mostrarConfirmeSenha"
                  src="/../assets/eye.png"
                  alt="Mostrar senha"
                />
              </span>
            </div>
          </fieldset>
        </form>
        <footer>
          <p>
            <img src="/../assets/warning.svg" alt="Aviso importante" />
            Importante!
            <br />
            Preencha todos os dados
          </p>
          <button
            type="button"
            form="create-registration"
            @click="validaCadastro"
          >
            Salvar cadastro
          </button>
        </footer>
      </main>
    </div>

    <div id="modal" class="hide">
      <div class="content">
        <div class="header">
          <img src="/../assets/check.svg" alt="Cadastro concluído" />
          <h1>Cadastro concluído</h1>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CadFuncionario",
  data() {
    return {
      id: 0,
      login: "",
      senha: "",
      nome: "",
      cpf: "",
      email: "",
      cargo: "",
      confirmeSenha: "",
      employees: {},
      employees: [],
      baseURI: "http://localhost:8080/api/employees",
    };
  },
  methods: {
    mostrarSenha: function () {
      let btn = document.querySelector(".eye2");
      btn.addEventListener("click", function () {
        let input = document.querySelector("#senha");
        if (input.getAttribute("type") == "password") {
          input.setAttribute("type", "text");
        } else {
          input.setAttribute("type", "password");
        }
      });
    },
    mostrarConfirmeSenha: function () {
      let btn = document.querySelector(".eye");
      btn.addEventListener("click", function () {
        let input = document.querySelector("#confirmeSenha");
        if (input.getAttribute("type") == "password") {
          input.setAttribute("type", "text");
        } else {
          input.setAttribute("type", "password");
        }
      });
    },

    validaCadastro: function () {
      var formulario = document.forms["create-registration"];

      var nome = formulario.nome.value;
      var email = formulario.email.value;
      var cpf = formulario.cpf.value;
      var cargo = formulario.cargo.value;
      var senha = formulario.senha.value;
      var confirmeSenha = formulario.confirmeSenha.value;

      const buttonSave = document.querySelector(
        "#container main footer button"
      );
      const modal = document.querySelector("#modal");

      var erro = false; //Não tem erro
      if (nome.indexOf(" ") == -1) {
        alert("Prencha o nome completo");
        erro = true; //Quando identificar um erro
      }
      if (cpf.length != 11) {
        alert("CPF inválido! Preencha corretamente");
        erro = true;
      }
      if (cargo.indexOf("")) {
        alert("Prencha o cargo");
        erro = true; //Quando identificar um erro
      }
      if (senha.length < 6) {
        alert("Senha inválida! Sua senha deve possuir no mínimo 6 caracteres");
        erro = true;
      }
      if (confirmeSenha != senha) {
        alert("As senhas não coincidem! Digite novamente");
        erro = true;
      }
      if (erro == false) {
        this.$http
          .post(this.baseURI, {
            nome: this.nome,
            email: this.email,
            cpf: this.cpf,
            cargo: this.cargo,
            senha: this.senha,
          })
          .then((result) => {
            console.log(result);
            this.employee = result.data;

            modal.classList.remove("hide");
            setTimeout(() => {
              window.location = "/stock";
            }, 3000);
          });
      } else {
        return true;
      }
    },
  },
};
</script>
<style>
/*main.css */
:root {
  /*variáveis*/
  --color-background: #f0f0f7;
  --color-primary-lighter: #4865e6;
  --color-primary-light: #3051e2;
  --color-primary: #101444;
  --color-primary-dark: #774dd6;
  --color-primary-darker: #6842c2;
  --color-secondary: #04d361;
  --color-secondary-dark: #04bf58;
  --color-title-in-primary: #ffffff;
  --color-text-in-primary: #d4c2ff;
  --color-text-title: #32264d;
  --color-text-complement: #9c98a6;
  --color-text-base: #6a6180;
  --color-line-in-white: #e6e6f0;
  --color-input-background: #f8f8fc;
  --color-button-text: #ffffff;
  --color-box-base: #ffffff;
  --color-box-footer: #fafafc;
  --color-small-info: #c1bccc;
  /*tamanho da fonte padrão: 16px - 100% - 1rem*/
  font-size: 60%; /* controle das medidas rem */
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html,
#app #page-landing {
  height: 100vh;
}

.hello #app #page-landing,
#reapp body,
#stock,
#furniture {
  background: var(--color-background);
  display: flex;
  align-items: center;
  justify-content: center;
}

.hello #app,
#reapp,
#stock,
#furniture,
input,
button,
textarea,
.users {
  /* font: 500 1.6rem Poppins; */
  font-weight: 500;
  font-family: Poppins;
  font-size: 1.6rem;
  color: var(--color-text-base);
}

#app #page-landing #container {
  width: 90vw;
  max-width: 700px;
}

@media (min-width: 700px) {
  :root {
    font-size: 62.5%; /*Todo 1rem vai ser relativo a 10px*/
  }
}

/*modal.css*/
#modal {
  background-color: #0e0a14ef;

  height: 100vh;
  width: 100vw;

  position: fixed;
  top: 0;

  display: flex;
  justify-content: center;
  align-items: center;

  transition: 400ms;
}

#modal.hide {
  display: none;
}

#modal .content {
  color: white;

  width: 420px;
}

#modal .header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#modal .header h1 {
  line-height: 42px;
}
/*header.css*/

.page-header {
  background-color: var(--color-primary);

  display: flex;
  flex-direction: column;
}

.page-header .top-bar-container {
  width: 90%;
  margin: 0 auto;

  display: flex;
  align-items: center;
  justify-content: space-between;

  padding: 1.6rem;
}

.page-header .top-bar-container a {
  height: 3.2rem;
  transition: opacity 0.2s;
}

.page-header .top-bar-container a:hover {
  opacity: 0.6;
}

.page-header .top-bar-container > img {
  height: 3.2rem;
}

.page-header .header-content {
  width: 90%;
  margin: 3.2rem auto;

  position: relative;
}

.page-header .header-content strong {
  font: 700 3.6rem Archivo;
  line-height: 4.2rem;
  color: var(--color-title-in-primary);
}

.page-header .header-content p {
  max-width: 30rem;
  font-size: 1.6rem;
  line-height: 2.6rem;
  color: var(--color-text-in-primary);
  margin-top: 2.4rem;
}

@media (min-width: 700px) {
  .page-header {
    height: 340px;
  }

  .page-header .top-bar-container {
    max-width: 1100px;
  }

  .page-header .header-content {
    max-width: 740px;

    margin: 0 auto;

    flex: 1 1;
    padding-bottom: 48px;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .page-header .header-content strong {
    max-width: 350px;
  }
}

/*form.css*/

.select-block label,
input-block label,
.textarea-block label {
  font-size: 1.4rem;
  color: var(--color-text-complement);
}

.input-block input,
.select-block select,
.textarea-block textarea {
  width: 100%;
  height: 5.6rem;
  margin-top: 0.8rem;
  border-radius: 0.8rem;
  background: var(--color-input-background);
  border: 1px solid var(--color-line-in-write);
  outline: 0;
  padding: 0 1.6rem;
  font: 1.6rem Archivo;
}

.textarea-block textarea {
  padding: 1.2rem 1.6rem;
  height: 16rem;

  resize: vertical;
}

.input-block,
.textarea-block {
  position: relative;
}

.input-block:focus-within::after,
.textarea-block:focus-within::after {
  content: "";

  width: calc(100% - 3.2rem);
  height: 2px;
  background: var(--color-primary-light);

  position: absolute;
  left: 1.6rem;
  right: 1.6rem;
  bottom: 0px;
}

/*customer-registration*/
#container {
  width: 100vw;
  height: 100vh;
}

.page-header .header-content {
  margin-bottom: 6.4rem;
}

main {
  background: var(--color-box-base);
  width: 100%;
  max-width: 74rem;
  border-radius: 0.8rem;
  margin: -3.2rem auto 3.2rem;
  padding-top: 6.4rem;
}

fieldset {
  border: none;

  padding: 0 2.4rem;
}

fieldset legend {
  font: 700 2.4rem Archivo;
  color: var(--color-text-title);
  margin-bottom: 2.4rem;

  display: flex;
  align-items: center;
  justify-content: space-between;

  width: 100%;

  padding-bottom: 1.6rem;
  border-bottom: 1px solid var(--color-line-in-white);
}
fieldset + fieldset {
  margin-top: 6.4rem;
}

fieldset + fieldset #input-block2 input {
  margin-top: -1.9rem;
}

.input-block + .input-block,
.input-block + .textarea-block,
.select-block + .input-block {
  margin-top: 2.4rem;
}

main footer {
  padding: 4rem 2.4rem;

  background: var(--color-box-footer);
  border-top: 1px solid var(--color-line-in-white);
  margin-top: 6.4rem;
}

main footer p {
  display: flex;
  align-items: center;
  justify-content: center;

  font-size: 1.4rem;
  line-height: 2.4rem;

  color: var(--color-text-complement);
}

main footer p img {
  margin-right: 2rem;
}

main footer button {
  width: 100%;
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
  margin-top: 3.2rem;
}

main footer button:hover {
  background: var(--color-secondary-dark);
}

.schedule-item + .schedule-item {
  margin-top: 3.2rem;
  padding-top: 3.2rem;

  border-top: 1px solid var(--color-line-in-white);
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0; /* <--Removendo setas do input e magens*/
}

.input-block .eye img {
  position: absolute;
  cursor: pointer;
  width: 40px;
  height: 40px;
  margin-left: -65px;
  margin-top: 30px;
}

.input-block .eye2 img {
  position: absolute;
  cursor: pointer;
  width: 40px;
  height: 40px;
  margin-left: -65px;
  margin-top: -65px;
}

@media (min-width: 700px) {
  #container {
    max-width: 100vw;
  }

  .page-header .header-content {
    margin-bottom: 0;
  }

  main fieldset {
    padding: 0 64px;
  }

  .schedule-item {
    display: grid;
    grid-template-columns: 2fr 1fr 1fr;
    column-gap: 1.6rem;
  }

  #schedule-items .select-block + .input-block,
  #schedule-items .input-block + .input-block {
    margin-top: 0;
  }

  main footer {
    padding: 40px 64px;

    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  main footer button {
    margin-top: 0;

    width: 200px;
  }

  fieldset + fieldset #input-block3 input {
    max-width: 40rem;
    display: inline;
    align-items: center;
    justify-content: space-between;
    margin: 2rem;
  }
}
</style>