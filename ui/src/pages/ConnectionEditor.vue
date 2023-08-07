<template>
  <qpage padding>
    <q-expansion-item
      expand-separator
      label="Add or Update Connection"
      hide-expand-icon
      default-opened
      header-class="bg-primary text-white"
    >
      <q-form class="q-pa-md q-gutter-md" @submit="saveConnection">
        <q-input
          filled
          v-model="connnectionName"
          label="Connection name"
          lazy-rules
          :rules="[
            (val) =>
              (val && val.length > 0) || 'Enter a unique connection name',
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
          :rules="[
            (val) => (val && val.length > 0) || 'Enter BAW user login id',
          ]"
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

          <q-btn color="secondary" class="q-ml-sm" @click="goHome"
            >Cancel</q-btn
          >
        </div>
      </q-form>
    </q-expansion-item>
  </qpage>
</template>
<script>
import { api } from "src/boot/axios";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
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
      api
        .post("/api/saveConnection", {
          host: hostName.value,
          name: connnectionName.value,
          username: userName.value,
          password: password.value,
        })
        .then((res) => {
          $q.notify("New connection created");
          router.back();
        })
        .catch((res) => {
          $q.notify("Error occured while connection created");
        });
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
