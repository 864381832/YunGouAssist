package com.artifact.yungou;

import java.util.List;
import com.artifact.model.GoodsInfo;
import com.artifact.services.impl.YungouPublishAjaxServicesImpl;

/*
 * 云购监控服务
 */
public class YungouMonitoringServer {
	public static void selectGoodsInfo() {// 查询商品信息
		List<GoodsInfo> goodsInfoList = YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices()
				.getGoodsInfosBySale();
		if (goodsInfoList != null) {
			for (GoodsInfo goodsInfo : goodsInfoList) {
				CacheData.setGoodsInfoDate("" + goodsInfo.getGoodsID(), goodsInfo);
			}
		}
		System.out.println("商品数据大小：" + CacheData.getGoodsInfoDate().size());
		// new Timer().schedule(new TimerTask() {
		// @Override
		// public void run() {
		// System.out.println("运行了");
		// GetGoodsInfo.getRecGoodsList(1);
		// }
		// }, 2000, 100000);
	}
}
