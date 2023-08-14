<template>
  <q-layout view="hHh lpR fFf">
    <q-header bordered class="bg-dark text-white" height-hint="98">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="mini = !mini" />
        <q-toolbar-title> BAW Developer Tool </q-toolbar-title>
      </q-toolbar>
    </q-header>
    <q-drawer
      show-if-above
      v-model="leftDrawerOpen"
      side="left"
      bordered
      :mini="mini"
    >
      <q-scroll-area class="fit">
        <q-list padding class="menu-list">
          <q-item
            clickable
            v-ripple
            :active="selectedItem == 'connection'"
            @click="showConnectionPage"
            ><q-item-section avatar>
              <q-icon color="primary" name="layers" />
            </q-item-section>
            <q-item-section> Connections </q-item-section>
          </q-item>

          <q-item
            clickable
            v-ripple
            :active="selectedItem == 'instance'"
            @click="showInstancePage"
          >
            <q-item-section avatar>
              <q-icon color="primary" name="account_tree" />
            </q-item-section>
            <q-item-section> Instance Details </q-item-section>
          </q-item>

          <q-item
            clickable
            v-ripple
            :active="selectedItem == 'test'"
            @click="showTestSummary"
          >
            <q-item-section avatar>
              <q-icon color="primary" name="checklist" />
            </q-item-section>
            <q-item-section> Test </q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="selectedItem == 'test'"
            to="/testData"
          >
            <q-item-section avatar>
              <q-icon color="primary" name="folder" />
            </q-item-section>
            <q-item-section> Test Data </q-item-section>
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
    const leftDrawerOpen = ref(true);
    const selectedItem = ref("connection");
    const mini = ref(false);
    function showInstancePage() {
      selectedItem.value = "instance";
      router.push("/instance");
    }
    function showConnectionPage() {
      selectedItem.value = "connection";
      router.push("/");
    }
    function showTestSummary() {
      selectedItem.value = "test";
      router.push("/serviceCall");
    }
    return {
      mini,
      selectedItem,
      leftDrawerOpen,
      showInstancePage,
      showConnectionPage,
      showTestSummary,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
};
</script>
