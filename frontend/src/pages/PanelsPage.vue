<template>
  <q-page class="flex justify-center">
    <div class="main-container">
        <div class="row justify-between items-center q-my-md">
        <h1>Your Panels</h1>
        <QBtn
          flat
          no-caps
          class="bg-primary text-white submit-button q-mt-sm"
        >
          Add an installation
        </QBtn>
      </div>
      <div>
        <p
          v-if="panels.length === 1"
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
      panels: [],
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
