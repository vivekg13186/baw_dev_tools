import { api } from "src/boot/axios";
import { Notify } from "quasar";

function LogError(e) {
  console.error(e);
  Notify.create(e);
}

export function get_testdata(callback, on_error) {
  api
    .get("/api/testData")
    .then((res) => callback(res.data))
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}
export function get_testdata_by_name(name, callback, on_error) {
  api
    .get(`/api/testData/${name}`)
    .then((res) => callback(res.data))
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}

export function update_testdata(test_data, callback, on_error) {
  api
    .post("/api/testData", test_data)
    .then((res) => callback(res.data))
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}

export function delete_testdata(name, callback, on_error) {
  api
    .delete(`/api/testData/${name}`)
    .then((res) => callback(res.data))
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}

export function get_all_test_cases(callback, on_error) {
  api
    .get("/api/testcase")
    .then((res) => {
      callback(res.data);
    })
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}

export function delete_testcase(id, callback, on_error) {
  api
    .delete(`/api/testcase/${id}`)
    .then((res) => {
      callback();
    })
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}

export function update_testcase(data, callback, on_error) {
  api
    .post("/api/testcase", data)
    .then((res) => callback(res.data))
    .catch((e) => {
      LogError(e);
      on_error(e);
    });
}
