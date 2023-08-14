<template>
  <div class="q-pa-sm example-row-all-breakpoints round-edge">
    <div class="row q-pl-md q-pt-sm">
      <div class="col-10">
        <div style="font-weight: bold" class="cursor-pointer">
          {{ label }} <q-icon size="xs" name="edit" class="label" />
          <q-popup-edit v-model="label" auto-save v-slot="scope">
            <q-input
              v-model="scope.value"
              autofocus
              filled
              @keyup.enter="scope.set"
            />
          </q-popup-edit>
        </div>
      </div>
      <div class="col-2 run-tool">
        <q-btn el color="teal" unelevated size="xs">PASS</q-btn>
        <q-btn
          square
          flat
          style="color: #555; width: 20px"
          icon="play_arrow"
          size="sm"
        ></q-btn>

        <q-btn
          square
          flat
          style="color: #555; width: 20px"
          icon="code"
          size="sm"
          @click="$emit('editCode', { id: cell.id })"
        ></q-btn>
        <q-btn
          square
          flat
          style="color: #555; width: 20px"
          icon="add"
          size="sm"
          @click="$emit('addCell')"
        ></q-btn>
        <q-btn
          square
          flat
          style="color: #555; width: 20px"
          icon="delete"
          size="sm"
          @click="$emit('deleteCell', { id: cell.id })"
        ></q-btn>
      </div>
    </div>
    <div class="run-tool"></div>
    <GroovyCodeView :data="cell.code"></GroovyCodeView>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import { ref, onMounted } from "vue";
import GroovyCodeView from "./GroovyCodeView.vue";

export default defineComponent({
  name: "TestScriptCell",
  components: { GroovyCodeView },
  props: ["cell"],
  setup(props) {
    const label = ref("");
    onMounted(() => {
      console.log(props.cell);
      label.value = props.cell.title;
    });
    return {
      label,
    };
  },
});
</script>
<style lang="scss">
.round-edge {
  border: 1px solid #ccc;
  border-radius: 5px;
}
.run-tool {
  display: flex;
  justify-content: flex-end;
  width: 100%;
}
.label {
  color: $blue-grey-3;
}
</style>
