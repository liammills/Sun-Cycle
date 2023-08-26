<template>
  <div class="form-container column items-center">
    <div class="column q-py-lg full-width items-center">
      <h1 class="q-pb-lg">
        Reset Password
      </h1>
      <div class="column full-width text-center">
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
        <div v-if="error">
          <span class="text-red">{{ error }}</span>
        </div>
        <span
          class="q-mt-sm text-small cursor-pointer q-mt-md"
          @click="$emit('toggle-reset')"
        >
          Go back to login.
        </span>
      </div>
    </div>
    <QBtn
      flat
      no-caps
      class="bg-primary text-white submit-button"
      @click="$emit('reset')"
    >
      Reset
    </QBtn>
  </div>
</template>

<script>
export default {
  name: 'ResetPasswordForm',
  props: {
    email: {
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
  },
};
</script>
