<template>
  <div class="hello">
    <div id="app">
      <div id="container">
        <div class="logo-container">
          <img src="assets/logo.png" alt="Teixeira" />
          <h2>Sua plataforma de compras online</h2>
        </div>
        <img
          class="hero-image"
          src="assets/landing.svg"
          alt="Plataforma de Compras"
        />
        <div class="buttons-container">
          <button class="login">
            <router-link to="/login">Fazer Login</router-link>
          </button>
          <button class="registration" @click="clique">
            <router-link to="/cadastrarUsuario">Cadastre-se</router-link>
          </button>
        </div>
        <p class="total-sales">
          Total de 200 vendas já realizadas
          <img src="assets/purple-heart.svg" alt="Coração roxo" />
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import CadUser from "../components/CadUser.vue";
import Clientes from "../components/Clientes.vue";

export default {
  name: "Principal",
  components: {
    CadUser,
    Clientes,
  },
  methods: {
    clique() {
      this.$router
        .push({ name: "CadUser", params: { name: "Nome", email: "Email" } })
        .catch(() => {});
    },
  },
};
</script>

<style >
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

#container {
  height: 100vh;
}

.hello #app {
  background: var(--color-background);
  display: flex;
  align-items: center;
  justify-content: center;
}

.hello #app,
input,
button,
textare {
  font-weight: 500;
  font-family: Poppins;
  font-size: 1.6rem;
  color: var(--color-text-base);
}

.hello #app #container {
  width: 90vw;
  max-width: 700px;
}

@media (min-width: 700px) {
  :root {
    font-size: 62.5%;
  }
}

.hello #app {
  background: var(--color-primary);
}

.hello #app #container {
  color: var(--color-text-in-primary);
}

.logo-container img {
  height: 10rem;
  margin-top: 8rem;
}

.logo-container {
  text-align: center;
  margin-bottom: 3.2rem;
}

.logo-container h2 {
  font-weight: 500;
  font-size: 3.6rem;
  line-height: 4.6rem;
  margin-top: 4rem;
  margin-left: -0.8rem;
}

.hero-image {
  width: 100%;
}

.buttons-container {
  display: flex;
  justify-content: center;
  margin: 3.2rem 0;
}

.buttons-container button {
  width: 30rem;
  height: 10.4rem;

  border-radius: 0.8rem;
  margin-right: 1.6rem;

  font: 700 2.4rem Archivo;

  display: flex;
  align-items: center;
  justify-content: center;

  text-decoration: none;

  transition: background 0.2s;

  color: var(--color-button-text);
}
a{
    color: var(--color-button-text);
     text-decoration: none;
}
.buttons-container button img {
  width: 4rem;
  margin-right: 2.4rem;
}

.buttons-container button.login {
  background: var(--color-primary-lighter);
}

.buttons-container button.login:hover {
  background: var(--color-primary-light);
}

.buttons-container button.registration {
  background: var(--color-secondary);
  margin-right: 0;
}

.buttons-container button.registration:hover {
  background: var(--color-secondary-dark);
}

.total-sales {
  font-size: 1.8rem;

  display: flex;
  align-items: center;
  justify-content: center;
}

.total-sales img {
  margin-left: 0.8rem;
}

@media (max-width: 699px) {
  :root {
    font-size: 40%;
  }
}

@media (min-width: 1100px) {
  .hello #app #container {
    margin-top: 50px;
    max-width: 1100px;
    display: grid;
    grid-template-columns: 2fr 1fr 2fr;
    grid-template-rows: 350px 1fr;
    grid-template-areas:
      "vendedores image image"
      "button button texting";
    column-gap: 87px;
    row-gap: 86px;
  }

  .logo-container {
    grid-area: vendedores;
    text-align: initial;
    align-self: center;
    margin: 0;
  }

  .logo-container img {
    height: 127px;
  }

  .hero-image {
    grid-area: image;
    height: 350px;
  }

  .buttons-container {
    grid-area: button;
    justify-content: flex-start;
    margin: 0;
  }

  .total-connections {
    grid-area: texting;
    justify-self: end;
    font-size: 1.2rem;
  }
}
</style>