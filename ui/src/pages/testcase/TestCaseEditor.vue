<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Test Case </q-toolbar-title>
      <q-btn flat icon="delete"></q-btn>
      <q-btn flat icon="add" @click="new_test_cell"></q-btn>
      <q-btn flat icon="play_arrow"></q-btn>
      <q-btn flat icon="save"></q-btn>
    </q-toolbar>
    <div class="q-pa-md q-gutter-sm">
      <q-input filled v-model="name" label="Name"></q-input>
      <q-table
        :rows="test_cells"
        :columns="columns"
        row-key="name"
        selection="multiple"
      >
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn
              square
              flat
              icon="edit"
              @click="edit_tc(props.row.id)"
            ></q-btn>
            <q-btn
              square
              flat
              icon="play_arrow"
              @click="run_tc(props.row.id)"
            ></q-btn>
            <q-btn
              square
              flat
              icon="delete"
              @click="delete_tc(props.row.id)"
            ></q-btn>
          </q-td> </template
      ></q-table>
    </div>
  </q-page>
</template>
<script>
import { ref } from "vue";
const columns = [
  {
    name: "testcell",
    label: "Step",
    align: "left",
    field: (row) => row.name,
    sortable: true,
  },

  { name: "actions", align: "left", label: "Actions" },
];
export default {
  setup() {
    const test_cells = ref([{ name: "hello" }]);
    const name = ref("");
    function new_test_cell() {}
    function load_test_case() {}
    function load_test_cells() {}
    return { new_test_cell, test_cells, name, columns };
  },
};
</script>
