package com.fnst.officeapply.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fnst.officeapply.common.OnlineCounter;
import com.fnst.officeapply.common.ServletUtil;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CommonAction extends ActionSupport {
	
	private String errMsg;
	private String adminMailAddr;
	
	public String errorPage(HttpServletRequest request) {
		request.setAttribute("errMsg", errMsg);
		request.setAttribute("adminMailAddr", adminMailAddr);
		return "/WEB-INF/error.jsp";
	}
	@AuthorizerRequest
	public String authFailPage(HttpServletRequest request){
		request.setAttribute("adminMailAddr", adminMailAddr);
		return "WEB-INF/authFailPage.jsp";
	}
	
	@AuthorizerRequest
	public String showOnliner(HttpServletRequest request){
		OnlineCounter onlinerCounter = OnlineCounter.getInstance(request.getSession(true).getServletContext());
		Set<UserInfo> users = onlinerCounter.showOnliner();
		request.setAttribute("users", users);
		return "WEB-INF/jsp/common/onliner.jsp";
	}
	
	public String validateCode(HttpServletRequest request) throws OfficeException{
		Font mFont = new Font("宋体 ", Font.PLAIN, 20);
		Random random = new Random();
		String[] codeArray = new String[] { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		HttpServletResponse response = ServletUtil.getHttpServletResponse();
		HttpSession session = request.getSession(true);
		
		int num1 = random.nextInt(36);
		int num2 = random.nextInt(36);
		int num3 = random.nextInt(36);
		int num4 = random.nextInt(36);
		String code = codeArray[num1] + codeArray[num2] + codeArray[num3] + codeArray[num4];
		// 保存入session,用于与用户的输入进行比较.
		// 注意比较完之后清除session.
		Object[] obj = new Object[]{code, System.currentTimeMillis()};
		session.setAttribute("validateCode", obj);
		response.setContentType("image/jpeg;charset=utf-8");
		ServletOutputStream out;
		try {
			out = response.getOutputStream();

			BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
			Graphics gra = image.getGraphics();
			gra.setColor(Color.yellow);
			gra.fillRect(1, 1, 60, 20);
			gra.setColor(Color.black);
			gra.setFont(mFont);
			// 输出数字
			char c;
			for (int i = 0; i < 4; i++) {
				c = code.charAt(i);
				gra.drawString(c + " ", i * 13 + 2, 18);
			}
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (IOException e) {
			throw new OfficeException(e);
		}
		return null;
	}
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getAdminMailAddr() {
		return adminMailAddr;
	}

	public void setAdminMailAddr(String adminMailAddr) {
		this.adminMailAddr = adminMailAddr;
	}
	
}
