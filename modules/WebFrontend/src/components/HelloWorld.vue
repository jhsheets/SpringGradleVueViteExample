<script setup>
import {onMounted, ref} from 'vue'

defineProps({
  msg: String
})

const count = ref(0)
const service_version = ref(undefined)

onMounted(async() => {
  const response = await fetch("/api/service/version");
  const respData = await response.json();
  service_version.value = respData.version
})
</script>

<template>
  <h1>{{ msg }}</h1>

  <p>
    <a href="https://vitejs.dev/guide/features.html" target="_blank">
      Vite Documentation
    </a>
    |
    <a href="https://v3.vuejs.org/" target="_blank">Vue 3 Documentation</a>
  </p>

  <p>Service version from REST API is: {{service_version}}</p>

  <button type="button" @click="count++">count is: {{ count }}</button>
  <p>
    Edit
    <code>components/HelloWorld.vue</code> to test hot module replacement.
  </p>
</template>

<style scoped>
a {
  color: #42b983;
}
</style>
