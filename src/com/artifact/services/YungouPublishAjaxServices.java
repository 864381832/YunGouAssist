package com.artifact.services;

import java.util.List;

import com.artifact.model.GoodsInfo;

public interface YungouPublishAjaxServices {
	public String addYungouPublishs(int goodsID, int codePeriod, int codeID, int codeRNO, int awardPosition,
			String userName, int buyCount, String buyStartPosition);// 获取云购揭晓

	public String getYungouPublishs(int goodsID, int codePeriod, int selectNum);// 获取云购揭晓

	public void deleteYungouPublish(int goodsID, int codePeriod);// 删除云购记录

	public String getYungouPublishsNotData(int goodsID, int codePeriod);// 获取未查询到的数据

	public void addGoodsInfo(int goodsID, String goodsSName, int codeID, int codeLimitBuy, String goodsPic, int isSale);// 添加商品信息

	public void addGoodsInfo(String goodsInfo);// 添加商品信息

	public void setGoodsInfoIsSale(int goodsID, int isSale);// 设置商品上线情况

	public List<GoodsInfo> getGoodsInfosBySale();// 获取上线商品信息

	public String getGoodsInfos(int goodsID);// 获取商品信息
}
