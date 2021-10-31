<template>
  <div id="stock">
    <div id="page-product">
      <div id="container">
        <header class="page-header">
          <div class="top-bar-container">
            <router-link to="/login">
              <img src="assets/back.svg" alt="Voltar" />
            </router-link>
            <img src="assets/logo.png" alt="Teixeira Móveis" />
          </div>

          <div class="header-content">
            <strong>Esses são os nossos móveis disponíveis</strong>
            <form id="search-product">
              <button id="botaoproduto" type="button" @click="fetchFurnitures">
                <h3>Ver todos os móveis</h3>
              </button>

              <div class="select-block">
                <label for="products">Tipo de Produtos</label>
                <div class="input-block1">
                  <input
                    id="campo00"
                    type="text"
                    v-model="tipoget"
                    required="required"
                    placeholder="Digite o tipo de produtos"
                  />
                </div>
              </div>
              <button id="campo001" type="button" @click="fetchFurnitureByTipo">
                Filtrar
              </button>
            </form>
          </div>
        </header>

        <div class="bg"></div>
        <nav class="menu" id="principal">
          <ul>
            <li><a href="" class="voltar">Fechar</a></li>
            <li><router-link to="/">Tela Inicial</router-link></li>
            <li><router-link to="/carrinho">Ver carrinho</router-link></li>
            <li><router-link to="/">Sair</router-link></li>
          </ul>
        </nav>

        <main>
          <article
            class="stock-item"
            v-for="furniture in furnitures"
            :key="furniture.id"
          >
            <footer>
              <img
                :src="'/uploads/furniture/' + furniture.id + '.png'"
                width="300"
              />
              <br />
            </footer>
            <br />

            <p>
              <strong>Produto: </strong>
              <strong>{{ furniture.nome }}</strong
              ><br /><br />
              <strong>Descrição: </strong>
              <strong>{{ furniture.descricao }}</strong>
              <br /><br />
              <strong>Tipo de produto:</strong
              ><strong> {{ furniture.tipo }}</strong
              ><br /><br />
              <strong>Quantidade:</strong
              ><strong> {{ furniture.quantidade }}</strong>
            </p>
            <footer>
              <p>
                <strong>Preço do produto:</strong
                ><strong>R$ {{ furniture.preco }} à vista</strong>
              </p>

              <button type="button">
                <router-link to="/login">
                  <img
                    id="carrinho"
                    src="https://image.flaticon.com/icons/png/512/23/23258.png"
                    alt="carrinho de compras"
                  />Adicionar ao carrinho
                </router-link>
              </button>
            </footer>
          </article>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Produtos",
  data() {
    return {
      file: null,
      id: 0,
      id2: 0,
      nome: "",
      descricao: "",
      serie: "",
      tipo: "",
      preco: "",
      quantidade: "",
      tipoget: "",
      quant: "",
      furniture: {},
      furnitures: [],
      baseURI: "http://localhost:8080/api/furnitures",
      baseUploadURI: "http://localhost:8080/upload",
    };
  },
  created: function () {
    this.fetchFurnitures();
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

    fetchFurnitures: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.furnitures = result.data;
      });
    },

    fetchFurnitureById: function () {
      this.$http
        .get(this.baseURI + "/" + this.id2)
        .then((result) => {
          this.furniture = result.data;
          alert("Registro por ID");
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    fetchFurnitureByTipo: function () {
      this.$http
        .get(this.baseURI + "/tipo?tipo=" + this.tipoget)
        .then((result) => {
          this.furnitures = result.data;
          if(result.data == ""){
          alert("Não temos produtos deste tipo");
          } else {
             alert("Registros por tipo");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    fetchFurnitureByQuant: function () {
      this.$http
        .get(this.baseURI + "/quant?quant=" + this.quant)
        .then((result) => {
          this.furnitures = result.data;
          alert("Registros por Quantidade");
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
#campo00 {
  margin: 10px 0px 0px 0px;
  width: 220px;
}

#search-product #botaoproduto {
  width: 210px;
}
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
  font-size: 60%;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.menu-icon {
  font-size: 25px;
  font-weight: bold;
  padding: 5px;
  width: 100px;
  height: 100px;
  text-align: center;
  background-color: #696969;
  color: #fff;
  cursor: pointer;
  transition: all 0.4s;
  left: 300px;
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

#app #page-landing {
  height: 100vh;
}

#app #page-landing,
#furniture {
  background: var(--color-background);
  display: flex;
  align-items: center;
  justify-content: center;
}

#furniture,
input,
button,
textarea,
#page-customer-registration {
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
    font-size: 62.5%;
  }
}

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
#page-product #container {
  width: 100vw;
  height: 100vh;
}

#search-product {
  margin-top: 3.2rem;
}

#search-product label {
  color: var(--color-text-in-primary);
}

#search-product .select-block {
  margin-bottom: 1.4rem;
}

#search-product button {
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

  transition: background 0.2s;
  margin-top: 3.2rem;
}

#search-product button:hover {
  background-color: var(--color-secondary-dark);
}

#page-product main {
  margin: 3.2rem auto;
  width: 90%;
}

.stock-item {
  background-color: var(--color-box-base);
  border: 1px solid var(--color-line-in-white);
  border-radius: 0.8rem;
  margin-top: 2.4rem;
}

.stock-item header {
  padding: 3.2rem;
  display: flex;
  align-items: center;
}

.stock-item header img {
  width: 25rem;
  height: 25rem;
  border-radius: 2%;
}

.stock-item header div {
  margin-left: 2.4rem;
}

.stock-item header div strong {
  font: 700 2.4rem Archivo;
  display: block;
  color: var(--color-text-title);
}

.stock-item header div span {
  font-size: 1.6rem;
  display: block;
  margin-top: 0.4rem;
}

.stock-item > p {
  padding: 0 2rem;
  font-size: 1.6rem;
  line-height: 2.8rem;
}

.stock-item footer {
  padding: 3.2rem 2rem;
  background-color: var(--color-box-footer);
  border-top: 1px solid var(--color-line-in-white);
  margin-top: 3.2rem;

  display: flex;
  align-items: center;
  justify-content: space-between;
}

.stock-item footer p {
  font-size: 1.4rem;
  line-height: 2.4rem;
  color: var(--color-text-complement);
}

.stock-item footer p strong {
  font-size: 1.6rem;
  color: var(--color-primary);
  display: block;
}
.stock-item footer button img {
  width: 25px;
  height: 25px;
}

.stock-item footer button {
  width: 20rem;
  height: 5.6rem;
  background: var(--color-secondary);
  color: var(--color-button-text);
  border: 0;
  border-radius: 0.8rem;
  cursor: pointer;
  font: 700 1.4rem Archivo;

  display: flex;
  align-items: center;
  justify-content: space-evenly;

  text-decoration: none;

  transition: background 0.2s;
  margin-left: 1.6rem;
}
.stock-item footer a {
  width: 20rem;
  height: 5.6rem;

  color: var(--color-button-text);
  border: 0;
  border-radius: 0.8rem;
  cursor: pointer;
  font: 700 1.4rem Archivo;

  display: flex;
  align-items: center;
  justify-content: space-evenly;

  text-decoration: none;
}

.stock-item footer button:hover {
  background: var(--color-secondary-dark);
}

@media (min-width: 700px) {
  #page-product #container {
    max-width: 100vw;
  }

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

  .stock-item header,
  .stock-item footer {
    padding: 32px;
  }

  #search-product {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 16px;
    position: absolute;
    bottom: -28px;
  }

  #page-product main {
    padding: 32px 0;
    max-width: 740px;
    margin: 0 auto;
  }

  #search-product .select-block {
    margin-bottom: 0;
  }

  .stock-item > p {
    padding: 0 32px;
  }

  .stock-item footer p strong {
    display: initial;
    margin-left: 16px;
  }

  .stock-item footer button {
    width: 245px;
    font-size: 16px;
    justify-content: center;
  }

  #carrinho {
    margin-left: -90px;
  }
}
</style>