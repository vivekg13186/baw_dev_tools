<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Test Scripts</q-toolbar-title>
      <q-btn flat icon="delete" @click="deleteSelectedTestScript"></q-btn>
      <q-btn flat icon="add" @click="createTestScript"></q-btn>
    </q-toolbar>

    <div class="q-pa-md q-gutter-sm">
      <q-table
        :rows="rows"
        :columns="columns"
        row-key="id"
        selection="multiple"
        v-model:selected="selected"
        :loading="loading"
      >
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn
              square
              flat
              icon="edit"
              @click="editTestScript(props.row.id)"
            ></q-btn>
            <q-btn
              square
              flat
              icon="delete"
              @click="deleteTestScript(props.row.id)"
            ></q-btn>
          </q-td> </template
      ></q-table>
    </div>
  </q-page>
</template>
<script>
import { onMounted, ref } from "vue";
import { date } from "quasar";
import { useRouter } from "vue-router";
import { graphql } from "src/api/graphql";
const columns = [
  {
    name: "testscript",
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
    const selected = ref([]);
    const router = useRouter();
    const rows = ref([]);
    const loading = ref(false);
    function allTestScript() {
      loading.value = true;
      graphql(
        `
          {
            allTestScript {
              name
              id
              lastModified
            }
          }
        `,
        (data) => {
          loading.value = false;
          var tc = data.data.allTestScript
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

    function createTestScript() {
      var name = window.prompt("Test Case Name");
      if (!name) return;
      graphql(
        `mutation {
            createTestScript(name:"${name}"){
                id
            }
          }`,
        (d) => {
          allTestScript();
        },
        (e) => {}
      );
    }
    function deleteSelectedTestScript() {
      if (selected.value.length == 0) return;
      var q = "mutation {\n";
      selected.value.map((i) => {
        q += `del${i.id}:deleteTestScript(id:"${i.id}")\n`;
      });
      q += "}\n";
      console.log(q);
      graphql(
        q,
        (d) => {
          allTestScript();
        },
        (e) => {}
      );
    }
    function deleteTestScript(id) {
      graphql(
        `mutation {
            deleteTestScript(id:"${id}")
          }`,
        (d) => {
          allTestScript();
        },
        (e) => {}
      );
    }
    function editTestScript(id) {
      router.push(`testScript/${id}`);
    }
    onMounted(() => {
      allTestScript();
    });
    return {
      rows,
      columns,
      deleteTestScript,
      selected,
      createTestScript,
      editTestScript,
      deleteSelectedTestScript,
      loading,
    };
  },
};
</script>
