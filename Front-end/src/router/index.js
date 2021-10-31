import Vue from 'vue'
import VueRouter from 'vue-router'
import Principal from '../views/Principal.vue'
import Produtos from '../views/Produtos.vue'
import CadUser from '../components/CadUser.vue'
import Clientes from '../components/Clientes.vue'
import Login from '../components/Login.vue'
import LoginFunc from '../components/LoginFunc.vue'
import AddCarrinho from '../components/AddCarrinho.vue'
import Furnitures from '../components/Furnitures.vue'
import Stock from '../components/Stock.vue'
import UpdateCliente from '../components/UpdateCliente.vue'
import CadFuncionario from '../components/CadFuncionario.vue'
import Funcionarios from '../components/Funcionarios.vue'
import UpdateFuncionario from '../components/UpdateFuncionario.vue'
import UpdateMovel from '../components/UpdateMovel.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Principal',
    component: Principal
  },
  {
    path: '/produtos',
    name: 'Produtos',
    component: Produtos
  },
  {
    path: '/carrinho',
    name: 'AddCarrinho',
    component: AddCarrinho
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/loginFunc',
    name: 'LoginFunc',
    component: LoginFunc
  },
  {
    path: '/cadastrarUsuario',
    name: 'CadUser',
    component: CadUser
  },
  {
    path: '/cadastrarFuncionario',
    name: 'CadFuncionario',
    component: CadFuncionario
  },
  {
    path: '/clientes',
    name: 'Clientes',
    component: Clientes
  },
  {
    path: '/funcionarios',
    name: 'Funcionarios',
    component: Funcionarios
  },
  {
    path: '/furnitures',
    name: 'Furnitures',
    component: Furnitures
  },
  {
    path: '/stock',
    name: 'Stock',
    component: Stock,
  },
  {
    path: '/clientes/atualizar/:id',
    name: 'UpdateCliente',
    component: UpdateCliente,
    props: true,
  },
  {
    path: '/funcionarios/atualizar/:id',
    name: 'UpdateFuncionario',
    component: UpdateFuncionario,
    props: true,
  },
  {
    path: '/stock/atualizar/:id',
    name: 'UpdateMovel',
    component: UpdateMovel,
    props: true,
  },
  {
    path: '*', redirect: '/'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
