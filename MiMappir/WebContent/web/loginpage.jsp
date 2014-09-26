<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Acceso</title>
	<!-- ExtJS css -->
	<link rel="stylesheet" type="text/css" href="../ext-3.2.1/resources/css/ext-all-gray-sct.css" />

	<!-- Row Editor plugin css -->
	<link rel="stylesheet" type="text/css" href="../ext-3.2.1/examples/shared/examples.css" />
	<link rel="stylesheet" type="text/css" href="../ext-3.2.1/examples/ux/css/RowEditor.css" />

	<!-- App custom css -->
	<!-- <link rel="stylesheet" type="text/css" href="./css/crudgrid.css" /> -->
	<link href="../css/login.css" rel="stylesheet" type="text/css" />
	<link href="../css/custom-icons.css" rel="stylesheet" type="text/css" />
		
	<script language="JavaScript" type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
	
	<!-- ExtJS js -->
	<script language="JavaScript" type="text/javascript" src="../ext-3.2.1/adapter/ext/ext-base.js"></script>
	<script language="JavaScript" type="text/javascript" src="../ext-3.2.1/ext-all.js"></script>
	
	<!-- Row Editor plugin js -->
	<script language="JavaScript" type="text/javascript" src="../ext-3.2.1/examples/ux/RowEditor.js"></script>
	
	<!-- App js -->
	<script language="JavaScript" type="text/javascript" src="../js/login.js"></script>
</head>
<%request.getSession().invalidate();%>
<%request.getSession().setAttribute("session_", null);%>
 <% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
	<table width="600" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr valign="top">
			<td height="70">
			<table width="939" border="0" align="center"
					cellpadding="0" cellspacing="0">
					<tr>
						<td width="600"><img src="../images/Titulo.png" width="600"
							height="43" /></td>
							<td>&nbsp; </td>
						    <td width="245"><img src="../images/logo_empresa.png" width="245"
							height="78" /></td>
					</tr>
			  </table></td>
		</tr>
		<tr>
			<td class="gdtlogin">
				<div id="editableForm"></div>
				<!-- <div class="form">
					USUARIO CONTRASEÑA <img src="btn_entrar.png" width="56" height="22" />
				</div> -->
			</td>
		</tr>
		<tr>
			<td class="gdtbckd"><div align="center">
					<img src="../images/fondo.fw.png" width="984" height="587" />
				</div></td>
		</tr>
	</table>
</body>
</html>
