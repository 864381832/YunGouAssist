package com.artifact.dao;

import java.util.List;
import com.artifact.model.GoodsInfo;

public interface GoodsInfoDao {
	public GoodsInfo addGoodsInfo(int goodsID, String goodsSName, int codeID, int codeLimitBuy, String goodsPic,
			int isSale);// 添加商品信息

	public GoodsInfo getGoodsInfo(int goodsID);// 根据商品id获取商品信息

	public GoodsInfo getGoodsInfo(String goodsSName, String goodsPic);// 根据商品名和图片获取商品信息

	public GoodsInfo getGoodsInfo(String goodsPic);// 根据商品图片获取商品信息

	public List<GoodsInfo> getGoodsInfos();// 连续获取所有商品信息

	public List<GoodsInfo> getGoodsInfosBySale();// 连续获取上线商品信息

	public void setGoodsInfoIsSale(int goodsID, int isSale);// 设置商品上线情况

	public void deleteGoodsInfo(int goodsID);// 删除云购记录
}
