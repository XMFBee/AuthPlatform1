package com.gs.common.util;

import org.apache.shiro.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色验证
 */
public class RoleUtil {

    /**
     * 是否为系统管理员
     */
    public static boolean checkRoles(String roleName) {
        String[] rolesNames = roleName.split(",");
        List<String> roles= new ArrayList<String>();
        for(String r : rolesNames){
            roles.add(r);
        }
        boolean[] isRoles = SecurityUtils.getSubject().hasRoles(roles);
        for(boolean b: isRoles){
            if(b){
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为公司管理员
     */
//    public static boolean isCompanyAdmin() {
//        if(subject.hasRole(Constants.role_companyOrdinaryAdmin) || subject.hasRole(Constants.role_companySuperAdmin)){
//            return true;
//        }
//        return false;
//    }

}
