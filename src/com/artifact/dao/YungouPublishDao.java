package com.artifact.dao;

import java.util.List;
import com.artifact.model.YungouPublish;

public interface YungouPublishDao {
	public YungouPublish addYungouPublish(int goodsID, int codePeriod, int codeID, int codeRNO,
			int awardPosition, String userName, int buyCount, String buyStartPosition);// 添加云购揭晓记录

	public YungouPublish getYungouPublish(int goodsID, int codePeriod);// 根据商品id和期数获取单个云购揭晓信息

	public List<YungouPublish> getYungouPublishs(int goodsID, int selectNum);// 连续获取最近几期的的云购揭晓信息

	public void deleteYungouPublish(int goodsID, int codePeriod);// 删除云购记录
}
