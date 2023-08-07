<template>
  <q-card class="q-pa-md">
    <q-table
      title="Web services"
      :rows="data.ws_server_definitions"
      :columns="columns"
      row-key="name"
    />
  </q-card>
</template>
<script>
import { defineComponent } from "vue";

var columns = [
  {
    name: "name",
    required: true,
    label: "Name",
    align: "left",
    field: (row) => row.server_name,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "override_endpoint",
    required: true,
    label: "Override endpoint",
    align: "left",
    field: (row) => row.override_endpoint,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "endpoint_address",
    required: true,
    label: "Endpoint address",
    align: "left",
    field: (row) => row.endpoint_address,
    format: (val) => `${val}`,
    sortable: true,
  },
  ,
  {
    name: "authentication",
    required: true,
    label: "Authentication",
    align: "left",
    field: (row) => row.authentication,
    format: (val) => `${val}`,
    sortable: true,
  },
  ,
  {
    name: "user_name",
    required: true,
    label: "User name",
    align: "left",
    field: (row) => row.user_name,
    format: (val) => `${val}`,
    sortable: true,
  },
];
export default defineComponent({
  name: "WebService",
  props: ["data"],
  setup() {
    return {
      columns,
    };
  },
});
</script>
