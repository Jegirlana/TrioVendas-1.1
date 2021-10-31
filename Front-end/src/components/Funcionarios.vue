<template>
  <div class="login">
    <div id="container">
      <header class="page-header">
        <div class="top-bar-container">
          <a href="loginFunc">
            <img src="/../assets/back.svg" alt="Voltar" />
          </a>
          <img src="/../assets/logo.png" alt="Teixeira Móveis" />
        </div>

        <div class="header-content">
          <strong>Esses são os nossos funcionários cadastrados</strong>
          <p>Somente funcionários tem acesso a essas informações</p>
        </div>
      </header>

      <main>
        <fieldset>
          <legend>Funcionários cadastrados</legend>
          <div id="alinhamento">
            <div class="input-blockcliente">
              <input
                type="text"
                cpf=""
                id="cpf"
                v-model="cpf"
                required="required"
                placeholder="Digite o CPF"
              />
            </div>

            <button id="lupa" @click="fetchEmployeeByCpf" class="pesquisa">
              <img id="imagemlupa" src="/../assets/lupa.png" alt="lupa" />
              <h3>Buscar</h3>
            </button>

            <div class="input-blockcliente2">
              <input
                type="number"
                quantidade=""
                id="quantidade"
                v-model="quantidade"
                required="required"
                placeholder="Digite a quantidade"
              />
            </div>

            <button  @click="fetchEmployeeByQuantidade" class="pesquisa">
              <img id="imagemfiltro" src="/../assets/filtro.ico" alt="filtro" />
              <h3>Filtrar</h3>
            </button>
          </div>
          <br />
          <br />
          <br />

          <ul>
            <li v-for="employee in employees" :key="employee.id">
              <br />
              <p>Id: {{ employee.id }}</p>
              <p>Nome: {{ employee.nome }}</p>
              <p>Email: {{ employee.email }}</p>
              <p>CPF: {{ employee.cpf }}</p>
              <p>Cargo: {{ employee.cargo }}</p>
              <p>Senha: ******</p>

              <div id="alinhamentobotao">
              <button id="editar"
                type="button"
                @click="
                  $router.push({
                    name: 'UpdateFuncionario',
                    params: { id: employee.id },
                  })
                "
              >
                Editar
                <i class="fas fa-edit"></i>
              </button>

              <button id="excluir" type="button" @click="deleteEmployeeById(employee.id)">
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
  </div>
</template>

<script>
export default {
  name: "Funcionarios",
  data() {
    return {
      id: 0,
      login: "",
      cpf: "",
      senha: "",
      nome: "",
      email: "",
      cargo: "",
      confirmeSenha: "",
      quantidade: "",
      employee: {},
      employees: [],
      baseURI: "http://localhost:8080/api/employees",
    };
  },
  created: function () {
    this.fetchEmployees();
  },
  methods: {
    fetchEmployees: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.employees = result.data;
      });
    },
    fetchEmployeeByCpf: function () {
      this.$http
        .get(this.baseURI + "/search?cpf=" + this.cpf)
        .then((result) => {
          this.employees = result.data;
          console.log(result.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    fetchEmployeeByQuantidade: function () {
      this.$http
        .get(this.baseURI + "/page?quantidade=" + this.quantidade)
        .then((result) => {
          this.employees = result.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    deleteEmployeeById: function (id) {
      this.$http.delete(this.baseURI + "/" + id).then((result) => {
        this.fetchEmployees();
        alert("Deletado com sucesso!");
        console.log(result.status);
      });
    },
  },
};
</script>

<style>
.input-blockfunc input {
  height: 5.6rem;
  border-radius: 0.8rem;
  background: var(--color-input-background);
  border: 1px solid var(--color-line-in-write);
  outline: 0;
  padding: 0 1.6rem;
  font: 1.6rem Archivo;

  width: 150px;
  margin: 3.2rem 0px 0px 100px;
  margin-left: 4px;
}

.input-blockfunc2 input {
  height: 5.6rem;
  border-radius: 0.8rem;
  background: var(--color-input-background);
  border: 1px solid var(--color-line-in-write);
  outline: 0;
  padding: 0 1.6rem;
  font: 1.6rem Archivo;

  width: 170px;
  margin: 3.2rem 10px 0px -100px;
  margin-left: 20px;
}
p {
  font-size: 1.9rem;
  color: var(--color-text-complement);
}

button {
  width: 30%;
  height: 5.6rem;
  background: var(--color-secondary);
  color: white;
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

button:hover {
  background: var(--color-secondary-dark);
}

h3 {
  color: white;
}

.input-block1 input {
  height: 5.6rem;
  border-radius: 0.8rem;
  background: var(--color-input-background);
  border: 1px solid var(--color-line-in-write);
  outline: 0;
  padding: 0 1.6rem;
  font: 1.6rem Archivo;

  width: 180px;
  margin: 3.2rem 20px 0px 100px;
  margin-left: 4px;
}

.input-block2 input,
.input-block3 input {
  height: 5.6rem;
  border-radius: 0.8rem;
  background: var(--color-input-background);
  border: 1px solid var(--color-line-in-write);
  outline: 0;
  padding: 0 1.6rem;
  font: 1.6rem Archivo;

  width: 180px;
  margin: 3.2rem 20px 0px -100px;
  margin-left: 250px;
}

#alinhamento {
  display: flex;
  align-items: center;
  justify-content: center;
}

#alinhamento2,
#alinhamento3 {
  display: flex;
  align-items: center;
  justify-content: center;
}

#alinhamento2 button img {
  position: absolute;
  cursor: pointer;
  width: 25px;
  height: 25px;
  margin-left: -55px;
}

#alinhamento3 button img {
  position: absolute;
  cursor: pointer;
  width: 25px;
  height: 25px;
  margin-left: -60px;
}

button img {
  position: absolute;
  cursor: pointer;
  width: 40px;
  height: 40px;
  margin-left: -55px;
}
.pesquisa {
  background: #787680cb;
}

.pesquisa:hover {
  background: var(--color-text-base);
}

.pesquisa1 {
  background: #f80303;
}

.pesquisa1:hover {
  background: #d80303;
}
.page-header .header-content p {
  font-size: 1.9rem;
}

li {
  background: #f0f0f0e5;
  border-radius: 8px;
  margin: 10px 0px 0 0px;
  list-style-type: none;
}
li p {
  margin: 0px 100px 0 100px;
  color: var(--color-text-complement);
  font: 70 1.7rem Archivo;
}
</style>