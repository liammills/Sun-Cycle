<template>
    <QHeader class="bg-transparent text-black q-px-md q-py-sm" >
      <QToolbar class="row justify-between">
        <div class="row items-center">
          <img
            class="q-mr-lg cursor-pointer"
            style="width: 220px"
            src="~/assets/suncycle-logo.svg"
            @click="$router.push('/')"
          >
          <div
            v-if="!loggedIn"
            class="header-text cursor-pointer orange-hover q-mr-lg q-mt-xs"
          >
            About Us
          </div>
          <div
            class="header-text cursor-pointer orange-hover q-mr-lg q-mt-xs"
            @click="$router.push('/marketplace')"
          >
            Marketplace
          </div>
          <div
            v-if="loggedIn"
            class="header-text cursor-pointer orange-hover q-mr-lg q-mt-xs"
            @click="$router.push('/panels')"
          >
            Your Panels
          </div>
        </div>
        <q-btn-dropdown
          v-if="loggedIn"
          flat
          icon="account_circle"
          class="header-text cursor-pointer orange-hover"
        >
          <q-list>
            <q-item clickable class="header-text cursor-pointer orange-hover"> 
              <q-item-section>
                <q-item-label @click="$router.push('/profile')">
                  Edit profile
                </q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable class="header-text cursor-pointer orange-hover">
              <q-item-section>
                <q-item-label>
                  Log out
                </q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
        <QBtn
          v-else
          flat
          no-caps
          style="font-size: 18px;"
          class="bg-primary text-white submit-button"
          @click="$router.push('/login')"
        >
          Login
        </QBtn>
      </QToolbar>
    </QHeader>
</template>

<script>
export default {
    name: 'SCHeader',
    data() {
      return {
        loggedIn: false,
      }
    },
}
</script>

<style scoped>
  .header-text {
    font-size: 18px;
    font-weight: 500;
  }
  :deep(.q-btn-dropdown__arrow-container) {
    display: none;
  }
</style>