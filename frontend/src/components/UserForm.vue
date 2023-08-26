<template>
  <div class="form-container column items-center">
    <div class="column full-width items-center">
      <h1 class="q-pb-lg">
        {{ title || '...' }}
      </h1>
      <div class="column full-width">
        <QInput
          v-model="handleEmail"
          outlined
          label="Email"
          type="email"
          name="email"
          autocomplete="email"
          autofocus
          class="full-width no-outline"
          maxlength="255"
          aria-label="Email"
          :class="{ 'field-error': !!error }"
        />
        <QInput
          v-model="handlePassword"
          outlined
          label="Password"
          type="password"
          autofocus
          aria-label="Password"
          maxlength="255"
          class="full-width no-outline q-mt-md"
          :class="{ 'field-error': !!error }"
          @keyup.enter="$emit('submit')"
        />
        <div v-if="error">
          <span class="text-red">{{ error }}</span>
        </div>
      </div>
      <a
        v-if="submitText === 'Login'"
        class="q-mt-md cursor-pointer text-uderline"
        @click="$emit('toggle-reset')"
      >
        Forgot your password?
      </a>
    </div>
    <QBtn
      flat
      no-caps
      class="full-width bg-primary text-white submit-button q-mt-lg"
      @click="$emit('submit')"
    >
      {{ submitText || 'Loading...' }}
    </QBtn>
    <p
      v-if="submitText === 'Login'"
      class="q-mt-xl text-small"
    >
      Don’t have an account? 
      <a
        href="/register"
        class="cursor-pointer text-underline"
      >
        Sign up.
      </a>
    </p>
  </div>
</template>

<script>
export default {
  name: 'UserForm',
  props: {
    title: {
      type: String,
      default: null,
    },
    submitText: {
      type: String,
      default: null,
    },
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
