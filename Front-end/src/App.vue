<template>
  <div id="app">
    <div id="nav"></div>
    <router-view/>
  </div>
</template>

<script>

import Principal from './views/Principal.vue';
import Produtos from './views/Produtos.vue';

import CadUser from '@/components/CadUser.vue';
import Clientes from '@/components/Clientes.vue';
import Login from '@/components/Login.vue';
import LoginFunc from '@/components/LoginFunc.vue'
import AddCarrinho from '@/components/AddCarrinho.vue'
import CadFuncionario from '@/components/CadFuncionario.vue'
import Funcionarios from '@/components/Funcionarios.vue'
import UpdateFuncionario from '@/components/UpdateFuncionario.vue'

export default {
  name: "App",
  components:{
    Principal,
    CadUser,
    Clientes,
    Login,
    LoginFunc,
    Produtos,
    AddCarrinho,
    CadFuncionario,
    Funcionarios,
    UpdateFuncionario
  },
}
</script>

<style>
</style>
