import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import axios from 'axios'
import VueAxios from 'vue-axios'
import { createStore } from 'vuex'

const app = createApp(App)

// Create a new store instance.
const store = createStore({
    state () {
        return {
            isAuth: '',
            isAdmin: ''
        }
    },
    mutations: {
        doneAuthAsCustomer (state, value) {
            state.isAuth = value;
        },
        doneAuthAsAdmin (state, value) {
            state.isAuth = value;
            state.isAdmin = value;
        }
    },
    getters: {
        checkAuthAsCustomer: state => {
            return state.isAuth;
        },
        checkAuthAsAdmin: state => {
            return state.isAuth && state.isAdmin ;
        }
    }
});

app.use(router)

app.use(VueAxios, axios)
app.use(store)

app.mount('#app')
