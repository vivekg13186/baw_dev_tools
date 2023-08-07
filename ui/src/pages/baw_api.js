import { api } from "src/boot/axios";
import { Notify } from "quasar";
export function containerAction(
  connection,
  appId,
  snapshotId,
  action,
  error,
  callback
) {
  api
    .get("api/containerAction", {
      params: { connection, appId, snapshotId, action },
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
    .get("api/getInstanceDetails", {
      params: { host, username, password, instanceId },
    })
    .then((res) => callback(res.data))
    .catch((e) => {
      console.error("Error getting instance details " + instanceId, e);
      Notify.create("Error getting instance details " + instanceId);
    });
}
