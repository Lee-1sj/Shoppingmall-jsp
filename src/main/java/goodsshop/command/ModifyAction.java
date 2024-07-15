package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.process.CommandAction;

public class ModifyAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("type", Integer.valueOf(1));
		System.out.println("ModifyAction");
		return "/member/modify.jsp";
	}

}
