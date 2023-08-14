<template>
  <q-page padding>
    <q-toolbar class="bg-primary text-white shadow-2">
      <q-toolbar-title
        ><div style="font-weight: bold" class="cursor-pointer">
          {{ name }} <q-icon size="xs" name="edit" class="label" />
          <q-popup-edit v-model="name" auto-save v-slot="scope">
            <q-input
              v-model="scope.value"
              autofocus
              filled
              @keyup.enter="scope.set"
            />
          </q-popup-edit></div
      ></q-toolbar-title>
      <q-btn flat>Save</q-btn>
      <q-btn flat class="q-ml-sm">Run All</q-btn>
    </q-toolbar>

    <div class="q-pa-md">
      <div class="column">
        <div class="col q-mt-sm" v-for="c in cells" v-bind:key="c.id">
          <TestScriptCell
            :cell="c"
            @add-cell="appendNewCell"
            @delete-cell="removeCell"
            @edit-code="editCode"
          />
        </div>
      </div>
    </div>
    <GroovyEditorDialog
      v-if="showCodeEditor"
      :code="code"
      :title="title"
      @finish-edit="finishEdit"
      @cancel-edit="cancelEdit"
    ></GroovyEditorDialog>
  </q-page>
</template>
<script>
import { ref, shallowRef } from "vue";

import TestScriptCell from "components/TestScriptCell.vue";
import GroovyEditorDialog from "components/GroovyEditorDialog.vue";
export default {
  components: { TestScriptCell, GroovyEditorDialog },
  setup() {
    const name = ref("Hello");
    const title = ref("roovy");
    const showCodeEditor = ref(true);
    const code = ref("");

    const cells = ref([
      {
        id: 0,
        title: "Hello",
        code: "def callWebService() { \nprint('call webService;')\n}",
      },
      {
        id: 1,
        title: "Hello2",
        code: "def sent SMSDATA() { \nprint('call webService;')\n}",
      },
    ]);

    function findCellById(id) {
      for (var i = 0; i < cells.value.length; i++) {
        if (cells.value[i].id == id) return cells.value[i];
      }
      return null;
    }
    function findCellIndex(id) {
      for (var i = 0; i < cells.value.length; i++) {
        if (cells.value[i].id == id) return i;
      }
      return null;
    }
    function appendNewCell() {
      console.log("new cell added");
      cells.value.push({
        id: cells.value.length - 1,
        code: "def callWebService() { \nprint('call webService;')\n}",
      });
    }
    function removeCell(data) {
      var index = findCellIndex(data.id);
      cells.value.splice(index, 1);
    }
    function editCode(data) {
      var cell = findCellById(data.id);
      code.value = cell.code;
      console.log("Edit Code1", code.value);
      showCodeEditor.value = true;
    }
    function finishEdit(data) {
      var cell = findCellById(data.id);
      cell.value.code = data.code;
      showCodeEditor.value = false;
    }

    function cancelEdit() {
      showCodeEditor.value = false;
    }
    return {
      name,
      appendNewCell,
      removeCell,
      cells,
      title,
      code,
      showCodeEditor,
      editCode,
      finishEdit,
      cancelEdit,
    };
  },
};
</script>
