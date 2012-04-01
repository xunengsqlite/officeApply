package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.GoodsApplyService;

@AuthorizerRequest
public class GoodsApplyAction extends ActionSupport {

	private String[] goodsNameList;
	private String note;
	private GoodsApplyService goodsService;
	
	public GoodsApplyAction() throws DBPoolException, OfficeException{
		goodsService = new GoodsApplyService();
	}
	
	public String applyGoods(HttpServletRequest request) throws DBPoolException, OfficeException{
		UserInfo user = getUserFSession(request);
		boolean isOK = goodsService.applyGoods(request, user);
		return redirectPage(request,isOK, "currentRecord/currRecordByUser.action");
	}
	
	public String currRecordByUser(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo user = getUserFSession(request);
		goodsService.currRecordByUser(request, user);
		
		return "/WEB-INF/jsp/goods/pcurrRecord.jsp";
	}
	
	public String currRecordByGoods(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo user = getUserFSession(request);
		goodsService.currRecordByGoods(request, user);
		return "/WEB-INF/jsp/goods/gcurrRecord.jsp";
	}
	
	public String enableApplyPage(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo user = getUserFSession(request);
		goodsService.currRecordByGoods(request, user);
		
		return "/WEB-INF/jsp/goods/enableApply.jsp";
	}
	
	@AuthorizerRequest(authorizerStep=1)
	public String allowApply(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo user = getUserFSession(request);
		boolean isOK = goodsService.allowApply(request, goodsNameList, note, user);
		return redirectPage(request, isOK, "currentRecord/enableApplyPage.action");
	}

	public String delOwnGoods(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo user = getUserFSession(request);
		boolean isOK = goodsService.delOwnGoods(user);
		return redirectPage(request,isOK, "homePage.action");
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setGoodsNameList(String[] goodsNameList) {
		this.goodsNameList = goodsNameList;
	}

	public String[] getGoodsNameList() {
		return goodsNameList;
	}
}
