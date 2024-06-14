<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gqy.usersystem.povo.User" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            margin: 0 auto;
            padding: 2rem;
            font-weight: normal;
            display: flex;
            flex-direction: column;
            align-items: center;
            align-content: center;
            text-align: center;
            width: 100vw;
            height: 100vh;
            background: linear-gradient(to right, #12c2e9, #c471ed, #f64f59);
            font-family: "JetBrainsMono Nerd Font Mono", Times, serif !important;
        }

        a,
        .green {
            text-decoration: none;
            color: hsla(160, 100%, 37%, 1);
            transition: 0.4s;
            padding: 8px
        }
        * {
            box-sizing: border-box;
        }
        @media (max-width: 380px) {
            .form-control {
                width: 180px;
            }
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background-color: rgba(0, 0, 0, 0.1);
            padding: 20px 40px;
            border-radius: 5px;
            margin: 100px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1), 0 6px 20px rgba(0, 0, 0, 0.1);
        }
        * {
            margin: 0;
            padding: 0;
        }
        #myTable {
            width: 90vw;
        }
        input {
            background: transparent;
            border: none;
            border-radius: 3px;
            width: 80%;
        }
        input:focus {
            background: white;
            outline: none;
        }
        .btn {
            margin: 0 10px;
            letter-spacing: 2px;
            font-size: 14px;
            border-radius: 5px;
            background-color: #7cdbef;
            border: none;
            box-shadow: 1px 2px 10px 0 rgba(0, 0, 0, 0.3);
        }
        .btn:hover {
            opacity: 0.8;
        }
        .btn:active {
            transform: scale(0.98);
        }
        .btn:focus {
            outline: none;
        }
        .ht{
            text-align: center;
            color: #155557;
            font-size: 36px;
        }
        .tab{
            width: 80%;
            border: 5px solid cadetblue;
            margin: 0 auto;
            border-radius: 5px;
        }
        .ltr td{
            border: 1px solid  powderblue;
            text-align: center;
        }
        .buttonContainer{
            margin: 0 auto;
        }
    </style>
    <script>
        function submitForm(operation) {
            var form = document.createElement('form');
            form.setAttribute('method', 'post');
            form.setAttribute('action', '/user.do');

            var opField = document.createElement('input');
            opField.setAttribute('type', 'hidden');
            opField.setAttribute('name', 'op');
            opField.setAttribute('value', operation.split('_')[0]);

            form.appendChild(opField);

            if (operation.startsWith('0x002')) {
                var userId = operation.split('_')[1];

                var inputField = document.createElement('input');
                inputField.setAttribute('type', 'hidden');
                inputField.setAttribute('name', 'updateId');
                inputField.setAttribute('value', userId);
                form.appendChild(inputField);

                var selectedSex = document.querySelector('input[name="sex_' + userId + '"]:checked').value;
                // Append "Sex" field
                var sexField = document.createElement('input');
                sexField.setAttribute('type', 'hidden');
                sexField.setAttribute('name', 'sex_' + userId);
                sexField.setAttribute('value', selectedSex);
                form.appendChild(sexField);

                var fields = ['name', 'birth', 'nation', 'hobby', 'memo'];
                fields.forEach(function(field) {
                    var inputField = document.createElement('input');
                    inputField.setAttribute('type', 'hidden');
                    inputField.setAttribute('name', field + '_' + userId);
                    inputField.setAttribute('value', document.querySelector('input[name="' + field + '_' + userId + '"]').value);
                    form.appendChild(inputField);
                });
            } else if (operation.startsWith('0x003')) {
                var userId = operation.split('_')[1];
                var deleteIdField = document.createElement('input');
                deleteIdField.setAttribute('type', 'hidden');
                deleteIdField.setAttribute('name', 'deleteId');
                deleteIdField.setAttribute('value', userId);
                form.appendChild(deleteIdField);
            }

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>
    <div class="container">
        <h3 class="ht">用户列表如下</h3>
        <form id="userForm" action="/user.do", method="post">
            <table class="tab" id="myTable">
                <tr class="ltr">
                    <th>序号</th>
                    <th>用户名</th>
                    <th>生日</th>
                    <th>名族</th>
                    <th style="width: 150px">性别</th>
                    <th>爱好</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                <%
                    List<User> users = (List<User>) request.getSession().getAttribute("users");
                    int index = 0;
                    for (User user : users) {
                        index++;
                %>
                    <tr class="ltr">
                        <td><%= index %></td>
                        <td><input name="name_<%= user.getUserId() %>" value="<%= user.getName() %>"/></td>
                        <td><input name="birth_<%= user.getUserId() %>" value="<%= user.getBirth() %>"/></td>
                        <td><input name="nation_<%= user.getUserId() %>" value="<%= user.getNation() %>"/></td>
                        <td>
                            <div style="display: inline-block;">
                                <input class="btnRadio" type="radio" name="sex_<%= user.getUserId() %>" value="1" <% if (user.getSex().equals("1")) { %> checked <% } %>> 男
                            </div>
                            <div style="display: inline-block; margin-left: 10px;">
                                <input class="btnRadio" type="radio" name="sex_<%= user.getUserId() %>" value="0" <% if (!user.getSex().equals("1")) { %> checked <% } %>> 女
                            </div>
                        </td>
                        <td><input name="hobby_<%= user.getUserId() %>" value="<%= user.getHobby() %>"/></td>
                        <td><input name="memo_<%= user.getUserId() %>" value="<%= user.getMemo() %>"/></td>
                        <td class="buttonContainer">
                            <div style="white-space: nowrap; display: inline-block">
                                <input class="btn" type="button" value="Update" onclick="submitForm('0x002_<%= user.getUserId() %>')" style="width: 100px" />
                                <input class="btn" type="button" value="Delete" onclick="submitForm('0x003_<%= user.getUserId() %>')" style="width: 100px" />
                            </div>
                        </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </div>
</body>
</html>