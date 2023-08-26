<template>
    <QHeader class="bg-transparent text-black q-px-md q-py-sm">
      <QToolbar>
        <QToolbarTitle>
          <img
            class="q-mr-sm"
            style="width: 220px"
            src="src/assets/suncycle-logo.svg"
          >
        </QToolbarTitle>
        <QBtn v-if="!loggedin" stretch flat no-caps label="About Us" style="font-size: 18px;" />
        <QBtn stretch flat no-caps label="Marketplace" style="font-size: 18px;" />
        <QBtn v-if="loggedin" stretch flat no-caps label="Your Panels" style="font-size: 18px;" />
        <q-space />
        <q-space />
        <q-space />
        <q-btn-dropdown
          v-if="loggedin"
          style="font-size: 18px;"
          stretch
          flat
          icon="person"
        >
          <q-list>
            <q-item clickable v-close-popup> 
              <q-item-section>
                <q-item-label style="font-size: 18px;">Edit profile</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-close-popup>
              <q-item-section>
                <q-item-label style="font-size: 18px;">Log out</q-item-label>
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