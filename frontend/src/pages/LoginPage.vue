<template>
  <q-page class="flex flex-center">
    <PasswordResetForm
      v-if="resetForm"
      :email="email"
      :error="error"
      @toggle-reset="toggleReset()"
      @reset="resetPassword()"
    />
    <LoginForm
      v-else
      :email="email"
      :password="password"
      :error="error"
      @toggle-reset="toggleReset()"
      @submit="submit()"
    />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';
import LoginForm from '../components/LoginForm.vue';

export default defineComponent({
  name: 'LoginPage',
  components: {
    LoginForm,
  },
  data() {
    return {
      resetForm: false,
      email: null,
      password: null,
      error: null,
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
    // async resetPassword() {
    //   try {
    //     this.error = null;
    //     await this.$api.post('users/reset_password',
    //       {
    //         email: this.email,
    //       });
    //     this.toggleReset();
    //     this.error = 'Password reset email sent, please check your email';
    //   } catch (error) {
    //     this.error = 'Error resetting password';
    //     if (error.response?.data?.message) {
    //       this.error = error.response.data.message;
    //     }
    //   }
    // },
  },
})
</script>
