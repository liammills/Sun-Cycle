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
import UserForm from '../components/UserForm.vue';
import { useAuthStore } from 'stores/auth';

export default {
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
  computed: {
    areFieldsValid() {
      return /^(\S+@\S+\.\S+|\d{10}|\d{11})$/.test(this.email);
    },
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
        this.error = 'Please enter a valid email';
        return;
      }
      this.error = null;
      try {
        const result = await this.authStore.register({
          email: this.email,
          password: this.password,
        });

        if (!result) {
          this.password = '';
          this.error = 'Invalid login. Please try again';
        }
        this.$router.push('/panels');
      } catch (error) {
        this.password = '';
        if (error.response?.data?.message) {
          this.error = error.response.data.message;
        }
      }
    },
  },
}
</script>
