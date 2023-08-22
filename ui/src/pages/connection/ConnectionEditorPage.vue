<template>
  <q-page>
    <q-toolbar class="bg-primary text-white">
      <q-btn flat round dense icon="arrow_back" to="/" />
      <q-toolbar-title>Add or Update Connection</q-toolbar-title>
    </q-toolbar>

    <q-form class="q-pa-md q-gutter-md" @submit="saveConnection">
      <q-input
        filled
        v-model="connnectionName"
        label="Connection name"
        lazy-rules
        :rules="[
          (val) => (val && val.length > 0) || 'Enter a unique connection name',
        ]"
      />
      <q-input
        filled
        v-model="hostName"
        label="Host"
        lazy-rules
        :rules="[
          (val) =>
            (val && val.length > 0) || 'Enter base url of the base server',
        ]"
      />
      <q-input
        filled
        v-model="userName"
        label="User name"
        lazy-rules
        :rules="[(val) => (val && val.length > 0) || 'Enter BAW user login id']"
      />
      <q-input
        filled
        v-model="password"
        label="Password"
        type="password"
        lazy-rules
        :rules="[(val) => (val && val.length > 0) || 'Enter password']"
      />
      <div>
        <q-btn color="primary" type="submit">Save</q-btn>

        <q-btn color="secondary" class="q-ml-sm" @click="goHome">Cancel</q-btn>
      </div>
    </q-form>
  </q-page>
</template>
<script>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useQuasar } from "quasar";
import { graphql } from "src/api/graphql";
export default {
  setup() {
    const router = useRouter();
    const route = useRoute();
    const id = ref(route.params.id);
    const $q = useQuasar();

    const hostName = ref();
    const userName = ref();
    const password = ref();
    const connnectionName = ref();
    function goHome() {
      router.back();
    }
    function saveConnection() {
      var ql = "";
      var host = hostName.value;
      var name = connnectionName.value;
      var username = userName.value;
      var password1 = password.value;
      if (id.value == -1) {
        ql = `mutation {
            createConnection(name:"${name}",host:"${host}",username:"${username}",password:"${password1}"){
                id
             }
          }`;
      } else {
        ql = `mutation {
            updateConnection(id:${id.value},name:"${name}",host:"${host}",username:"${username}",password:"${password1}"){
                id
             }
          }`;
      }
      graphql(
        ql,
        (d) => {
          goHome();
        },
        (e) => {
          $q.notify(e);
        }
      );
    }
    return {
      hostName,
      userName,
      password,
      connnectionName,
      goHome,
      saveConnection,
    };
  },
};
</script>
