package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fnst.officeapply.common.InputCheckUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Organization;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.RedirectBean;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.OrganizationService;

@AuthorizerRequest(authorizerStep=10)
public class OrganizationAction extends ActionSupport{

	private int id;
	private int orgzID;
	private String oldOrgzName;
	private String orgzName;
	private int orgzType;
	private OrganizationService service;
	
	public OrganizationAction() throws DBPoolException, OfficeException{
		service = new OrganizationService();
	}
	
	public String defOrgzList(HttpServletRequest request) throws DBPoolException, OfficeException{
		String path1 = "/WEB-INF/jsp/admin/department/depmentlist.jsp";
		String path2 = "/WEB-INF/jsp/admin/sectionOffice/officelist.jsp";
		String path3 = "/WEB-INF/jsp/admin/projGroup/projlist.jsp";
		String path = null;
		HttpSession session = request.getSession(true);
		session.setAttribute("pageNum_orgz", 0);
		if (InputCheckUtil.checkOrgzType(orgzType)) {
			service.depList4Page(request, id, 0, orgzType);
			path = getPath(orgzType, path1, path2, path3);
		}
		
		return path;
	}
	
	public String orgzNextList(HttpServletRequest request) throws DBPoolException, OfficeException{
		String path1 = "/WEB-INF/jsp/admin/department/depmentlist.jsp";
		String path2 = "/WEB-INF/jsp/admin/sectionOffice/officelist.jsp";
		String path3 = "/WEB-INF/jsp/admin/projGroup/projlist.jsp";
		String path = null;
		HttpSession session = request.getSession(true);
		Integer pageNum = (Integer) session.getAttribute("pageNum_orgz");
		pageNum = (pageNum == null ? 0 : pageNum);
		if (InputCheckUtil.checkOrgzType(orgzType)) {
			service.depList4Page(request, id, pageNum, orgzType);
			path = getPath(orgzType, path1, path2, path3);
		}
		return path;
	}
	
	public String adderPage(HttpServletRequest request) {
		if (InputCheckUtil.checkOrgzType(orgzType)) {
			String path1 = "/WEB-INF/jsp/admin/department/add.jsp";
			String path2 = "/WEB-INF/jsp/admin/sectionOffice/add.jsp";
			String path3 = "/WEB-INF/jsp/admin/projGroup/add.jsp";
			String path = getPath(orgzType, path1, path2, path3);
			return path;
		}
		return null;
	}
	
	public String add(HttpServletRequest request) throws OfficeException, DBPoolException{
		boolean isOK = false;
		if(InputCheckUtil.checkOrgzType(orgzType) && InputCheckUtil.checkOrgzName(orgzName)){
			isOK = service.add(InputCheckUtil.replaceTags(orgzName), orgzType);
		}
		return redirectPage(request, isOK, "admin/orgz/adderPage.action", orgzType);
	}
	
	public String updatePage(HttpServletRequest request) throws OfficeException, DBPoolException{
		String path1 = "/WEB-INF/jsp/admin/department/update.jsp";
		String path2 = "/WEB-INF/jsp/admin/sectionOffice/update.jsp";
		String path3 = "/WEB-INF/jsp/admin/projGroup/update.jsp";
		String path = null;
		if(InputCheckUtil.checkOrgzType(orgzType) && InputCheckUtil.isPositiveNumber(orgzID + "")){
			service.updatePage(request, orgzID, orgzType);
			path = getPath(orgzType, path1, path2, path3);
		}
		return path;
	}
	
	public String update(HttpServletRequest request) throws OfficeException, DBPoolException{
		boolean isOK = false;
		if(InputCheckUtil.checkOrgzType(orgzType) && InputCheckUtil.checkOrgzName(oldOrgzName)
				&& InputCheckUtil.checkOrgzName(orgzName) && InputCheckUtil.isPositiveNumber(orgzID + "")){
			Organization orgz = new Organization(orgzID, InputCheckUtil.replaceTags(orgzName));
			isOK = service.update(InputCheckUtil.replaceTags(oldOrgzName), orgz, orgzType);
		}
		return redirectPage(request, isOK, "admin/orgz/orgzNextList.action", orgzType);
	}

	public String delete(HttpServletRequest request) throws OfficeException, DBPoolException{
		boolean isOK = false;
		if(InputCheckUtil.checkOrgzType(orgzType) && InputCheckUtil.isPositiveNumber(orgzID + "")){
			isOK = service.delete(orgzID, orgzType);
		}
		return redirectPage(request, isOK, "admin/orgz/orgzNextList.action", orgzType);
	}
	
	public String redirectPage(HttpServletRequest request,Object obj,String requestName, int orgzType){
		RedirectBean redirectBean = new RedirectBean();
		redirectBean.addControl("isOK", obj + "");
		redirectBean.addControl("orgzType", orgzType + "");
		redirectBean.setRequest(requestName);
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
		return REDIRECT_PAGE;
	}
	
	private String getPath(int type, String path1, String path2, String path3){
		String path;
		switch(type){
		case 0:
			path = path1;
			break;
		case 1:
			path = path2;
			break;
		case 2:
			path = path3;
			break;
		default:
			path = null;
			break;
		}
		return path;
	} 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrgzID() {
		return orgzID;
	}

	public void setOrgzID(int orgzID) {
		this.orgzID = orgzID;
	}

	public String getOldOrgzName() {
		return oldOrgzName;
	}

	public void setOldOrgzName(String oldOrgzName) {
		this.oldOrgzName = oldOrgzName;
	}

	public String getOrgzName() {
		return orgzName;
	}

	public void setOrgzName(String orgzName) {
		this.orgzName = orgzName;
	}

	public void setOrgzType(int orgzType) {
		this.orgzType = orgzType;
	}

	public int getOrgzType() {
		return orgzType;
	}
	
}
