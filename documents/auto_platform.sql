DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `companyId` varchar(36) NOT NULL COMMENT '公司编号,UUID，主键',
  `companyName` varchar(100) NOT NULL COMMENT '公司名称,not null, unique',
  `companyAddress` varchar(150) NOT NULL COMMENT '公司地址,not null',
  `companyTel` varchar(11) NOT NULL COMMENT '公司联系方式, not null',
  `companyPricipal` varchar(20) DEFAULT NULL COMMENT '公司负责人',
  `companyPricipalphone` varchar(11) DEFAULT NULL COMMENT '公司负责人联系电话',
  `companyWebsite` varchar(100) DEFAULT NULL COMMENT '公司官网URL',
  `companyLogo` varchar(200) DEFAULT NULL COMMENT '公司logo图片，使用默认值',
  `companyOpenDate` date DEFAULT NULL COMMENT '公司成立时间',
  `companySize` varchar(50) DEFAULT NULL COMMENT '公司规模,页面直接选择，不需要输入',
  `companyLongitude` double DEFAULT NULL COMMENT '公司经度',
  `companyLatitude` double DEFAULT NULL COMMENT '公司纬度',
  `companyDes` varchar(500) DEFAULT NULL COMMENT '公司描述',
  `companyStatus` varchar(2) DEFAULT NULL COMMENT '公司状态，Y为可用，N为不可用',
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
用户表
*/
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` varchar(36) NOT NULL COMMENT '用户编号，UUID，主键',
  `userEmail` varchar(100) COMMENT '用户邮箱，可用于登录，unique',
  `userPhone` varchar(11) DEFAULT NULL COMMENT '用户手机号，可用于登录',
  `userPwd` varchar(100) NOT NULL COMMENT '用户登录密码，MD5加密,not null',
  `userNickname` varchar(20) DEFAULT NULL COMMENT '用户昵称，默认为用户邮箱',
  `userIdentity` varchar(18) DEFAULT NULL COMMENT '用户身份证号，车主选填，汽修公司和管理员必填',
  `userName` varchar(20) DEFAULT NULL COMMENT '用户真实姓名，车主选填，汽修公司和管理员必填',
  `userGender` varchar(2) DEFAULT NULL COMMENT '用户性别，M表示男，F表示女，N表示未选择',
  `userBirthday` datetime DEFAULT NULL COMMENT '用户生日，车主选填，汽修公司和管理员必填',
  `userAddress` varchar(150) DEFAULT NULL COMMENT '用户地址，车主选填，汽修公司和管理员必填',
  `qqOpenId` varchar(100) DEFAULT NULL COMMENT 'QQ open id',
  `weiboOpenId` varchar(100) DEFAULT NULL COMMENT '微博open id',
  `wechatOpenId` varchar(100) DEFAULT NULL COMMENT '微信open id',
  `userIcon` varchar(200) DEFAULT NULL COMMENT '用户头像，使用默认值',
  `userDes` varchar(500) DEFAULT NULL COMMENT '用户描述',
  `companyId` varchar(36) DEFAULT NULL COMMENT '用户所属公司，来源于t_company表，不需要设置外键，可为空',
  `userSalary` double DEFAULT NULL COMMENT '用户基本工资，汽修公司设置',
  `userCreatedTime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `userLoginedTime` datetime DEFAULT NULL COMMENT '用户最近一次登录时间',
  `userStatus` varchar(2) DEFAULT NULL COMMENT '用户状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_user` VALUES ('7ff4f1c5-3205-11e7-bc72-507b9d763421', '1@qq.com', '15570102341', '6khXbzC+FmmXFpnAmtBclA==', '星空', '360721199812014014', '张文星', '男', '1999-01-01 08:23:38', '赣州', '', '', '', 'static/img/a3.jpg', '系统超级管理员', '', null, '2017-05-17 08:25:04', null, 'Y');

/**
角色表
*/
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` varchar(36) NOT NULL COMMENT '角色编号，UUID，主键',
  `roleName` varchar(20) NOT NULL COMMENT '角色名称,not null, unique',
  `roleDes` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `roleStatus` varchar(2) DEFAULT NULL COMMENT '角色状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_role` VALUES ('7ff4f1c5-3205-11e7-bc72-507b9d765567', '系统超级管理员', '系统超级管理员', 'Y');
INSERT INTO `t_role` VALUES ('80095901-3205-11e7-bc72-507b9d765567', '系统普通管理员', '系统普通管理员', 'Y');
INSERT INTO `t_role` VALUES ('8010cecf-3205-11e7-bc72-507b9d765567', '公司超级管理员', '公司超级管理员', 'Y');
INSERT INTO `t_role` VALUES ('80195a53-3205-11e7-bc72-507b9d765567', '公司普通管理员', '公司普通管理员', 'Y');
INSERT INTO `t_role` VALUES ('8020abdc-3205-11e7-bc72-507b9d765567', '汽车公司接待员', '汽车公司接待员', 'Y');
INSERT INTO `t_role` VALUES ('802895f4-3205-11e7-bc72-507b9d765567', '汽车公司总技师', '汽车公司总技师', 'Y');
INSERT INTO `t_role` VALUES ('802fd749-3205-11e7-bc72-507b9d765567', '汽车公司技师', '汽车公司技师', 'Y');
INSERT INTO `t_role` VALUES ('80360abc-3205-11e7-bc72-507b9d765567', '汽车公司学徒', '汽车公司学徒', 'Y');
INSERT INTO `t_role` VALUES ('803f0384-3205-11e7-bc72-507b9d765567', '汽车公司销售人员', '汽车公司销售人员', 'Y');
INSERT INTO `t_role` VALUES ('804532c4-3205-11e7-bc72-507b9d765567', '汽车公司财务人员', '汽车公司财务人员', 'Y');
INSERT INTO `t_role` VALUES ('804cc69c-3205-11e7-bc72-507b9d765567', '汽车公司采购人员', '汽车公司采购人员', 'Y');
INSERT INTO `t_role` VALUES ('8052af73-3205-11e7-bc72-507b9d765567', '汽车公司库管人员', '汽车公司库管人员', 'Y');
INSERT INTO `t_role` VALUES ('805a2613-3205-11e7-bc72-507b9d765567', '汽车公司人力资源管理部', '汽车公司人力资源管理部', 'Y');
INSERT INTO `t_role` VALUES ('8067fa42-3205-11e7-bc72-507b9d765567', '车主', '车主', 'Y');

/**
模块表
*/
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `moduleId` varchar(36) NOT NULL COMMENT '模块编号，UUID,主键',
  `moduleName` varchar(20) NOT NULL COMMENT '模块名称，not null, unique',
  `moduleDes` varchar(500) DEFAULT NULL COMMENT '模块描述',
  `moduleStatus` varchar(2) DEFAULT NULL COMMENT '模块状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
权限表
*/
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `permissionId` varchar(36) NOT NULL COMMENT '权限编号，UUID,主键',
  `permissionName` varchar(30) NOT NULL COMMENT '权限名称，not null, unique',
  `permissionZHName` varchar(50) DEFAULT NULL COMMENT '权限中文名称',
  `permissionDes` varchar(500) DEFAULT NULL COMMENT '权限描述',
  `moduleId` varchar(36) DEFAULT NULL COMMENT '权限所属模块，来源于t_module表',
  `permissionStatus` varchar(2) DEFAULT NULL COMMENT '权限状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
用户角色表
*/
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `userRoleId` varchar(36) NOT NULL COMMENT '用户角色编号，UUID，主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
  `roleId` varchar(36) DEFAULT NULL COMMENT '角色编号，来源于t_role表',
  `urCreatedTime` datetime DEFAULT NULL COMMENT '用户角色分配时间',
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_user_role` VALUES ('7ff4f1c5-3205-11e7-bc72-507b9d123456', '7ff4f1c5-3205-11e7-bc72-507b9d763421', '7ff4f1c5-3205-11e7-bc72-507b9d765567', '2017-05-17 08:26:28');

/**
角色权限表
*/
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `rpId` varchar(36) NOT NULL COMMENT '权限角色编号，UUID,主键',
  `roleId` varchar(36) DEFAULT NULL COMMENT '角色编号，来源于t_role表',
  `permissionId` varchar(36) DEFAULT NULL COMMENT '权限编号，来源于t_permission表',
  `rpCreatedTime` datetime DEFAULT NULL COMMENT '权限角色分配时间',
  PRIMARY KEY (`rpId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
汽车品牌表
*/
DROP TABLE IF EXISTS `t_car_brand`;
CREATE TABLE `t_car_brand` (
  `brandId` varchar(36) NOT NULL COMMENT '品牌编号，UUID,主键',
  `brandName` varchar(20) NOT NULL COMMENT '品牌名称，not null,unique',
  `brandDes` varchar(500) DEFAULT NULL COMMENT '品牌描述',
  `brandStatus` varchar(2) DEFAULT NULL COMMENT '品牌状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_car_brand` VALUES ('da0cae95-3317-11e7-b181-f832e40312b3', '奥迪', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('dbfe486d-3317-11e7-b181-f832e40312b3', '克迪拉克', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('dd92ce7a-3317-11e7-b181-f832e40312b3', '玛莎拉蒂', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('df4451a2-3317-11e7-b181-f832e40312b3', '宝马', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('e16dbcbb-3317-11e7-b181-f832e40312b3', '本田', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('e4091843-3317-11e7-b181-f832e40312b3', '大众', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('e5868988-3317-11e7-b181-f832e40312b3', '奔驰', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('e742c5a4-3317-11e7-b181-f832e40312b3', '保时捷', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('e8c1cdbc-3317-11e7-b181-f832e40312b3', '别克', '全国限量版', 'Y');
INSERT INTO `t_car_brand` VALUES ('ea503e96-3317-11e7-b181-f832e40312b3', '哈弗', '全国限量版', 'Y');

/***
汽车颜色表
*/
DROP TABLE IF EXISTS `t_car_color`;
CREATE TABLE `t_car_color` (
  `colorId` varchar(36) NOT NULL COMMENT '颜色编号，UUID,主键',
  `colorName` varchar(20) NOT NULL COMMENT '颜色名称，not null, unique',
  `colorRGB` varchar(20) DEFAULT NULL COMMENT '颜色的RGB值，如255,255,255',
  `colorHex` varchar(10) DEFAULT NULL COMMENT '颜色的十六进制值，如#FFFFFF',
  `colorDes` varchar(500) DEFAULT NULL COMMENT '颜色描述',
  `colorStatus` varchar(2) DEFAULT NULL COMMENT '颜色状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`colorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_car_color` VALUES ('59022ceb-24dc-11e7-9b5f-507b9d765567', '紫色', '128,0,128', '#800080', '紫色', 'Y');
INSERT INTO `t_car_color` VALUES ('5b97fbd0-24dc-11e7-9b5f-507b9d765567', '绿色', '0,128,0', '#008000', '纯绿', 'Y');
INSERT INTO `t_car_color` VALUES ('5d5ba68a-24dc-11e7-9b5f-507b9d765567', '红色', '255,0,0', '＃FF0000', '纯红', 'Y');
INSERT INTO `t_car_color` VALUES ('5ef60a0b-24dc-11e7-9b5f-507b9d765567', '黑色', '0,0,0', '#000000', '纯黑', 'Y');
INSERT INTO `t_car_color` VALUES ('c318241d-3319-11e7-b181-f832e40312b3', '纯白', '255,255,255', '#ffffff', '白色', 'Y');
INSERT INTO `t_car_color` VALUES ('de88f347-3319-11e7-b181-f832e40312b3', '黄色', '242,255,0', '#f2ff00', '黄色', 'Y');

/**
汽车车型表
*/
DROP TABLE IF EXISTS `t_car_model`;
CREATE TABLE `t_car_model` (
  `modelId` varchar(36) NOT NULL COMMENT '车型编号，UUID,主键',
  `modelName` varchar(50) NOT NULL COMMENT '车型名称，unique, not null',
  `modelDes` varchar(500) DEFAULT NULL COMMENT '车型描述',
  `brandId` varchar(36) DEFAULT NULL COMMENT '车型品牌，来源于t_car_brand表',
  `modelStaus` varchar(2) DEFAULT NULL COMMENT '车型状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_car_model` VALUES ('2c639452-3318-11e7-b181-f832e40312b3', '奥迪A3', '2017款 Sportback 35 TFSI 时尚型', 'da0cae95-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('2e497723-3318-11e7-b181-f832e40312b3', '凯迪拉克ATS-L', '2017款 28T 豪华型', 'dbfe486d-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('2fd54aba-3318-11e7-b181-f832e40312b3', '	2017款 3.0T S', '总裁', 'dd92ce7a-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('31a30bbe-3318-11e7-b181-f832e40312b3', '宝马1系', '2017款 118i 时尚型', 'df4451a2-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3321f0d6-3318-11e7-b181-f832e40312b3', '本田XR-V', '2017款 1.5L LXi 手动经典版', 'e16dbcbb-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('34d228eb-3318-11e7-b181-f832e40312b3', '大众-桑塔纳', '2017款 浩纳 1.4L 手动风尚版', 'e4091843-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3697ba01-3318-11e7-b181-f832e40312b3', '奔驰C级', '2017款 改款 C 200 运动版', 'e5868988-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('38452e74-3318-11e7-b181-f832e40312b3', '保时捷911', '2017款 Carrera GTS 3.0T', 'e742c5a4-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3a2b60d8-3318-11e7-b181-f832e40312b3', '别克-VELITE 5', '2017款 都市增程型', 'e8c1cdbc-3317-11e7-b181-f832e40312b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3c8f048c-3318-11e7-b181-f832e40312b3', '哈弗H1', '2017款 红标 1.5L 手动标准型', 'ea503e96-3317-11e7-b181-f832e40312b3', 'Y');

/**
汽车车牌表
*/
DROP TABLE IF EXISTS `t_car_plate`;
CREATE TABLE `t_car_plate` (
  `plateId` varchar(36) NOT NULL COMMENT '车牌编号，UUID,主键',
  `plateName` varchar(10) NOT NULL COMMENT '车牌名称，not null, unqiue',
  `plateDes` varchar(500) DEFAULT NULL COMMENT '车牌描述',
  `plateStatus` varchar(2) DEFAULT NULL COMMENT '车牌状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`plateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_car_plate` VALUES ('39b6f81e-2a77-11e7-a039-f832e40312b3', '赣B', '赣州市', 'Y');
INSERT INTO `t_car_plate` VALUES ('6310142e-24ef-11e7-9b5f-507b9d765567', '赣A', '南昌市', 'Y');
INSERT INTO `t_car_plate` VALUES ('663c23e5-24ef-11e7-9b5f-507b9d765567', '赣C', '宜春市', 'Y');
INSERT INTO `t_car_plate` VALUES ('68612b83-24ef-11e7-9b5f-507b9d765567', '赣D', '吉安市', 'Y');
INSERT INTO `t_car_plate` VALUES ('aa9ce266-2570-11e7-a97a-f832e40312b3', '赣E', '上饶市', 'Y');
INSERT INTO `t_car_plate` VALUES ('c515f5d623e011e7a97af832e403acs3', '赣G', '九江市 ', 'Y');

/**
供应商分类表
*/
DROP TABLE IF EXISTS `t_supply_type`;
CREATE TABLE `t_supply_type` (
  `supplyTypeId` varchar(36) NOT NULL COMMENT '供应商分类编号，UUID,主键',
  `supplyTypeName` varchar(100) NOT NULL COMMENT '供应商分类名称，not null,unique',
  `supplyTypeDes` varchar(500) DEFAULT NULL COMMENT '供应商分类描述',
  `companyId` varchar(36) DEFAULT NULL COMMENT '供应商分类所属公司，来源于t_company表',
  `supplyTypeStatus` varchar(2) DEFAULT NULL COMMENT '供应商分类状态，Y表示可以，N表示不可用',
  PRIMARY KEY (`supplyTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
供应商表
*/
DROP TABLE IF EXISTS `t_supply`;
CREATE TABLE `t_supply` (
  `supplyId` varchar(36) NOT NULL COMMENT '供应商编号，UUID,主键',
  `supplyName` varchar(100) NOT NULL COMMENT '供应商名称，not null, unique',
  `supplyTel` varchar(11) NOT NULL COMMENT '供应商联系电话，not null',
  `supplyPricipal` varchar(20) DEFAULT NULL COMMENT '供应商负责人',
  `supplyAddress` varchar(150) NOT NULL COMMENT '供应商地址，not null',
  `supplyBank` varchar(30) DEFAULT NULL COMMENT '供应商开户银行全称',
  `supplyBankAccount` varchar(20) DEFAULT NULL COMMENT '供应商开户人姓名',
  `supplyBankNo` varchar(50) DEFAULT NULL COMMENT '供应商开户卡号',
  `supplyAlipay` varchar(100) DEFAULT NULL COMMENT '供应商支付宝',
  `supplyWechat` varchar(50) DEFAULT NULL COMMENT '供应商微信号',
  `supplyCreatedTime` datetime DEFAULT NULL COMMENT '供应商创建时间',
  `supplyTypeId` varchar(36) DEFAULT NULL COMMENT '供应商分类编号，来源于t_supply_type表',
  `companyId` varchar(36) DEFAULT NULL COMMENT '供应商所属公司，来源于t_company表',
  `supplyStatus` varchar(2) DEFAULT NULL COMMENT '供应商状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`supplyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
配件分类表
*/
DROP TABLE IF EXISTS `t_accessories_type`;
CREATE TABLE `t_accessories_type` (
  `accTypeId` varchar(36) NOT NULL COMMENT '配件分类编号，UUID,主键',
  `accTypeName` varchar(50) NOT NULL COMMENT '配件分类名称，not null, unique',
  `accTypeDes` varchar(500) DEFAULT NULL COMMENT '配件分类描述',
  `companyId` varchar(36) DEFAULT NULL COMMENT '配件分类所属公司，来源于t_company表',
  `accTypeStatus` varchar(2) DEFAULT NULL COMMENT '配件分类状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`accTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
配件表
*/
DROP TABLE IF EXISTS `t_accessories`;
CREATE TABLE `t_accessories` (
  `accId` varchar(36) NOT NULL COMMENT '配件编号，UUID,主键',
  `accName` varchar(100) NOT NULL COMMENT '配件名称，not null',
  `accCommodityCode` varchar(100) NOT NULL COMMENT '配件商品条码，not null',
  `accDes` varchar(500) DEFAULT NULL COMMENT '配件描述',
  `accPrice` double NOT NULL COMMENT '配件价格，not null',
  `accSalePrice` double NOT NULL COMMENT '配件售价，not null',
  `accUnit` varchar(10) DEFAULT NULL COMMENT '配件计量单位',
  `accTotal` int(11) NOT NULL COMMENT '配件数量, not null',
  `accIdle` int(11) NOT NULL COMMENT '配件可用数量, not null',
  `accUsedTime` datetime DEFAULT NULL COMMENT '配件的最近一次领料时间',
  `accBuyedTime` datetime DEFAULT NULL COMMENT '配件的最近一次购买时间',
  `supplyId` varchar(36) DEFAULT NULL COMMENT '配件供应商编号，来源于t_supply表',
  `accCreatedTime` datetime DEFAULT NULL COMMENT '配件创建时间',
  `accTypeId` varchar(36) DEFAULT NULL COMMENT '配件所属分类编号，来源于t_accessories_type表',
  `companyId` varchar(36) DEFAULT NULL COMMENT '配件所属公司，来源于t_company表',
  `accStatus` varchar(2) DEFAULT NULL COMMENT '配件状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`accId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
配件采购表
*/
DROP TABLE IF EXISTS `t_accessories_buy`;
CREATE TABLE t_accessories_buy
(
    accBuyId VARCHAR(36) PRIMARY KEY NOT NULL COMMENT '配件采购编号，UUID,主键',
    accId VARCHAR(36) COMMENT '配件编号，来源于t_accessories表。配件的采购分为两种方式，一种是新增配件采购，一种是已有配件采购。如果是新增配件采购，则需要在t_accessories表中新增记录，并把上accId设置成t_accessories表中新增的记录的accId；如果是已有配件采购，则不需要在t_accessories表中新增记录，而是直接选择某个配件',
    accUnit VARCHAR(10) COMMENT '配件计量单位',
    accBuyCount INT(11) NOT NULL COMMENT '配件购买数量，not null',
    accBuyPrice DOUBLE NOT NULL COMMENT '配件购买单价，not null',
    accBuyTotal DOUBLE NOT NULL COMMENT '配件购买总价，not null',
    accBuyDiscount DOUBLE NOT NULL COMMENT '配件购买折扣，not null, default 0。可选择折扣，也可选择减价',
    accBuyMoney DOUBLE NOT NULL COMMENT '配件购买最终价，not null',
    accBuyTime DATETIME NOT NULL COMMENT '配件购买时间，not null',
    accBuyCreatedTime DATETIME COMMENT '配件购买记录创建时间',
    companyId VARCHAR(36) COMMENT '配件购买记录所属公司，来源于t_company表',
    accTypeId VARCHAR(36),
    supplyId VARCHAR(36),
    accBuyStatus VARCHAR(2) COMMENT '配件购买记录状态，Y表示可用，N表示不可用'
);


/**
配件销售表
*/
DROP TABLE IF EXISTS `t_accessories_sale`;
CREATE TABLE `t_accessories_sale` (
  `accSaleId` varchar(36) NOT NULL COMMENT '配件销售编号',
  `accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
  `accSaledTime` datetime NOT NULL COMMENT '配件销售时间，not null',
  `accSaleCount` int(11) NOT NULL COMMENT '配件销售数量，not null',
  `accSalePrice` double NOT NULL COMMENT '配件销售单价，not null',
  `accSaleTotal` double NOT NULL COMMENT '配件销售总价，not null',
  `accSaleDiscount` double NOT NULL COMMENT '配件销售折扣，not null, default 0。可选择折扣，也可选择减价',
  `accSaleMoney` double NOT NULL COMMENT '配件销售最终价，not null',
  `accSaleCreatedTime` datetime DEFAULT NULL COMMENT '配件销售记录创建时间',
  `companyId` varchar(36) DEFAULT NULL COMMENT '配件销售记录所属公司，来源于t_company表',
  `accSaleStatus` varchar(2) DEFAULT NULL COMMENT '配件销售记录状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`accSaleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
维修保养项目表
*/
DROP TABLE IF EXISTS `t_maintain_fix`;
CREATE TABLE `t_maintain_fix` (
  `maintainId` varchar(36) NOT NULL COMMENT '维修保养项目编号，UUID，主键',
  `maintainName` varchar(50) NOT NULL COMMENT '维修保养项目名称，not null',
  `maintainHour` double NOT NULL COMMENT '维修保养项目工时,not null',
  `maintainMoney` double NOT NULL COMMENT '维修保养项目基础费用，not null',
  `maintainManHourFee` double NOT NULL COMMENT '维修保养项目工时费,not null',
  `maintainOrFix` varchar(20) DEFAULT NULL COMMENT '标识是保养还是维修',
  `maintainDes` varchar(500) DEFAULT NULL COMMENT '维修保养项目描述',
  `companyId` varchar(36) DEFAULT NULL COMMENT '维修保养项目所属公司，来源于t_company表',
  `maintainStatus` varchar(2) DEFAULT NULL COMMENT '维修保养项目状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`maintainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
维修保养项目配件关联表
*/
DROP TABLE IF EXISTS `t_maintain_fix_acc`;
CREATE TABLE `t_maintain_fix_acc` (
  `mainAccId` varchar(36) NOT NULL COMMENT '保养项目配件编号，UUID,主键',
  `maintainId` varchar(36) DEFAULT NULL COMMENT '保养项目编号，来源于t_maintain_fix表',
  `accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
  `accCount` int(11) DEFAULT NULL COMMENT '配件个数',
  PRIMARY KEY (`mainAccId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
预约表
*/
DROP TABLE IF EXISTS `t_appointment`;
CREATE TABLE `t_appointment` (
  `appointmentId` varchar(36) NOT NULL COMMENT '预约编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表，可为空，当为空时，表示非注册车主用户预约',
  `userName` varchar(100) DEFAULT NULL COMMENT '车主姓名',
  `userPhone` varchar(11) DEFAULT NULL COMMENT '车主电话',
  `brandId` varchar(36) DEFAULT NULL COMMENT '汽车品牌编号，可为空',
  `colorId` varchar(36) DEFAULT NULL COMMENT '汽车颜色编号，可为空',
  `modelId` varchar(36) DEFAULT NULL COMMENT '汽车车型编号，可为空',
  `plateId` varchar(36) NOT NULL COMMENT '汽车车牌编号，not null',
  `carPlate` varchar(20) NOT NULL COMMENT '汽车车牌，not null',
  `arriveTime` datetime NOT NULL COMMENT '预估到店时间，not null',
  `maintainOrFix` varchar(20) DEFAULT NULL COMMENT '标识是保养还是维修',
  `appCreatedTime` datetime NOT NULL COMMENT '预约记录创建时间，not null',
  `companyId` varchar(36) DEFAULT NULL COMMENT '汽修公司编号，来源于t_company表',
  `appoitmentStatus` varchar(2) DEFAULT NULL COMMENT '预约状态，Y表示可用，N表示不可用',
  `currentStatus` varchar(100) DEFAULT NULL COMMENT '标明此预约记录当前状态是什么',
  PRIMARY KEY (`appointmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
登记表
*/
DROP TABLE IF EXISTS `t_checkin`;
CREATE TABLE `t_checkin` (
  `checkinId` varchar(36) NOT NULL DEFAULT '' COMMENT '登记编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表，可为空，当为空时，表示非注册车主用户登记',
  `appointmentId` varchar(36) DEFAULT NULL COMMENT '预约编号，来源于t_appointment表，可为空，当为空时，表示未预约过',
  `userName` varchar(100) DEFAULT NULL COMMENT '车主姓名',
  `userPhone` varchar(11) DEFAULT NULL COMMENT '车主电话',
  `brandId` varchar(36) DEFAULT NULL COMMENT '汽车品牌编号',
  `colorId` varchar(36) DEFAULT NULL COMMENT '汽车颜色编号',
  `modelId` varchar(36) DEFAULT NULL COMMENT '汽车车型编号',
  `plateId` varchar(36) DEFAULT NULL COMMENT '汽车车牌编号',
  `carPlate` varchar(20) NOT NULL COMMENT '汽车车牌，not null',
  `arriveTime` datetime NOT NULL COMMENT '到店时间，not null',
  `carMileage` double DEFAULT NULL COMMENT '汽车行驶里程',
  `carThings` varchar(500) DEFAULT NULL COMMENT '车上物品描述',
  `intactDegrees` varchar(500) DEFAULT NULL COMMENT '汽车完好度描述',
  `userRequests` varchar(500) DEFAULT NULL COMMENT '用户要求描述',
  `ifClearCar` varchar(2) DEFAULT NULL COMMENT '是否洗车, Y或N',
  `nowOil` double DEFAULT NULL COMMENT '当前油量',
  `maintainOrFix` varchar(20) DEFAULT NULL COMMENT '标识是保养还是维修',
  `checkinCreatedTime` datetime DEFAULT NULL COMMENT '登记记录创建的时间',
  `companyId` varchar(36) DEFAULT NULL COMMENT '汽修公司编号，来源于t_company表',
  `checkinStatus` varchar(2) DEFAULT NULL COMMENT '登记记录状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`checkinId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
维修保养记录表
*/
DROP TABLE IF EXISTS `t_maintain_record`;
CREATE TABLE `t_maintain_record` (
   `recordId` varchar(36) NOT NULL COMMENT '维修保养记录编号，UUID,主键',
  `checkinId` varchar(36) DEFAULT NULL COMMENT '维修保养登记编号，来源于t_checkin表',
  `startTime` datetime DEFAULT NULL COMMENT '维修保养开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '维修保养预估结束时间',
  `actualEndTime` datetime DEFAULT NULL COMMENT '维修保养实际结束时间',
  `recordCreatedTime` datetime DEFAULT NULL COMMENT '维修保养记录创建时间',
  `pickupTime` datetime DEFAULT NULL COMMENT '维修保养结束车主提车时间',
  `recordDes` varchar(500) DEFAULT NULL COMMENT '维修保养记录描述',
  `recordStatus` varchar(2) DEFAULT NULL COMMENT '维修保养记录状态，Y表示可用，N表示不可用',
  `currentStatus` varchar(100) DEFAULT NULL COMMENT '标明此维修保养记录当前状态是什么',
  `ifConfirm` varchar(2) DEFAULT NULL COMMENT '是否用户签字, 进行维修保养',
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
维修保养明细表
*/
DROP TABLE IF EXISTS `t_maintain_detail`;
CREATE TABLE `t_maintain_detail` (
  `maintainDetailId` varchar(36) NOT NULL COMMENT '维修保养明细编号，UUID,主键',
  `maintainRecordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `maintainItemId` varchar(36) DEFAULT NULL COMMENT '维修保养项目编号，来源于t_maintain_fix表，可为空',
  `maintainDiscount` double DEFAULT NULL COMMENT '维修保养项目折扣，default 0,可选择折扣，也可选择减价',
  `mdCreatedTime` datetime DEFAULT NULL COMMENT '维修保养明细创建时间',
  `mdStatus` varchar(2) DEFAULT NULL COMMENT '维修保养明细状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`maintainDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
维修保养进度表
*/
DROP TABLE IF EXISTS `t_maintain_schedule`;
CREATE TABLE `t_maintain_schedule` (
 	`maintainScheduleId` varchar(36) NOT NULL COMMENT '维修保养进度编号，UUID,主键',
  `maintainRecordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `maintainScheduleDes` varchar(500) DEFAULT NULL COMMENT '维修保养进度描述',
  `msCreatedTime` datetime DEFAULT NULL COMMENT '维修保养进度创建时间',
  `msStatus` varchar(2) DEFAULT NULL COMMENT '维修保养进度状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`maintainScheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
物料清单表
*/
DROP TABLE IF EXISTS `t_material_list`;
CREATE TABLE `t_material_list` (
  `materialId` varchar(36) NOT NULL COMMENT '物料清单记录编号，UUID,主键',
  `maintainRecordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
  `materialCount` int(11) DEFAULT NULL COMMENT '物料数量',
  `materialCreatedTime` datetime DEFAULT NULL COMMENT '物料清单记录创建时间',
  `materialStatus` varchar(2) DEFAULT NULL COMMENT '物料清单状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`materialId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
工单信息表
*/
DROP TABLE IF EXISTS `t_work_info`;
CREATE TABLE `t_work_info` (
  `workId` varchar(36) NOT NULL COMMENT '工单编号，UUID,主键',
  `recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `userId` varchar(36) DEFAULT NULL COMMENT '工单指派的用户编号，来源于t_user表',
  `workAssignTime` datetime DEFAULT NULL COMMENT '工单指派时间',
  `workCreatedTime` datetime DEFAULT NULL COMMENT '工单创建时间',
  `workStatus` varchar(2) DEFAULT NULL COMMENT '当前状态,Y表示可用，N表示不可用',
  PRIMARY KEY (`workId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
领料信息表
*/
DROP TABLE IF EXISTS `t_material_use`;
CREATE TABLE `t_material_use` (
  `materialUseId` varchar(36) NOT NULL COMMENT '领料记录编号，UUID,主键',
  `matainRecordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
  `accCount` int(11) DEFAULT NULL COMMENT '领料数量',
  `muCreatedTime` datetime DEFAULT NULL COMMENT '领料记录创建时间',
  `muUseDate` datetime DEFAULT NULL COMMENT '领料时间',
  PRIMARY KEY (`materialUseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
退料信息表
*/
DROP TABLE IF EXISTS `t_material_return`;
CREATE TABLE `t_material_return` (
  `materialReturnId` varchar(36) NOT NULL COMMENT '退料记录编号，UUID,主键',
  `matainRecordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
  `accCount` int(11) DEFAULT NULL COMMENT '退料数量',
  `mrCreatedDate` datetime DEFAULT NULL COMMENT '退料记录创建时间',
  `mrReturnDate` datetime DEFAULT NULL COMMENT '退料时间',
  PRIMARY KEY (`materialReturnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
收费单据表
*/
DROP TABLE IF EXISTS `t_charge_bill`;
DROP TABLE IF EXISTS `t_charge_bill`;
CREATE TABLE `t_charge_bill` (
  `chargeBillId` varchar(36) NOT NULL COMMENT '收费单据编号，UUID,主键',
  `maintainRecordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `chargeBillMoney` double DEFAULT NULL COMMENT '收费总金额',
  `paymentMethod` varchar(20) DEFAULT NULL COMMENT '付款方式',
  `actualPayment` double DEFAULT NULL COMMENT '实付款',
  `chargeTime` datetime DEFAULT NULL COMMENT '收费时间',
  `chargeCreatedTime` datetime DEFAULT NULL COMMENT '收费单据创建时间',
  `chargeBillDes` varchar(500) DEFAULT NULL COMMENT '收费单据描述',
  `chargeBillStatus` varchar(2) DEFAULT NULL COMMENT '收费状态,Y表示可用，N表示不可用',
  `cdStatus` varchar(2) DEFAULT NULL COMMENT '用户是否确认状态， Y为确认， N为未确认',
  PRIMARY KEY (`chargeBillId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
保养提醒记录表
*/
DROP TABLE IF EXISTS `t_maintain_remind`;
CREATE TABLE `t_maintain_remind` (
  `remindId` varchar(36) NOT NULL COMMENT '保养提醒记录编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
  `lastMaintainTime` datetime DEFAULT NULL COMMENT '上次保养时间',
  `lastMaintainMileage` double DEFAULT NULL COMMENT '上次保养汽车行驶里程',
  `remindMsg` varchar(500) DEFAULT NULL COMMENT '保养提醒消息',
  `remindTime` datetime DEFAULT NULL COMMENT '保养提醒时间',
  `remindType` varchar(20) DEFAULT NULL COMMENT '保养提醒方式，如邮箱，手机短信。默认使用手机短信方式发送提醒',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
  `remindCreatedTime` datetime DEFAULT NULL COMMENT '保养提醒记录创建时间',
  PRIMARY KEY (`remindId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
短信发送记录表
*/
DROP TABLE IF EXISTS `t_message_send`;
CREATE TABLE `t_message_send` (
  `messageId` varchar(36) NOT NULL COMMENT '短信发送记录编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
  `sendMsg` varchar(500) DEFAULT NULL COMMENT '短信发送内容',
  `sendTime` datetime DEFAULT NULL COMMENT '短信发送时间',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
  `sendCreatedTime` datetime DEFAULT NULL COMMENT '短信发送记录创建时间',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
投诉表
*/
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint` (
  `complaintId` varchar(36) NOT NULL COMMENT '投诉编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
  `complaintContent` varchar(500) DEFAULT NULL COMMENT '投诉内容',
  `complaintCreatedTime` datetime DEFAULT NULL COMMENT '投诉时间',
  `complaintReply` varchar(500) DEFAULT NULL COMMENT '投诉回复内容',
  `complaintReplyTime` datetime DEFAULT NULL COMMENT '投诉回复时间',
  `complaintReplyUser` varchar(36) DEFAULT NULL COMMENT '投诉回复人，来源于t_user表',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
  PRIMARY KEY (`complaintId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
跟踪回访表
*/
DROP TABLE IF EXISTS `t_track_list`;
CREATE TABLE `t_track_list` (
  `trackId` varchar(36) NOT NULL COMMENT '跟踪回访编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
  `trackContent` varchar(500) DEFAULT NULL COMMENT '回访的问题',
  `serviceEvaluate` int(11) DEFAULT NULL COMMENT '本次服务评价,1-10分',
  `trackUser` varchar(36) DEFAULT NULL COMMENT '跟踪回访用户，来源于t_user表',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
  `trackCreatedTime` datetime DEFAULT NULL COMMENT '跟踪回访创建时间',
  PRIMARY KEY (`trackId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
支出类型表
*/
DROP TABLE IF EXISTS `t_outgoing_type`;
CREATE TABLE `t_outgoing_type` (
  `outTypeId` varchar(36) NOT NULL COMMENT '支出类型编号，UUID,主键',
  `outTypeName` varchar(20) DEFAULT NULL COMMENT '支出类型名称',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
   `outTypeStatus` varchar(2) DEFAULT NULL COMMENT '支出类型状态，Y表示可用，N表示不可用',
 `createTime` datetime DEFAULT NULL COMMENT '支出类型创建时间',
  PRIMARY KEY (`outTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
收入类型表
*/
DROP TABLE IF EXISTS `t_incoming_type`;
CREATE TABLE `t_incoming_type` (
  `inTypeId` varchar(36) NOT NULL COMMENT '收入类型编号，UUID,主键',
  `inTypeName` varchar(20) DEFAULT NULL COMMENT '收入类型名称',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
  `inTypeStatus` varchar(2) DEFAULT NULL COMMENT '收入类型状态，Y表示可用，N表示不可用',
  `createTime` datetime DEFAULT NULL COMMENT '收入类型创建时间',
  PRIMARY KEY (`inTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
收支记录表
*/
DROP TABLE IF EXISTS `t_incoming_outgoing`;
CREATE TABLE `t_incoming_outgoing` (
  `inOutId` varchar(36) NOT NULL COMMENT '收支记录编号，UUID，主键',
  `inTypeId` varchar(36) DEFAULT NULL COMMENT '收入类型编号，来源于t_incoming_type表',
  `outTypeId` varchar(36) DEFAULT NULL COMMENT '支出类型编号，来源于t_outgoing_type表',
  `inOutMoney` double DEFAULT NULL COMMENT '收支金额',
  `inOutCreatedUser` varchar(36) DEFAULT NULL COMMENT '收支记录创建人，来源于t_user表',
  `inOutCreatedTime` datetime DEFAULT NULL COMMENT '收支记录创建时间',
  `companyId` varchar(36) DEFAULT NULL COMMENT '标识来源于哪家公司',
  `inOutStatus` varchar(2) DEFAULT NULL COMMENT '收支记录状态，Y表示可用，N表示不可用',
  PRIMARY KEY (`inOutId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
工资发放表
*/
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary` (
  `salaryId` varchar(36) NOT NULL COMMENT '工资发放编号，UUID,主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
  `prizeSalary` double DEFAULT NULL COMMENT '奖金',
  `minusSalay` double DEFAULT NULL COMMENT '罚款',
  `totalSalary` double DEFAULT NULL COMMENT '总工资',
  `salaryDes` varchar(500) DEFAULT NULL COMMENT '工资发放描述',
  `salaryTime` datetime DEFAULT NULL COMMENT '工资发放时间',
  `salaryCreatedTime` datetime DEFAULT NULL COMMENT '工资发放创建时间',
  PRIMARY KEY (`salaryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
提醒表
*/
CREATE TABLE t_remind
(
    remindUser VARCHAR(36) NOT NULL,
    remindDes VARCHAR(100) NOT NULL,
    remindId VARCHAR(36) PRIMARY KEY NOT NULL
);