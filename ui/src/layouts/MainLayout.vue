<template>
  <q-layout view="lHh LpR lFf">
    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered>
      <q-scroll-area class="fit">
        <q-list padding class="menu-list">
          <q-item
            clickable
            v-ripple
            :active="selectedItem == 'connection'"
            @click="showConnectionPage"
          >
            <q-item-section> Connections </q-item-section>
          </q-item>

          <q-item
            clickable
            v-ripple
            :active="selectedItem == 'instance'"
            @click="showInstancePage"
          >
            <q-item-section> Instance Details </q-item-section>
          </q-item>

          <q-item clickable v-ripple :active="selectedItem == 'test'">
            <q-item-section> Test </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>
<script>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    const leftDrawerOpen = ref(false);
    const selectedItem = ref("connection");
    function showInstancePage() {
      selectedItem.value = "instance";
      router.push("/instance");
    }
    function showConnectionPage() {
      selectedItem.value = "connection";
      router.push("/");
    }
    return {
      selectedItem,
      leftDrawerOpen,
      showInstancePage,
      showConnectionPage,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
};
</script>
