<template>
  <q-page class="flex flex-center">
    <UserForm
      title="Edit profile details"
      submit-text="Save"
      v-model:email="emailInput"
      v-model:password="passwordInput"
      v-model:error="error"
      @submit="submit()"
    />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';
import UserForm from '../components/UserForm.vue';
import { useAuthStore } from 'src/stores/auth';

export default defineComponent({
  name: 'RegisterPage',
  setup() {
    const authStore = useAuthStore();
    return {
      authStore,
    };
  },
  components: {
    UserForm,
  },
  data() {
    return {
      emailInput: null,
      passwordInput: null,
      error: null,
    };
  },
  mounted() {
    this.emailInput = this.authStore.user?.email?.email;
    this.passwordInput = this.authStore.user?.email?.password;
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
