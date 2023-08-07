<template>
  <q-card class="q-pa-md">
    <q-table
      title="REST Servers"
      :rows="data.rest_server_definitions"
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
    label: "Server Name",
    align: "left",
    field: (row) => row.server_name,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "server_url",
    required: true,
    label: "Server url",
    align: "left",
    field: (row) => row.server_url,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "host_name",
    required: true,
    label: "Host name",
    align: "left",
    field: (row) => row.host_name,
    format: (val) => `${val}`,
    sortable: true,
  },
  ,
  {
    name: "port",
    required: true,
    label: "port",
    align: "left",
    field: (row) => row.port,
    format: (val) => `${val}`,
    sortable: true,
  },
  ,
  {
    name: "use_https",
    required: true,
    label: "Use https",
    align: "left",
    field: (row) => row.use_https,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "ssl_configuration",
    required: true,
    label: "SSL configuration",
    align: "left",
    field: (row) => row.ssl_configuration,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "request_timeout",
    required: true,
    label: "Request timeout",
    align: "left",
    field: (row) => row.request_timeout,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "response_timeout",
    required: true,
    label: "Response timeout",
    align: "left",
    field: (row) => row.response_timeout,
    format: (val) => `${val}`,
    sortable: true,
  },
];
export default defineComponent({
  name: "RestService",
  props: ["data"],
  setup() {
    return {
      columns,
    };
  },
});
</script>
