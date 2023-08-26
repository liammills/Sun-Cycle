<template>
  <QLayout view="hHh lpR fFf">

    <SCHeader :loggedin="true"/>

    <QPageContainer>
      <RouterView />
    </QPageContainer>

    <QFooter class="bg-grey-9 text-white q-px-md q-py-lg">
      <QToolbar>
        <QToolbarTitle>
          <img
            class="q-mb-sm"
            style="width: 120px"
            src="src/assets/suncycle-logo-white.svg"
          >
        </QToolbarTitle>
        <div class="row q-gutter-md">
          <span>About Us</span>
          <span>Marketplace</span>
        </div>
      </QToolbar>
    </QFooter>

  </QLayout>
</template>

<script>
import { defineComponent } from 'vue'
import SCHeader from 'src/components/SCHeader.vue';

export default defineComponent({
    name: 'MainLayout',
    components: { SCHeader }
})
</script>
