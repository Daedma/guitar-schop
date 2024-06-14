<script>
import axios from 'axios';

export default {
  data() {
    return {
      name: '',
      description: '',
      cost: null,
      images: [],
      remaining: null,
      categories: [],
      type: '',
      stringGauge: '',
      stringMaterial: '',
      guitarForm: '',
      guitarFrets: ''
    }
  },
  methods: {
    addProduct() {
      const formData = new FormData();

      for (const image of this.images) {
        formData.append('images', image);
      }

      let propertiesObject = {
        name: this.name,
        description: this.description,
        cost: this.cost,
        images: ['No photo'],
        remaining: this.remaining,
        categories: this.categories.split(/,\s*/),
        type: this.type,
      }
      if (this.type === 'strings') {
        propertiesObject.stringGauge = this.stringGauge;
        propertiesObject.stringMaterial = this.stringMaterial;
      } else if (this.type === 'guitar') {
        propertiesObject.guitarForm = this.guitarForm;
        propertiesObject.guitarFrets = this.guitarFrets;
      }

      formData.append('properties', JSON.stringify(propertiesObject))
      console.log(this.images)
      axios.post('http://localhost:8080/guitar-shop/api/items/new', formData,
          { withCredentials: true})
          .then(response => {
            console.log(response.data);
            // здесь можно добавить логику для отображения сообщения об успешном добавлении товара
          })
          .catch(error => {
            console.error(error);
            // здесь можно добавить логику для отображения сообщения об ошибке при добавлении товара
          });
    },
    showCharacteristics() {
      if (this.type === 'strings') {
        this.stringGauge = '';
        this.stringMaterial = '';
      } else if (this.type === 'guitar') {
        this.guitarForm = '';
        this.guitarFrets = '';
      } else {
        this.stringGauge = '';
        this.stringMaterial = '';
        this.guitarForm = '';
        this.guitarFrets = '';
      }
    },
    onFileChange(event) {
      this.images = [];
      for (const file of event.target.files) {
        this.images.push(file);
      }
    }
  }
}
</script>

<template>
  <div class="add-product">
    <h2>Добавить товар</h2>
    <form @submit.prevent="addProduct">
      <label for="name">Название:</label>
      <input v-model="name" type="text" id="name" name="name" required>
      <br>
      <label for="description">Описание:</label>
      <textarea v-model="description" id="description" name="description" required></textarea>
      <br>
      <label for="cost">Цена:</label>
      <input v-model="cost" type="number" id="cost" name="cost" step="0.01" required>
      <br>
      <label for="images">Изображения:</label>
      <div>
		<input type="file" multiple @change="onFileChange">
		<ul>
		<li v-for="(image, index) in images" :key="index">
			{{ image.name }}
		</li>
		</ul>
	  </div>
      <br>
      <label for="remaining">Остаток:</label>
      <input v-model="remaining" type="number" id="remaining" name="remaining" required>
      <br>
      <label for="categories">Категории:</label>
      <input v-model="categories" type="text" id="categories" name="categories" placeholder="Введите категории через запятую" required>
      <br>
      <label for="type">Тип товара:</label>
      <select v-model="type" id="type" name="type" required @change="showCharacteristics">
        <option value="">Выберите тип товара</option>
        <option value="strings">Струны</option>
        <option value="guitar">Гитара</option>
        <!-- другие типы товаров -->
      </select>
      <br>
      <div v-if="type === 'strings'">
        <label for="stringGauge">Толщина струн:</label>
        <input v-model="stringGauge" type="text" id="stringGauge" name="stringGauge">
        <br>
        <label for="stringMaterial">Материал струн:</label>
        <input v-model="stringMaterial" type="text" id="stringMaterial" name="stringMaterial">
        <br>
      </div>
      <div v-if="type === 'guitar'">
        <label for="guitarForm">Форма гитары:</label>
        <input v-model="guitarForm" type="text" id="guitarForm" name="guitarForm">
        <br>
        <label for="guitarFrets">Количество ладов:</label>
        <input v-model="guitarFrets" type="text" id="guitarFrets" name="guitarFrets">
        <br>
      </div>
      <button type="submit">Добавить товар</button>
    </form>
  </div>
</template>

<style scoped>

</style>