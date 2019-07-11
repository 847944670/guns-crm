package com.stylefeng.guns.modular.crm.wrapper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class CrmCusrecordWarpper extends BaseControllerWarpper {

		public CrmCusrecordWarpper(List<Map<String, Object>> obj) {
			super(obj);
		}

		@Override
		protected void warpTheMap(Map<String, Object> map) {
			map.put("followType", ConstantFactory.me().getfollwoType((Integer) map.get("followType")));
			/*Integer.parseInt(String.valueOf(map.get("salerId")))*/
			map.put("salerId", ConstantFactory.me().getUserNameById(Integer.parseInt(String.valueOf(map.get("salerId")))));
		}
}

