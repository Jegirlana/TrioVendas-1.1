<template>
  <div id="app">
    <div id="nav"></div>
    <router-view/>
  </div>
</template>

<script>

import Principal from './views/Principal.vue';
import Produtos from './views/Produtos.vue';

import CadUser from '@/features/customers/CadUser.vue';
import Clientes from '@/features/customers/Clientes.vue';
import Login from '@/features/customers/Login.vue';
import LoginFunc from '@/features/employees/LoginFunc.vue'
import AddCarrinho from '@/features/products/AddCarrinho.vue'
import CadFuncionario from '@/features/employees/CadFuncionario.vue'
import Funcionarios from '@/features/employees/Funcionarios.vue'
import UpdateFuncionario from '@/features/employees/UpdateFuncionario.vue'

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
