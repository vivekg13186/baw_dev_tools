import { api } from "src/boot/axios";

export function graphql(query, on_data, on_error) {
  api
    .post("api/graphql", { query })
    .then((res) => {
      on_data(res.data);
    })
    .catch((e) => {
      console.error(e);
      on_error(e);
    });
}
