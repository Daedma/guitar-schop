<script setup>
function updateRating(value) {
  document.getElementById('rating-value').value = value;
}
</script>

<template>
  <div class="product">
    <div class="product-header">
      <div class="product-rating">
        <span>Рейтинг:</span>
        <span>11.0</span>
      </div>
      <h2 class="product-name">Название товара</h2>
    </div>
    <div class="product-body">
      <div class="product-image-container">
        <div class="product-image">
          <img src="https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcR4v3PXlZ6V92qeYdk0hCY89eN5ksLOZd_vGUCcc9jdIOaU2_o6UkFXC59xxAHFlP_moiYUpJMtDymtkHbyA2hIKsq9g2j8iy1jgHFukNqKUR5fbQRu2BzG&usqp=CAE" alt="Изображение товара">
        </div>
        <div class="product-characteristics">
          <table>
            <tr>
              <td>Характеристика1</td>
              <td>Значение1</td>
            </tr>
            <tr>
              <td>Характеристика2</td>
              <td>Значение2</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>

  <!-- Отзывы к товару -->
  <!-- Написать свой отзыв -->
  <div class="product-review">
    <h2>Оставить отзыв</h2>
    <table class="product-review">
      <tr>
        <td>
          <textarea id="review-text" name="review-text" placeholder="Введите текст отзыва"></textarea>
        </td>
        <td>
          <div class="rating">
            <label for="rating">Оценка:</label>
            <input type="range" id="rating" name="rating" min="1" max="5" value="5" oninput="updateRating(this.value)">
            <output id="rating-value">5</output>
          </div>
          <button type="submit" id="publish-review">Опубликовать отзыв</button>
        </td>
      </tr>
    </table>
  </div>
  <!-- Отзывы -->
  <div class="product-reviews">
    <h2>Отзывы о товаре</h2>
    <table>
      <tr>
        <td>
          <div class="review-info">
            <span class="review-date">01.01.2023</span>
            <span class="review-author">Иван Иванов</span>
          </div>
          <div class="review-rating">
            <span class="rating-stars">★★★★☆</span>
          </div>
        </td>
        <td>
          <div class="review-text">
            <p>Отличный товар, соответствует описанию. Доставка была быстрой и удобной. Рекомендую!</p>
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="review-info">
            <span class="review-date">15.02.2023</span>
            <span class="review-author">Петр Петров</span>
          </div>
          <div class="review-rating">
            <span class="rating-stars">★★★☆☆</span>
          </div>
        </td>
        <td>

          <div class="review-text">
            <p>Товар пришел невредимым и в срок. Но качество немного хуже, чем ожидал. В целом нормально.</p>
          </div>
        </td>
      </tr>
      <!-- другие отзывы -->
    </table>
  </div>
</template>

<style scoped>

</style>