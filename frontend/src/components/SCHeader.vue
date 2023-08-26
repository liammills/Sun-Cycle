<template>
    <QHeader class="bg-transparent text-black q-px-md q-py-sm" >
      <QToolbar>
        <img
          class="q-mr-sm cursor-pointer"
          style="width: 220px"
          src="src/assets/suncycle-logo.svg"
          @click="$router.push('/')"
        >
        <QBtn
          v-if="!loggedin"
          stretch
          flat
          no-caps
          label="About Us"
          class="header-text"
        />
        <QBtn
          stretch
          flat
          no-caps
          label="Marketplace"
          class="header-text"
          @click="$router.push('/marketplace')"
        />
        <QBtn
          v-if="loggedin"
          stretch
          flat
          no-caps
          label="Your Panels"
          class="header-text"
        />
        <q-space />
        <q-space />
        <q-space />
        <q-btn-dropdown
          v-if="loggedin"
          stretch
          flat
          icon="person"
          class="header-text"
        >
          <q-list>
            <q-item clickable v-close-popup> 
              <q-item-section>
                <q-item-label
                  class="header-text"
                  @click="$router.push('/profile')"
                >
                  Edit profile
                </q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-close-popup>
              <q-item-section>
                <q-item-label class="header-text">Log out</q-item-label>
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
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'SCHeader',
    props: {
      loggedin: {
        type: Boolean,
        default: false,
      },
    },
})
</script>

<style scoped>
  .header-text {
    font-size: 18px;
  }
</style>