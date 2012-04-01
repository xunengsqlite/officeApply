package com.fnst.officeapply.entity;

//该类与数据库中Goods数据表对应
public class Goods {

	private int id;
	private String goodsName;     //办公用品名
	private String goodsUnit;     //办公用品单位
	
	
    public Goods() {
	}

	public Goods(String goodsName, String goodsUnit) {
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
	}
	
	public Goods(int id, String goodsName, String goodsUnit) {
		this.id = id;
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
	}
	
	//以下是对上述信息的get与set方法
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
}
