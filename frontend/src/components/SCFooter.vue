<template>
    <QFooter class="bg-grey-9 text-white q-px-md q-py-lg">
      <QToolbar>
        <img
          class="q-mb-sm"
          style="width: 120px"
          src="src/assets/suncycle-logo-white.svg"
        >
        </QToolbar>
        <div class="row q-ml-sm q-pb-lg" style="font-size: 16px; border-bottom: 1px solid white;">
            <span class="q-mr-lg">About Us</span>
            <span>Marketplace</span>
        </div>
        <div class="row justify-between q-ml-sm q-mt-md" style="font-size: 12px;">
            <div>
                <span class="q-mr-lg">Privacy policy</span>
                <span>Cookie policy</span>
            </div>
            <div>
                <span>© 2023 SunCycle. All rights reserved.</span>
            </div>
        </div>
    </QFooter>
</template>

<script>
export default {
    name: 'SCFooter',
}
</script>