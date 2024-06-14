<script setup>
import TheHeader from './components/the-header.vue'
</script>

<script>
import { RouterView } from 'vue-router'
import axios from 'axios'

export default {
  components: {
    RouterView,
  },
  data() {
    return {
      products: [],
      searchQuery: '',
    };
  },
  methods: {
    async getProducts(type) {
      try {
        const response = await axios.get('/api/items', {
          params: {
            type,
          },
        });
        this.products = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async searchProducts() {
      try {
        const response = await axios.get('/api/items', {
          params: {
            q: this.searchQuery,
          },
        });
        this.products = response.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
  mounted() {
    this.getProducts('guitar');
  },
};
</script>

<template>

  <the-header/>
  <router-view/>

</template>

<style scoped>

</style>
