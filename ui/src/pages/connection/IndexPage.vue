<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Connections</q-toolbar-title>
      <q-btn flat round dense icon="delete" @click="deleteSelectedConnections"
        ><q-tooltip> Delete connections </q-tooltip></q-btn
      >
      <q-btn flat round dense icon="add" @click="createConnection"
        ><q-tooltip> New Connection </q-tooltip></q-btn
      >
    </q-toolbar>

    <div class="q-pa-md column q-gutter-sm">
      <div class="col">
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
                flat
                size="sm"
                color="primary"
                label="View Apps"
                no-caps
                @click="openViewAppsPage(props.row.id, props.row.name)"
              ></q-btn>
              <q-btn
                flat
                size="sm"
                color="red"
                label="Delete"
                no-caps
                @click="deleteConnection(props.row.id)"
              ></q-btn>
            </q-td>
          </template>
        </q-table>
      </div>
    </div>
  </q-page>
</template>
<script>
import { ref, onMounted } from "vue";
import { graphql } from "src/api/graphql";
import { useRoute, useRouter } from "vue-router";
import { useQuasar } from "quasar";

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
    name: "host",
    required: true,
    label: "Host",
    align: "left",
    field: (row) => row.host,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "user",
    required: true,
    label: "User",
    align: "left",
    field: (row) => row.username,
    format: (val) => `${val}`,
    sortable: true,
  },
  { name: "actions", align: "left", label: "Actions" },
];

export default {
  setup() {
    const $q = useQuasar();
    const selected = ref([]);
    const router = useRouter();
    const rows = ref([]);
    const loading = ref(false);

    function openConnectionPage(id) {
      router.push(`/editConnnection/${id}`);
    }
    function openViewAppsPage(id, name) {
      router.push(`/viewApp/${id}/${name}`);
    }

    function allConnections() {
      loading.value = true;
      graphql(
        `
          {
            allConnections {
              name
              id
              host
              username
            }
          }
        `,
        (data) => {
          loading.value = false;
          var tc = data.data.allConnections;
          rows.value = tc;
        },
        (e) => {
          $q.notify(e);
          loading.value = false;
        }
      );
    }
    function createConnection() {
      router.push("/connection/-1");
    }
    function deleteConnection(id) {
      graphql(
        `mutation {
            deleteConnection(id:${id})
          }`,
        (d) => {
          allConnections();
        },
        (e) => {}
      );
    }
    function deleteSelectedConnections() {
      if (selected.value.length == 0) return;
      var q = "mutation {\n";
      selected.value.map((i) => {
        q += `del${i.id}:deleteConnection(id:${i.id})\n`;
      });
      q += "}\n";
      console.log(q);
      graphql(
        q,
        (d) => {
          allConnections();
        },
        (e) => {}
      );
    }
    onMounted(() => {
      allConnections();
    });
    return {
      columns,
      rows,
      openConnectionPage,
      deleteConnection,
      openViewAppsPage,
      createConnection,
      deleteSelectedConnections,
      selected,
      loading,
    };
  },
};
</script>
