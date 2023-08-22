<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-btn flat icon="arrow_back" @click="goBack"></q-btn>
      <q-toolbar-title>{{ name }}</q-toolbar-title>

      <q-btn flat icon="save" @click="saveData"></q-btn>
    </q-toolbar>
    <codemirror
      v-model="code"
      :style="{ height: '600px' }"
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
import { graphql } from "../../api/graphql";
export default {
  components: { Codemirror },
  setup() {
    const $q = useQuasar();
    const extensions = [json(), oneDark];
    const route = useRoute();
    const router = useRouter();
    const name = ref(route.params.name);
    const code = ref();
    const id = ref(route.params.id);
    function goBack() {
      router.back();
    }
    function handleReady(payload) {
      code.value = payload.view;
    }
    function loadData() {
      graphql(
        `
          {
            getTestData(id: ${id.value}) {
              id
              lastModified
              data
              name
            }
          }
        `,
        (d) => {
          var data = d.data.getTestData;
          name.value = data.name;
          code.value = data.data;
        }
      );
    }
    function saveData() {
      var data = JSON.stringify(code.value);
      graphql(
        `
         mutation{
  updateTestData(id:${id.value},name:"${name.value}",data:${data}){
    id
  }
}
        `,
        (d) => {
          $q.notify("Saved");
        },
        (e) => {
          $q.notify("Error while saving");
        }
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
<style>
.cm-editor {
  height: 100%;
}
.cm-scroller {
  overflow: auto;
}
</style>
