<template>
  <div class="product-list">
    <div class="product-item" v-for="product in products" :key="product.id">
<!--      <img :src="product.images[0]" alt="product image">-->
      <h3>{{ product.name }}</h3>
      <p>{{ product.description }}</p>
      <p>Цена: {{ product.cost }} руб.</p>
      <p>Остаток: {{ product.remaining }}</p>
      <p>Рейтинг: {{ product.rate }}</p>
      <p v-if="product.type === 'guitar'">Форм-фактор: {{ product.guitarForm }}</p>
      <p v-if="product.type === 'guitar'">Количество ладов: {{ product.guitarFrets }}</p>
      <p v-if="product.type === 'string'">Толщина струн: {{ product.stringGauge }}</p>
      <p v-if="product.type === 'string'">Материал: {{ product.stringMaterial }}</p>
      <button @click="addToCart(product)">Добавить в корзину</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    products: {
      type: Array,
      required: true,
    },
  },
  methods: {
    addToCart(product) {
      // реализация добавления товара в корзину
    },
  },
};
</script>

<style scoped>
/* стили для компонента ProductList */
</style>
