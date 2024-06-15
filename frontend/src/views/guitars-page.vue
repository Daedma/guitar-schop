<script>
// import axios from 'axios'

import axios from "axios";

export default {
  data() {
    return {
      goods: []
    }
  },
  methods: {
    async getGoods() {
      try {
        const response = await axios.get('http://localhost:8080/guitar-shop/api/items?type=guitar',
            { withCredentials: true });
        // console.log(JSON.parse(response.request.responseText).length);
        const numberOfGoods = JSON.parse(response.request.responseText).length;
        // console.log(response.data[1].description)

        for (let i = 0; i < numberOfGoods; i++) {
          const object = response.data[i];
          // console.log(response.data[i].images);
          this.goods.push({
            label: object.name,
            images: object.images,
            mainImage: "http://localhost:8080/guitar-shop/" + response.data[i].images[0],
            cost: object.cost,
            rate: object.rate
          })
          console.log(this.goods[i].mainImage);
        }
      } catch (error) {
        if (error.response && error.response.status !== 200) {
          console.log(error.response.data.error);
      }
    }
  }
},
  beforeMount() {
    this.getGoods()
  }
}
</script>

  <template>
    <div class="width-100 margin-top-50">
      <div class="container">
        <div class="product-text">
          <div class="text">
            <a>Гитары</a>
          </div>
        </div>
        <div class="width-25" form @submit.prevent="getGoods" v-for="good in goods" :key="good">

          <div class="product-section" >
            <div class="product-border" >
              <div class="product-img-center">
                <a href="#">
                  <img class="product-img" :src="good.mainImage" >
                </a>
              </div>
              <div>
                <p class="product-name">
                  <a href="#"> {{ good.label }} </a>
                </p>
                <p class="product-rating">
                  <i class='fa fa-star' style='color: #f3da35'></i>
                  <i class='fa fa-star' style='color: #f3da35'></i>
                  <i class='fa fa-star' style='color: #f3da35'></i>
                  <i class='fa fa-star' style='color: #f3da35'></i>
                  <i class="fa fa-star" style="color: rgb(178,178,178);"></i>
                  <span> {{ good.rate }} </span>
                </p>
                <p class="product-price">
                  <span class="product-price">₽{{ good.cost }}</span>
                </p>
              </div>
            </div>
          </div>


        </div>
      </div>
    </div>
  </template>


<style scoped>
</style>