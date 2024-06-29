package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.LogonDBBean;
import goodsshop.process.CommandAction;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");

		// 주어진 id의 중복여부를 체크해 값을 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check = manager.confirmId(id);
		request.setAttribute("check", Integer.valueOf(check));

		return "/member/confirmId.jsp";
	}
}
