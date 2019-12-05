<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <div v-show="isAlive">
      <h2>登录成功</h2>
    </div>
    <div v-show="!isAlive">
      <div v-show="!showQR">
        <h2 v-show="firstLogin">正在获取登录验证码，请稍后...</h2>
        <h2 v-show="!firstLogin">二维码失效，请刷新页面</h2>
      </div>
      <div v-show="showQR">
        <h4>网页版微信需要配合手机使用</h4>
        <h4>使用手机微信扫码登录</h4>
      </div>
    </div>
    <ul>
      <img v-show="!showQR" alt="default" src="../assets/default.jpg" />
      <img v-show="showQR" alt="login" :src="qrData" />
    </ul>
  </div>
</template>

<script>
import login from "../api/login"

export default {
  name: 'HelloWorld',
  data() {
    return {
      firstLogin: true,
      showQR: false,
      qrData: "data:image/jpeg;base64,",
      isAlive: false,
      info: ""
    }
  },
  props: {
    msg: String
  },
  methods: {
    login() {
      login.login().then(res => {
        this.info = res.data.info;
      });
    },
    getQR() {
      login.getQR().then(res => {
        this.showQR = res.data.data.showQR;
        this.qrData = res.data.data.qrData;
        this.isAlive = res.data.data.isAlive;
        this.firstLogin = false;
      });
    }
  },
  mounted() {
    this.login();
    let getQRInterval = setInterval(() => {
      this.getQR();
    });
    setTimeout(() => {
      getQRInterval();
      while(this.showQR) {
        clearInterval(getQRInterval);
      }
    }, 2000);
    // this.getQR();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
