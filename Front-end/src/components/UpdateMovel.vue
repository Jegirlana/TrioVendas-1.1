<template>
  <div class="users">
    <div id="container">
      <header class="page-header">
        <div class="top-bar-container">
          <router-link to="/stock">
            <img src="/../assets/back.svg" alt="Voltar" />
          </router-link>
          <img src="/../assets/logo.png" alt="Teixeira Móveis" />
        </div>

        <div class="header-content">
          <strong
            >Seja bem vindo!<br />Vamos atualizar o cadastro do móvel</strong
          >
          <p>O primeiro passo, é atualizar o formulário de cadastro</p>
        </div>
      </header>
      <main>
        <form id="create-registration" onsubmit="return validaCadastro()">
          <fieldset>
            <legend>Atualize os dados do móvel</legend>
            <div class="input-block">
              <label for="name">Id</label>
              <input type="text" v-model="furniture.id" required="required" />
            </div>
            <div class="input-block">
              <label for="nome">Nome do produto</label>
              <input
                type="text"
                name="nome"
                id="nome"
                v-model="furniture.nome"
                required="required"
              />
              <span class="msg-erro msg-nome"></span>
            </div>
            <div class="input-block">
              <label for="numero">Número de série</label>
              <input
                type="text"
                name="serie"
                id="serie"
                v-model="furniture.serie"
                placeholder="xxxxxxxxxxxx"
                required="required"
              />
            </div>
            <div class="input-block">
              <label id="legenda" for="descricao">Descrição</label>
              <input
                type="text"
                name="descricao"
                id="descricao"
                v-model="furniture.descricao"
                required="required"
              />
            </div>
            <div class="input-block">
              <label id="legenda" for="quantidade">Quantidade </label>
              <input
                name="quantidade"
                id="quantidade"
                type="number"
                v-model="furniture.quantidade"
                required="required"
              />
            </div>
            <div class="input-block">
              <label for="imagem"
                >Imagem <small>(selecione um arquivo)</small></label
              >
            </div>
            <br />
            <br />
            <input type="file" id="file" ref="file" name="image" />
          </fieldset>

          <fieldset>
            <legend>Informe o tipo de produto</legend>
            <div class="input-block">
              <label id="legenda" for="subject">Tipo</label>
              <input
                type="text"
                name="tipo"
                id="tipo"
                v-model="furniture.tipo"
                required="required"
              />
            </div>
          </fieldset>

          <fieldset>
            <legend>Estamos quase lá</legend>
            <div class="input-block" id="input-block3">
              <label id="legenda" for="valor"
                >Digite o preço <small>(R$)</small></label
              >
              <input
                type="number"
                name="preco"
                id="preco"
                v-model="furniture.preco"
                required="required"
              />
            </div>
          </fieldset>
        </form>
        <footer>
          <p>
            <img src="assets/warning.svg" alt="Aviso importante" />
            Importante!
            <br />
            Preencha todos os dados
          </p>
          <button
            type="button"
            @click="validaCadastro()"
            form="create-registration"
          >
            Atualizar cadastro
          </button>
        </footer>
      </main>
    </div>

    <div id="modal" class="hide">
      <div class="content">
        <div class="header">
          <img src="/../assets/check.svg" alt="Cadastro concluído" />
          <h1>Cadastro atualizado</h1>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateMovel",
  props: ["id"],
  data() {
    return {
      file: null,
      id: 0,
      nome: "",
      descricao: "",
      serie: "",
      tipo: "",
      preco: "",
      quantidade: "",
      furniture: {},
      furnitures: [],
      baseURI: "http://localhost:8080/api/furnitures",
      baseUploadURI: "http://localhost:8080/upload",
    };
  },
  created: function () {
    this.$http
      .get(this.baseURI + "/" + this.id)
      .then((result) => {
        this.furniture = result.data;
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    handleFileUpload(id) {
      this.file = this.$refs.file.files[0];

      let form = new FormData();
      form.append("resource", "furniture");
      form.append("id", id);
      form.append("file", this.file);

      this.$http
        .post(this.baseUploadURI, form, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((result) => {
          console.log(result);
        });
    },
    
    validaCadastro: function () {
      var formulario = document.forms["create-registration"];

      var nome = formulario.nome.value;
      var descricao = formulario.descricao.value;
      var serie = formulario.serie.value;
      var tipo = formulario.tipo.value;
      var preco = formulario.preco.value;
      var quantidade = formulario.quantidade.value;

      const buttonSave = document.querySelector(
        "#container main footer button"
      );
      const modal = document.querySelector("#modal");

      var erro = false; //sem erro
      if (nome.length < 3) {
        alert("Preencha o nome do produto");
        erro = true; //Quando identificar um erro
      }
      if (serie.length != 12) {
        alert("Nº de série inválido! Preencha corretamente");
        erro = true;
      }
      if (descricao.length < 20) {
        alert("Ponha todas as informações importantes do Produto");
        erro = true;
      }
      if (quantidade == "" || quantidade < 0) {
        alert("Quantidade de produtos inválida! Preencha corretamente");
        erro = true;
      }
      if (preco == "" || preco < 0) {
        alert("Preço de produto inválido! Preencha corretamente");
        erro = true;
      }
      if (tipo == "" || tipo.length < 4) {
        alert("Tipo de produto inválido! Preencha corretamente");
        erro = true;
      }

      if (erro) {
        buttonSave.addEventListener("click", () => {
          modal.classList.remove("hide");
          setTimeout(() => {
            window.location = "/";
          }, 3000);
        });
        return false;
      }
      if (erro == false) {
        let obj = {
          nome: this.furniture.nome,
          descricao: this.furniture.descricao,
          serie: this.furniture.serie,
          tipo: this.furniture.tipo,
          preco: this.furniture.preco,
          quantidade: this.furniture.quantidade,
        };
        this.$http.put(this.baseURI + "/" + this.id, obj).then((result) => {
          this.furniture = result.data;
          this.handleFileUpload(this.furniture.id + ".png");

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
:root {
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