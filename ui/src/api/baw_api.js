import { api } from "src/boot/axios";
import { Notify } from "quasar";

function LogError(e) {
  console.error(e);
  Notify.create(e);
}

export function getApps(connection_id, callback, on_error) {
  api
    .get(`/api/baw/${connection_id}/getApps`)
    .then((res) => {
      callback(res.data);
    })
    .catch((e) => {
      LogError(e);
      on_error();
    });
}

export function getToolkits(connection_id, callback, on_error) {
  api
    .get(`/api/baw/${connection_id}/getToolkits`)
    .then((res) => {
      callback(res.data);
    })
    .catch((e) => {
      LogError(e);
      on_error();
    });
}

export function getSnapshotDetails(connection, snapshotId, callback, on_error) {
  api
    .get(`/api/baw/${connection}/getSnapshotDetails`, {
      params: { id: snapshotId },
    })
    .then((res) => callback(res.data))
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}
export function containerAction(
  connection,
  appId,
  snapshotId,
  action,
  error,
  callback
) {
  api
    .get(`/api/baw/containerAction`, {
      params: { name: connection, appId, snapshotId, action },
    })
    .then((res) => callback(res.data))
    .catch((e) => {
      console.error("Error calling action " + action, e);
      Notify.create(error);
    });
}
export function findInstanceDetails(
  host,
  username,
  password,
  instanceId,
  callback
) {
  api
    .post("api/baw/getInstanceDetails", {
      host,
      username,
      password,
      instanceId,
    })
    .then((res) => callback(res.data))
    .catch((e) => {
      console.error("Error getting instance details " + instanceId, e);
      Notify.create("Error getting instance details " + instanceId);
    });
}
