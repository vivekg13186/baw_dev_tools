<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-btn flat round dense icon="arrow_back" to="/" />
      <q-toolbar-title
        >{{ connection }} -Applications and Toolkits</q-toolbar-title
      >
    </q-toolbar>

    <div class="q-pa-md q-gutter-md">
      <q-splitter v-model="splitterModel">
        <template v-slot:before>
          <q-table
            title="Applications"
            :rows="apps"
            :columns="columns"
            row-key="name"
            :loading="loading_apps"
            :filter="filter1"
          >
            <template v-slot:top-right>
              <q-input
                borderless
                dense
                debounce="300"
                v-model="filter1"
                placeholder="Search"
              >
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </template>
            <template v-slot:body-cell-actions="props">
              <q-td :props="props">
                <q-btn
                  flat
                  color="primary"
                  size="sm"
                  label="View Snapshots"
                  @click="showSnapshots(props.row)"
                ></q-btn>
              </q-td>
            </template>
          </q-table>
          <q-table
            title="Toolkits"
            :rows="toolkits"
            :columns="columns"
            row-key="name"
            :loading="loading_toolkits"
            :filter="filter2"
          >
            <template v-slot:top-right>
              <q-input
                borderless
                dense
                debounce="300"
                v-model="filter2"
                placeholder="Search"
              >
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </template>
            <template v-slot:body-cell-actions="props">
              <q-td :props="props">
                <q-btn
                  flat
                  color="primary"
                  size="sm"
                  label="View Snapshots"
                  @click="showSnapshots(props.row)"
                ></q-btn>
              </q-td>
            </template>
          </q-table>
        </template>
        <template v-slot:after>
          <div class="q-pa-sm q-gutter-lg">
            <div class="q-pa-sm q-gutter-md">
              <q-btn color="primary" @click="makeDefault">Make Default</q-btn>
              <q-btn color="primary" @click="doAction('activate')"
                >Activate</q-btn
              >
              <q-btn color="primary" @click="doAction('deactivate')"
                >DeActivate</q-btn
              >
              <q-btn color="primary" @click="doAction('stop')">Stop</q-btn>
              <q-btn color="primary" @click="doAction('archive')"
                >Archive</q-btn
              >
            </div>
            <q-table
              :title="'Snapshots - ' + selected_app"
              :rows="snapshots"
              :columns="snapshot_columns"
              row-key="acronym"
              selection="multiple"
              :selected-rows-label="getSelectedString"
              v-model:selected="selected"
              :filter="filter3"
            >
              <template v-slot:top-right>
                <q-input
                  borderless
                  dense
                  debounce="300"
                  v-model="filter3"
                  placeholder="Search"
                >
                  <template v-slot:append>
                    <q-icon name="search" />
                  </template>
                </q-input>
              </template>
              <template v-slot:body-cell-actions="props">
                <q-td key="action" :props="props">
                  <q-btn
                    flat
                    color="primary"
                    size="sm"
                    label="View Config"
                    @click="openSnapshotPage(props.row)"
                  ></q-btn>
                </q-td> </template
            ></q-table>
          </div>
        </template>
      </q-splitter>
    </div>
  </q-page>
</template>
<script>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useQuasar } from "quasar";

import { containerAction, getApps, getToolkits } from "../../api/baw_api";
const columns = [
  {
    name: "appName",
    required: true,
    label: "App name",
    align: "left",
    field: (row) => row.name,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "shortName",
    required: true,
    label: "ID",
    align: "left",
    field: (row) => row.shortName,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "defaultBranch",
    required: true,
    label: "Default Branch",
    align: "left",
    field: (row) => row.defaultVersion,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "count",
    required: true,
    label: "Snapshot Count",
    align: "left",
    field: (row) =>
      row.installedSnapshots ? row.installedSnapshots.length : "",
    format: (val) => `${val}`,
    sortable: true,
  },
  { name: "actions", align: "left", label: "Actions" },
];
const snapshot_columns = [
  {
    name: "snapshotName",
    required: true,
    label: "Snapshot name",
    align: "left",
    field: (row) => row.name,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "shortName",
    required: true,
    label: "ID",
    align: "left",
    field: (row) => row.acronym,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "branchName",
    required: true,
    label: "Branch",
    align: "left",
    field: (row) => row.branchName,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "createdOn",
    required: true,
    label: "Created On",
    align: "left",
    field: (row) => row.createdOn,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "active",
    field: (row) => row.active,
    format: (val) => `${val}`,
    label: "Active",
    align: "left",

    sortable: true,
  },

  { name: "actions", align: "left", label: "Actions" },
];

export default {
  setup() {
    const $q = useQuasar();
    const router = useRouter();
    const route = useRoute();
    const connection = ref(route.params.id);

    const apps = ref([]);
    const toolkits = ref([]);
    const loading_apps = ref(true);
    const loading_toolkits = ref(true);
    const splitterModel = ref(50);
    const snapshots = ref([]);
    const selected_app = ref("");
    const selected = ref([]);
    const selected_app_id = ref("");
    const filter1 = ref("");
    const filter2 = ref("");
    const filter3 = ref("");
    function loadToolkits() {
      getToolkits(
        connection.value,
        (data) => {
          loading_toolkits.value = false;
          var data = data.data.processAppsList;
          toolkits.value.splice(0, toolkits.value.length, ...data);
        },
        (data) => {
          loading_toolkits.value = false;
        }
      );
    }
    function loadApps() {
      getApps(
        connection.value,
        (data) => {
          loading_apps.value = false;
          var data = data.data.processAppsList;
          apps.value.splice(0, apps.value.length, ...data);
        },
        (data) => {
          loading_apps.value = false;
        }
      );
    }
    function showSnapshots(row) {
      snapshots.value.splice(0, snapshots.value.length);
      selected_app.value = `${row.name}(${row.shortName})`;
      selected_app_id.value = row.shortName;
      snapshots.value.splice(
        0,
        snapshots.value.length,
        ...row.installedSnapshots
      );
    }
    function openSnapshotPage(row) {
      router.push({
        name: "viewSnapshot",
        query: {
          con: connection.value,
          appName: selected_app.value,
          appSName: selected_app_id.value,
          snapId: row.ID,
          snapSName: row.acronym,
          snapName: row.name,
        },
      });
    }
    function doAction(action) {
      var arr = selected.value;

      for (var i = 0; i < arr.length; i++) {
        var snapId = arr[i].acronym;
        console.log(arr[i]);
        containerAction(
          connection.value,
          selected_app_id.value,
          snapId,
          action,
          `Error ${action} snapshot ${snapId}`,
          function (d) {
            $q.notify(`${action} snapshot ${snapId}`);
          }
        );
      }
    }
    function makeDefault() {
      var arr = selected.value;
      var action = "make_default";
      if (arr.length == 1) {
        var snapId = arr[0].acronym;
        containerAction(
          connection.value,
          selected_app_id.value,
          snapId,
          action,
          `Error ${action} snapshot ${snapId}`,
          function (d) {
            $q.notify(`${action} snapshot ${snapId}`);
          }
        );
      } else {
        $q.notify(`Make default need single selection`);
      }
    }
    onMounted(() => {
      loadApps();
      loadToolkits();
    });
    return {
      connection,
      loading_toolkits,

      columns,
      apps,
      loading_apps,
      toolkits,
      splitterModel,
      showSnapshots,
      snapshot_columns,
      snapshots,
      selected_app,
      selected,
      openSnapshotPage,
      filter1,
      filter3,
      filter2,
      makeDefault,
      doAction,
      getSelectedString() {
        return selected.value.length === 0
          ? ""
          : `${selected.value.length} record${
              selected.value.length > 1 ? "s" : ""
            } selected of ${snapshots.value.length}`;
      },
    };
  },
};
</script>
../api/baw_api
