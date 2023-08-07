<template>
  <qpage padding>
    <q-expansion-item
      expand-separator
      label="Connections"
      hide-expand-icon
      default-opened
      header-class="bg-primary text-white"
    >
      <div class="q-pa-md column q-gutter-sm">
        <div class="col">
          <q-btn color="primary" @click="openConnectionPage('new')"
            >New Connection</q-btn
          >
        </div>
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
                  label="View Apps"
                  @click="openViewAppsPage(props.row.alias)"
                ></q-btn>
                <q-btn
                  flat
                  color="red"
                  size="sm"
                  label="Delete Connection"
                  @click="deleteConnection(props.row.alias)"
                ></q-btn>
              </q-td>
            </template>
          </q-table>
        </div>
      </div>
    </q-expansion-item>
  </qpage>
</template>
<script>
import { ref, onMounted } from "vue";
import { api } from "../boot/axios";
import { useRoute, useRouter } from "vue-router";
import { useQuasar } from "quasar";
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
    function openViewAppsPage(id) {
      router.push(`/viewApp/${id}`);
    }

    function deleteConnection(id) {
      api
        .get(`api/deleteConnection/${id}`)
        .then((res) => {
          $q.notify("Connection deleted");
          loadData();
        })
        .catch((res) => {
          $q.notify("Error occured while deleting connection");
        });
    }
    function loadData() {
      api.get(`api/connections`).then((response) => {
        console.log(response.data);
        rows.value.splice(0, rows.value.length, ...response.data);
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
