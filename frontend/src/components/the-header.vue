<script>
import { useStore } from 'vuex'
import {computed } from "vue"


export default {
  setup() {
    const store = useStore()
    let customerData = computed(() => store.state.isAuth)
    let adminData = computed(() => store.state.isAdmin)
    return {
      isCustomer: customerData,
      isAdmin: adminData
    }
  },
  methods: {
    checkStatus() {
      const store = useStore()
      let customerData = computed(() => store.state.isAuth)
      let adminData = computed(() => store.state.isAdmin)
      return {
        customerData: customerData,
        adminData: adminData
      }
    }
  }
}
</script>

<template>

  <div class="width-100 top-header">
    <div class="container">
      <router-link to="/" class="logo">
        <img src="@/assets/pictures/logo.png">
      </router-link>
    </div>
  </div>
  <!---------- Logo and Search Panel HTML Code Starts --------->
  <div class="width-100 search-panel">
    <div class="container">
      <div class="width-50">
        <input class="search-textbox" type="text" Placeholder="Поиск...">
        <button class="search-button">
          <i class="fa fa-search" aria-hidden="true"></i>
        </button>
      </div>
      <div class="width-30">
        <ul class="cart-sect">
          <li>
            <i class="fa fa-user-circle-o" aria-hidden="true"></i>
            <router-link to="/" v-if="isCustomer || isAdmin">Выйти</router-link>
            <router-link to="/sign-in" v-else> Войти </router-link>
          </li>
          <li>
            <i class="fa fa-heart-o" aria-hidden="true"></i>
            <router-link to="/tracking" v-if="isCustomer==true">Мои заказы</router-link>
          </li>
          <li>
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
            <router-link to="/cart" v-if="isCustomer==true">Корзина</router-link>
          </li>
          <li>
            <i class="fa fa-plus" aria-hidden="true"></i>
            <router-link to="/new-good" v-if="isAdmin==true">Добавить товар</router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <!---------- Logo and Search Panel HTML Code Ends --------->

  <!---------- Main Menu HTML Code Starts --------->
  <div class="width-100">
    <div class="container">
      <ul class="main-menu">
        <li>
          <router-link to="/guitars">Гитары</router-link>
        </li>
        <li>
          <router-link to="/strings">Струны</router-link>
        </li>
        <li>
          <a href="#">Аксессуары</a>
        </li>
        <li>
          <a href="#">Усилители</a>
        </li>
        <li>
          <a href="#">Эффекты</a>
        </li>
      </ul>
    </div>
  </div>
  <!---------- Main Menu HTML Code Ends --------->
</template>

<style scoped>

</style>