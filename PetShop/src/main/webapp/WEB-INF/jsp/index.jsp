<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物商店首页</title>

    <style>
        #tab2, #tab2 th, #tab2 td {
            border: 2px sandybrown solid;
        }
        #tbody1 input,#tbody1 select{
            width: 100px;
        }
    </style>
</head>
<body>

<form action="/pet/add" method="post" id="myform">
    <table>
        <thead>
        <tr>
            <th>宠物KEY</th>
            <th>宠物种类</th>
            <th>宠物名字</th>
            <th>宠物照片</th>
            <th>宠物标签</th>
            <th>宠物状态</th>
        </tr>
        </thead>
        <tbody id="tbody1">
        <tr>
            <td><input type="number" name="id"></td>
            <td>
                <select name="category">
                    <option>1</option>
                    <option>2</option>
                </select>
            </td>
            <td><input type="text" name="name"></td>
            <td><input type="text" name="photourls"></td>
            <td>
                <select name="tags">
                    <option>1</option>
                    <option>2</option>
                </select>
            </td>
            <td>
                <select name="status">
                    <option>幼年期</option>
                    <option>成年期</option>
                    <option>壮年期</option>
                </select>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<%--绑定一个动态事件--%>
<input type='button' onclick='doSubmit()' value='添加'/>

<br/><br/><br/>
<hr>
<br/><br/><br/>


<%--这一波操作，是把数据库里面有的数据拿出来显示出来--%>
<table id="tab2">
    <tr>
        <th>宠物编号</th>
        <th>宠物种类</th>
        <th>宠物名字</th>
        <th>光子量子</th>
        <th>宠物标签</th>
        <th>宠物状态</th>
        <th>删除宠物</th>
        <th>修改宠物</th>
    </tr>
    <c:forEach var="p" items="${petList}">
        <tr>
            <td>${p.id}</td>
            <td>${p.category}</td>
            <td>${p.name}</td>
            <td>${p.photourls}</td>
            <td>${p.tags}</td>
            <td>${p.status}</td>
                <%--获取每一行的id，进行删除--%>
            <td><a class="petDel" href="/pet/del/${p.id}">删除</a></td>
            <td><a href="/pet/update/${p.id}">修改</a></td>
        </tr>
    </c:forEach>
</table>
</body>

<script src="../../js/jquery-3.3.1.min.js"></script>
<script>
    // 添加一行新的数据，然后显示出来
    function doSubmit() {
        var rows = $("#tbody1 tr");
        rows.each(function (i, e) {
            $("input:text,select,input[type='number']", e).each(function (ii, ee) {
                $(ee).attr("name", "pets[" + i + "]." + $(ee).attr("name"));
            });
        });
        $("#myform").submit();
    }

    // 删除前做询问的操作，毕竟删除一条数据是很重要的
    $(function () {
        $("#tab2").on("click", ".petDel", function (event) {
            // 判断该条数据不删除，点击确定是删除
            if (!window.confirm("确认删除吗？")) {
                return false;
            }
        });
    });



</script>
</html>
