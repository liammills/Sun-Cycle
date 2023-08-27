<template>
  <QLayout view="hhh lpR fff">

    <SCHeader />

    <QPageContainer>
      <RouterView />
    </QPageContainer>

    <SCFooter />

  </QLayout>
</template>

<script>
import { defineComponent } from 'vue'
import SCHeader from 'src/components/SCHeader.vue';
import SCFooter from 'src/components/SCFooter.vue';

export default defineComponent({
    name: 'MainLayout',
    components: { SCHeader, SCFooter, }
})
</script>
