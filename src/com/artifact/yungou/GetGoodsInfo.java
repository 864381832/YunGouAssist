package com.artifact.yungou;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.CloseableHttpClient;
import com.artifact.model.GoodsInfo;
import com.artifact.services.impl.YungouPublishAjaxServicesImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GetGoodsInfo {

	/*
	 * 查询商品网址id goodsID商品id period期数
	 */
	public static String getGoodsPeriodInfo(String goodsID) {
		StringBuffer getUrl = new StringBuffer();
		getUrl.append("http://api.1yyg.com/JPData?action=getGoodsPeriodInfo&goodsID=").append(goodsID);
		getUrl.append("&period=1");
		String refererUrl = "http://www.1yyg.com/";
		// 返回值{"code":0,"codeID":2654637,"codeState":3}
		// code0正常 codeState-1未开始 1正在进行 2等待开奖 3已经开奖
		String content = HttpGetUtil.getHttpData(getUrl.toString(), refererUrl);
		try {
			Map<String, String> contentMap = new Gson().fromJson(content.substring(1, content.length() - 1),
					new TypeToken<Map<String, String>>() {
					}.getType());
			if (contentMap.get("code").equals("0")) {
				return contentMap.get("codeID");
			} else {
				return getGoodsPeriodInfo(goodsID);
			}
		} catch (Exception e) {
			return getGoodsPeriodInfo(goodsID);
		}
	}

	/*
	 * 获取本期剩余数量 return String{codeID,期数,已购买数,总价,剩余数}
	 */
	public static String[] shopCartNew(String codeID, CloseableHttpClient HttpClient) {
		StringBuffer getUrl = new StringBuffer();
		getUrl.append("http://cart.1yyg.com/JPData?action=shopCartNew&codeID=").append(codeID);
		getUrl.append("&num=1");
		String refererUrl = "http://www.1yyg.com/";
		// String refererUrl = "http://www.1yyg.com/products/"+goodsID+".html";
		// 返回值{"code":1,"num":0,"str":"2710463|26214|5|5188|5183|0"}
		String content = HttpGetUtil.getHttpData(getUrl.toString(), refererUrl, HttpClient);
		try {
			content = content.substring(content.indexOf("str") + 6, content.length() - 3);
			return content.split("\\|");
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * 查询所有产品
	 */
	public static void getRecGoodsList(int page) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		// 获取购买信息
		// 返回值({"Code":0,"Count":,"Data":{"Tables":{"Table1":{"Rows":[{"rowID":0,"goodsID":,"goodsSName":"","goodsPic":"","codeID":,"codePrice":"","codeQuantity":,"codeSales":,"codePeriod":,"codeType":,"goodsTag":"","codeLimitBuy":""}]}}}})
		String url = "http://api.1yyg.com/JPData?action=getGoodsList&sortID=0&brandID=0&orderFlag=10&FIdx=" + page
				+ "&EIdx=" + (page + 39) + "&isCount=1";
		String refererUrl = "http://www.1yyg.com/";
		String content = HttpGetUtil.getHttpData(url, refererUrl);
		// System.out.println(content);
		try {
			String count = content.substring(content.indexOf("Count\":") + 7, content.indexOf(",\"Data\":"));
			System.out.println(count);
			String data = content.substring(content.indexOf(":{\"Rows\":[") + 9, content.length() - 5);
			ArrayList<Map<String, String>> goodsList = new Gson().fromJson(data,
					new TypeToken<List<Map<String, String>>>() {
					}.getType());
			for (Map<String, String> goodsInfo : goodsList) {
				if (CacheData.getGoodsInfoDate(goodsInfo.get("goodsID")) == null) {
					int codeID = Integer.parseInt(getGoodsPeriodInfo(goodsInfo.get("goodsID")));
					CacheData.setGoodsInfoDate(goodsInfo.get("goodsID"),
							new GoodsInfo(Integer.parseInt(goodsInfo.get("goodsID")), goodsInfo.get("goodsSName"),
									codeID, Integer.parseInt(goodsInfo.get("codeLimitBuy")), goodsInfo.get("goodsPic"),
									1));
					YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices().addGoodsInfo(
							Integer.parseInt(goodsInfo.get("goodsID")), goodsInfo.get("goodsSName"), codeID,
							Integer.parseInt(goodsInfo.get("codeLimitBuy")), goodsInfo.get("goodsPic"), 1);
					// System.out.println(CacheData.getGoodsInfoDate(goodsInfo.get("goodsID")).toString());
				}
			}
			if (page < Integer.parseInt(count)) {
				getRecGoodsList(page + 40);
			}
		} catch (Exception e) {
		}
	}

}
