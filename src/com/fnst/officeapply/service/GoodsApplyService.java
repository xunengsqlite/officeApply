package com.fnst.officeapply.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.InputCheckUtil;
import com.fnst.officeapply.dao.GoodsApplyDAO;
import com.fnst.officeapply.dao.GoodsManagerDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.CurrentRecord;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class GoodsApplyService {

	private static GoodsApplyDAO applyDao;
	
	private static GoodsManagerDAO managerDao;
	
	public GoodsApplyService() throws DBPoolException, OfficeException{
		synchronized (this) {
			if (applyDao == null) {
				applyDao = new GoodsApplyDAO();
			}
			if (managerDao == null) {
				managerDao = new GoodsManagerDAO();
			}
		}
	}
	
	public boolean applyGoods(HttpServletRequest request, UserInfo user) throws DBPoolException, OfficeException{
		List<CurrentRecord> addCurrRecs = new ArrayList<CurrentRecord>();
		List<CurrentRecord> updateCurrRecs = new ArrayList<CurrentRecord>();
		List<CurrentRecord> delCurrRecs = new ArrayList<CurrentRecord>();
		String userName = user.getUserName();
		int userID = user.getUserID();
		List<CurrentRecord> currRecords = applyDao.getUserNameRecs(userName);
		int goodsCount = managerDao.getCount("");
		for (int i = 1; i < goodsCount+1; i++) {
			boolean f = false;
			int goodsID = Integer.parseInt((String)request.getParameter("goodsID" + i));
			int count = Integer.parseInt(request.getParameter("selectcount" + i));
			boolean isChanged = Boolean.parseBoolean((String)request.getParameter("isChanged" + i));
			if(!isChanged || count < 0 || count > 10){
				continue;
			}
			
			for (CurrentRecord cr : currRecords) {
				int GID = cr.getGoodsID();
				if (count == 0) {
					if (ComUtil.isEqual(goodsID, GID)) {
//						applyDao.delCurrRec(goodsID, userID);
						delCurrRecs.add(new CurrentRecord(goodsID, userID, count));
						f = true;
						break;
					}
				} else {
					if (ComUtil.isEqual(goodsID, GID)) {
//						applyDao.updateCurrRec(goodsID, userID, count);
						updateCurrRecs.add(new CurrentRecord(goodsID, userID, count));
						f = true;
						break;
					}
				}
			}
			if (f) {
				continue;
			}
			if (count != 0) {
//				applyDao.addCurrRec(goodsID, userID, count);
				addCurrRecs.add(new CurrentRecord(goodsID, userID, count));
			}
		}
		addCurrRecs = managerDao.isGoodsExisted(addCurrRecs);
		return applyDao.applyGoods(delCurrRecs, updateCurrRecs, addCurrRecs);
	}
	
	public List<String[]> currRecordByUser(HttpServletRequest request, UserInfo user) throws OfficeException, DBPoolException{
		List<String[]> records = currRecordByUser(user);
		request.setAttribute("currRecord", records);
		return records;
	}
	
	public List<String[]> currRecordByGoods(HttpServletRequest request, UserInfo user) throws OfficeException, DBPoolException{
		List<String[]> records = currRecordByGoods(user);
		request.setAttribute("currRecord", records);
		return records;
	}
	
	public boolean allowApply(HttpServletRequest request,String[]goodsNameList, String note, UserInfo user) throws DBPoolException, OfficeException{
		int groupID = (user == null ? -1 : user.getGroupID());
		if(groupID == -1 || goodsNameList == null || goodsNameList.length==0 || !InputCheckUtil.checkNote(note)){
			return false;	
		}
		return applyDao.enableApplyStep1(InputCheckUtil.replaceTags(note), groupID, goodsNameList);
	}
	
	public List<String[]> currRecordByUser(UserInfo user) throws OfficeException, DBPoolException {
		int number = 0;
		String groupName = user.getGroupName();
		List<CurrentRecord> list = applyDao.CurrRecByGroup(groupName, "u.userName");
		List<String[]> recordInfos= new ArrayList<String[]>();
		CurrentRecord record =null;
		for (int i = 0; i < list.size();) {
			StringBuilder sb = new StringBuilder();
			String[] infos = new String[3];
			record = list.get(i);
			String userName = record.getUserName();
			String userRealName = record.getUserRealName();
			number = applyDao.getRecordCount(groupName, "u.userName", userName);
			infos[0] = userRealName;
			infos[1] = record.getGroupName();
			for (int j = 0; j < number; j++) {
				record = list.get(j+i);
				sb.append(record.getGoodsName() + ":" + record.getCount() + ",");
			}
			infos[2] = sb.substring(0, sb.length()-1);
			recordInfos.add(infos);
			i += number;
		}
		return recordInfos;
	}
	
	public List<String[]> currRecordByGoods(UserInfo user) throws OfficeException, DBPoolException {
		int number = 0;
		String groupName = user.getGroupName();
		List<CurrentRecord> list = applyDao.CurrRecByGroup(groupName, "g.goodsName");
		List<String[]> recordInfos= new ArrayList<String[]>();
		CurrentRecord record =null;
		for (int i = 0; i < list.size();) {
			int count = 0;
			StringBuilder sb = new StringBuilder();
			String[] infos = new String[3];
			record = list.get(i);
			String goodsName = record.getGoodsName();
			number = applyDao.getRecordCount(groupName, "g.goodsName", goodsName);
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

	public boolean delOwnGoods(UserInfo user) throws OfficeException, DBPoolException {
		return applyDao.delOwnGoods(user);
	}
	
}
