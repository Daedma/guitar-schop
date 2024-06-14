import { createRouter, createWebHistory } from 'vue-router'
import StartPage from "@/views/start-page.vue";
import CartPage from "@/views/cart-page.vue";
import ErrorPage from "@/views/error-page.vue";
import GoodInfoPage from "@/views/good-info.vue";
import GoodNewPage from "@/views/good-new.vue";
import GuitarsPage from "@/views/guitars-page.vue";
import OrderPlacementPage from "@/views/order-placement-page.vue";
import OrderTrackingPage from "@/views/order-tracking.vue";
import SignInPage from "@/views/sign-in.vue";
import SignUpPage from "@/views/sign-up.vue";
import StringsPage from "@/views/strings-page.vue";

import 'bootstrap/dist/css/bootstrap.css'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: StartPage
    },
    {
      path: '/cart',
      name: 'cart',
      component: CartPage
    },
    {
      path: '/error',
      name: 'error',
      component: ErrorPage
    },
    {
      path: '/info',
      name: 'good-info',
      component: GoodInfoPage
    },
    {
      path: '/new-good',
      name: 'good-new',
      component: GoodNewPage
    },
    {
      path: "/guitars",
      name: 'guitars',
      component: GuitarsPage
    },
    {
      path: '/new-order',
      name: 'new-order',
      component: OrderPlacementPage
    },
    {
      path: '/tracking',
      name: 'tracking',
      component: OrderTrackingPage
    },
    {
      path: '/sign-in',
      name: 'sign-in',
      component: SignInPage
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: SignUpPage
    },
    {
      path: "/strings",
      name: 'strings',
      component: StringsPage
    }
  ]
})

export default router
