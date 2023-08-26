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

    <QFooter class="bg-grey-9 text-white q-pa-md">
      <!-- <QToolbar>
        <QToolbarTitle>
          <QAvatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
          </QAvatar>
          <div>Title</div>
        </QToolbarTitle>
      </QToolbar> -->
    </QFooter>

  </QLayout>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'MainLayout',
})
</script>
