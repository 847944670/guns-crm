package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class CustomerSeasWarpper extends BaseControllerWarpper {

    public CustomerSeasWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("customerHy", ConstantFactory.me().getDictsByName("客户行业", (Integer) map.get("customerHy")));
        map.put("customerStar", ConstantFactory.me().getDictsByName("重要程度", (Integer) map.get("customerStar")));
        map.put("customerState", ConstantFactory.me().getDictsByName("销售阶段", (Integer) map.get("customerState")));
//        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
//        map.put("deptName", ConstantFactory.me().getDeptName((Integer) map.get("deptid")));
//        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }

}
