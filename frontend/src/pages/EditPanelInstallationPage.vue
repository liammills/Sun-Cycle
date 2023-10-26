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
          @click="savePanelInstallation()"
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
        v-for="(panel) in panels"
        :key="panel.id"
        class="card-container full-width q-mt-md"
        style="font-size: 16px"
      >
        <div style="font-weight: 600">Panel</div>
        <div class="row justify-between q-my-sm">
          <QSelect
            outlined
            use-input
            v-model="panel.model"
            :options="filteredModels"
            option-value="id"
            option-label="modelName"
            label="Model"
            class="q-mr-md col-5"
            @filter="filterModels"
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
            @click="createModel()"
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
      installationId: null,
      address: '',
      selectedInstallationType: '',
      installationTypeOptions: [
        'Residential',
        'Commercial',
        'Industrial',
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
      models: [],
      filteredModels: [],
    }
  },
  async mounted() {
    if (this.panelInstallationId) {
      await this.getPanelInstallation();
      this.installationId = this.panelInstallationId;
    }
    this.getModels();
  },
  methods: {
    filterModels(val, update, abort) {
      update(() => {
        if (val === '') {
          this.filteredModels = this.models;
        } else {
          const needle = val.toLowerCase();
          this.filteredModels = this.models.filter(v => v.modelName.toLowerCase().indexOf(needle) > -1);
        }
      });
    },
    isValidInput() {
      return this.address && this.address !== '' && this.selectedInstallationType && this.selectedInstallationType !== ''
      && this.panels.length > 0 && this.panels.every((panel) => panel.model && panel.model?.modelName !== '' && panel.qty
      && panel.qty > 0 && panel.installation_date && panel.installation_date !== '');
    },
    async getPanelInstallation() {
      try {
        const response = await this.$api.get(`/installations/${this.panelInstallationId}`);

        this.address = response.data.solarPanelInstallation.address;
        this.selectedInstallationType = response.data.solarPanelInstallation.type;
        this.panels = response.data.solarPanels.map((panel) => {
          const installationDate = new Date(panel.installationDate).toISOString().split('T')[0];
          return {
            id: panel.id,
            model: panel.model,
            qty: panel.quantity,
            installation_date: installationDate,
          }
        });
      } catch (error) {
        console.log(error);
      }
    },
    async savePanelInstallation() {
      try {
        if (!this.isValidInput()) {
          this.$q.notify('Please fill in all fields and add at least one panel.');
          return;
        }
        let response;
        if (this.installationId) {
          response = await this.$api.put(`/installations/${this.installationId}`, {
            userId: this.authStore.user.userId,
            address: this.address,
            type: this.selectedInstallationType,
          });
        } else {
          response = await this.$api.post('/installations', {
            userId: this.authStore.user.userId,
            address: this.address,
            geoLocation: "-33.88832701093788, 151.19404158191045",
            state: "NSW",
            postcode: "2006",
            type: this.selectedInstallationType,
          });
          this.installationId = response.data.id;
        }
        await this.savePanels();
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
        for (const panel of this.panels) {
          const panelData = {
            modelId: panel.model.id,
            installationId: this.installationId,
            quantity: panel.qty,
            installationDate: panel.installation_date,
            retirementDate: '10/10/2028',  // Add retirement date logic here
          };
          if (this.panelInstallationId) {
            await this.$api.put(`/panels/${panel.id}`, panelData);
          } else {
            await this.$api.post(`/panels`, { ...panelData, installationId: this.installationId });
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    addPanel() {
      const today = new Date().toISOString().split('T')[0];
      this.panels.push({
        id: this.panels.length + 1,
        model: '',
        qty: 0,
        installation_date: today,
      });
    },
    async deletePanel(id) {
      this.panels = this.panels.filter((panel) => panel.id !== id);
    },
    async getModels() {
      try {
        const response = await this.$api.get('/models');
        this.models = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    async createModel() {
      if (this.newModelName && this.selectedRecyclingMethod) {
        try {
          const [silicone, silver, polymers, aluminium, copper, glass] = this.materials.map(material => material.input);
          const response = await this.$api.post('/models', {
            modelName: this.newModelName,
            recyclingMethod: this.selectedRecyclingMethod,
            silicone,
            silver,
            polymers,
            aluminium,
            copper,
            glass
          });
          if (response.status == 200) {
            this.$q.notify('Solar panel maodel created successfully.')
          }
          console.log(response);
        } catch (error) {
          console.log(error);
        }

        this.showAddModelDialog = false;
        this.newModelName = '';
      }
    },
  },
}
</script>
