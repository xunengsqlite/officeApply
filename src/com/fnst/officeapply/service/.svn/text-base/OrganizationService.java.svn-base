package com.fnst.officeapply.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dao.OrganizationDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Organization;
import com.fnst.officeapply.exception.OfficeException;

public class OrganizationService {
	
	public static final int PAGE_SHOW_DEFNUM = 10;
	public static final int PAGE_SHOW_DEPNUM = 10;
	public static final int PAGE_SHOW_SECNUM = 10;
	public static final int PAGE_SHOW_PROJNUM = 10;
	
	private static OrganizationDAO dao;
	
	public OrganizationService() throws DBPoolException, OfficeException{
		synchronized (this) {
			if(dao == null){
				dao = new OrganizationDAO();
			}
		}
	}
	
	public List<Organization> depList4Page(HttpServletRequest request, int id, int pageNum, int type) throws OfficeException, DBPoolException{
		
		int pageShowNum = getPageShowNum(type);
		int count = dao.getCount(type);
		
		HttpSession session = request.getSession(true);
		pageNum = ComUtil.getNextPageNum(pageNum, id, count, pageShowNum);
		int serialNum = (pageNum - 1) * pageShowNum;
		serialNum = (serialNum <= 0 ? 0 : serialNum);
		
		List<Organization> orgzs = dao.splitPage(pageNum, pageShowNum, type);
		request.setAttribute("orgzs", orgzs);
		request.setAttribute("serialNum", serialNum);
		session.setAttribute("pageNum_orgz", pageNum);
		return orgzs;
	}
	
	public boolean add(String orgzName, int type) throws OfficeException, DBPoolException {
		return dao.add(orgzName, type);
	}

	public void updatePage(HttpServletRequest request, int orgzID, int type) throws DBPoolException, OfficeException {
		Organization orgz = dao.getOrgz(orgzID, type);
		request.setAttribute("orgz", orgz);
	}

	public boolean update(String oldOrgzName, Organization orgz, int type) throws DBPoolException, OfficeException {
		return dao.update(oldOrgzName, orgz, type);
	}
	
	public boolean delete(int orgzID, int type) throws DBPoolException, OfficeException{
		return dao.delete(orgzID, type);
	}
	
	private int getPageShowNum(int type) {
		int count;
		switch (type) {
		case 0:
			count = PAGE_SHOW_DEPNUM;
			break;
		case 1:
			count = PAGE_SHOW_SECNUM;
			break;
		case 2:
			count = PAGE_SHOW_PROJNUM;
			break;
		default:
			count = PAGE_SHOW_DEFNUM;
			break;
		}
		return count;
	}

}
