<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加班级</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript"> 
            $(document).ready(function() {
                $("#addClassForm").validate();
            });
        </script> 
    </head>
    <body>
        <div style="margin-left: 15px;">
            <s:actionmessage/>
            <h2>添加班级</h2>
            <form id="addClassForm" action="classManage!add.action" method="post" >
                <p>
                    入学年份：
                    <input type="text"  name="classes.rxnf" class="required" />
                </p>
                <p>
                    班级名称： <input type="text"  name="classes.className" class="required" ></input>
                    <s:fielderror />
                </p>
                <p>
                    班级人数： <input type="text"  name="classes.stuNum" class="required" ></input>
                </p>              
                <input type="submit"  value="提交" />
            </form>
        </div>
    </body>
</html>
