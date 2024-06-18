<script>

import axios from 'axios'
export default {
  data() {
    return {
      login: '',
      password: '',
      registerLogin: '',
      registerPassword: '',
      showRegisterForm: false,
    }
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('http://localhost:8080/guitar-shop/api/users/login', {
          login: this.login,
          password: this.password
        }, { withCredentials: true });

        if (response.status === 200) {
          const userInfo = JSON.parse(response.request.responseText);

          if (userInfo.role == 'admin') {
            localStorage.setItem('adminAuth', true)
            localStorage.setItem('customerAuth', false)
          }
          if (userInfo.role == 'customer') {
            localStorage.setItem('adminAuth', false)
            localStorage.setItem('customerAuth', true)
          }
          window.location = '/';
        }
        else if (response.status === 401) {
          // Обработайте ошибку входа, например, покажите сообщение об ошибке
          console.log(response.data.error);
          alert('Неправильный ввод!')
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          // Обработайте ошибку входа, например, покажите сообщение об ошибке
          console.log(error.response.data.error);
          alert('Неправильный ввод!')
        } else {
          alert('Неправильный ввод!')
          console.error(error);
        }
      }
    },
  },
  beforeRouteEnter(to, from, next) {
    if ((JSON.parse(localStorage.getItem('adminAuth')) === false) &&
        (JSON.parse(localStorage.getItem('customerAuth')) === false)) {
      next()
    } else {
      next('/error')
    }
  }
}
</script>

<template>
  <div class="sign-in">
    <div class="container">
      <div class="sign-in-text">
        <div class="text">
          <a>Введите логин и пароль.</a>
        </div>
      </div>
      <form class="login">
        <div class="login-textbox">
          <input required v-model="login" class="login-textbox-text" type="text" Placeholder="Логин">
        </div>
        <div class="password-textbox">
          <input required v-model="password" class="password-textbox-text" type="text" Placeholder="Пароль">
        </div>
        <div class="button-sign-in">
          <a to="/" class="button-sign-in-textSIGNIN" @click.prevent="loginUser">Войти</a>
        </div>
        <div class="button-to-sign-up">
          <router-link to="/sign-up" class="button-sign-in-textSIGNUP">Регистрация</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>

</style>

