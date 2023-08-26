<template>
  <q-page class="flex flex-center">
    <UserForm
      title="Sign up"
      submit-text="Sign up"
      v-model:email="email"
      v-model:password="password"
      v-model:error="error"
      @submit="submit()"
    />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';
import UserForm from '../components/UserForm.vue';

export default defineComponent({
  name: 'RegisterPage',
  components: {
    UserForm,
  },
  data() {
    return {
      email: null,
      password: null,
      error: null,
    };
  },
  methods: {
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
