import Vue from 'vue'
import VueRouter from 'vue-router'
import Principal from '../views/Principal.vue'
import Produtos from '../views/Produtos.vue'
import CadUser from '../features/customers/CadUser.vue'
import Clientes from '../features/customers/Clientes.vue'
import Login from '../features/customers/Login.vue'
import LoginFunc from '../features/employees/LoginFunc.vue'
import AddCarrinho from '../features/products/AddCarrinho.vue'
import Furnitures from '../features/products/Furnitures.vue'
import Stock from '../features/products/Stock.vue'
import UpdateCliente from '../features/customers/UpdateCliente.vue'
import CadFuncionario from '../features/employees/CadFuncionario.vue'
import Funcionarios from '../features/employees/Funcionarios.vue'
import UpdateFuncionario from '../features/employees/UpdateFuncionario.vue'
import UpdateMovel from '../features/products/UpdateMovel.vue'

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
