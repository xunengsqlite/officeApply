package com.fnst.officeapply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.CurrentRecord;
import com.fnst.officeapply.entity.Goods;
import com.fnst.officeapply.exception.OfficeException;
public class GoodsManagerDAO extends BaseDAO{

	public GoodsManagerDAO() throws DBPoolException, OfficeException{
	}
	
	/**
	 * 分页显示物品列表
	 */
	public List<Goods> splitPage(int pageNum, int count, String goodsName) throws OfficeException, DBPoolException {
		String sql = "select * from Goods where goodsName like ? limit ?,?;";
		List<Goods> glists = new ArrayList<Goods>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setString(1, "%" + goodsName + "%");
			state.setInt(2, (pageNum - 1) * count);
			state.setInt(3, count);
			
			rs = state.executeQuery();
			Goods goods = null;
			while (rs.next()) {
				goods = new Goods(rs.getInt("id"),rs.getString("goodsName"),rs.getString("goodsUnit"));
				glists.add(goods);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return glists;
	}
	
	/**
	 * 获得物品的总数量
	 */
	public int getCount(String goodsName) throws DBPoolException, OfficeException {
		
		int count = 0;
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select count(*) from goods where goodsName like ?;");
			state.setString(1, "%" + goodsName + "%");
			rs = state.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return count;
	}
	
	public boolean delGoods(int goodsID) throws DBPoolException, OfficeException{
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		GoodsApplyDAO applyDao = new GoodsApplyDAO();
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			if(applyDao.isCurrRecExisted(conn, goodsID, 1)){
				return false;
			}
			state = conn.prepareStatement("delete from goods where id =?;");
			state.setInt(1, goodsID);
			int count =state.executeUpdate();
			if(count>0)
				flag = true;
			else
				flag =false;
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}
	
	public Goods getGoods(String goodsName) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		Goods goods = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select * from goods where goodsName=?;");
			state.setString(1, goodsName);
			rs = state.executeQuery();
			if (rs.next()) {
				goods = new Goods(rs.getInt("id"),rs.getString("goodsName"),rs.getString("goodsUnit"));
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return goods;
	}
	
	public boolean addGoods(Goods goods) throws DBPoolException, OfficeException{
		if (getGoods(goods.getGoodsName()) != null){
			return false;
		}
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("insert into goods values(?,?,?);");
			state.setString(1, null);
			state.setString(2, goods.getGoodsName());
			state.setString(3, goods.getGoodsUnit());
			
			int count=state.executeUpdate();
			if(count>0)
				flag = true;
			else
				flag =false;
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return  flag;
	}
	
	public List<Goods> goodsList() throws DBPoolException, OfficeException {
		List<Goods> goodsList = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select * from Goods;");
			rs = state.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setGoodsUnit(rs.getString("goodsUnit"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return goodsList;
	}

	public boolean updateGoods(String oldGoodsName, Goods goods) throws DBPoolException, OfficeException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement state = null;
		String goodsName = goods.getGoodsName();
		
		if(!ComUtil.isEqual(oldGoodsName, goodsName)){
			if (getGoods(goodsName) != null){
				return false;
			}
		}
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("update goods set goodsName =?,goodsUnit =? where id=?");
			state.setString(1, goodsName);
			state.setString(2, goods.getGoodsUnit());
			state.setInt(3, goods.getId());
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

	public List<CurrentRecord> isGoodsExisted(List<CurrentRecord> crs) throws OfficeException, DBPoolException {
		List<CurrentRecord> rtCurrRecs = crs;
//		List<Integer> goodsIDs = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select goodsName from Goods where id=?");
			for (int i = rtCurrRecs.size() - 1; i >= 0; i--) {
				CurrentRecord cr = rtCurrRecs.get(i);
				state.setInt(1, cr.getGoodsID());
				rs = state.executeQuery();
				if (!rs.next()) {
					rtCurrRecs.remove(i);
				}
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return rtCurrRecs;
	}

}
