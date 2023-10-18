<template>
  <q-page class="flex flex-center">
    <UserForm
      title="Edit profile details"
      submit-text="Save"
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
      email: null,
      password: null,
      error: null,
    };
  },
  mounted() {
    this.email = this.authStore.user?.email?.email;
    this.password = this.authStore.user?.email?.password;
  },
  computed: {
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
        this.error = 'Please enter a valid email address';
        return;
      }
      this.error = null;
      try {
        const response = await this.authStore.updateUser({
          email: this.email,
          password: this.password,
        });
        if (response.data?.message) {
          this.error = response.data.message;
          return;
        }
        this.$router.push('/panels');
      }
      catch (error) {
        if (error.response?.data?.message) {
          this.error = error.response.data.message;
        }
      }
    },
  },
})
</script>
