<template>
  <qpage padding>
    <q-expansion-item
      expand-separator
      label="Search Details"
      hide-expand-icon
      default-opened
      header-class="bg-primary text-white"
    >
      <div class="q-pa-md q-gutter-md">
        <q-input filled v-model="instanceID" label="Instance ID" />
        <q-input filled v-model="url" label="URL" />
        <q-input filled v-model="username" label="Username" />
        <q-input filled v-model="password" type="password" label="Password" />
        <div>
          <q-btn color="primary" icon="search" @click="search">Search</q-btn>
        </div>
      </div>
    </q-expansion-item>
    <q-expansion-item
      v-if="instance"
      expand-separator
      label="Instance Details"
      hide-expand-icon
      default-opened
      header-class="bg-primary text-white"
    >
      <div class="q-pa-md q-gutter-md">
        <q-list bordered separator>
          <q-item>
            <q-item-section>
              <q-item-label>Instance ID</q-item-label>
              <q-item-label caption>{{ instance.piid }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Status</q-item-label>
              <q-item-label caption>{{ instance.executionState }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Instance Name</q-item-label>
              <q-item-label caption>{{ instance.name }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Instance Creation Time</q-item-label>
              <q-item-label caption>{{ instance.creationTime }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Last Modification Date</q-item-label>
              <q-item-label caption>{{
                instance.lastModificationTime
              }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Process App Name</q-item-label>
              <q-item-label caption>{{ instance.processAppName }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Snapshot Name</q-item-label>
              <q-item-label caption>{{ instance.snapshotName }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Branch Name</q-item-label>
              <q-item-label caption>{{ instance.branchName }}</q-item-label>
            </q-item-section>
            <q-item-section>
              <q-item-label>Due Date</q-item-label>
              <q-item-label caption>{{ instance.dueDate }}</q-item-label>
            </q-item-section>
          </q-item>
        </q-list>
        <q-btn color="primary" @click="showInstanceData"
          >View Instance Data</q-btn
        >
        <q-table
          title="Task"
          :rows="instance.tasks"
          :columns="columns"
          row-key="name"
        >
          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <q-btn
                flat
                color="primary"
                size="sm"
                label="View data"
                @click="showTaskData(props.row.data)"
              ></q-btn>
            </q-td> </template
        ></q-table>
        <h6>Execution Tree</h6>
        <q-tree node-key="nodeId" :nodes="treeNode" label-key="name"></q-tree>
      </div>
    </q-expansion-item>
    <q-dialog v-model="code_dialog_viz" full-width>
      <q-card>
        <q-card-section>
          <div class="text-h6">Variables</div>
        </q-card-section>

        <q-separator />

        <q-card-section class="scroll" style="max-height: 550px">
          <codemirror
            v-model="code"
            :style="{ height: '500px' }"
            :autofocus="true"
            :indent-with-tab="true"
            :tab-size="2"
            :extensions="extensions"
            @ready="handleReady"
          />
        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
          <q-btn flat label="Close" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </qpage>
</template>

<script>
const columns = [
  {
    name: "taskName",
    label: "Task name",
    align: "left",
    field: (row) => row.displayName,
    sortable: true,
  },
  {
    name: "startTime",
    label: "Start time",
    align: "left",
    field: (row) => row.startTime,
    sortable: true,
  },
  {
    name: "priority",
    label: "Priority",
    align: "left",
    field: (row) => row.priorityName,
    sortable: true,
  },
  {
    name: "assignedTo",

    label: "Assigned to",
    align: "left",
    field: (row) => row.assignedToDisplayName,
    sortable: true,
  },
  {
    name: "dueDate",
    label: "Due date",
    align: "left",
    field: (row) => row.dueTime,
    sortable: true,
  },
  {
    name: "taskId",
    label: "Task id",
    align: "left",
    field: (row) => row.tkiid,
    sortable: true,
  },
  {
    name: "status",
    label: "Status",
    align: "left",
    field: (row) => row.status,
    sortable: true,
  },
  { name: "actions", align: "left", label: "Actions" },
];
function prettify(a) {
  a = JSON.parse(a);
  var t = JSON.stringify(JSON.parse(a), null, 4);
  console.log(t);
  return t;
}

import { Codemirror } from "vue-codemirror";
import { json } from "@codemirror/lang-json";
import { oneDark } from "@codemirror/theme-one-dark";
import { ref, shallowRef } from "vue";
import { findInstanceDetails } from "./baw_api";
export default {
  components: { Codemirror },
  setup() {
    const extensions = [json(), oneDark];
    const instanceID = ref("25175");
    const url = ref("");
    const username = ref("");
    const password = ref("");
    const instance = ref();
    const treeNode = ref();
    const code = ref("");
    const code_dialog_viz = ref(false);
    const editor = shallowRef();
    function handleReady(payload) {
      editor.value = payload.view;
    }
    function search() {
      findInstanceDetails(
        url.value,
        username.value,
        password.value,
        instanceID.value,
        function (d) {
          console.log(d);
          instance.value = d.data;
          treeNode.value = [d.data.executionTree.root];
          console.log(d.data.executionTree.root);
        }
      );
    }

    function showInstanceData() {
      code.value = prettify(instance.value.data);
      code_dialog_viz.value = true;
    }

    function showTaskData(d) {
      code.value = JSON.stringify(d, null, 4);
      code_dialog_viz.value = true;
    }
    return {
      instanceID,
      url,
      username,
      password,
      instance,
      search,
      columns,
      code_dialog_viz,
      extensions,
      code,
      handleReady,
      showTaskData,
      showInstanceData,
      treeNode,
    };
  },
};
</script>
