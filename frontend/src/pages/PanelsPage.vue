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
                <QBtn
                  size="md"
                  icon="add"
                  no-caps
                  ripple
                  dense
                  flat
                  text-color="dark"
                  class="q-mr-sm"
                  @click="$router.push(`/panels/${panel.id}/edit`)"
                >
                  <QTooltip>Add a panel</QTooltip>
                </QBtn>
                <QBtn
                  size="md"
                  icon="edit"
                  no-caps
                  ripple
                  dense
                  flat
                  text-color="dark"
                  @click="$router.push(`/panels/${panel.id}/edit`)"
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
                    <span>{{ panel.type }}</span>
                  </div>
                  <div>
                    <span style="font-weight: 600">Added: </span>
                    <span>{{ panel.createdAt }}</span>
                  </div>
                </div>
                <!-- <div class="q-mt-xs row">
                  <div class="q-mr-lg">
                    <span style="font-weight: 600">Size: </span>
                    <span>{{ panel.size }}</span>
                  </div>
                  <div>
                    <span style="font-weight: 600">Status: </span>
                    <span>{{ panel.status }}</span>
                  </div>
                </div> -->
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
                  <QBtn
                    size="sm"
                    icon="info_outline"
                    no-caps
                    ripple
                    dense
                    flat
                    text-color="dark"
                    @click="$router.push(`/panels/${panel.id}/edit`)"
                  >
                    <QTooltip>More info</QTooltip>
                  </QBtn>
                </div>
              </div>
              <a
                :href='`https://www.google.com/maps/search/?api=1&query=${panel.address}`'
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
export default {
  name: 'PanelsPage',
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
