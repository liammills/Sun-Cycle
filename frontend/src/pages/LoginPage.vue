<template>
  <q-page class="flex flex-center">
    <ResetPasswordForm
      v-if="resetForm"
      v-model:email="email"
      v-model:error="error"
      @toggle-reset="toggleReset()"
      @reset="resetPassword()"
    />
    <UserForm
      v-else
      title="Login"
      submit-text="Login"
      v-model:email="email"
      v-model:password="password"
      v-model:error="error"
      @toggle-reset="toggleReset()"
      @submit="submit()"
    />
  </q-page>
</template>

<script>
import UserForm from '../components/UserForm.vue';
import ResetPasswordForm from '../components/ResetPasswordForm.vue';
import { useAuthStore } from '@/stores';

export default {
  name: 'LoginPage',
  components: {
    UserForm,
    ResetPasswordForm,
  },
  data() {
    return {
      resetForm: false,
      email: null,
      password: null,
      error: null,
    };
  },
  computed: {
    // ...mapState('global', ['user']),
    // ...mapState('login', ['isLoggedIn']),
    areFieldsValid() {
      return /^(\S+@\S+\.\S+|\d{10}|\d{11})$/.test(this.email);
    },
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
      try {
        const result = await this.$store.dispatch('login/login', {
          email: this.email,
          password: this.password,
        });

        if (!result) {
          this.password = '';
          this.error = 'Invalid login. Please try again';
        }
        this.$router.push(this.redirect || '/panels');
      } catch (error) {
        this.password = '';
        if (error.response?.data?.message) {
          this.error = error.response.data.message;
        }
      }
    },
    async resetPassword() {
      // TODO

      // try {
      //   this.error = null;
      //   await this.$api.post('users/reset_password',
      //     {
      //       email: this.email,
      //     });
      //   this.toggleReset();
      //   this.error = 'Password reset email sent, please check your email';
      // } catch (error) {
      //   this.error = 'Error resetting password';
      //   if (error.response?.data?.message) {
      //     this.error = error.response.data.message;
      //   }
      // }
    },
  },
}
</script>
