import { api } from "src/boot/axios";
import { Notify } from "quasar";

function LogError(e) {
  console.error(e);
  Notify.create(e);
}
export function get_all_connections(callback) {
  api
    .get("/api/connection")
    .then((res) => callback(res.data))
    .catch(LogError);
}

export function delete_connection(id, callback) {
  api
    .delete(`/api/connection/${id}`)
    .then((res) => {
      callback();
    })
    .catch(LogError);
}

export function new_connection(data, callback) {
  api
    .post(`/api/connection`, data)
    .then((res) => {
      callback();
    })
    .catch(LogError);
}
