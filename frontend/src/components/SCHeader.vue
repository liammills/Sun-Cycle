<template>
    <QHeader v-if="loggedin" class="bg-transparent text-black q-px-md q-py-sm">
      <QToolbar>
        <QToolbarTitle>
          <img
            class="q-mr-sm"
            style="width: 220px"
            src="src/assets/suncycle-logo.svg"
          >
        </QToolbarTitle>
        <QBtn stretch flat no-caps label="Marketplace" style="font-size: 20px;" />
        <QBtn stretch flat no-caps label="Your Panels" style="font-size: 20px;" />
        <q-space />
        <q-space />
        <q-space />
        <q-btn-dropdown style="font-size: 20px;" stretch flat icon="person">
          <q-list>
            <!-- @click here -->
            <q-item clickable v-close-popup> 
              <q-item-section>
                <q-item-label style="font-size: 20px;">Edit profile</q-item-label>
              </q-item-section>
            </q-item>
            <!-- @click here -->
            <q-item clickable v-close-popup>
              <q-item-section>
                <q-item-label style="font-size: 20px;">Log out</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
      </QToolbar>
    </QHeader>
    <QHeader v-else class="bg-transparent text-black q-px-md q-py-sm">
      <QToolbar>
        <QToolbarTitle>
          <img
            class="q-mr-sm"
            style="width: 220px"
            src="src/assets/suncycle-logo.svg"
          >
        </QToolbarTitle>
        <QBtn stretch flat no-caps label="About Us" style="font-size: 20px;" />
        <QBtn stretch flat no-caps label="Marketplace" style="font-size: 20px;" />
        <q-space />
        <q-space />
        <q-space />
        <QBtn
          flat
          no-caps
          class="bg-primary text-white submit-button"
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