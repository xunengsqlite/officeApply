package com.fnst.officeapply.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.dao.GoodsApplyDAO;
import com.fnst.officeapply.dao.GoodsManagerDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.CurrentRecord;
import com.fnst.officeapply.entity.Goods;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class HomePageService {
	
	private static GoodsManagerDAO goodsDao;
	private static GoodsApplyDAO applyDao;

	public HomePageService() throws DBPoolException, OfficeException {
		synchronized (this) {
			if (goodsDao == null) {
				goodsDao = new GoodsManagerDAO();
			}
			if (applyDao == null) {
				applyDao = new GoodsApplyDAO();
			}
		}
	}
	
	public void homePage(HttpServletRequest request, UserInfo user) throws DBPoolException, OfficeException{
		
		String username = null;
		ArrayList<Goods> goods = (ArrayList<Goods>) goodsDao.goodsList();
		if(user != null){
			username = user.getUserName();
		}
		List<CurrentRecord> uglist = (ArrayList<CurrentRecord>) applyDao.getUserNameRecs(username);
		
		request.setAttribute("goods", goods);
		request.setAttribute("uglist", uglist);
	}
}
