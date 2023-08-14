<template>
  <q-dialog v-model="show" full-width>
    <q-toolbar class="bg-primary text-white shadow-2">
      <q-toolbar-title>{{ title }}</q-toolbar-title>
    </q-toolbar>

    <q-btn
      flat
      icon="save"
      @click="$emit('finishEdit', { code: data.value, id: id })"
      >Save</q-btn
    >
    <q-btn
      flat
      icon="save"
      @click="$emit('cancelEdit', { code: data.value, id: id })"
      >Cancel</q-btn
    >
    <codemirror
      v-model="data"
      :style="{ height: '500px' }"
      :autofocus="true"
      :indent-with-tab="true"
      :tab-size="2"
      :extensions="extensions"
      @ready="handleReady"
    />
  </q-dialog>
</template>
<script>
import { defineComponent, onMounted, watch, ref } from "vue";
import { Codemirror } from "vue-codemirror";
import { json } from "@codemirror/lang-json";
import { oneDark } from "@codemirror/theme-one-dark";

export default defineComponent({
  components: { Codemirror },
  name: "GroovyEditorDialog",
  props: ["code", "title", "id"],
  setup(props) {
    const extensions = [json(), oneDark];
    const data = ref();
    const show = ref(true);

    onMounted(() => {
      data.value = props.code;
    });
    function handleReady(payload) {
      data.value = payload.view;
    }

    return { show, data, extensions, handleReady };
  },
});
</script>
