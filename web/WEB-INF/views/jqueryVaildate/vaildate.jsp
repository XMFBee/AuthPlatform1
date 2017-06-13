<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/jqueryVaildate/jquery.validate.js"></script>
    <script src="/static/js/jqueryVaildate/messages_zh.js"></script>
    <script>
        $.validator.setDefaults({
            submitHandler: function() {
                alert("提交事件!");
            }
        });
        $().ready(function() {
            $("#commentForm").validate();
        });
    </script>
    <style>
        label.error{color: red;font-weight: 100;font-size: 16px;}
        label{font-weight: 100;font-size: 16px;}
    </style>
</head>
<body>
<div class="container">
<form class="form-horizontal" id="commentForm" method="get" action="">
    <fieldset>
        <%--<legend>输入您的名字，邮箱，URL，备注。</legend>--%>
        <p>
            <label for="cname">Name (必需, 最小两个字母)</label>
            <input class="form-control" id="cname" name="name" minlength="2" type="text" required="true">
        </p>
        <p>
            <label for="cemail">E-Mail (必需)</label>
                     <input class="form-control" id="cemail" type="email" name="email" required>
        </p>
        <p>
            <label for="curl">URL (可选)</label>
                       <input class="form-control" id="curl" type="url" name="url">
        </p>
        <p>
            <label for="curl">只允许一个字符</label>
            <input class="form-control" id="byOne" type="byteRangeLength" name="byOne">
        </p>
        <p>
            <label for="ccomment">身份证</label>
            <input class="form-control" id="idcard" name="idcard" type="isIdCardNo" required></input>
        </p>
        <p>
            <label for="ccomment">手机号</label>
            <input class="form-control" id="phone" name="phone" type="phone" required></input>
        </p>
        <p>
            <label for="ccomment">备注 (必需)</label>
            <textarea class="form-control" id="ccomment" name="comment" required></textarea>
        </p>
        <p><!-- radio 的 required 表示必须选中一个。 -->
            <label for="ccomment">必须选中一个</label><br/>
            <input  type="radio" id="gender_female" value="f" name="gender"/>
            <input  type="radio" id="gender_male" value="m" name="gender" />
        </p>
        <p><!-- checkbox 的 required 表示必须选中。 -->
            <label for="ccomment">必须选中</label>
            <input type="checkbox" class="checkbox" id="agree" name="agree" title="必须选中" required />
        </p>
        <p><!-- checkbox 的 minlength 表示必须选中的最小个数，maxlength 表示最大的选中个数，rangelength:[2,3] 表示选中个数区间。 -->
            <label for="ccomment">必须选中二个</label>
            <input type="checkbox" class="checkbox" id="spam_email" value="email" name="spam[]" required minlength="2" title="必须选择两个以上"/>
            <input type="checkbox" class="checkbox" id="spam_phone" value="phone" name="spam[]" />
            <input type="checkbox" class="checkbox" id="spam_mail" value="mail" name="spam[]" />
        </p>
        <p><!--select 的 required 表示选中的 value 不能为空。-->
            <label for="ccomment">select的值不可为空</label><br/>
            <select id="jungle" name="jungle" title="请选择一个选项" required>
                <option value=""></option>
                <option value="1">Buga</option>
                <option value="2">Baga</option>
                <option value="3">Oi</option>
            </select>
        </p>
        <p>
            <input class="submit" type="submit" value="Submit">
        </p>
    </fieldset>
</form>
    </div>
</body>
</html>
