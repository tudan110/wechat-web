(function(e){function t(t){for(var r,i,s=t[0],l=t[1],u=t[2],f=0,v=[];f<s.length;f++)i=s[f],Object.prototype.hasOwnProperty.call(o,i)&&o[i]&&v.push(o[i][0]),o[i]=0;for(r in l)Object.prototype.hasOwnProperty.call(l,r)&&(e[r]=l[r]);c&&c(t);while(v.length)v.shift()();return a.push.apply(a,u||[]),n()}function n(){for(var e,t=0;t<a.length;t++){for(var n=a[t],r=!0,s=1;s<n.length;s++){var l=n[s];0!==o[l]&&(r=!1)}r&&(a.splice(t--,1),e=i(i.s=n[0]))}return e}var r={},o={app:0},a=[];function i(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.m=e,i.c=r,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)i.d(n,r,function(t){return e[t]}.bind(null,r));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/wechat/";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=t,s=s.slice();for(var u=0;u<s.length;u++)t(s[u]);var c=l;a.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("85ec"),o=n.n(r);o.a},"24b4":function(e,t,n){e.exports=n.p+"img/default.7c2d880f.jpg"},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("HelloWorld",{attrs:{msg:"Welcome to wechat-web"}})],1)},a=[],i=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"hello"},[r("h1",[e._v(e._s(e.msg))]),r("div",{directives:[{name:"show",rawName:"v-show",value:e.isAlive,expression:"isAlive"}]},[r("h2",[e._v("登录成功")])]),r("div",{directives:[{name:"show",rawName:"v-show",value:!e.isAlive,expression:"!isAlive"}]},[r("div",{directives:[{name:"show",rawName:"v-show",value:!e.showQR,expression:"!showQR"}]},[r("h2",{directives:[{name:"show",rawName:"v-show",value:e.firstLogin,expression:"firstLogin"}]},[e._v("正在获取登录验证码，请稍后...")]),r("h2",{directives:[{name:"show",rawName:"v-show",value:!e.firstLogin,expression:"!firstLogin"}]},[e._v("二维码失效，请刷新页面")])]),r("div",{directives:[{name:"show",rawName:"v-show",value:e.showQR,expression:"showQR"}]},[r("h4",[e._v("网页版微信需要配合手机使用")]),r("h4",[e._v("使用手机微信扫码登录")])])]),r("ul",[r("img",{directives:[{name:"show",rawName:"v-show",value:!e.showQR,expression:"!showQR"}],attrs:{alt:"default",src:n("24b4")}}),r("img",{directives:[{name:"show",rawName:"v-show",value:e.showQR,expression:"showQR"}],attrs:{alt:"login",src:e.qrData}})])])},s=[],l=n("bc3a"),u=n.n(l),c={baseUrl:{dev:"http://localhost:9081/wechat/",pro:"http://10.20.22.9:9081/wechat/"}},f=c.baseUrl.pro,v=u.a.create({baseURL:f,timeout:5e3}),h=v,p={login:function(){return h.request({url:"login",method:"get"})},getQR:function(){return h.request({url:"getQR",method:"get"})}},d=p,w={name:"HelloWorld",data:function(){return{firstLogin:!0,showQR:!1,qrData:"data:image/jpeg;base64,",isAlive:!1,info:""}},props:{msg:String},methods:{login:function(){var e=this;d.login().then((function(t){e.info=t.data.info}))},getQR:function(){var e=this;d.getQR().then((function(t){e.showQR=t.data.data.showQR,e.qrData=t.data.data.qrData,e.isAlive=t.data.data.isAlive,e.firstLogin=!1}))}},mounted:function(){var e=this;this.login();var t=setInterval((function(){e.getQR()}));setTimeout((function(){t();while(e.showQR)clearInterval(t)}),2e3)}},m=w,g=(n("673d"),n("2877")),b=Object(g["a"])(m,i,s,!1,null,"612effe1",null),R=b.exports,x={name:"app",components:{HelloWorld:R}},y=x,Q=(n("034f"),Object(g["a"])(y,o,a,!1,null,null,null)),_=Q.exports,j=n("f825"),O=n.n(j);n("f8ce");r["default"].config.productionTip=!1,r["default"].use(O.a),new r["default"]({render:function(e){return e(_)}}).$mount("#app")},"673d":function(e,t,n){"use strict";var r=n("a774"),o=n.n(r);o.a},"85ec":function(e,t,n){},a774:function(e,t,n){}});