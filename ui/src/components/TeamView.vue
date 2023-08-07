<template>
  <q-expansion-item
    expand-separator
    icon="groups"
    :label="data.name"
    :caption="data.manager_name"
    header-class="bg-primary text-white"
    hide-expand-icon
  >
    <q-card class="q-pa-md">
      {{ data.manager }}
      <b v-if="data.user_members && data.user_members.length > 0">Users</b>
      <ol v-if="data.user_members && data.user_members.length > 0">
        <li v-for="user in data.user_members" v-bind:key="user">
          {{ user }}
        </li>
      </ol>
      <b v-if="data.group_members && data.group_members.length > 0">Groups</b>
      <ol v-if="data.group_members && data.group_members.length > 0">
        <li v-for="gp in data.group_members" v-bind:key="gp">
          {{ gp }}
        </li>
      </ol>
    </q-card>
  </q-expansion-item>
</template>
<script>
import { ref } from "vue";
import { defineComponent } from "vue";
export default defineComponent({
  name: "TeamView",
  props: ["data"],
});
</script>
