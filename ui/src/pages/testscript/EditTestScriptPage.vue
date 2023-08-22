<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-btn flat round dense icon="arrow_back" @click="goBack"></q-btn>
      <q-toolbar-title>{{ name }}</q-toolbar-title>

      <q-select
        borderless
        v-model="selectedTestData"
        :options="options"
        option-value="id"
        option-label="name"
      />

      <q-btn flat round dense icon="play_arrow" @click="evalTestScript"></q-btn>
      <q-btn flat round dense icon="save" @click="saveData">
        <q-badge floating color="red" rounded v-if="codeChanged" />
      </q-btn>
    </q-toolbar>

    <q-splitter v-model="splitterModel" style="height: 600px">
      <template v-slot:before>
        <codemirror
          v-model="code"
          :style="{ height: '350px' }"
          :autofocus="true"
          :indent-with-tab="true"
          :tab-size="2"
          :extensions="extensions"
          @change="onCodeChange"
          @ready="handleReady"
      /></template>

      <template v-slot:after>
        <q-tabs v-model="tab" align="left">
          <q-tab name="snippets" label="Snippets" />
          <q-tab name="output" label="Output" />
        </q-tabs>
        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="snippets">
            <CodeSnippetComponent
              @on-link-click="onLinkClick"
            ></CodeSnippetComponent>
          </q-tab-panel>

          <q-tab-panel name="output">
            <div class="q-pa-sm q-gutter-sm">
              <q-btn unelevated color="primary" size="sm">{{
                runStatus
              }}</q-btn>
              <q-btn unelevated color="primary" size="sm" icon="timer"
                >{{ testOutput.elapsedTime }} ms</q-btn
              >
              <q-btn
                outline
                color="green"
                size="sm"
                v-if="testOutput.result == 'passed'"
                >Passed</q-btn
              >
              <q-btn
                outline
                color="red"
                size="sm"
                v-if="testOutput.result == 'failed'"
                >Failed</q-btn
              >
            </div>
            <div class="q-pa-sm q-gutter-sm" v-if="testOutput.error">
              <div>Error Message</div>
              <pre
                style="
                  color: red;
                  font-weight: bold;
                  background-color: #ddd;
                  border-color: 1px solid #ccc;
                  padding: 10px;
                  border-radius: 5px;
                  font-family: 'Courier New', Courier, monospace;
                "
                >{{ testOutput.error }}</pre
              >
            </div>
            <div class="q-pa-sm q-gutter-sm" v-if="testOutput.output">
              <div>Output Message</div>
              <pre
                style="
                  font-weight: bold;
                  background-color: #ddd;
                  border-color: 1px solid #ccc;
                  padding: 10px;
                  border-radius: 5px;
                  font-family: 'Courier New', Courier, monospace;
                "
                >{{ testOutput.output }}</pre
              >
            </div>
          </q-tab-panel>
        </q-tab-panels>
      </template>
    </q-splitter>
  </q-page>
</template>
<script>
import { useQuasar } from "quasar";
import { Codemirror } from "vue-codemirror";
import { StreamLanguage } from "@codemirror/language";
import { groovy } from "@codemirror/legacy-modes/mode/groovy";
import { oneDark } from "@codemirror/theme-one-dark";
import { onMounted, ref, shallowRef } from "vue";
import { useRoute, useRouter } from "vue-router";
import { graphql } from "../../api/graphql";
import CodeSnippetComponent from "../../components/CodeSnippetComponent.vue";

export default {
  components: { Codemirror, CodeSnippetComponent },
  setup() {
    const $q = useQuasar();
    const extensions = [StreamLanguage.define(groovy), oneDark];
    const route = useRoute();
    const router = useRouter();
    const name = ref(route.params.name);
    const code = ref();
    const editorView = shallowRef();
    const id = ref(route.params.id);
    const tab = ref("snippets");
    const testOutput = ref({});
    const runStatus = ref("no status");
    const codeChanged = ref(false);
    const options = ref([{ name: "None", id: "-1" }]);
    const selectedTestData = ref(options.value[0]);
    function goBack() {
      router.back();
    }
    function onCodeChange() {
      codeChanged.value = true;
    }
    function handleReady(payload) {
      editorView.value = payload.view;
    }
    function onLinkClick(text) {
      const state = editorView.value.state;
      const cursor = state.selection.main.head;
      editorView.value.dispatch({
        changes: {
          from: cursor,
          insert: text,
        },
      });
    }
    function evalTestScript() {
      tab.value = "output";
      runStatus.value = "running";
      graphql(
        `{
              evalTestScript(id:"${id.value}",tid:${selectedTestData.value.id}){
                output
                status
                result
                error
                elapsedTime

              }
      }`,
        (d) => {
          var e = d.data.evalTestScript;
          testOutput.value = e;
          runStatus.value = "completed";
        },
        (e) => {}
      );
    }
    function loadData() {
      graphql(
        `
          {
            getTestScript(id: "${id.value}") {
              id
              lastModified
              code
              name
            }
            allTestData{
              name
              id
            }
          }
        `,
        (d) => {
          var data = d.data.getTestScript;
          name.value = data.name;
          code.value = data.code;
          options.value.splice(1, options.value.length, ...d.data.allTestData);
        }
      );
    }
    function saveData() {
      var data = JSON.stringify(code.value);
      graphql(
        `
         mutation{
  updateTestScript(id:"${id.value}",name:"${name.value}",code:${data}){
    id
  }
}
        `,
        (d) => {
          codeChanged.value = false;
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
      tab,
      evalTestScript,
      testOutput,
      runStatus,
      onCodeChange,
      codeChanged,
      onLinkClick,
      splitterModel: ref(65),
      options,
      selectedTestData,
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
pre {
  white-space: pre-wrap; /* css-3 */
  white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
  white-space: -pre-wrap; /* Opera 4-6 */
  white-space: -o-pre-wrap; /* Opera 7 */
  word-wrap: break-word; /* Internet Explorer 5.5+ */
}
</style>
