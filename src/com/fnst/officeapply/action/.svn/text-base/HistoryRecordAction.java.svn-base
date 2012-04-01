package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.InputCheckUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.HistoryRecordService;

@AuthorizerRequest
public class HistoryRecordAction extends ActionSupport {

	private HistoryRecordService historyService;
//	private int id;
	private int historyID;
	private String downloadPageName;
	private String note;
	private String beginTime;
	private String endTime;
	private int pageNum;
	public HistoryRecordAction() throws DBPoolException, OfficeException{
		historyService = new HistoryRecordService();
	}
	
//	public String defHistoryList(HttpServletRequest request) throws OfficeException, DBPoolException{
//		UserInfo yourself = this.getUserFSession(request);
//		int groupID = (yourself == null ? -1 : yourself.getGroupID());
//		HttpSession session = request.getSession(true);
//		session.setAttribute("pageNum_h", 0);
//		historyService.historyList4Page(request, id, 0, groupID);
//		return "/WEB-INF/jsp/history/historyRecordList.jsp";
//	}
	
	public String historyNextList(HttpServletRequest request) throws OfficeException, DBPoolException{
		if(!InputCheckUtil.checkQueryTime(beginTime)){
			beginTime = "0000-00-00" + " 00:00:00";
		}else{
			beginTime = beginTime + " 00:00:00";
		}
		if(!InputCheckUtil.checkQueryTime(endTime)){
			endTime = "9999-99-99" + " 99:99:99";
		}else{
			endTime = endTime + " 99:99:99";
		}
		if(!InputCheckUtil.isPositiveNumber(pageNum + "")){
			pageNum = 1;
		}
		UserInfo yourself = this.getUserFSession(request);
		int groupID = (yourself == null ? -1 : yourself.getGroupID());
		
		historyService.historyList4Page(request, this, groupID);
		
		return "/WEB-INF/jsp/history/historyRecordList.jsp";
	}
	
	public String historyRecordByUser(HttpServletRequest request) throws OfficeException, DBPoolException {
		UserInfo user = this.getUserFSession(request);
		if(InputCheckUtil.isPositiveNumber(historyID + "")){
			historyService.historyRecordByUser(request, user, this);
		}
		return "/WEB-INF/jsp/history/pHistoryRecord.jsp";
	}
	
	public String historyRecordByGoods(HttpServletRequest request) throws OfficeException, DBPoolException {
		UserInfo user = this.getUserFSession(request);
		if(InputCheckUtil.isPositiveNumber(historyID + "")){
			historyService.historyRecordByGoods(request, user, this);
		}
		return "/WEB-INF/jsp/history/gHistoryRecord.jsp";
	}

	@AuthorizerRequest(authorizerStep = 1)
	public String noteUpdatePage(HttpServletRequest request) throws DBPoolException, OfficeException{
		if(InputCheckUtil.isPositiveNumber(historyID + "")){
			UserInfo yourself = this.getUserFSession(request);
			int groupID = (yourself == null ? -1 : yourself.getGroupID());
			historyService.noteUpdatePage(request, this, groupID);
		}
		return "/WEB-INF/jsp/history/noteUpdate.jsp";
	}

	@AuthorizerRequest(authorizerStep = 1)
	public String updateNote(HttpServletRequest request) throws DBPoolException, OfficeException{
		boolean isOK = false;
		if(InputCheckUtil.isPositiveNumber(historyID + "") && InputCheckUtil.checkNote(note)){
			UserInfo yourself = this.getUserFSession(request);
			int groupID = (yourself == null ? -1 : yourself.getGroupID());
			isOK = historyService.updateNote(InputCheckUtil.replaceTags(note), historyID, groupID);
		}
		return redirectPage(request, isOK, "history/historyNextList.action?pageNum=" + pageNum +"&beginTime=" + beginTime + "&endTime=" + endTime);
	}
	
	public String download(HttpServletRequest request) throws OfficeException, DBPoolException {
		UserInfo user = this.getUserFSession(request);
		
		if(ComUtil.isEqual("pdownload.jsp", downloadPageName) && InputCheckUtil.isPositiveNumber(historyID + "")){
			historyService.historyRecordByUser(request, user, this);
		}else if(ComUtil.isEqual("gdownload.jsp", downloadPageName) && InputCheckUtil.isPositiveNumber(historyID + "")){
			historyService.historyRecordByGoods(request, user, this);
		}else{
			return null;
		}
		return "/WEB-INF/jsp/history/" + downloadPageName;
	}

	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}

	public int getHistoryID() {
		return historyID;
	}

	public void setDownloadPageName(String downloadPageName) {
		this.downloadPageName = downloadPageName;
	}

	public String getDownloadPageName() {
		return downloadPageName;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}
	
	
}
