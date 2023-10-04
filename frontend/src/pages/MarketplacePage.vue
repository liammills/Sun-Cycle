<template>
  <q-page class="flex justify-center">
    <div class="row main-container large-container justify-between">
      <div class="column">
        <h1>Discover installations</h1>
        <div class="row q-my-xl">
          <div class="column q-mr-md">
            <QSelect class="q-mb-md" outlined v-model="recmethodselect" :options="recmethods" label="Method of recycling" style="width: 300px;" />
            <QInput outlined v-model="text" label="City/town" />
          </div>
          <div class="column">
            <QInput class="q-mb-md" outlined v-model="text" type="date" label="Retired by" />
            <QSelect outlined v-model="stateselect" :options="states" label="State" />
          </div>
        </div>
        <h3 class="q-mb-md">Material breakdown</h3>
        <div class="row q-mb-md">
          <div class="column q-mr-xl">
            <div class="row justify-between items-center matbreakpair">
              <span>Silicone</span>
              <QInput outlined v-model.number="siliconeinput" type="number" class="matbreakinput">
                <template v-slot:append>
                  <span style="font-size: 14px;">g</span>
                </template>
              </QInput>
            </div>
          </div>
          <div class="column">
            <div class="row justify-between items-center matbreakpair">
              <span>Silver</span>
              <QInput outlined v-model.number="siliconeinput" type="number" class="matbreakinput">
                <template v-slot:append>
                  <span style="font-size: 14px;">g</span>
                </template>
              </QInput>
            </div>
          </div>
        </div>
        <div class="row q-mb-md">
          <div class="column q-mr-xl">
            <div class="row justify-between items-center matbreakpair">
              <span>Polymers</span>
              <QInput outlined v-model.number="siliconeinput" type="number" class="matbreakinput">
                <template v-slot:append>
                  <span style="font-size: 14px;">g</span>
                </template>
              </QInput>
            </div>
          </div>
          <div class="column">
            <div class="row justify-between items-center matbreakpair">
              <span>Aluminium</span>
              <QInput outlined v-model.number="siliconeinput" type="number" class="matbreakinput">
                <template v-slot:append>
                  <span style="font-size: 14px;">g</span>
                </template>
              </QInput>
            </div>
          </div>
        </div>
        <div class="row q-mb-xl">
          <div class="column q-mr-xl">
            <div class="row justify-between items-center matbreakpair">
              <span>Copper</span>
              <QInput outlined v-model.number="siliconeinput" type="number" class="matbreakinput">
                <template v-slot:append>
                  <span style="font-size: 14px;">g</span>
                </template>
              </QInput>
            </div>
          </div>
          <div class="column">
            <div class="row justify-between items-center matbreakpair">
              <span>Glass</span>
              <QInput outlined v-model.number="siliconeinput" type="number" class="matbreakinput">
                <template v-slot:append>
                  <span style="font-size: 14px;">g</span>
                </template>
              </QInput>
            </div>
          </div>
        </div>
      </div>
      <div class="q-ml-xl column self-end">
        <div class="full-width full-height">
          <GMapMap
            :center="center"
            :zoom="7"
            style="width: 500px; height: 500px"
          >
          </GMapMap>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'MarketplacePage',
  data() {
    return {
      states: ["NSW", "QLD", "SA", "TAS", "VIC", "WA" ],
      stateselect: ref(null),

      recmethods: ["Chemical processing", "Electrochemical processing", "Hydrometallurgical separation", "Mechanical processing", "Thermal processing"],
      recmethodselect: ref(null),

      siliconeinput: ref(null),
      center: {lat: 51.093048, lng: 6.842120},
    };
  },
}
</script>

<style>
.matbreakpair {
  width: 200px;
}

.matbreakinput {
  width: 100px;
}
</style>
