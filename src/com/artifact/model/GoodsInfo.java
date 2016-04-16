package com.artifact.model;

/**
 * 商品信息
 */
public class GoodsInfo {
	private int id;
	private int goodsID;// 商品id
	private String goodsSName;// 商品名称
	private int codeID;// 商品首期id
	private int codeLimitBuy;// 限购次数
	private String goodsPic;// 商品图片地址
	private int isSale;// 是否下架

	public GoodsInfo(int goodsID, String goodsSName, int codeID, int codeLimitBuy, String goodsPic, int isSale) {
		super();
		this.goodsID = goodsID;
		this.goodsSName = goodsSName;
		this.codeID = codeID;
		this.codeLimitBuy = codeLimitBuy;
		this.goodsPic = goodsPic;
		this.isSale = isSale;
	}

	public GoodsInfo(int id, int goodsID, String goodsSName, int codeID, int codeLimitBuy, String goodsPic,
			int isSale) {
		super();
		this.id = id;
		this.goodsID = goodsID;
		this.goodsSName = goodsSName;
		this.codeID = codeID;
		this.codeLimitBuy = codeLimitBuy;
		this.goodsPic = goodsPic;
		this.isSale = isSale;
	}

	public GoodsInfo() {
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

	public String getGoodsSName() {
		return goodsSName;
	}

	public void setGoodsSName(String goodsSName) {
		this.goodsSName = goodsSName;
	}

	public int getCodeID() {
		return codeID;
	}

	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}

	public int getCodeLimitBuy() {
		return codeLimitBuy;
	}

	public void setCodeLimitBuy(int codeLimitBuy) {
		this.codeLimitBuy = codeLimitBuy;
	}

	public String getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}

	public int getIsSale() {
		return isSale;
	}

	public void setIsSale(int isSale) {
		this.isSale = isSale;
	}

	public String[] getGoodsInfo() {
		return new String[] { "" + goodsID, goodsSName, "" + codeID, "" + codeLimitBuy };
	}

}
