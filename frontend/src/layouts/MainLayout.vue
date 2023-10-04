<template>
  <QLayout
    view="hhh lpR fff"
    :style="layoutStyle"
  >

    <SCHeader />

    <QPageContainer>
      <RouterView />
    </QPageContainer>

    <SCFooter />

  </QLayout>
</template>

<script>
import { defineComponent } from 'vue';
import { useRoute } from 'vue-router';
import SCHeader from 'src/components/SCHeader.vue';
import SCFooter from 'src/components/SCFooter.vue';

export default defineComponent({
    name: 'MainLayout',
    components: { SCHeader, SCFooter },
    computed: {
    layoutStyle() {
      const route = useRoute();
      if (route.path === '/' || route.path == '/about') {
        return "background: linear-gradient(322deg, #E64A1A 20.13%, #F39519 48.4%, #FFF 82.17%), #FDFDFD;";
      }
      return "";
    },
  },
})
</script>

<!-- <style scoped>
  * {
    
  }
</style> -->
