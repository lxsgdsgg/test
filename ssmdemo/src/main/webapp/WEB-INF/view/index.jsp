<%--
  Created by IntelliJ IDEA.
  User: YNBSJ-003
  Date: 2019/1/14
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/jquery.dataTables.css">

</head>
<body>
    <div>
        <table id="example" class="display" style="width:100%">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>age</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/jquery.dataTables.js"/>
    <script src="../js/layui.js"></script>
    <script>
        $(document).ready(function() {
            var arr = []
            $('#example').DataTable({
                ajax: '/demo/queryUser',
                dataSrc:function ( json ) {
                console.log(json)
                },
                "columns": [
                    { "data": "id" },
                    { "data": "name" },
                    { "data": "age" },
                ],
                success:function (data) {
                    console.log(data)
                }

            });
            // $.ajax({
            //     dataType:"JSON",
            //     url:"/demo/queryUser",
            //     success:function(e){
            //         console.log(e)
            //     }
            // })
        } );
    </script>
</body>
</html>
