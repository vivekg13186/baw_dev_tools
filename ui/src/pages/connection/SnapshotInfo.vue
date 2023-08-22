<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-btn flat round dense icon="arrow_back" @click="goBack()" />
      <q-toolbar-title>Snapshot Info</q-toolbar-title>
    </q-toolbar>

    <div class="q-pa-lg q-gutter-md">
      <q-breadcrumbs>
        <q-breadcrumbs-el :label="appName" />
        <q-breadcrumbs-el :label="snapName" />
      </q-breadcrumbs>

      <q-tabs
        v-model="tab"
        dense
        class="text-grey"
        active-color="primary"
        indicator-color="primary"
        align="left"
        narrow-indicator
      >
        <q-tab name="toolkits" label="Toolkits" />
        <q-tab name="envs" label="Enviroment Variables" />
        <q-tab name="teams" label="Teams" />
        <q-tab name="epvs" label="EPV" />
        <q-tab name="web_servers" label="Web Service" />
        <q-tab name="rest_servers" label="REST Servers" />
      </q-tabs>

      <q-separator />
      <q-tab-panels v-model="tab" animated>
        <q-tab-panel name="toolkits">
          <div class="q-pa-md q-gutter-sm">
            <q-tree :nodes="toolkits" node-key="id" />
          </div>
        </q-tab-panel>

        <q-tab-panel name="envs">
          <div class="q-pa-md q-gutter-sm">
            <q-table :rows="env_rows" :columns="env_columns"></q-table>
          </div>
        </q-tab-panel>

        <q-tab-panel name="teams">
          <TeamView v-for="t in teams" :data="t" v-bind:key="t.name" />
        </q-tab-panel>
        <q-tab-panel name="epvs">
          <EPVView v-for="e in epvs" :data="e" v-bind:key="e.id" />
        </q-tab-panel>
        <q-tab-panel name="web_servers">
          <WebService :data="webService"></WebService>
        </q-tab-panel>
        <q-tab-panel name="rest_servers">
          <WebService :data="webService"></WebService>
        </q-tab-panel>
      </q-tab-panels>
    </div>
  </q-page>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useQuasar } from "quasar";

import TeamView from "src/components/TeamView.vue";
import EPVView from "src/components/EPVView.vue";
import { containerAction, getSnapshotDetails } from "../../api/baw_api";

const env_columns = [
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
    name: "value",
    required: true,
    label: "Value",
    align: "left",
    field: (row) => row.value,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "container",
    required: true,
    label: "Container",
    align: "left",
    field: (row) => row.container,
    format: (val) => `${val}`,
    sortable: true,
  },
];
export default {
  components: { TeamView, EPVView },
  setup() {
    const $q = useQuasar();
    const route = useRoute();
    const router = useRouter();
    const connection = ref(route.query.con);
    const appName = ref(route.query.appName);
    const appSName = ref(route.query.appSName);
    const snapId = ref(route.query.snapId);
    const snapSName = ref(route.query.snapSName);
    const snapName = ref(route.query.snapName);

    const tab = ref("toolkits");
    const env_rows = ref([]);
    const toolkits = ref([]);
    const teams = ref([]);
    const epvs = ref([]);
    const webService = ref();
    const restService = ref();
    var idx = 0;
    function goBack() {
      router.back();
    }
    function normalize(node) {
      var res = {};
      res.id = idx++;
      res.label = `${node.projectName}(${node.snapshotName})`;
      if (node.projDeps) {
        res.children = node.projDeps.map(normalize);
      }
      return res;
    }
    function loadSnapshotDetails() {
      getSnapshotDetails(
        connection.value,
        snapId.value,
        (data) => {
          idx = 0;
          var d = data.data.projDeps;

          d = d.map(normalize);
          console.log(d);
          toolkits.value.splice(0, toolkits.value.length, ...d);
        },
        (e) => {}
      );
    }
    function loadEnvs() {
      containerAction(
        connection.value,
        appSName.value,
        snapSName.value,
        "env_vars",
        "Error loading env var details",
        (d) => env_rows.value.splice(0, env_rows.value.length, ...d.pairs)
      );
    }

    function loadTeams() {
      containerAction(
        connection.value,
        appSName.value,
        snapSName.value,
        "team_bindings",
        "Error loading team details",
        (d) => teams.value.splice(0, teams.value.length, ...d.team_bindings)
      );
    }

    function loadEPV() {
      containerAction(
        connection.value,
        appSName.value,
        snapSName.value,
        "epvs",
        "Error loading epvs details",
        (d) => epvs.value.splice(0, epvs.value.length, ...d.epvs)
      );
    }
    function loadWebService() {
      containerAction(
        connection.value,
        appSName.value,
        snapSName.value,
        "webservice",
        "Error loading webservice details",
        (d) => (webService.value = d)
      );
    }
    function loadRESTService() {
      containerAction(
        connection.value,
        appSName.value,
        snapSName.value,
        "rest",
        "Error loading REST details",
        (d) => (restService.value = d)
      );
    }
    onMounted(() => {
      loadSnapshotDetails();
      loadEnvs();
      loadTeams();
      loadEPV();
      loadWebService();
      loadRESTService();
    });
    return {
      tab,
      connection,
      appName,
      appSName,
      snapId,
      snapSName,
      snapName,
      toolkits,
      env_columns,
      env_rows,
      teams,
      epvs,
      goBack,
    };
  },
};
</script>
../api/baw_api
