<template>
    <QFooter class="bg-grey-9 text-white q-px-md q-py-lg">
      <QToolbar>
        <QToolbarTitle>
          <img
            class="q-mb-sm"
            style="width: 120px"
            src="src/assets/suncycle-logo-white.svg"
          >
        </QToolbarTitle>
        </QToolbar>
        <div class="row q-ml-sm q-pb-lg" style="font-size: 16px; border-bottom: 1px solid white;">
            <span class="q-mr-lg">About Us</span>
            <span>Marketplace</span>
        </div>
        <div class="row q-ml-sm q-mt-xs" style="font-size: 14px;">
            <span class="q-mr-lg">Privacy policy</span>
            <span>Cookie policy</span>
        </div>
    </QFooter>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'SCFooter',
})
</script>