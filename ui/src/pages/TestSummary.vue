<template>
  <q-page padding>
    <q-expansion-item
      expand-separator
      label="Test Cases"
      hide-expand-icon
      default-opened
      header-class="bg-primary text-white"
    >
      <div class="q-pa-md q-gutter-sm">
        <q-btn-dropdown label="Actions">
          <q-list>
            <q-item clickable v-close-popup @click="onItemClick">
              <q-item-section>
                <q-item-label>Call Service</q-item-label>
              </q-item-section>
            </q-item>

            <q-item clickable v-close-popup @click="onItemClick">
              <q-item-section>
                <q-item-label>HTTP Call</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
        <q-btn color="primary">New</q-btn>
        <q-btn color="primary">Delete</q-btn>
        <q-btn color="primary">Run</q-btn>
      </div>
      <q-table
        :rows="rows"
        :columns="columns"
        row-key="name"
        selection="multiple"
      >
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn square flat color="primary">Edit</q-btn>
          </q-td>
        </template></q-table
      ></q-expansion-item
    >
  </q-page>
</template>
<script>
import { ref } from "vue";

const columns = [
  {
    name: "testcase",
    label: "Testcase",
    align: "left",
    field: (row) => row.name,
    sortable: true,
  },
  {
    name: "type",
    label: "Type",
    align: "left",
    field: (row) => row.type,
    sortable: true,
  },
  { name: "actions", align: "left", label: "Actions" },
];
export default {
  setup() {
    const rows = ref([{ name: "Test name", type: "callService" }]);
    return { rows, columns };
  },
};
</script>
