package com.fnst.officeapply.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.action.HistoryRecordAction;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dao.HistoryQueryDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.HistoryRecord;
import com.fnst.officeapply.entity.HistoryRecordList;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;

@AuthorizerRequest
public class HistoryRecordService {
	
	public static final int PAGE_SHOW_HISTORY = 16;
	
	private static HistoryQueryDAO historyDao;
	
	public HistoryRecordService() throws DBPoolException, OfficeException{
		synchronized (this) {
			if (historyDao == null) {
				historyDao = new HistoryQueryDAO();
			}
		}
	}
	
	public List<HistoryRecordList> historyList4Page(HttpServletRequest request, HistoryRecordAction action, int groupID) throws OfficeException, DBPoolException{
		String beginTime = action.getBeginTime();
		String endTime = action.getEndTime();
		int historyAmount = historyDao.getHistoryListCount(groupID, beginTime, endTime);
		int lastPageNum = ComUtil.getLastPageNum(historyAmount, PAGE_SHOW_HISTORY);
		int pageNum = action.getPageNum();
//		HttpSession session = request.getSession(true);
//		pageNum = ComUtil.getNextPageNum(pageNum, id, historyListCount, PAGE_SHOW_HISTORY);
		
		pageNum = ComUtil.checkPageNum(historyAmount, PAGE_SHOW_HISTORY, lastPageNum, pageNum);
		int serialNum = (pageNum - 1) * PAGE_SHOW_HISTORY;
		serialNum = (serialNum <= 0 ? 0 : serialNum);
		List<HistoryRecordList> historyList = historyDao.splitPage(pageNum, PAGE_SHOW_HISTORY, groupID, beginTime, endTime);
		request.setAttribute("historyList", historyList);
		request.setAttribute("serialNum", serialNum);
//		session.setAttribute("pageNum_h", pageNum);
		request.setAttribute("pageNum_h", pageNum);
		request.setAttribute("lastPageNum", lastPageNum);
		request.setAttribute("beginTime", beginTime.substring(0, 10));
		request.setAttribute("endTime", endTime.substring(0, 10));
		return historyList;
	}
	
	public List<String[]> historyRecordByUser(HttpServletRequest request,
			UserInfo user, HistoryRecordAction action) throws OfficeException, DBPoolException {
		int historyID = action.getHistoryID();
		List<String[]> records = historyRecordByUser(user, historyID);
		String note = historyDao.getNote(historyID);
		request.setAttribute("historyRecords", records);
		request.setAttribute("historyID", historyID);
		request.setAttribute("note", note);
		request.setAttribute("pageNum_h", action.getPageNum());
		request.setAttribute("beginTime", action.getBeginTime());
		request.setAttribute("endTime", action.getEndTime());
		return records;
	}

	public List<String[]> historyRecordByGoods(HttpServletRequest request,
			UserInfo user, HistoryRecordAction action) throws OfficeException, DBPoolException {
		int historyID = action.getHistoryID();
		List<String[]> records = historyRecordByGoods(user, historyID);
		String note = historyDao.getNote(historyID);
		request.setAttribute("historyRecords", records);
		request.setAttribute("historyID", historyID);
		request.setAttribute("note", note);
		request.setAttribute("pageNum_h", action.getPageNum());
		request.setAttribute("beginTime", action.getBeginTime());
		request.setAttribute("endTime", action.getEndTime());
		return records;
	}
	
	public void noteUpdatePage(HttpServletRequest request, HistoryRecordAction action, int myGroupID) throws DBPoolException, OfficeException {
		int historyID = action.getHistoryID();
		int groupID = historyDao.getGroupID(historyID);
		if(groupID != myGroupID){
			return;
		}
		String note = historyDao.getNote(historyID);
		request.setAttribute("note", note);
		request.setAttribute("historyID", historyID);
		request.setAttribute("pageNum_h", action.getPageNum());
		request.setAttribute("beginTime", action.getBeginTime());
		request.setAttribute("endTime", action.getEndTime());
	}
	
	public boolean updateNote(String note, int historyID, int myGroupID) throws DBPoolException, OfficeException {
		int groupID = historyDao.getGroupID(historyID);
		if(groupID != myGroupID){
			return false;
		}
		return historyDao.updateNote(note, historyID);
	}
	
	public List<String[]> historyRecordByUser(UserInfo user, int historyID) throws OfficeException, DBPoolException {
		int number = 0;
		String[] infos = null;
		StringBuilder sb = null;
		List<HistoryRecord> list = historyDao.showHistoryRecord(historyID, "a.userName");
		List<String[]> recordInfos= new ArrayList<String[]>();
		HistoryRecord record =null;
		for (int i = 0; i < list.size();) {
			sb = new StringBuilder();
			infos = new String[3];
			record = list.get(i);
			String userName = record.getUserName();
			String userRealName = record.getUserRealName();
			number = historyDao.getRecordCount(historyID, "userName", userName);
			infos[0] = userRealName;
			for (int j = 0; j < number; j++) {
				record = list.get(j+i);
				sb.append(record.getGoodsName() + ":" + record.getCount() + ",");
			}
			infos[1] = record.getGroupName();
			infos[2] = sb.substring(0, sb.length()-1);
			recordInfos.add(infos);
			i += number;
		}
		return recordInfos;
	}
	
	public List<String[]> historyRecordByGoods(UserInfo user,  int historyID) throws OfficeException, DBPoolException {
		int number = 0;
		List<HistoryRecord> list = historyDao.showHistoryRecord(historyID, "a.goodsName");
		List<String[]> recordInfos= new ArrayList<String[]>();
		HistoryRecord record =null;
		for (int i = 0; i < list.size();) {
			int count = 0;
			StringBuilder sb = new StringBuilder();
			String[] infos = new String[3];
			record = list.get(i);
			String goodsName = record.getGoodsName();
			number = historyDao.getRecordCount(historyID, "goodsName", goodsName);
			infos[0] = goodsName;
			for (int j = 0; j < number; j++) {
				record = list.get(j+i);
				String userRealName = record.getUserRealName();
				int goodsCount = record.getCount();
				sb.append(userRealName + ":" + goodsCount + ",");
				count += goodsCount;
			}
			infos[1] = "" + count;
			infos[2] = sb.substring(0, sb.length()-1);
			recordInfos.add(infos);
			i += number;
		}
		return recordInfos;
	}

	
}
