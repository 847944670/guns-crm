package com.stylefeng.guns.modular.crm.wrapper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class CrmCustomerWarpper extends BaseControllerWarpper {

		public CrmCustomerWarpper(List<Map<String, Object>> obj) {
			super(obj);
		}

		@Override
		protected void warpTheMap(Map<String, Object> map) {
			map.put("customerHy", ConstantFactory.me().getKhhangy((Integer) map.get("customerHy")));
			//map.put("customerStar", ConstantFactory.me().getkhStar((Integer) map.get("customerStar")));
			map.put("customerState", ConstantFactory.me().getkhstare((Integer) map.get("customerState")));
		}
}

