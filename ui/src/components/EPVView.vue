<template>
  <q-expansion-item
    expand-separator
    icon="groups"
    :label="data.name"
    header-class="bg-primary text-white"
    hide-expand-icon
  >
    <q-card class="q-pa-md">
      {{ data.container_acronym + "-" + data.version_acronym }}

      <q-table
        title="EPVs"
        :rows="data.epv_variables"
        :columns="columns"
        row-key="name"
      />
    </q-card>
  </q-expansion-item>
</template>
<script>
import { defineComponent } from "vue";

var columns = [
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
    field: (row) => row.current_value,
    format: (val) => `${val}`,
    sortable: true,
  },
];
export default defineComponent({
  name: "EPVView",
  props: ["data"],
  setup() {
    return {
      columns,
    };
  },
});
</script>
