<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Test Case</q-toolbar-title>
      <q-btn flat icon="delete"></q-btn>
      <q-btn flat icon="add" @click="new_test_case"></q-btn>
      <q-btn flat icon="play_arrow"></q-btn>
    </q-toolbar>

    <div class="q-pa-md q-gutter-sm"></div>
    <q-table
      :rows="rows"
      :columns="columns"
      row-key="name"
      selection="multiple"
    >
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn square flat color="primary" icon="edit"></q-btn>
          <q-btn
            square
            flat
            icon="delete"
            color="red"
            @click="delete_tc(props.row.id)"
          ></q-btn>
        </q-td> </template
    ></q-table>
  </q-page>
</template>
<script>
import { onMounted, ref } from "vue";
import { date } from "quasar";
import {
  get_all_test_cases,
  delete_testcase,
  update_testcase,
} from "../../api/test_api";
const columns = [
  {
    name: "testcase",
    label: "Name",
    align: "left",
    field: (row) => row.name,
    sortable: true,
  },
  {
    name: "lastModified",
    label: "Last modified",
    align: "left",
    field: (row) =>
      date.formatDate(new Date(row.lastModified), "DD-MM-YYYY HH:mm"),
    sortable: true,
  },

  { name: "actions", align: "left", label: "Actions" },
];
export default {
  setup() {
    const rows = ref([]);
    function update_tcs() {
      get_all_test_cases((d) => {
        rows.value.splice(0, rows.value.length, ...d);
      });
    }
    function new_test_case() {
      var name = window.prompt("Test case name");
      if (name) {
        update_testcase(
          { name },
          () => {
            update_tcs();
          },
          () => {}
        );
      }
    }
    function delete_tc(id) {
      delete_testcase(
        id,
        (d) => {
          update_tcs();
        },
        (d) => {}
      );
    }
    onMounted(() => {
      update_tcs();
    });
    return { rows, columns, delete_tc, get_all_test_cases, new_test_case };
  },
};
</script>
