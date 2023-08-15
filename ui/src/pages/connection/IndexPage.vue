<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>Connections</q-toolbar-title>
      <q-btn flat round dense icon="add" @click="openConnectionPage('new')" />
    </q-toolbar>

    <div class="q-pa-md column q-gutter-sm">
      <div class="col">
        <q-table
          title="Connections"
          :rows="rows"
          :columns="columns"
          row-key="name"
        >
          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <q-btn
                flat
                color="primary"
                size="sm"
                icon="visibility"
                @click="openViewAppsPage(props.row.alias)"
                ><q-tooltip> View apps and toolkit </q-tooltip></q-btn
              >
              <q-btn
                flat
                color="red"
                size="sm"
                icon="delete"
                @click="deleteConnection(props.row.id)"
                ><q-tooltip> Delete connection </q-tooltip></q-btn
              >
            </q-td>
          </template>
        </q-table>
      </div>
    </div>
  </q-page>
</template>
<script>
import { ref, onMounted } from "vue";

import { useRoute, useRouter } from "vue-router";
import { useQuasar } from "quasar";
import {
  get_all_connections,
  delete_connection,
} from "../../api/connection_api";
const columns = [
  {
    name: "name",
    required: true,
    label: "Connection",
    align: "left",
    field: (row) => row.alias,
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
    const router = useRouter();
    const rows = ref([{ name: "hello" }]);
    function openConnectionPage(id) {
      router.push(`/editConnnection/${id}`);
    }
    function openViewAppsPage(alias) {
      router.push(`/viewApp/${alias}`);
    }

    function deleteConnection(id) {
      delete_connection(id, () => {
        loadData();
      });
    }
    function loadData() {
      get_all_connections((data) => {
        rows.value.splice(0, rows.value.length, ...data);
      });
    }
    onMounted(() => {
      console.log("mounted");
      loadData();
    });
    return {
      columns,
      rows,
      openConnectionPage,
      deleteConnection,
      openViewAppsPage,
    };
  },
};
</script>
../api/connection_api
