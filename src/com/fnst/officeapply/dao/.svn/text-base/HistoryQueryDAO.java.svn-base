package com.fnst.officeapply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.HistoryRecord;
import com.fnst.officeapply.entity.HistoryRecordList;
import com.fnst.officeapply.exception.OfficeException;

public class HistoryQueryDAO extends BaseDAO {
	
	public HistoryQueryDAO() throws DBPoolException, OfficeException{
	}
	
	public String getNote(int historyId) throws DBPoolException, OfficeException{
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		String note = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select note from HistoryRecordList where HistoryRecordListID=?");
			state.setInt(1, historyId);
			rs = state.executeQuery();
			if (rs.next()) {
				note = rs.getString("note");
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return note;
	}
	
	public int getRecordCount(int historyId, String conditionName, String conditionVal) throws OfficeException, DBPoolException {

		PreparedStatement state = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select count(*) from HistoryRecord where " + conditionName + "=? and historyrecordlistid=?");
			state.setString(1, conditionVal);
			state.setInt(2, historyId);
			rs = state.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return count;
	}

	/**
	 * 显示历史记录详细
	 * 
	 * @param historyID
	 * @return list
	 * @throws OfficeException 
	 * @throws DBPoolException 
	 */
	public List<HistoryRecord> showHistoryRecord(int historyID, String orderBy) throws OfficeException, DBPoolException {

		List<HistoryRecord> list = new ArrayList<HistoryRecord>();
		PreparedStatement state = null;
		ResultSet rs = null;
		HistoryRecord hrd = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(SHOW_HISTORY_DETAIL + orderBy);
			state.setInt(1, historyID);
			rs = state.executeQuery();
			while (rs.next()) {
				hrd = new HistoryRecord(historyID, rs.getString("goodsName"), 
						rs.getString("userName"), rs.getInt("count"), rs.getString("userRealName"),rs.getString("groupName"));
				list.add(hrd);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}

		return list;

	}

	/**
	 * 分页显示历史记录列表
	 * @param pageNum
	 * @param count
	 * @return list
	 * @throws OfficeException 
	 * @throws DBPoolException 
	 */
	public List<HistoryRecordList> splitPage(int pageNum, int count, int groupID, String beginTime, String endTime) throws OfficeException, DBPoolException {

		List<HistoryRecordList> records = new ArrayList<HistoryRecordList>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select h.historyRecordListID,h.historyRecordDate,h.note,h.groupID,p.groupName " +
					"from HistoryRecordList h,ProjectGroup p where h.groupID=p.id and h.groupID=? and h.historyRecordDate between ? and ? limit ?,?;");
			state.setInt(1, groupID);
			state.setString(2, beginTime);
			state.setString(3, endTime);
			state.setInt(4, (pageNum - 1) * count);
			state.setInt(5, count);
			rs = state.executeQuery();
			while (rs.next()) {
				HistoryRecordList record = new HistoryRecordList();
				record.setHistoryRecordListID(rs.getInt("historyRecordListID"));
				record.setHistoryRecordDate(rs.getString("historyRecordDate"));
				record.setNote(rs.getString("note"));
				record.setGroupName(rs.getString("groupName"));
				record.setGroupID(groupID);
				records.add(record);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);

		}
		return records;
	}
	
	public boolean isHisRecListExist(int orgzID) throws OfficeException, DBPoolException{
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean flag = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select historyRecordListID from HistoryRecordList where groupID=? limit 0,1;");
			state.setInt(1, orgzID);
			rs = state.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);

		}
		return flag;
	}
	
	/**
	 * 获得某个项目组的列表的记录条数
	 * @return
	 * @throws DBPoolException 
	 * @throws OfficeException 
	 */
	public int getHistoryListCount(int groupID, String beginTime, String endTime) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select count(*) from historyRecordList where groupID=? and historyRecordDate between ? and ?;");
			state.setInt(1, groupID);
			state.setString(2, beginTime);
			state.setString(3, endTime);
			rs = state.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return count;
	}

	public boolean updateNote(String note, int historyID) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		Connection conn = null;
		boolean flag = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("update historyRecordList set note=? where historyRecordListID=?;");
			state.setString(1, note);
			state.setInt(2, historyID);
			int num = state.executeUpdate();
			if(num > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}

	public int getGroupID(int historyID) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		int groupID=0;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select groupID from historyRecordList where historyRecordListID=?;");
			state.setInt(1, historyID);
			rs = state.executeQuery();
			if(rs.next()) {
				groupID=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return groupID;
	}
	
}
