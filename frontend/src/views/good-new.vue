<script setup>
function showCharacteristics() {
  var type = document.getElementById('type').value;
  var stringCharacteristics = document.getElementById('string-characteristics');
  var guitarCharacteristics = document.getElementById('guitar-characteristics');

  if (type === 'strings') {
    stringCharacteristics.style.display = 'block';
    guitarCharacteristics.style.display = 'none';
  } else if (type === 'guitar') {
    stringCharacteristics.style.display = 'none';
    guitarCharacteristics.style.display = 'block';
  } else {
    stringCharacteristics.style.display = 'none';
    guitarCharacteristics.style.display = 'none';
  }
}
</script>

<template>

  <div class="add-product">
    <h2>Добавить товар</h2>
    <form action="/add-product" method="POST" enctype="multipart/form-data">
      <label for="name">Название:</label>
      <input type="text" id="name" name="name" required>
      <br>
      <label for="description">Описание:</label>
      <textarea id="description" name="description" required></textarea>
      <br>
      <label for="cost">Цена:</label>
      <input type="number" id="cost" name="cost" step="0.01" required>
      <br>
      <label for="images">Изображения:</label>
      <input type="file" id="images" name="images[]" multiple required>
      <br>
      <label for="remaining">Остаток:</label>
      <input type="number" id="remaining" name="remaining" required>
      <br>
      <label for="categories">Категории:</label>
      <input type="text" id="categories" name="categories" placeholder="Введите категории через запятую" required>
      <br>
      <label for="type">Тип товара:</label>
      <select id="type" name="type" required onchange="showCharacteristics()">
        <option value="">Выберите тип товара</option>
        <option value="strings">Струны</option>
        <option value="guitar">Гитара</option>
        <!-- другие типы товаров -->
      </select>
      <br>
      <div id="string-characteristics" style="display: none;">
        <label for="stringGauge">Толщина струн:</label>
        <input type="text" id="stringGauge" name="stringGauge">
        <br>
        <label for="stringMaterial">Материал струн:</label>
        <input type="text" id="stringMaterial" name="stringMaterial">
        <br>
      </div>
      <div id="guitar-characteristics" style="display: none;">
        <label for="guitarForm">Форма гитары:</label>
        <input type="text" id="guitarForm" name="guitarForm">
        <br>
        <label for="guitarFrets">Количество ладов:</label>
        <input type="text" id="guitarFrets" name="guitarFrets">
        <br>
      </div>
      <button type="submit">Добавить товар</button>
    </form>
  </div>
</template>

<style scoped>

</style>