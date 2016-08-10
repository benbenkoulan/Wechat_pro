<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
</head>
<body>
	<form action="https://yintong.com.cn/llpayh5/authpay.htm" method="post" id="llpaysubmit" name="llpaysubmit">
		<input type="hidden" name="req_data" value='${req_data}' />
		<input hidden="hidden" type="submit" />
	</form>
	<script>
		document.forms["llpaysubmit"].submit();
	</script>
</body>
</html>