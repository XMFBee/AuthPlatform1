$(document).ready(function() {
	// 手机号码验证
	jQuery.validator.addMethod("isPhone", function(value, element) {
		var length = value.length;
		return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
	}, "请正确填写您的手机号码。");

	// 电话号码验证
	jQuery.validator.addMethod("isTel", function(value, element) {
		var tel = /^(\d{3,4}-)?\d{7,8}$/g; // 区号－3、4位 号码－7、8位
		return this.optional(element) || (tel.test(value));
	}, "请正确填写您的电话号码。");
	// 匹配密码，以字母开头，长度在6-12之间，必须包含数字和特殊字符。
	jQuery.validator.addMethod("isPwd", function(value, element) {
		var str = value;
		if (str.length < 6 || str.length > 18)
			return false;
		if (!/^[a-zA-Z]/.test(str))
			return false;
		if (!/[0-9]/.test(str))
			return false;
		return this.optional(element) || /[^A-Za-z0-9]/.test(str);
	}, "以字母开头，长度在6-12之间，必须包含数字和特殊字符。");

// 身份证
    jQuery.validator.addMethod("isIdCardNo", function (value, element, param){
        var checkName = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
        return this.optional(element) || (checkName.test(value));
    },$.validator.format("请输入正确的身份证号码"));

	$("#register-form").validate({
		errorElement : 'span',
		errorClass : 'help-block',
		rules : {
			name : {
                required : true,
                minlength : 2
			},
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				isPwd : true
			},
			confirm_password : {
				required : true,
				isPwd : true,
				equalTo : "#password"
			},
			phone : {
				required : true,
				isPhone : true
			},
            isIdCardNo : {
                required : true,
                isIdCardNo : true
			},
			tel : {
				isTel : true
			},
			address : {
				minlength : 10
			}
		},
		messages : {
            name : "请输入姓名",
			email : {
				required : "请输入Email地址",
				email : "请输入正确的email地址"
			},
			password : {
				required : "请输入密码",
				minlength : jQuery.format("密码不能小于{0}个字 符")
			},
			confirm_password : {
				required : "请输入确认密码",
				minlength : "不能小于5个字符",
				equalTo : "两次输入密码不一致"
			},
			phone : {
				required : "请输入手机号码"
			},
			tel : {
				required : "请输入座机号码"
			},
			address : {
				required : "请输入家庭地址",
				minlength : jQuery.format("家庭地址不能少于{0}个字符")
			}
		},
		errorPlacement : function(error, element) {
			element.next().remove();
			element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
			element.closest('.form-group').append(error);
		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error has-feedback');
		},
		success : function(label) {
			var el=label.closest('.form-group').find("input");
			el.next().remove();
			el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
			label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
			label.remove();
		},
		submitHandler: function(form) { 
			alert("submitted!");
		}
	})
});