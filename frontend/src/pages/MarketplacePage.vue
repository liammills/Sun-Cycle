<template>
    <q-page class="row justify-around">
        <div class="column">
            <h1>Discover installations</h1>
            <div class="row">
                <div class="column">
                    <QSelect outlined v-model="model" :options="options" label="Method of recycling" />
                    <QInput outlined v-model="text" label="City/town" />
                </div>
                <div class="column">
                    <QInput outlined v-model="text" type="date" label="Retired by" />
                    <QSelect outlined v-model="model" :options="states" label="State" />
                </div>
            </div>
            <h3>Material breakdown</h3>
            <div class="row">
                <div class="column">
                    <QInput outlined v-model.number="text" type="text">
                        <template v-slot:append>
                            <span style="font-size: 14px;">g</span>
                        </template>
                    </QInput>
                </div>
                <div class="column">
                    <QInput outlined v-model.number="text" type="text" label="Retired by" />
                </div>
            </div>
        </div>
        <QSelect outlined v-model="model" :options="options" label="Outlined" />
    </q-page>
  </template>
  
  <script>
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    name: 'MarketplacePage',
    data() {
      return {
        states: ["NSW", "QLD", "SA", "TAS", "VIC", "WA" ],
      };
    },
    methods: {
      toggleReset() {
        this.error = null;
        this.resetForm = !this.resetForm;
      },
      async submit() {
        if (!this.areFieldsValid) {
          return;
        }
        this.error = null;
        // try {
        //   const result = await this.$store.dispatch('login/login', {
        //     email: this.email,
        //     password: this.password,
        //   });
  
        //   if (!result) {
        //     this.password = '';
        //     this.error = 'Invalid login. Please try again';
        //   }
        //   this.$router.push(this.redirect || '/panels');
        // } catch (error) {
        //   this.password = '';
        //   if (error.response?.data?.message) {
        //     this.error = error.response.data.message;
        //   }
        // }
      },
    },
  })
  </script>
  