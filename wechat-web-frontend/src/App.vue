<template>
  <div id="app">
    <HelloWorld msg="Welcome to wechat-web" />
  </div>
</template>

<script>
import HelloWorld from "./components/HelloWorld.vue";

export default {
  name: "app",
  components: {
    HelloWorld
  }
};
</script>

<style>
html, body {
  height: 100%;
  width: 100%;
}

body {
  display: flex;
  flex-direction: wrap;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  background: url(./assets/bg.jpg) no-repeat;
  background-size: cover;
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

::-webkit-scrollbar-track-piece {
  background-color: #f8f8f8;
}
::-webkit-scrollbar {
  width: 9px;
  height: 9px;
}
::-webkit-scrollbar-thumb {
  background-color: #dddddd;
  background-clip: padding-box;
  min-height: 28px;
}
::-webkit-scrollbar-thumb:hover {
  background-color: #bbb;
}
</style>
