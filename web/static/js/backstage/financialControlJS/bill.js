/**
 * 添加窗口
 */
function showAdd(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
//                $('#editId').val(row[0].id);
//                $('#editName').val(row[0].name);
//                $('#editPrice').val(row[0].price);
        $("#add").modal('show'); // 显示弹窗
        var chargeBill =  row[0];
        console.log(chargeBill.maintainRecord.checkin.company.companyTel + "公司电话")
        console.log(chargeBill.maintainRecord.checkin.company.companyAddress + "公司地址")
        document.getElementById('companyName').innerHTML=chargeBill.maintainRecord.checkin.company.companyName;

        document.getElementById('paymentMethod').innerHTML=chargeBill.chargeBillDes;

        document.getElementById('totalPrice').innerHTML=chargeBill.chargeBillMoney;

        document.getElementById('newestPrice').innerHTML=chargeBill.actualPayment;

        document.getElementById('newestPrice').innerHTML=chargeBill.actualPayment;

        document.getElementById('chargeBillDes').innerHTML=chargeBill.chargeBillDes;

        document.getElementById('companyTel').innerHTML=chargeBill.maintainRecord.checkin.company.companyTel;

        document.getElementById('companyAddress').innerHTML=chargeBill.maintainRecord.checkin.company.companyAddress;

        document.getElementById('chargeCreatedTime').innerHTML= formatterDate((chargeBill.chargeCreatedTime));


        validator("addForm");
    }else{
        swal({
            title:"",
            text:"请选择要查询的收费单据",
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText: "确定", // 提示按钮上的文本
            type: "warning"
        })
    }


}

//格式化不带时分秒的时间值。
function formatterDate(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    } else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day + ""
    }
}
