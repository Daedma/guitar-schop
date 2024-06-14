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
          localStorage.setItem('user', JSON.stringify(response.data));
          const userInfo = JSON.parse(response.request.responseText);

          if (userInfo.role == 'admin') {
            this.$store.commit('doneAuthAsAdmin', true)
          }
          if (userInfo.role == 'customer') {
            this.$store.commit('doneAuthAsCustomer', true)
          }
          //window.location = '/';
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          // Обработайте ошибку входа, например, покажите сообщение об ошибке
          console.log(error.response.data.error);
        } else {
          console.error(error);
        }
      }
    },
    registerUser() {
      // проверяем, что заполнены оба поля
      if (!this.login || !this.password) {
        alert('Login and password are required');
        return;
      }

      // проверяем, что длина пароля не менее 8 символов
      if (this.password.length < 8) {
        alert('Password must be at least 8 characters long');
        return;
      }

      // отправляем запрос на регистрацию
      axios.post('/api/users/register', {
        login: this.login,
        password: this.password
      })
          .then(response => {
            // обрабатываем успешную регистрацию
            if (response.status === 201) {
              alert('Registration successful');
              // сохраняем данные пользователя в локальном хранилище и перенаправляем на главную страницу
              localStorage.setItem('user', JSON.stringify(response.data));
              window.location = '/';
            }
          })
          .catch(error => {
            // обрабатываем ошибки регистрации
            if (error.response) {
              if (error.response.status === 409) {
                alert('Login is already taken');
              } else if (error.response.status === 400) {
                alert(error.response.data.error);
              }
            } else {
              console.error(error);
            }
          });
    },
  },
}
</script>

<template>
  <div class="sign-in">
    <div class="container">
      <div class="sign-in-text">
        <sit class="text">
          <a>Введите логин и пароль.</a>
        </sit>
      </div>
      <form class="login">
        <div class="login-textbox">
          <input required v-model="login" class="login-textbox-text" type="text" Placeholder="Логин">
        </div>
        <div class="password-textbox">
          <input required v-model="password" class="password-textbox-text" type="text" Placeholder="Пароль">
        </div>
        <div class="button-sign-in">
          <a class="button-sign-in-text" @click.prevent="loginUser">Войти</a>
        </div>
        <div class="button-to-sign-up">
          <router-link to="/sign-up" class="button-sign-in-text">Регистрация</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>

</style>

