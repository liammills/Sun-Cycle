<template>
  <QPage class="flex justify-center">
    <div class="main-container">
      <h1>{{ panelInstallationId ? 'Edit' : 'Add' }} a solar panel installation</h1>
      <div class="row justify-between q-mt-lg">
        <div class="row">
          <QInput
            outlined
            v-model="address"
            label="Address"
            class="q-mr-md"
            style="width: 300px;"
          />
          <QSelect
            outlined
            v-model="selectedInstallationType"
            :options="installationTypeOptions"
            label="Type"
            style="width: 220px;"
          />
        </div>
        <QBtn
          flat
          no-caps
          class="bg-primary text-white submit-button"
          @click="submit()"
        >
          Save
        </QBtn>
      </div>
      <QBtn
        flat
        no-caps
        class="bg-grey-4 text-black q-mt-md"
        @click="addPanel()"
      >
        Add a panel
      </QBtn>
      <div
        v-for="panel in panels"
        :key="panel.id"
        class="card-container full-width q-mt-md"
        style="font-size: 16px"
      >
        <div style="font-weight: 600">Panel</div>
        <div class="row justify-between q-my-sm">
          <QInput
            outlined
            type="text"
            v-model="panel.model"
            label="Model"
            class="q-mr-md col-5"
          />
          <QInput
            outlined
            type="number"
            v-model="panel.qty"
            label="Quantity"
            class="q-mr-md col-3"
          />
          <QInput
            outlined
            type="date"
            v-model="panel.installation_date"
            label="Installation Date"
            class="col-3"
          />
        </div>
        <div class="row justify-between q-mt-md">
          <div class="text-smaller">
            Can’t find the model? 
            <a
              class="text-underline cursor-pointer"
              @click="showAddModelDialog = true"
            >
              Make a new one.
            </a>
          </div>
          <div
            class="cursor-pointer text-smaller orange-hover"
            @click="deletePanel(panel.id)"
          >
            Delete
          </div>
        </div>
      </div>
    </div>
    <QDialog v-model="showAddModelDialog">
      <QCard
        class="q-pa-lg"
        style="width: 500px"
      >
        <div class="row items-center justify-between q-mb-md">
          <div class="text-h6">Add New Model</div>
          <QBtn
            flat
            no-caps
            class="bg-primary text-white submit-button"
            @click="submit()"
          >
            Save
          </QBtn>
        </div>
        <div>
          <QInput
            outlined
            label="Model Name"
            v-model="newModelName"
          />
          <QSelect class="q-mt-sm q-mb-md" outlined v-model="selectedRecyclingMethod" :options="recyclingMethodOptions" label="Method of recycling"  />
          <div class="q-mt-md">
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
      </QCard>
    </QDialog>
  </QPage>
</template>

<script>
import { useAuthStore } from 'src/stores/auth';

export default {
  name: 'EditPanelInstallationPage',
  setup() {
    const authStore = useAuthStore();
    return {
      authStore,
    };
  },
  props: {
    panelInstallationId: {
      type: Number,
      default: null,
      required: false,
    },
  },
  data() {
    return {
      address: '',
      selectedInstallationType: '',
      installationTypeOptions: [
        {
          label: 'Residential',
          value: 'residential',
        },
        {
          label: 'Commercial',
          value: 'commercial',
        },
        {
          label: 'Industrial',
          value: 'industrial',
        },
      ],
      selectedRecyclingMethod: '',
      recyclingMethodOptions: [
        {
          label: 'Chemical processing',
          value: 'chemical',
        },
        {
          label: 'Electrochemical processing',
          value: 'electrochemical',
        },
        {
          label: 'Hydrometallurgical separation',
          value: 'hydrometallurgical',
        },
        {
          label: 'Mechanical processing',
          value: 'mechanical',
        },
        {
          label: 'Thermal processing',
          value: 'thermal',
        },
      ],
      panels: [],
      showAddModelDialog: false,
      newModelName: '',
      materials: [
        { id: 1, name: 'Silicone', input: '' },
        { id: 2, name: 'Silver', input: '' },
        { id: 3, name: 'Polymers', input: '' },
        { id: 4, name: 'Aluminium', input: '' },
        { id: 5, name: 'Copper', input: '' },
        { id: 6, name: 'Glass', input: '' }
      ],
    }
  },
  mounted() {
    if (this.panelInstallationId) {
      this.getPanelInstallation();
    }
  },
  methods: {
    async getPanelInstallation() {
      // TODO
      this.address = '131 Heeney Street, Chinchilla QLD 4413';
      this.selectedRecyclingMethod = 'Chemical processing';
      this.panels = [
        {
          id: 1,
          model: 'SunPower Maxeon',
          qty: 186,
          installation_date: '2015-03-22',
        },
        {
          id: 2,
          model: 'Tindo Karra 300',
          qty: 40,
          installation_date: '2021-08-27',
        },
      ];
    },
    async savePanelInstallation() {
      try {
        let response;
        if (this.panelInstallationId) {
          response = await this.$api.put(`/installations/${this.panelInstallationId}`, {
            address: this.address,
            installation_type: this.selectedInstallationType,
          });
        } else {
          response = await this.$api.post('/installations/', {
            userId: this.authStore.state.user.userId,
            address: this.address,
            geoLocation: "-33.88832701093788, 151.19404158191045",
            state: "NSW",
            postcode: "2006",
            type: this.selectedInstallationType,
          });
        }
        await this.savePanels(response.data.id);
        if (response.status === 200) {
          this.$q.notify('Panel installation saved successfully');
        }
        this.$router.push(`/panels`);
      } catch (error) {
        console.log(error);
      }
    },
    async savePanels() {
      try {
        if (this.panelInstallationId) {
          await this.$api.put(`/installations/${this.panelInstallationId}/panels`, {
            panels: this.panels,
          });
        }
        else {
          await this.$api.post('/installations/', {
            panels: this.panels,
          });
        }
        console.log(response);
      } catch (error) {
        console.log(error);
      }
    },
    addPanel() {
      this.panels.push({
        id: this.panels.length + 1,
        model: '',
        qty: 0,
        installation_date: '',
      });
    },
    async deletePanel(id) {
      this.panels = this.panels.filter((panel) => panel.id !== id);
    },
    addNewModel() {
      if (this.newModelName) {
        // TODO
        console.log('New model:', this.newModelName);

        this.showAddModelDialog = false;
        this.newModelName = '';
      }
    },
  },
}
</script>
