<template>
  <q-page class="flex justify-center">
    <div class="main-container large-container">
      <h1>Discover installations</h1>
      <div class="row q-mt-lg">
        <div class="column q-mr-md">
          <QSelect class="q-mb-md" outlined v-model="selectedRecyclingMethod" :options="recyclingMethodOptions" label="Method of recycling" style="width: 300px;" />
          <QInput outlined v-model="text" label="City/town" />
        </div>
        <div class="column">
          <QInput class="q-mb-md" outlined v-model="text" type="date" label="Retired by" />
          <QSelect outlined v-model="selectedState" :options="stateOptions" label="State" />
        </div>
        <div
          class="q-ml-xl"
          style="max-width: 500px; margin-top: -42px"
        >
          <h3 class="q-pb-md">Material breakdown</h3>
          <div class="q-gutter-lg row q-col-gutter-xs">
            <div class="q-col-4" v-for="material in materials" :key="material.id">
              <div
                class="row justify-between items-center"
                style="width: 190px"
              >
                <span class="q-mr-md">{{ material.name }}</span>
                <QInput 
                  outlined
                  dense
                  v-model.number="material.input" 
                  type="number" 
                  style="width: 100px;"
                >
                  <template v-slot:append>
                    <span style="font-size: 14px;">g</span>
                  </template>
                </QInput>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="q-my-xl">
        <GMapMap
          :center="center"
          :zoom="7"
          style="width: 1100px; height: 600px"
        >
        </GMapMap>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'MarketplacePage',
  data() {
    return {
      stateOptions: ["NSW", "QLD", "SA", "TAS", "VIC", "WA" ],
      selectedState: '',
      recyclingMethodOptions: ["Chemical processing", "Electrochemical processing", "Hydrometallurgical separation", "Mechanical processing", "Thermal processing"],
      selectedRecyclingMethod: '',
      materials: [
        { id: 1, name: 'Silicone', input: '' },
        { id: 2, name: 'Silver', input: '' },
        { id: 3, name: 'Polymers', input: '' },
        { id: 4, name: 'Aluminium', input: '' },
        { id: 5, name: 'Copper', input: '' },
        { id: 6, name: 'Glass', input: '' }
      ],
      center: {lat: 51.093048, lng: 6.842120},
      radius: 10000,
      markers: [],
    };
  },
  mounted() {
    this.loadMapData();
  },
  watch: {
    center: function() {
      this.loadMapData();
    },
    selectedState: function() {
      this.loadMapData();
    },
    selectedRecyclingMethod: function() {
      this.loadMapData();
    },
  },
  methods: {
    async loadMapData() {
      try {
        const response = await this.$api.get('/map',
          {
            params: {
              latitude: this.center.lat,
              longitude: this.center.lng,
              radius: this.radius,
              state: this.selectedState,
              recycling_method: this.selectedRecyclingMethod,
            },
          },
        );
        const markers = response.data.map(marker => {
          return {
            position: {
              lat: panel.latitude,
              lng: panel.longitude,
            },
            title: panel.name,
            icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png',
          };
        });
        this.markers = markers;
      } catch (error) {
        console.log(error);
      }
    }
  }
}
</script>
