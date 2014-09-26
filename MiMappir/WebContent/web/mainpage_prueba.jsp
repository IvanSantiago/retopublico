<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Ejemplos</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/ext-all-gray.css"/>

    <!-- GC -->
    <!-- LIBS -->
    <script type="text/javascript" src="../../adapter/ext/ext-base.js"></script>
    <!-- ENDLIBS -->

    <script type="text/javascript" src="../../ext-all.js"></script>

    <script language="javascript" src="../state/save-state.php"></script>
    <script language="javascript" src="../state/get-state.php"></script>
    <script language="javascript" src="../state/SessionProvider.js"></script>
    <script language="javascript" src="../WebContent/js/layout.js"></script>

    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="../shared/examples.css"/>

    <style type="text/css">
        .x-panel-body p {
            margin: 10px;
            font-size: 12px;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="../shared/examples.js"></script>
<!-- EXAMPLES -->
<h1>Windows with Layouts</h1>
<p>This example shows how Ext containers can be nested in windows to create advanced layouts.</p>
<input type="button" id="show-btn" value="Show Window"/><br/><br/>
<p>Note that the js is not minified so it is readable. See <a href="../js/ejemplos/layout.js">layout.js</a> for the full source code.</p>
</body>
</html>