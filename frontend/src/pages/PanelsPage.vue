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
          v-if="installations?.length === 0"
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
                  icon="delete"
                  no-caps
                  ripple
                  dense
                  flat
                  text-color="dark"
                  @click="deleteInstallation(installation.solarPanelInstallation.id)"
                >
                  <QTooltip>Delete</QTooltip>
                </QBtn>
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
                    <span class="q-mr-xs">{{ panel.model.modelName }}</span>
                    <span>({{ panel.quantity }} units)</span>
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
    setTimeout(async () => {
      await this.getPanels();
    }, 300);
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
    async deleteInstallation(id) {
      try {
        this.$q.dialog({
          title: 'Confirm',
          message: 'Are you sure you want to delete this installation?',
          ok: 'Yes',
          cancel: 'No',
        }).onOk(async() => {
          console.log("Dialog resolved with OK");
          const response = await this.$api.delete(`/installations/${id}`);
          this.installations = this.installations.filter(installation => installation.solarPanelInstallation.id !== id);
          if (response.success) {
            this.$q.notify('Installation deleted successfully');
          }
        });
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
      const flattenedData = this.flattenData(this.installations);
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
      return data.flatMap(item => {
        if (item.solarPanels && item.solarPanels.length > 0) {
          // If there are solar panels, flatten each solar panel along with the installation data
          return item.solarPanels.map(panel => {
            return {
              installationId: item.solarPanelInstallation.id,
              geoLocation: item.solarPanelInstallation.geoLocation,
              address: item.solarPanelInstallation.address,
              state: item.solarPanelInstallation.state,
              postcode: item.solarPanelInstallation.postcode,
              installationType: item.solarPanelInstallation.type,
              addedDate: item.solarPanelInstallation.addedDate,
              panelId: panel.id,
              installationDate: panel.installationDate,
              retirementDate: panel.retirementDate,
              quantity: panel.quantity,
              modelId: panel.model.id,
              modelName: panel.model.modelName,
              recyclingMethod: panel.model.recyclingMethod,
              polymers: panel.model.polymers,
              silicon: panel.model.silicon,
              copper: panel.model.copper,
              glass: panel.model.glass,
              silver: panel.model.silver,
              aluminium: panel.model.aluminium
            };
          });
        } else {
          // If there are no solar panels, just return the installation data
          return [{
            installationId: item.solarPanelInstallation.id,
            geoLocation: item.solarPanelInstallation.geoLocation,
            address: item.solarPanelInstallation.address,
            state: item.solarPanelInstallation.state,
            postcode: item.solarPanelInstallation.postcode,
            installationType: item.solarPanelInstallation.type,
            addedDate: item.solarPanelInstallation.addedDate,
            panelId: null,
            installationDate: null,
            retirementDate: null,
            quantity: null,
            modelId: null,
            modelName: null,
            recyclingMethod: null,
            polymers: null,
            silicon: null,
            copper: null,
            glass: null,
            silver: null,
            aluminium: null
          }];
        }
      });
    }
  }
}
</script>
