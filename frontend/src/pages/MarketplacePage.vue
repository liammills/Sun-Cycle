<template>
  <q-page class="flex justify-center">
    <div class="main-container large-container">
      <h1>Discover installations</h1>
      <div class="row q-mt-lg">
        <div class="column q-mr-md">
          <QSelect class="q-mb-md" outlined v-model="selectedRecyclingMethod" :options="recyclingMethodOptions" label="Method of recycling" style="width: 300px;" />
          <QInput outlined v-model="selectedCity" label="City/town" />
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
        <QBtn
          flat
          no-caps
          class="bg-primary text-white submit-button q-mt-sm"
          @click="loadMapData"
        >
          Discover
        </QBtn>
      </div>
      <div class="q-my-xl">
        <GMapMap
          v-if="showMarkers"
          :center="center"
          :zoom="4.2"
          style="width: 1100px; height: 600px"
        >
          <GMapMarker
            v-for="marker in markers"
            :key="marker.solarPanel.id"
            :position="marker.geoLocation"
            :clickable="true"
            @click="center=marker.geoLocation, openInfoWindow(marker)"
          >
            <GMapInfoWindow
              v-if="infoWindowOpen && activeMarker === marker"
            >
              <div style="font-weight: bold;">Solar panel information</div>
              <div style="font-style: italic;">{{ marker.solarPanel.installation.address }}</div>
              <div>Retires on {{ marker.solarPanel.retirementDate.slice(0, 10) }}</div>
              <a :href="ownerEmail">Email the owner</a>
              <div style="font-weight: bold; margin-top: 12px;">Material breakdown</div>
              <div>Silicone: {{ marker.solarPanel.model.silicon }}</div>
              <div>Silver: {{ marker.solarPanel.model.silver }}</div>
              <div>Polymers: {{ marker.solarPanel.model.polymers }}</div>
              <div>Aluminium: {{ marker.solarPanel.model.aluminium }}</div>
              <div>Copper: {{ marker.solarPanel.model.copper }}</div>
              <div>Glass: {{ marker.solarPanel.model.glass }}</div>
            </GMapInfoWindow>
          </GMapMarker>
        </GMapMap>
      </div>
    </div>
  </q-page>
</template>

<script>
import { RemoteChunkSize } from 'papaparse';

export default {
  name: 'MarketplacePage',
  data() {
    return {
      stateOptions: ["NSW", "QLD", "SA", "TAS", "VIC", "WA" ],
      selectedState: '',
      recyclingMethodOptions: ["Chemical processing", "Electrochemical processing", "Hydrometallurgical separation", "Mechanical processing", "Thermal processing"],
      selectedRecyclingMethod: '',
      selectedCity: '',
      materials: [
        { id: 1, name: 'Silicone', input: '' },
        { id: 2, name: 'Silver', input: '' },
        { id: 3, name: 'Polymers', input: '' },
        { id: 4, name: 'Aluminium', input: '' },
        { id: 5, name: 'Copper', input: '' },
        { id: 6, name: 'Glass', input: '' }
      ],
      center: {lat: -25.274398, lng: 133.775136},
      radius: 10000,
      markers: [
        // {
        //   id: 1,
        //   installationDate: "2023-10-09T13:00:00.000+00:00",
        //   retirementDate: "2028-10-09T13:00:00.000+00:00",
        //   installation: {
        //     id: 1,
        //     geoLocation: {
        //       lat: 40.730610,
        //       lng: -73.935242
        //     },
        //     address: "1 Cleveland St, Camperdown",
        //     state: "NSW",
        //     postcode: 2006,
        //     type: "Personal",
        //     addedDate: null,
        //   },
        //   model: {
        //     id: 1,
        //     modelName: "Very cool model",
        //     recyclingMethod: "Chemical processing",
        //     polymers: 100.0,
        //     silicon: 100.0,
        //     copper: 100.0,
        //     glass: 100.0,
        //     silver: 100.0,
        //     aluminium: 100.0
        //   }
        // },
      ],
      showMarkers: false,
      infoWindowOpen: false,
      activeMarker: null,
      ownerEmail: "",
    };
  },
  mounted() {
    this.showMarkers = true;
  },
  methods: {
    openInfoWindow(marker) {
      this.ownerEmail = "mailto:" + marker.solarPanel.installation.email;
      this.activeMarker = marker;
      this.infoWindowOpen = true;
    },
    async loadMapData() {
      this.showMarkers = false;
      try {
        const body = {
          recyclingMethod: this.selectedRecyclingMethod,
          retirementDate: "10/10/2028",
          city: this.selectedCity,
          state: this.selectedState,
          breakdown: {
            silicon: this.materials[0].input,
            silver: this.materials[1].input,
            polymers: this.materials[2].input,
            aluminium: this.materials[3].input,
            copper: this.materials[4].input,
            glass: this.materials[5].input,
          }
        };
        const response = await this.$api.post('/market', body);
        console.log("HELLO", response.data);
        this.markers = response.data;
      } catch (error) {
        console.log(error);
      }
      this.showMarkers = true;
    }
  }
}
</script>
