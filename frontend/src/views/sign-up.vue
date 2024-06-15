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
    async registerUser() {
      try {
        const response = await axios.post('http://localhost:8080/guitar-shop/api/users/register', {
          login: this.login,
          password: this.password
        }, { withCredentials: true });

        if (response.status === 201) {
          localStorage.setItem('user', JSON.stringify(response.data));
          const userInfo = JSON.parse(response.request.responseText);

          if (userInfo.role == 'admin') {
            this.$store.commit('doneAuthAsAdmin', true)
          }
          if (userInfo.role == 'client') {
            this.$store.commit('doneAuthAsClient', true)
          }
          window.location = '/';
        }
      } catch (error) {
        if (error.response && error.response.status !== 201) {
          // Обработайте ошибку входа, например, покажите сообщение об ошибке
          console.log(error.response.data.error);
        } else {
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
          <a to="/" class="button-sign-up-text" @click.prevent="registerUser">Зарегистрироваться!</a>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>

</style>