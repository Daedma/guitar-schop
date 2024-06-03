import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import Axios from 'axios'

const app = createApp(App)

app.use(router)

app.mount('#app')


// для авторизации

Vue.prototype.$http = Axios;
const token = localStorage.getItem('token')
if (token) {
    Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}