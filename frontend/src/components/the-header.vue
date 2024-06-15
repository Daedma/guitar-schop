<script>

import axios from "axios";
import {watch} from "vue";

watch(

)
export default {
  data() {
    return {
      isCustomer: JSON.parse(localStorage.customerAuth),
      isAdmin: JSON.parse(localStorage.adminAuth)
    }
  },
  methods: {
    localstoragefalse() {
      localStorage.customerAuth = false
      localStorage.adminAuth = false
    },
    async logoutUser() {
      try {
        const response = await axios.post('http://localhost:8080/guitar-shop/api/users/logout', {},
            { withCredentials: true });
        // console.log(JSON.parse(response.request.responseText))
        if (response.status === 200) {
          // console.log(JSON.parse(response.request.responseText))
          this.isCustomer = localStorage.setItem('customerAuth', false)
          this.isAdmin = localStorage.setItem('adminAuth', false)
          // const userInfo = JSON.parse(response.request.responseText);
        }
        // удалить куки
      } catch (error) {
        if (error.response && error.response.status === 401) {
          // Обработайте ошибку входа, например, покажите сообщение об ошибке
          console.log(error.response.data.error);
        } else {
          console.error(error);
        }
      }
    }
  },
  updated() {
    return {
      isCustomer: JSON.parse(localStorage.customerAuth),
      isAdmin: JSON.parse(localStorage.adminAuth)
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
        <input class="search-textbox" v-model="searchQuery" type="text" Placeholder="Поиск...">
        <button class="search-button">
          <i class="fa fa-search" aria-hidden="true"></i>
        </button>
      </div>
      <div class="width-30">
        <ul class="cart-sect">
          <li>
            <i class="fa fa-user-circle-o" aria-hidden="true"></i>
            <router-link to="#" v-if="isCustomer || isAdmin" @click.prevent="logoutUser">Выйти</router-link>
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