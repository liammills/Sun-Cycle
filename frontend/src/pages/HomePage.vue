<template>
  <q-page class="flex flex-center column">
    <h1 class="landing-header">Recycling your PVs</h1>
    <h1 class="landing-header">has never been this EZ.</h1>
    <QBtn
      flat
      no-caps
      style="font-size: 18px;"
      class="landing-btn text-black submit-button q-mt-xl"
      @click="$router.push('/marketplace')"
    >
      Go to marketplace
    </QBtn>
  </q-page>
</template>

<script>
export default {
  name: 'HomePage',
}
</script>

<style lang="sass" scoped>
.landing-header
  font-family: Satoshi
  font-size: 95px
  font-weight: 700
  line-height: 105%
.landing-btn
  background: rgba(255, 255, 255, 0.10)
  box-shadow: 0px 0px 15px 4px rgba(0, 0, 0, 0.05)
.landing-btn:hover
  background: rgba(255, 255, 255, 0.4)
.submit-button
  transition: 0.25s ease-in-out
</style>