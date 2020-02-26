<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>
</head>

<body>
<h2>Hello World!</h2>
	<h3>当前在线设备</h3>
	<div id="app">
		<table border="1">
			<tr>
				<th>loginTime</th>
				<th>serialNo</th>
			</tr>
			<tr v-for="client in clients">
				<th>{{client.loginTime}}</th>
				<th>{{client.serialNo}}</th>
			</tr>
		</table>

		<button v-on:click="clickButton()">刷新</button>
		<p>{{ message }}</p>
	</div>
	
<script  type="text/javascript">
new Vue({
  el: '#app',
  data: {
    message: '0',
    clients:[]
  },
  methods:{
		clickButton:function() {
			var api='http://localhost:8080/zhgd/listDevice';
			this.$http.get(api).then((res)=>{
				//var jsonObj = JSON.parse(JSON.stringify(res.data));
				console.log(res.body);
				//this.message = res.body;
				//this.$options.methods.test(res.body);
				this.clients = res.body;
			}, (err)=>{
				console.log(err)
			})
		}/* ,
			test:function (data) {
			console.log(345);
				var vm = new Vue({
					el : '#clientTable',
					data : {
						clients : data
					}
				});
			} */
		}
})
</script>
</body>
</html>
