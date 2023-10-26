<template>
  <q-page class="flex justify-center">
    <div class="main-container">
      <div class="row justify-between items-center q-mb-md">
        <h1>Your Panels</h1>
        <QBtn
          flat
          no-caps
          class="bg-primary text-white submit-button q-mt-sm"
          @click="$router.push('/panels/add')"
        >
          Add an installation
        </QBtn>
      </div>
      <div>
        <p
          v-if="installations.length === 0"
          class="text-center q-pt-xl"
        >
          You have no panels yet. Add an installation to get started.
        </p>
        <div v-else>
          <div class="text-right">
            <a
              class="cursor-pointer q-mb-md"
              @click="exportData"
            >
              Export data as csv
            </a>
          </div>
          <div
            v-for="installation in installations"
            :key="installation.solarPanelInstallation.id"
            class="card-container full-width q-mt-md"
          >
            <div class="row justify-between items-center">
              <h3 style="font-weight: 600">{{ installation.solarPanelInstallation.address }}</h3>
              <div class="row items-center q-mt-xs">
                <QBtn
                  size="md"
                  icon="edit"
                  no-caps
                  ripple
                  dense
                  flat
                  text-color="dark"
                  @click="$router.push(`/panels/${installation.solarPanelInstallation.id}/edit`)"
                >
                  <QTooltip>Edit</QTooltip>
                </QBtn>
              </div>
            </div>
            <div class="row justify-between">
              <div style="font-size: 16px">
                <div class="q-mt-xs row">
                  <div class="q-mr-lg">
                    <span style="font-weight: 600">Type: </span>
                    <span>{{ installation.solarPanelInstallation.type }}</span>
                  </div>
                  <div>
                    <span style="font-weight: 600">Added: </span>
                    <span>{{ formatDate(installation.solarPanelInstallation.addedDate) }}</span>
                  </div>
                </div>
                <div
                  class="q-mt-xs"
                  style="font-weight: 600"
                >
                  Panels
                </div>
                <div
                  v-if="installation.solarPanels?.length === 0"
                  class="q-mt-xs q-ml-sm"
                >
                  No panels added yet.
                </div>
                <div
                  v-for="panel in installation.solarPanels"
                  :key="panel.id"
                  class="q-mt-xs q-ml-sm row items-center"
                >
                  <div class="q-mr-lg">
                    <span class="q-mr-xs">{{ panel.name }}</span>
                    <span>({{ panel.units }} units)</span>
                  </div>
                  <QBtn
                    size="sm"
                    icon="info_outline"
                    no-caps
                    ripple
                    dense
                    flat
                    text-color="dark"
                    @click="$router.push(`/panels/${installation.solarPanelInstallation.id}/edit`)"
                  >
                    <QTooltip>More info</QTooltip>
                  </QBtn>
                </div>
              </div>
              <a
                :href='`https://www.google.com/maps/search/?api=1&query=${installation.solarPanelInstallation.address}`'
                target="_blank"
                class="self-end"
              >
                Show on map
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import Papa from 'papaparse';

export default {
  name: 'PanelsPage',
  data() {
    return {
      installations: [],
    };
  },
  async mounted() {
    await this.getPanels();
  },
  methods: {
    async getPanels() {
      try {
        const response = await this.$api.get('/installations');
        this.installations = response.data;
      } catch (error) {
        if (error.response?.data?.message) {
          this.error = error.response.data.message;
        }
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const day = String(date.getDate()).padStart(2, '0');
      const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
      const year = date.getFullYear();
      
      return `${day}/${month}/${year}`;
    },
    exportData() {
      // Sample JSON data
      const jsonData = [{
        id: 3,
        installationDate: "2023-10-09T13:00:00.000+00:00",
        retirementDate: "2028-10-09T13:00:00.000+00:00",
        recyclingMethod: "Recycle",
        installation: {
          id: 1,
          user: {
            userId: 1,
            email: "jili5381@uni.sydney.edu.au",
            password: "{bcrypt}$2a$10$Qsiyn5t.MDbIyxml5LN72OOjWAfoYZU2mIlsXbUqV6tLfHwh4BIom",
            enabled: true,
            username: "jili5381@uni.sydney.edu.au",
            authorities: null,
            accountNonExpired: true,
            accountNonLocked: true,
            credentialsNonExpired: true
          },
          geoLocation: "-33.88832701093788, 151.19404158191045",
          address: "1 Cleveland St, Camperdown",
          state: "NSW",
          postcode: 2006,
          type: "Personal"
        },
        model: {
          id: 1,
          polymers: 100.0,
          silicon: 100.0,
          copper: 100.0,
          glass: 100.0,
          silver: 100.0,
          aluminium: 100.0
        }
      }];

      const flattenedData = this.flattenData(jsonData);
      const csv = Papa.unparse(flattenedData);

      const blob = new Blob([csv], { type: 'text/csv' });
      const url = window.URL.createObjectURL(blob);

      const a = document.createElement('a');
      a.setAttribute('hidden', '');
      a.setAttribute('href', url);
      a.setAttribute('download', 'data.csv');
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    },

    flattenData(data) {
      return data.map(item => {
        return {
          id: item.id,
          installationDate: item.installationDate,
          retirementDate: item.retirementDate,
          recyclingMethod: item.recyclingMethod,
          'installation.id': item.installation.id,
          'installation.geoLocation': item.installation.geoLocation,
          'installation.address': item.installation.address,
          'installation.state': item.installation.state,
          'installation.postcode': item.installation.postcode,
          'installation.type': item.installation.type,
          'model.id': item.model.id,
          'model.polymers': item.model.polymers,
          'model.silicon': item.model.silicon,
          'model.copper': item.model.copper,
          'model.glass': item.model.glass,
          'model.silver': item.model.silver,
          'model.aluminium': item.model.aluminium
        };
      });
    }
  }
}
</script>
