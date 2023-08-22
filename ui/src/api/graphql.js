import { api } from "src/boot/axios";

export function graphql(query, on_data, on_error) {
  console.log(query);
  api
    .post("api/graphql", { query })
    .then((res) => {
      console.log(query, res.data);
      if (res.data.errors) {
        on_error(res.data.errors[0].message);
      } else {
        on_data(res.data);
      }
    })
    .catch((e) => {
      console.error(e);
      on_error(e);
    });
}
