<template>
  <div class="furniture">
    <div id="container">
      <header class="page-header">
        <div class="top-bar-container">
          <a href="loginFunc">
            <img src="/assets/back.svg" alt="Voltar" />
          </a>
          <img src="/assets/logo.png" alt="Teixeira Móveis" />
        </div>

        <div class="header-content">
          <strong>Vamos inserir novos móveis</strong>
          <p>O primeiro passo, é preencher esse formulário de inscrição</p>
        </div>
      </header>
      <main>
        <form id="create-registration" onsubmit="return validaCadastro()">
          <fieldset>
            <legend>Insira os dados do produto</legend>
            <div class="input-block">
              <label id="legenda" for="nome">Nome do produto</label>
              <input
                type="text"
                name="nome"
                id="nome"
                v-model="nome"
                required="required"
              />
              <span class="msg-erro msg-nome"></span>
            </div>
            <div class="input-block">
              <label id="legenda" for="numero">Número de série</label>
              <input
                type="text"
                name="serie"
                id="serie"
                v-model="serie"
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
                v-model="descricao"
                required="required"
              />
            </div>
            <div class="input-block">
              <label id="legenda" for="quantidade">Quantidade </label>
              <input
                name="quantidade"
                id="quantidade"
                type="number"
                v-model="quantidade"
                required="required"
              />
            </div>
            <div class="input-block">
              <label id="legenda" for="imagem">
                Imagem <small>(selecione um arquivo)</small></label
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
                v-model="tipo"
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
                v-model="preco"
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
            @click="postFurniture"
            form="create-registration"
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
  name: "Furnitures",
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
      serieget: "",
      quant: "",
      furniture: {},
      furnitures: [],
      baseURI: "http://localhost:8080/api/furnitures",
      baseUploadURI: "http://localhost:8080/upload",
    };
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

    postFurniture: function () {
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

      //if (file.indexOf() == null) {
      // alert("Cadê a imagem?!");
      // erro = true;
      // }

      if (erro == false) {
        this.$http
          .post(this.baseURI, {
            nome: this.nome,
            descricao: this.descricao,
            serie: this.serie,
            tipo: this.tipo,
            preco: this.preco,
            quantidade: this.quantidade,
          })
          .then((result) => {
            console.log(result);
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
#legenda {
  font: 500 1.6rem Poppins;
  font-weight: 500;
  font-family: Poppins;
  font-size: 1.6rem;
  color: var(--color-text-base);
}
</style>