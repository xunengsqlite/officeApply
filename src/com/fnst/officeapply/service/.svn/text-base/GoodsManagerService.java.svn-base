package com.fnst.officeapply.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.action.GoodsManagerAction;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dao.GoodsManagerDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Goods;
import com.fnst.officeapply.exception.OfficeException;

public class GoodsManagerService {

	public static final int SINGLE_PAGE_GOODS_COUNT =16;
	private static GoodsManagerDAO goodsDao;
	
	public GoodsManagerService() throws DBPoolException, OfficeException{
		synchronized (this) {
			if (goodsDao == null) {
				goodsDao = new GoodsManagerDAO();
			}
		}
	}
	
	public List<Goods> goodsList4Page(HttpServletRequest request, GoodsManagerAction action) throws DBPoolException, OfficeException{
		int pageNum = action.getPageNum();
		String qGoodsName = action.getqGoodsName();
		int goodsAmount = goodsDao.getCount(qGoodsName);
		int lastPageNum = ComUtil.getLastPageNum(goodsAmount, SINGLE_PAGE_GOODS_COUNT);
		pageNum = ComUtil.checkPageNum(goodsAmount, SINGLE_PAGE_GOODS_COUNT, lastPageNum, pageNum);
		int serialNum = (pageNum - 1) * SINGLE_PAGE_GOODS_COUNT;
		serialNum = (serialNum <= 0 ? 0 : serialNum);
		List<Goods> goodslist = goodsDao.splitPage(pageNum, SINGLE_PAGE_GOODS_COUNT, qGoodsName);
		request.setAttribute("goodslist", goodslist);
		request.setAttribute("serialNum", serialNum);
		request.setAttribute("pageNum_g", pageNum);
		request.setAttribute("lastPageNum", lastPageNum);
		request.setAttribute("qGoodsName", qGoodsName);
		
		return goodslist;
	}

	public boolean delGoods(int goodsID) throws DBPoolException, OfficeException {
		return goodsDao.delGoods(goodsID);
	}

	public boolean addGoods(Goods goods) throws DBPoolException, OfficeException {
		return goodsDao.addGoods(goods);
	}

	public boolean updateGoods(String oldGoodsName, Goods goods)throws DBPoolException, OfficeException {
		return goodsDao.updateGoods(oldGoodsName, goods);
	}

	public void goodsUpdatePage(HttpServletRequest request, GoodsManagerAction action) throws DBPoolException, OfficeException {
		
		int pageNum = action.getPageNum();
		String goodsName = action.getGoodsName();
		String qGoodsName = action.getqGoodsName();
		
		Goods goods = goodsDao.getGoods(goodsName);
		request.setAttribute("pageNum_g", pageNum);
		request.setAttribute("qGoodsName", qGoodsName);
		request.setAttribute("goods", goods);
	}

	public void goodsAdderPage(HttpServletRequest request, GoodsManagerAction action) {
		int pageNum = action.getPageNum();
		String qGoodsName = action.getqGoodsName();
		request.setAttribute("pageNum_g", pageNum);
		request.setAttribute("qGoodsName", qGoodsName);
	}

}
