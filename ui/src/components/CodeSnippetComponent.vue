<template>
  <ol>
    <li>
      <a href="#" @click.prevent="$.emit('onLinkClick', snippets[0])"
        >Call BAW service</a
      >
    </li>
    <li>
      <a href="#" @click.prevent="$.emit('onLinkClick', snippets[1])"
        >Get Input value</a
      >
    </li>
    <li>
      <a href="#" @click.prevent="$.emit('onLinkClick', snippets[2])"
        >Test standard response</a
      >
    </li>
    <li>
      <a href="#" @click.prevent="$.emit('onLinkClick', snippets[3])"
        >Convert String to JSON</a
      >
    </li>
  </ol>
</template>
<script>
import { defineComponent } from "vue";

const snippets = [
  `input  = [ "variable" :"value" ];
res = baw.callService(  "<<host>>",   "<<username>>",   "<<password>>",   "<<app name>>",   "<<service name>>",   input)
  `,
  `ctx["<<name>>"]`,
  `assert res.status == 200;
json = util.toJSON(res.getBody());
assert json["status"] == "200";
assert  json["data"]["data"]["<<variable>>"]=="value"`,
  `util.toJSON("{}")`,
];
export default defineComponent({
  name: "CodeSnippetComponent",
  setup() {
    return { snippets };
  },
});
</script>
