 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <title>错误</title>
        
    </head>
    <body>
        <h2><s:fielderror/></h2>
        <h2><s:actionmessage/></h2>
    </body>
</html>
