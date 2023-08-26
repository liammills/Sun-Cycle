<template>
    <q-page class="row justify-around">
        <div class="column">
            <h1>Discover installations</h1>
            <div class="row q-my-xl">
                <div class="column q-mr-md">
                    <QSelect class="q-mb-md" outlined v-model="model" :options="options" label="Method of recycling" style="width: 300px;" />
                    <QInput outlined v-model="text" label="City/town" />
                </div>
                <div class="column">
                    <QInput class="q-mb-md" outlined v-model="text" type="date" label="Retired by" />
                    <QSelect outlined v-model="stateselect" :options="states" label="State" />
                </div>
            </div>
            <h3 class="q-mb-md">Material breakdown</h3>
            <div class="row q-mb-md">
                <div class="column q-mr-xl">
                    <div class="row justify-between items-center matbreakpair">
                        <span>Silicone</span>
                        <QInput outlined v-model.number="siliconeinput" type="text" class="matbreakinput">
                            <template v-slot:append>
                                <span style="font-size: 14px;">g</span>
                            </template>
                        </QInput>
                    </div>
                </div>
                <div class="column">
                    <div class="row justify-between items-center matbreakpair">
                        <span>Silver</span>
                        <QInput outlined v-model.number="siliconeinput" type="text" class="matbreakinput">
                            <template v-slot:append>
                                <span style="font-size: 14px;">g</span>
                            </template>
                        </QInput>
                    </div>
                </div>
            </div>
            <div class="row q-mb-md">
                <div class="column q-mr-xl">
                    <div class="row justify-between items-center matbreakpair">
                        <span>Polymers</span>
                        <QInput outlined v-model.number="siliconeinput" type="text" class="matbreakinput">
                            <template v-slot:append>
                                <span style="font-size: 14px;">g</span>
                            </template>
                        </QInput>
                    </div>
                </div>
                <div class="column">
                    <div class="row justify-between items-center matbreakpair">
                        <span>Aluminium</span>
                        <QInput outlined v-model.number="siliconeinput" type="text" class="matbreakinput">
                            <template v-slot:append>
                                <span style="font-size: 14px;">g</span>
                            </template>
                        </QInput>
                    </div>
                </div>
            </div>
            <div class="row q-mb-xl">
                <div class="column q-mr-xl">
                    <div class="row justify-between items-center matbreakpair">
                        <span>Copper</span>
                        <QInput outlined v-model.number="siliconeinput" type="text" class="matbreakinput">
                            <template v-slot:append>
                                <span style="font-size: 14px;">g</span>
                            </template>
                        </QInput>
                    </div>
                </div>
                <div class="column">
                    <div class="row justify-between items-center matbreakpair">
                        <span>Glass</span>
                        <QInput outlined v-model.number="siliconeinput" type="text" class="matbreakinput">
                            <template v-slot:append>
                                <span style="font-size: 14px;">g</span>
                            </template>
                        </QInput>
                    </div>
                </div>
            </div>
        </div>
        <QSelect outlined v-model="model" :options="options" label="Outlined" />
    </q-page>
</template>
  
<script>
  import { defineComponent } from 'vue';
  import { ref } from 'vue'
  
  export default defineComponent({
    name: 'MarketplacePage',
    data() {
      return {
        states: ["NSW", "QLD", "SA", "TAS", "VIC", "WA" ],
        stateselect: ref(null),

        siliconeinput: ref(null),
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
  
<style>
.matbreakpair {
    width: 200px;
}

.matbreakinput {
    width: 100px;
}
</style>