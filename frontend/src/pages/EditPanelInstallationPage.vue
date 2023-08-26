<template>
  <q-page class="flex justify-center">
    <div class="main-container">
      <h1>{{ panelInstallationId ? 'Edit' : 'Add' }} a solar panel installation</h1>
      <!-- <div>
        <p
          v-if="panels.length === 0"
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
            v-for="panel in panels"
            :key="panel.id"
            class="card-container full-width q-mt-md"
          >
            <div class="row justify-between items-center">
              <h3 style="font-weight: 600">{{ panel.address }}</h3>
              <div class="row items-center q-mt-xs">
                <QIcon
                  name="add"
                  size="sm"
                  class="q-mr-md"
                />
                <QIcon
                  name="edit"
                  size="20px"
                />
              </div>
            </div>
            <div class="row justify-between">
              <div style="font-size: 16px">
                <div class="q-mt-xs row">
                  <div class="q-mr-lg">
                    <span style="font-weight: 600">Type: </span>
                    <span>{{ panel.type }}</span>
                  </div>
                  <div>
                    <span style="font-weight: 600">Added: </span>
                    <span>{{ panel.createdAt }}</span>
                  </div>
                </div>
                <div
                  class="q-mt-xs"
                  style="font-weight: 600"
                >
                  Panels
                </div>
                <div
                  v-for="panel in panel.panels"
                  :key="panel.id"
                  class="q-mt-xs q-ml-sm row items-center"
                >
                  <div class="q-mr-lg">
                    <span class="q-mr-xs">{{ panel.name }}</span>
                    <span>({{ panel.units }} units)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->
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
          address: '123 Main St, Chinchilla QLD',
          type: 'Commercial',
          createdAt: '27/08/2021',
          size: '10kW',
          status: 'Active',
          panels: [
            {
              id: 1,
              name: 'SunPower Maxeon',
              units: 186,
            },
            {
              id: 2,
              name: 'Tindo Karra 300',
              units: 40,
            }
          ],
        },
      ],
    };
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
