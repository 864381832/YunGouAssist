package com.artifact.yungou;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.artifact.model.GoodsInfo;
import com.artifact.model.YungouPublish;

/*
 * 缓存数据
 */
public class CacheData {
	// 缓存中奖信息
	private static ConcurrentHashMap<Integer, TreeMap<Integer, YungouPublish>> yungouPublishCacheDate = new ConcurrentHashMap<Integer, TreeMap<Integer, YungouPublish>>();
	// 缓存商品信息
	private static ConcurrentHashMap<String, GoodsInfo> goodsInfoDate = new ConcurrentHashMap<String, GoodsInfo>();

	public static YungouPublish getYungouPublishCacheDate(int goodsID, int codePeriod) {
		TreeMap<Integer, YungouPublish> yungouPublishCacheDateMap = yungouPublishCacheDate.get(goodsID);
		if (yungouPublishCacheDateMap != null) {
			return yungouPublishCacheDateMap.get(codePeriod);
		}
		return null;
	}

	public static TreeMap<Integer, YungouPublish> getYungouPublishCacheDate(int goodsID) {
		return yungouPublishCacheDate.get(goodsID);
	}

	public static void setYungouPublishCacheDate(int goodsID, int codePeriod, YungouPublish yungouPublish) {
		TreeMap<Integer, YungouPublish> yungouPublishCacheDateMap = yungouPublishCacheDate.get(goodsID);
		if (yungouPublishCacheDateMap == null) {
			TreeMap<Integer, YungouPublish> periodHashMap = new TreeMap<Integer, YungouPublish>(
					new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2 - o1;
						}
					});
			periodHashMap.put(codePeriod, yungouPublish);
			yungouPublishCacheDate.put(goodsID, periodHashMap);
		} else {
			if (yungouPublishCacheDateMap.get(codePeriod) == null) {
				yungouPublishCacheDateMap.put(codePeriod, yungouPublish);
				if (yungouPublishCacheDateMap.size() > 50) {
					int selectIndex = 0;
					Set<Integer> IntSet = new TreeMap<>(yungouPublishCacheDateMap).keySet();
					for (Integer periodIndex : IntSet) {
						// System.out.println("数据" + periodIndex + ":"
						// +
						// yungouPublishCacheDate.get(goodsID).get(periodIndex).getCodeRNO());
						if (selectIndex >= 50) {
							yungouPublishCacheDateMap.remove(periodIndex);
						}
						selectIndex++;
					}
				}
			}
		}
	}

	public static void removeYungouPublishCacheDate(int goodsID, int codePeriod) {
		TreeMap<Integer, YungouPublish> yungouPublishCacheDateMap = yungouPublishCacheDate.get(goodsID);
		if (yungouPublishCacheDateMap != null) {
			yungouPublishCacheDateMap.remove(codePeriod);
		}
	}

	public static ConcurrentHashMap<String, GoodsInfo> getGoodsInfoDate() {
		return goodsInfoDate;
	}

	public static void setGoodsInfoDate(String goodsID, GoodsInfo goodsInfo) {
		CacheData.goodsInfoDate.put(goodsID, goodsInfo);
	}

	public static void removeGoodsInfoDate(String goodsID) {
		CacheData.goodsInfoDate.remove(goodsID);
	}

	public static GoodsInfo getGoodsInfoDate(String goodsID) {
		return goodsInfoDate.get(goodsID);
	}

}
