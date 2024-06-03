<script setup>
// import { ref } from 'vue'
//
// const isAuth = ref()
// const isAdmin = ref()
</script>

<template>
  <div class="sign-in">
    <div class="container">
      <div class="sign-in-text">
        <sit class="text">
          <a>Введите логин и пароль.</a>
        </sit>
      </div>
      <form class="login" @submit.prevent="signingIn">
        <div class="login-textbox">
          <input required class="login-textbox-text" type="text" Placeholder="Логин">
        </div>
        <div class="password-textbox">
          <input required class="password-textbox-text" type="text" Placeholder="Пароль">
        </div>
        <div class="button-sign-in">
          <router-link to="/" class="button-sign-in-text" target="_blank">Войти</router-link>
        </div>
        <div class="button-to-sign-up">
          <router-link to="/sign-up" class="button-sign-in-text" target="_blank">Регистрация</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>

</style>