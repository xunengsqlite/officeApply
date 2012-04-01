package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.common.InputCheckUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Goods;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.GoodsManagerService;

@AuthorizerRequest(authorizerStep=10)
public class GoodsManagerAction extends ActionSupport {

	private GoodsManagerService goodsService;
//	private int id;
	private int goodsID;
	private String oldGoodsName;
	private String goodsName;
	private String goodsUnit;
	private int pageNum;
	private String qGoodsName;
	
	public GoodsManagerAction() throws DBPoolException, OfficeException{
		goodsService = new GoodsManagerService();
	}
	
//	@AuthorizerRequest
//	public String defGoodsList(HttpServletRequest request) throws DBPoolException, OfficeException{
//		HttpSession session = request.getSession(true);
//		session.setAttribute("pageNum_g", 0);
//		goodsService.goodsList4Page(request, id, 0);
//		return "/WEB-INF/jsp/goods/goodsManager/goodsPage.jsp";
//	}
	
	@AuthorizerRequest
	public String goodsNextList(HttpServletRequest request) throws DBPoolException, OfficeException{
		if(!InputCheckUtil.isPositiveNumber(pageNum + "")){
			pageNum = 1;
		}
		if(!InputCheckUtil.checkGoods(qGoodsName)){
			qGoodsName = "";
		}
		goodsService.goodsList4Page(request, this);
		
		return "/WEB-INF/jsp/goods/goodsManager/goodsPage.jsp";
	}
	
	public String delGoods(HttpServletRequest request) throws DBPoolException, OfficeException{
		boolean isOK = false;
		if(InputCheckUtil.isPositiveNumber(goodsID + "")){
			isOK = goodsService.delGoods(goodsID);
		}
		
		return redirectPage(request, isOK, "goods/manager/goodsNextList.action?pageNum=" + pageNum + "&qGoodsName=" + qGoodsName);
	}
	
	public String goodsUpdatePage(HttpServletRequest request) throws DBPoolException, OfficeException{
		if(InputCheckUtil.checkGoods(goodsName)){
			goodsName = InputCheckUtil.replaceTags(goodsName);
			goodsService.goodsUpdatePage(request, this);
		}
		return "/WEB-INF/jsp/goods/goodsManager/goodsupdate.jsp";
	}
	
	public String updateGoods(HttpServletRequest request) throws DBPoolException, OfficeException{
		boolean isOK = false;
		if(InputCheckUtil.checkGoods(goodsName) 
				&& InputCheckUtil.checkGoods(goodsUnit)
				&& InputCheckUtil.checkGoods(oldGoodsName)){
			
			Goods goods = new Goods(goodsID, InputCheckUtil.replaceTags(goodsName), InputCheckUtil.replaceTags(goodsUnit));
			isOK = goodsService.updateGoods(InputCheckUtil.replaceTags(oldGoodsName), goods);
		}
		return redirectPage(request, isOK, "goods/manager/goodsNextList.action?pageNum=" + pageNum + "&qGoodsName=" + qGoodsName);
	}
	
	public String goodsAdderPage(HttpServletRequest request){
		goodsService.goodsAdderPage(request, this);
		return "/WEB-INF/jsp/goods/goodsManager/goodsadd.jsp";
	}
	
	public String addGoods(HttpServletRequest request) throws DBPoolException, OfficeException{
		boolean isOK = false;
		if(InputCheckUtil.checkGoods(goodsName) && InputCheckUtil.checkGoods(goodsUnit)){
			Goods goods = new Goods(InputCheckUtil.replaceTags(goodsName), InputCheckUtil.replaceTags(goodsUnit));
			isOK = goodsService.addGoods(goods);
		}
		return redirectPage(request, isOK, "goods/manager/goodsAdderPage.action?pageNum=" + pageNum + "&qGoodsName=" + qGoodsName);
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setOldGoodsName(String oldGoodsName) {
		this.oldGoodsName = oldGoodsName;
	}
	public String getOldGoodsName() {
		return oldGoodsName;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setqGoodsName(String qGoodsName) {
		this.qGoodsName = qGoodsName;
	}

	public String getqGoodsName() {
		return qGoodsName;
	}
	
}
