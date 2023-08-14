<template>
  <pre style="background-color: #eaeaea; padding: 10px; border-radius: 5px">
    <code   v-html="code"  />
    </pre>
</template>
<script>
import Prism from "prismjs";
import "prismjs/components/prism-groovy";
import "prismjs/themes/prism-coy.css";

import { ref, onMounted } from "vue";
export default {
  setup() {
    const code = ref("");
    function highLightCode() {
      console.log("view", props.data, Prism.languages.groovy);
      code.value = Prism.highlight(
        props.data,
        Prism.languages.groovy,
        "groovy"
      );
    }
    onMounted(() => {
      highLightCode();
    });
    return {
      code,
      highLightCode,
    };
  },
};
</script>
