<template>
  <q-page class="flex justify-center">
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
            v-model="selectedRecyclingMethod"
            :options="recyclingMethodOptions"
            label="Method of recycling"
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
              class="text-underline"
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
  </q-page>
</template>

<script>
export default {
  name: 'EditPanelInstallationPage',
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
      // TODO
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
  },
}
</script>
