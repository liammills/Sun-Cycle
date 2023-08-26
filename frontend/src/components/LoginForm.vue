<template>
  <div class="form-container column items-center">
    <div class="column q-py-lg full-width items-center">
      <h3 class="login-text q-pb-md">
        Log in
      </h3>
      <div class="column full-width">
        <h3 class="q-py-xs">Email</h3>
        <input
          v-model="handleEmail"
          type="email"
          name="email"
          autocomplete="email"
          autofocus
          class="full-width no-outline"
          maxlength="255"
          aria-label="Email"
          :class="{ 'field-error': !!error }"
        >
        <h3 class="q-py-xs">Password</h3>
        <input
          v-model="handlePassword"
          type="password"
          aria-label="Password"
          class="full-width no-outline"
          :class="{ 'field-error': !!error }"
          @keyup.enter="$emit('submit')"
        >
        <div v-if="error">
          <span class="text-red">{{ error }}</span>
        </div>
      </div>
      <span
        class="q-mt-sm cursor-pointer text-underline"
        @click="$emit('toggle-reset')"
      >
        Forgot your password?
      </span>
    </div>
    <QBtn
      flat
      no-caps
      class="full-width bg-primary text-white submit-button"
      @click="$emit('submit')"
    >
      Login
    </QBtn>
    <p class="q-mt-sm text-smaller">
      Don’t have an account? 
      <a
        href="https://www.emmi.io/privacy"
        class="cursor-pointer text-underline"
      >
        Sign up.
      </a>
    </p>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  props: {
    email: {
      type: String,
      default: null,
    },
    password: {
      type: String,
      default: null,
    },
    error: {
      type: String,
      default: null,
    },
  },
  computed: {
    handleEmail: {
      get() {
        return this.email;
      },
      set(val) {
        this.$emit('update:email', val);
      },
    },
    handlePassword: {
      get() {
        return this.password;
      },
      set(val) {
        this.$emit('update:password', val);
      },
    },
  },
};
</script>

<!-- <style lang="scss" scoped>
  .form-container {
    min-width: 300px;
  }
  .login-text {
    font-size: 1.6em;
  }
</style> -->
