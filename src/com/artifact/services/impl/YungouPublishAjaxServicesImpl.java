package com.artifact.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.artifact.dao.GoodsInfoDao;
import com.artifact.dao.YungouPublishDao;
import com.artifact.dao.impl.GoodsInfoDaoImpl;
import com.artifact.dao.impl.YungouPublishDaoImpl;
import com.artifact.model.GoodsInfo;
import com.artifact.model.YungouPublish;
import com.artifact.services.YungouPublishAjaxServices;
import com.artifact.yungou.CacheData;
import com.google.gson.Gson;

public class YungouPublishAjaxServicesImpl implements YungouPublishAjaxServices {
	static private YungouPublishAjaxServices yungouPublishAjaxServices;

	private YungouPublishDao yungouPublishDao = null;
	private GoodsInfoDao goodsInfoDao = null;

	public YungouPublishAjaxServicesImpl() {
		super();
		this.yungouPublishDao = new YungouPublishDaoImpl();
		this.goodsInfoDao = new GoodsInfoDaoImpl();
	}

	public static YungouPublishAjaxServices getYungouPublishAjaxServices() {
		if (yungouPublishAjaxServices == null) {
			yungouPublishAjaxServices = new YungouPublishAjaxServicesImpl();
		}
		return yungouPublishAjaxServices;
	}

	@Override
	public String addYungouPublishs(int goodsID, int codePeriod, int codeID, int codeRNO, int awardPosition,
			String userName, int buyCount, String buyStartPosition) {
		if (yungouPublishDao.getYungouPublish(goodsID, codePeriod) == null) {
			yungouPublishDao.addYungouPublish(goodsID, codePeriod, codeID, codeRNO, awardPosition, userName, buyCount,
					buyStartPosition);
			YungouPublish yungouPublish = new YungouPublish(goodsID, codePeriod, codeID, codeRNO, awardPosition,
					userName, buyCount, buyStartPosition);
			CacheData.setYungouPublishCacheDate(goodsID, codePeriod, yungouPublish);
		}
		return null;
	}

	@Override
	public String getYungouPublishs(int goodsID, int codePeriod, int selectNum) {
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		try {
			if (codePeriod == 0) {
				TreeMap<Integer, YungouPublish> yungouPublishCacheDateMap = CacheData
						.getYungouPublishCacheDate(goodsID);
				if (yungouPublishCacheDateMap == null || yungouPublishCacheDateMap.size() < selectNum) {
					List<YungouPublish> YungouPublishList = yungouPublishDao.getYungouPublishs(goodsID, selectNum);
					for (YungouPublish yungouPublish : YungouPublishList) {
						if (yungouPublishCacheDateMap.get(yungouPublish.getCodePeriod()) == null) {
							if (yungouPublish.getAwardPosition() > 0 && yungouPublish.getBuyCount() > 0) {
								stringList.add(yungouPublish.getYungouPublishInfo());
								CacheData.setYungouPublishCacheDate(goodsID, yungouPublish.getCodePeriod(),
										yungouPublish);
							} else {
								yungouPublishDao.deleteYungouPublish(goodsID, yungouPublish.getCodePeriod());
								CacheData.removeYungouPublishCacheDate(goodsID, yungouPublish.getCodePeriod());
							}
						} else {
							stringList.add(yungouPublish.getYungouPublishInfo());
						}
					}
				} else {
					int selectIndex = 0;
					for (YungouPublish yungouPublish : yungouPublishCacheDateMap.values()) {
						// System.out.println("数据" +
						// yungouPublish.getCodePeriod() + ":" +
						// yungouPublish.getCodeRNO());
						if (selectIndex < selectNum) {
							stringList.add(yungouPublish.getYungouPublishInfo());
							selectIndex++;
						}
					}
				}
			} else {
				YungouPublish yungouPublish = CacheData.getYungouPublishCacheDate(goodsID, codePeriod);
				if (yungouPublish == null) {
					TreeMap<Integer, YungouPublish> yungouPublishCacheDateMap = CacheData
							.getYungouPublishCacheDate(goodsID);
					if (yungouPublishCacheDateMap.lastKey() > codePeriod) {
						yungouPublish = yungouPublishDao.getYungouPublish(goodsID, codePeriod);
						if (yungouPublish != null) {
							CacheData.setYungouPublishCacheDate(goodsID, codePeriod, yungouPublish);
						}
					}
				}
				if (yungouPublish != null) {
					if (yungouPublish.getAwardPosition() > 0 && yungouPublish.getBuyCount() > 0) {
						stringList.add(yungouPublish.getYungouPublishInfo());
					} else {
						CacheData.removeYungouPublishCacheDate(goodsID, codePeriod);
						yungouPublishDao.deleteYungouPublish(goodsID, codePeriod);
					}
				}
			}
			String string = new Gson().toJson(stringList);
			return string;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void deleteYungouPublish(int goodsID, int codePeriod) {
		CacheData.removeYungouPublishCacheDate(goodsID, codePeriod);
		yungouPublishDao.deleteYungouPublish(goodsID, codePeriod);
	}

	@Override
	public void addGoodsInfo(int goodsID, String goodsSName, int codeID, int codeLimitBuy, String goodsPic,
			int isSale) {
		if (goodsInfoDao.getGoodsInfo(goodsID) == null) {
			goodsInfoDao.addGoodsInfo(goodsID, goodsSName, codeID, codeLimitBuy, goodsPic, isSale);
		}
	}

	@Override
	public void setGoodsInfoIsSale(int goodsID, int isSale) {
		goodsInfoDao.setGoodsInfoIsSale(goodsID, isSale);
	}

	@Override
	public List<GoodsInfo> getGoodsInfosBySale() {
		return goodsInfoDao.getGoodsInfosBySale();
	}

	@Override
	public void addGoodsInfo(String goodsInfo) {
		String[] info = goodsInfo.split("__");
		if (CacheData.getGoodsInfoDate(info[0]) == null) {
			if ("1".equals(info[5])) {
				CacheData.setGoodsInfoDate(info[0], new GoodsInfo(Integer.parseInt(info[0]), info[1],
						Integer.parseInt(info[2]), Integer.parseInt(info[3]), info[4], Integer.parseInt(info[5])));
			}
			addGoodsInfo(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]),
					info[4], Integer.parseInt(info[5]));
		} else {
			if ("0".equals(info[5])) {
				CacheData.removeGoodsInfoDate(info[0]);
			}
		}
	}

	@Override
	public String getGoodsInfos(int goodsID) {
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		try {
			if (goodsID == 0) {
				ConcurrentHashMap<String, GoodsInfo> goodsInfoMap = CacheData.getGoodsInfoDate();
				for (GoodsInfo goodsInfo : goodsInfoMap.values()) {
					stringList.add(goodsInfo.getGoodsInfo());
				}
			} else {
				GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfo(goodsID);
				if (goodsInfo != null) {
					stringList.add(goodsInfo.getGoodsInfo());
				}
			}
			return new Gson().toJson(stringList);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getYungouPublishsNotData(int goodsID, int codePeriod) {
		try {
			TreeMap<Integer, YungouPublish> yungouPublishCacheDateMap = CacheData.getYungouPublishCacheDate(goodsID);
			ArrayList<Integer> stringList = new ArrayList<Integer>();
			for (int i = codePeriod; i > codePeriod - 50 && i > 0; i--) {
				stringList.add(i);
			}
			if (yungouPublishCacheDateMap == null || yungouPublishCacheDateMap.size() < 50) {
				List<YungouPublish> YungouPublishList = yungouPublishDao.getYungouPublishs(goodsID, 50);
				for (YungouPublish yungouPublish : YungouPublishList) {
					stringList.remove(new Integer(yungouPublish.getCodePeriod()));
				}
			} else {
				for (YungouPublish yungouPublish : yungouPublishCacheDateMap.values()) {
					stringList.remove(new Integer(yungouPublish.getCodePeriod()));
				}
			}
			String string = new Gson().toJson(stringList);
			return string;
		} catch (Exception e) {
		}
		return null;
	}
}
