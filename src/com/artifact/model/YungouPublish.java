package com.artifact.model;

/*
 * 云购揭晓记录
 */
public class YungouPublish {
	private int id;
	private int goodsID;// 商品id
	private int codePeriod;// 期数
	private int codeID;// 云购id
	private int codeRNO;// 幸运云购码
	private int awardPosition;// 中奖位置
	private String userName;// 中奖人姓名
	private int buyCount;// 中奖人本次购买人次
	private String buyStartPosition;// 购买起始位置

	public YungouPublish(int goodsID, int codePeriod, int codeID, int codeRNO, int awardPosition, String userName,
			int buyCount, String buyStartPosition) {
		super();
		this.goodsID = goodsID;
		this.codePeriod = codePeriod;
		this.codeID = codeID;
		this.codeRNO = codeRNO;
		this.awardPosition = awardPosition;
		this.userName = userName;
		this.buyCount = buyCount;
		this.buyStartPosition = buyStartPosition;
	}

	public YungouPublish(int id, int goodsID, int codePeriod, int codeID, int codeRNO, int awardPosition,
			String userName, int buyCount, String buyStartPosition) {
		super();
		this.id = id;
		this.goodsID = goodsID;
		this.codePeriod = codePeriod;
		this.codeID = codeID;
		this.codeRNO = codeRNO;
		this.awardPosition = awardPosition;
		this.userName = userName;
		this.buyCount = buyCount;
		this.buyStartPosition = buyStartPosition;
	}

	public YungouPublish() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	public int getCodePeriod() {
		return codePeriod;
	}

	public void setCodePeriod(int codePeriod) {
		this.codePeriod = codePeriod;
	}

	public int getCodeID() {
		return codeID;
	}

	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}

	public int getCodeRNO() {
		return codeRNO;
	}

	public void setCodeRNO(int codeRNO) {
		this.codeRNO = codeRNO;
	}

	public int getAwardPosition() {
		return awardPosition;
	}

	public void setAwardPosition(int awardPosition) {
		this.awardPosition = awardPosition;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public String getBuyStartPosition() {
		return buyStartPosition;
	}

	public void setBuyStartPosition(String buyStartPosition) {
		this.buyStartPosition = buyStartPosition;
	}

	public String[] getYungouPublishInfo() {
		return new String[] { "" + goodsID, "" + codePeriod, "" + codeRNO, userName, "" + buyCount, "" + awardPosition,
				buyStartPosition, "" + codeID };
	}

}
