<!DOCTYPE html>
<html>
	<head lang="en">
		<title>Spring Boot Demo - FreeMarker</title>
		<link href="/css/index.css" ref="stylesheet"/>		
	</head>
	
	<body>
		<center>
			<img src="/images/logo.png"/>
			<h1 id="title">${title}</h1>
		</center>
	</body>
	
	<script type="text/javascript" src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
	
	<script>
		$(function(){
			$('#title').click(function(){
				//alert("点击了");
				$.ajax({
					url:"http://localhost:8081/api/get",
					type:"POST",
					data:{
						name:"测试"
					},
					success:function(data,status,xhr){
						console.log(data);
						alert(data.name);
					}
				});
			});
		});
	</script>
	
</html>