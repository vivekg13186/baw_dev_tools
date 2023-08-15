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
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
import { new_connection } from "../../api/connection_api";
export default {
  setup() {
    const $q = useQuasar();
    const router = useRouter();
    const hostName = ref();
    const userName = ref();
    const password = ref();
    const connnectionName = ref();
    function goHome() {
      router.back();
    }
    function saveConnection() {
      new_connection(
        {
          host: hostName.value,
          alias: connnectionName.value,
          username: userName.value,
          password: password.value,
        },
        () => {
          router.back();
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
../api/connection_api
