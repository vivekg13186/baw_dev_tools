<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Test Data</q-toolbar-title>
      <q-btn flat icon="delete" @click="deleteSelectedTestData"></q-btn>
      <q-btn flat icon="add" @click="createTestData"></q-btn>
    </q-toolbar>
    <div class="q-pa-md q-gutter-sm">
      <q-table
        :columns="columns"
        :rows="rows"
        :loading="loading"
        row-key="id"
        selection="multiple"
        v-model:selected="selected"
      >
        <template v-slot:body-cell-actions="props">
          <q-td key="action" :props="props">
            <q-btn flat icon="edit" @click="editTestData(props.row.id)"></q-btn>
            <q-btn
              flat
              icon="delete"
              @click="deleteTestData(props.row.id)"
            ></q-btn> </q-td></template
      ></q-table>
    </div>
  </q-page>
</template>
<script>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { graphql } from "src/api/graphql";
import { date } from "quasar";
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
    const router = useRouter();
    const loading = ref(false);
    const selected = ref([]);
    function allTestData() {
      loading.value = true;
      graphql(
        `
          {
            allTestData {
              name
              id
              lastModified
            }
          }
        `,
        (data) => {
          loading.value = false;
          if (!data.data.allTestData) return;
          var tc = data.data.allTestData
            .map((r) => {
              r.lastModified = Number(r.lastModified);
              return r;
            })
            .sort((a, b) => b.lastModified - a.lastModified);

          rows.value = tc.map((r) => {
            r.lastModified = new Date(r.lastModified);
            return r;
          });
        },
        (e) => {
          loading.value = false;
        }
      );
    }

    function createTestData() {
      var name = window.prompt("Test Data Name");
      if (!name) return;
      graphql(
        `mutation {
            createTestData(name:"${name}"){
                 name
                id
                lastModified
             }
          }`,
        (d) => {
          allTestData();
        },
        (e) => {}
      );
    }
    function deleteSelectedTestData() {
      if (selected.value.length == 0) return;
      var q = "mutation {\n";
      selected.value.map((i) => {
        q += `del${i.id}:deleteTestData(id:${i.id})\n`;
      });
      q += "}\n";
      console.log(q);
      graphql(
        q,
        (d) => {
          allTestData();
        },
        (e) => {}
      );
    }
    function deleteTestData(id) {
      graphql(
        `mutation {
            deleteTestData(id:${id})
          }`,
        (d) => {
          allTestData();
        },
        (e) => {}
      );
    }
    function editTestData(id) {
      router.push(`testData/${id}`);
    }
    onMounted(() => {
      allTestData();
    });
    return {
      rows,
      columns,
      deleteTestData,
      deleteSelectedTestData,
      createTestData,
      loading,
      editTestData,
      selected,
    };
  },
};
</script>
../api/test_api
