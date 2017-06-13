

//年的格式化
function formatterYear(value) {
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
        return year
    }


}

//年月的格式化
function formatterMonth(value) {
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
        return year + "-" + month
    }
}

//年月日的格式化
function formatterDay(value) {
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

//季度的格式化
function formatterQuarter(value) {
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
        if  (month === "01"|| month === "02" || month === "03") {
            month = "1月－3月 ";
        } else  if  (month === "04"|| month === "05" || month === "06") {
            month = "4月－6月  ";
        } else  if  (month === "07"|| month === "08" || month === "09") {
            month = "7月－9月  ";
        } else  if  (month === 10|| month === 11 || month === 12) {
            month = "10月－12月  ";
        }

        alert(year + month + "aa")

        return year + "-" + month
    }


}

//季度的格式化
function formatterQuarterDate(value) {

    console.log(value)
    var year = value.substr(0, 4)

    var month = value.substring(4 + 1);
    console.log(month)
    if  (month === "01月"|| month === "02月" || month === "03月") {
        month = "1月－3月 ";
    } else  if  (month === "04月"|| month === "05月" || month === "06月") {
        month = "4月－6月  ";
    } else  if  (month === "07月"|| month === "08月" || month === "09月") {
        month = "7月－9月  ";
    } else  if  (month === "10月"|| month === "11月" || month === "12月") {
        month = "10月－12月  ";
    }
    return year + "-" + month
}



//周期的格式化
function formatterWeek(value) {
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
        return year +  "第" + getYearWeek(year, month, day) + "周";
    }
}

/**
 * 判断年份是否为润年
 *
 * @param {Number} year
 */
function isLeapYear(year) {
    return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
}
/**
 * 获取某一年份的某一月份的天数
 *
 * @param {Number} year
 * @param {Number} month
 */
function getMonthDays(year, month) {
    return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (isLeapYear(year) ? 29 : 28);
}26 /**
 * 获取某年的某天是第几周
 * @param {Number} y
 * @param {Number} m
 * @param {Number} d
 * @returns {Number}
 */
function getWeekNumber(y, m, d) {
    var now = new Date(y, m - 1, d),
        year = now.getFullYear(),
        month = now.getMonth(),
        days = now.getDate();
    //那一天是那一年中的第多少天
    for (var i = 0; i < month; i++) {
        days += getMonthDays(year, i);
    }

    //那一年第一天是星期几
    var yearFirstDay = new Date(year, 0, 1).getDay() || 7;

    var week = null;
    if (yearFirstDay == 1) {
        week = Math.ceil(days / yearFirstDay);
    } else {
        days -= (7 - yearFirstDay + 1);
        week = Math.ceil(days / 7) + 1;
    }
    return week;
}


var getYearWeek = function (a, b, c) {
    var d1 = new Date(a, b-1, c), d2 = new Date(a, 0, 1),
        d = Math.round((d1 - d2) / 86400000);
    return Math.ceil((d + ((d2.getDay() + 1) - 1)) / 7);
};
