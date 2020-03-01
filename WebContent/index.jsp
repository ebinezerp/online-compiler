<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<script type="text/javascript">
	function compile() {
		let sourcecodeForm = document.forms['sourcecodeForm'];

		sourcecodeForm.setAttribute('action', 'compile');

		sourcecodeForm.submit();

	}
	
	
	function run(){
		let sourcecodeForm = document.forms['sourcecodeForm'];
		sourcecodeForm.setAttribute('action','run');
		sourcecodeForm.submit();
	}
	
</script>

<style>
#container {
	display: flex;
}

#container>#sourcecodeForm-section {
	flex: 3;
}

#container>#compile-run-section {
	flex: 1;
	border: 1px solid black;
}
</style>

</head>
<body>
	<section id="container">
		<section id='sourcecodeForm-section'>
			<form name="sourcecodeForm" method="post">
				<div>
					<input type="hidden" name="compilestatus"  value="${compilestatus}"/>
				</div>
				<div>
					<textarea name="sourcecode" rows="20" cols="70">${param.sourcecode}</textarea>
				</div>
				<input type="button" value="Compile" onclick="compile()"> <input
					type="button" value="Run" onclick="run()">
			</form>
		</section>

		<section id="compile-run-section">
			  ${message}
		</section>
	</section>
</body>
</html>