<template>
  <q-page class="flex justify-center">
    <div class="main-container">
      <h1>{{ panelInstallationId ? 'Edit' : 'Add' }} a solar panel installation</h1>
      <div class="row justify-between q-mt-lg">
        <div class="row">
          <QInput
            outlined
            v-model="text"
            label="Address"
            class="q-mr-md"
          />
          <QSelect
            outlined
            v-model="model"
            :options="options"
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
            class="q-mr-md"
          />
          <QInput
            outlined
            type="number"
            v-model="panel.qty"
            label="Quantity"
            class="q-mr-md"
          />
          <QInput
            outlined
            type="date"
            v-model="panel.installation_date"
            label="Installation Date"
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
      panels: [
        {
          id: 1,
          address: '131 Heeney Street, Chinchilla QLD 4413',
          type: 'Commercial',
          createdAt: '27/08/2021',
          size: '10kW',
          status: 'Active',
          panels: [
            {
              id: 1,
              model: 'SunPower Maxeon',
              units: 186,
            },
            {
              id: 2,
              model: 'Tindo Karra 300',
              units: 40,
            }
          ],
        },
        {
          id: 2,
          address: '123 Fake St, Chinchilla QLD 4413',
          type: 'Commercial',
          createdAt: '27/08/2021',
          size: '10kW',
          status: 'Active',
          panels: [
            {
              id: 1,
              model: 'SunPower Maxeon',
              units: 186,
            },
            {
              id: 2,
              model: 'Tindo Karra 300',
              units: 40,
            }
          ],
        },
        {
          id: 3,
          address: '123 Main St, Chinchilla QLD 4413',
          type: 'Commercial',
          createdAt: '27/08/2021',
          size: '10kW',
          status: 'Active',
          panels: [
            {
              id: 1,
              model: 'SunPower Maxeon',
              units: 186,
            },
            {
              id: 2,
              model: 'Tindo Karra 300',
              units: 40,
            }
          ],
        },
      ],
    }
  },
  // methods: {
  //   // async getPanels() {
  //   //   // try {
  //   //   //   const result = await this.$store.dispatch('login/login', {
  //   //   //     email: this.email,
  //   //   //     password: this.password,
  //   //   //   });

  //   //   //   if (!result) {
  //   //   //     this.password = '';
  //   //   //     this.error = 'Invalid login. Please try again';
  //   //   //   }
  //   //   //   this.$router.push(this.redirect || '/panels');
  //   //   // } catch (error) {
  //   //   //   this.password = '';
  //   //   //   if (error.response?.data?.message) {
  //   //   //     this.error = error.response.data.message;
  //   //   //   }
  //   //   // }
  //   // },
  // },
}
</script>
