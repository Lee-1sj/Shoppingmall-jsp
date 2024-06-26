package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.process.CommandAction;

public class ManagerMainAction implements CommandAction {
	@SuppressWarnings("removal")
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 관리자를 구분할 때 사용
		request.setAttribute("type", 0);
		return "/mngr/managerMain.jsp";// 응답페이지
	}

}
