<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-btn flat icon="arrow_back" @click="goBack"></q-btn>
      <q-toolbar-title>{{ name }}</q-toolbar-title>
      <q-btn flat icon="save" @click="saveData"></q-btn>
    </q-toolbar>
    <codemirror
      v-model="code"
      :style="{ height: '500px' }"
      :autofocus="true"
      :indent-with-tab="true"
      :tab-size="2"
      :extensions="extensions"
    />
  </q-page>
</template>
<script>
import { useQuasar } from "quasar";
import { Codemirror } from "vue-codemirror";
import { json } from "@codemirror/lang-json";
import { oneDark } from "@codemirror/theme-one-dark";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { update_testdata, get_testdata_by_name } from "./test_api";
export default {
  components: { Codemirror },
  setup() {
    const $q = useQuasar();
    const extensions = [json(), oneDark];
    const route = useRoute();
    const router = useRouter();
    const name = ref(route.params.name);
    const code = ref();
    function goBack() {
      router.back();
    }
    function handleReady(payload) {
      code.value = payload.view;
    }
    function loadData() {
      get_testdata_by_name(name.value, (data) => {
        code.value = data.data;
      });
    }
    function saveData() {
      update_testdata(
        { name: name.value, data: code.value },
        (d) => {
          $q.notify("Saved");
        },
        (e) => {}
      );
    }

    onMounted(() => {
      loadData();
    });
    return {
      name,
      code,
      saveData,
      goBack,
      handleReady,
      extensions,
    };
  },
};
</script>
