<template>
  <div class="furniture">
    <header class="page-header">
      <div class="top-bar-container">
        <a href="loginFunc">
          <img src="/../assets/back.svg" alt="Voltar" />
        </a>
        <img src="/../assets/logo.png" alt="Teixeira Móveis" />
      </div>

      <div class="header-content">
        <strong>Esses são os nossos móveis cadastrados</strong>
        <p>Somente funcionários tem acesso a essas informações</p>
      </div>
    </header>

    <main>
      <fieldset>
        <legend>Móveis cadastrados</legend>
        <div id="alinhamento">
          <div class="input-blockcliente">
            <input
              type="text"
              cpf=""
              id="serie"
              v-model="serieget"
              required="required"
              placeholder="Digite o Nº Série"
            />
          </div>

          <button id="lupa" @click="fetchFurnitureBySerie" class="pesquisa">
            <img id="imagemlupa" src="/../assets/lupa.png" alt="lupa" />
            <h3>Buscar</h3>
          </button>

          <div class="input-blockcliente2">
            <input
              type="number"
              quantidade=""
              id="quantidade"
              v-model="quant"
              required="required"
              placeholder="Digite a quantidade"
            />
          </div>

          <button @click="fetchFurnitureByQuant" class="pesquisa">
            <img id="imagemfiltro" src="/../assets/filtro.ico" alt="filtro" />
            <h3>Filtrar</h3>
          </button>
          <br /><br />
        </div>

        <ul>
          <li v-for="furniture in furnitures" :key="furniture.id">
            <br />

            <p><b>Id:</b> {{ furniture.id }}</p>
            <p><b>Nome:</b> {{ furniture.nome }}</p>
            <p><b>Descrição:</b> {{ furniture.descricao }}</p>
            <p><b>Número de série:</b> {{ furniture.serie }}</p>
            <p><b>Tipo de produto:</b> {{ furniture.tipo }}</p>
            <p><b>Preço:</b> R$ {{ furniture.preco }}</p>
            <br />
            <p>
              <img
                :src="'/uploads/furniture/' + furniture.id + '.png'"
                width="300"
              />
            </p>

            <div id="alinhamentobotao">
              <button
                id="editar"
                type="button"
                @click="
                  $router.push({
                    name: 'UpdateMovel',
                    params: { id: furniture.id },
                  })
                "
              >
                Editar
                <i class="fas fa-edit"></i>
              </button>

              <button
                class="pesquisa"
                id="excluir"
                type="button"
                @click="deleteFurniture(furniture.id)"
              >
                Excluir
                <i class="fas fa-trash-alt"></i>
              </button>
            </div>

            <br /><br />
          </li>
        </ul>
      </fieldset>
    </main>
  </div>
</template>

<script>
export default {
  name: "Stock",
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

    fetchFurnitureBySerie: function () {
      this.$http
        .get(this.baseURI + "/search?serie=" + this.serieget)
        .then((result) => {
          this.furnitures = result.data;
          if(result.data == ""){
          alert("Não foi encontrado registro");
          } else {
             alert("Registros por Serie");
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
          if(result.data == ""){
          alert("Não foi encontrado registro");
          } else {
             alert("Registros por Quantidade");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    deleteFurniture: function (id) {
      this.$http
        .delete(this.baseURI + "/" + id)
        .then((result) => {
          console.log(result);
          this.fetchFurnitures();
          alert("Deletado com sucesso!");
          console.log(result.status);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
</style>