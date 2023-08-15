<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Test Data</q-toolbar-title>
      <q-btn flat round dense icon="add" @click="addNewTestData" />
    </q-toolbar>
    <div class="q-pa-md">
      <q-table :columns="columns" :rows="rows" :loading="loading">
        <template v-slot:body-cell-actions="props">
          <q-td key="action" :props="props">
            <q-btn
              flat
              color="primary"
              size="sm"
              icon="edit"
              @click="editTestData(props.row.name)"
            ></q-btn>
            <q-btn
              flat
              color="primary"
              size="sm"
              icon="delete"
              @click="de_testdata(props.row.name)"
            ></q-btn> </q-td></template
      ></q-table>
    </div>
  </q-page>
</template>
<script>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  get_testdata,
  delete_testdata,
  update_testdata,
} from "../../api/test_api";
const columns = [
  {
    name: "name",
    required: true,
    label: "Name",
    align: "left",
    field: (row) => row.name,
    format: (val) => `${val}`,
    sortable: true,
  },
  { name: "actions", align: "left", label: "Actions" },
];
export default {
  setup() {
    const rows = ref([]);
    const route = useRoute();
    const router = useRouter();

    const loading = ref(false);
    function addNewTestData() {
      var name = prompt("Enter test data set name", "");
      if (name) {
        update_testdata(
          { name: name, data: "{}" },
          (d) => {
            load_datasets();
          },
          (e) => {}
        );
      }
    }

    function load_datasets() {
      loading.value = true;
      get_testdata(
        (data) => {
          rows.value.splice(0, rows.value.length, ...data);
          console.log(rows.value);
          loading.value = false;
        },
        (e) => {
          loading.value = false;
        }
      );
    }
    function de_testdata(name) {
      delete_testdata(
        name,
        () => {
          load_datasets();
        },
        () => {}
      );
    }
    function editTestData(name) {
      router.push(`testData/${name}`);
    }
    onMounted(() => {
      load_datasets();
    });
    return {
      loading,
      columns,
      rows,
      addNewTestData,
      de_testdata,
      editTestData,
    };
  },
};
</script>
../api/test_api
