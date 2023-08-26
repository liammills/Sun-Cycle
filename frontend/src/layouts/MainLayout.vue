<template>
  <q-layout view="hHh lpR fFf">

    <q-header class="bg-transparent text-black q-px-md q-py-sm">
      <q-toolbar>
        <q-toolbar-title>
          <img
            class="q-mr-sm"
            style="width: 200px"
            src="src/assets/suncycle-logo.svg"
          >
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer class="bg-grey-9 text-white q-pa-md">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
          </q-avatar>
          <div>Title</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'MainLayout',
})
</script>
