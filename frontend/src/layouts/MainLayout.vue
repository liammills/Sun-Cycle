<template>
  <QLayout view="hHh lpR fFf">

    <QHeader class="bg-transparent text-black q-px-md q-py-sm">
      <QToolbar>
        <QToolbarTitle>
          <img
            class="q-mr-sm"
            style="width: 220px"
            src="src/assets/suncycle-logo.svg"
          >
        </QToolbarTitle>
      </QToolbar>
    </QHeader>

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

export default defineComponent({
  name: 'MainLayout',
})
</script>
