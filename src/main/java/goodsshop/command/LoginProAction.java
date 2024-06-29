package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.LogonDBBean;
import goodsshop.process.CommandAction;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		// 사용자가 입력한 id, passwd를 가지고 인증 체크 후 값 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check = manager.userCheck(id, passwd);
		System.out.println("id=" + id + " passwd=" + passwd + " check=" + check);

		request.setAttribute("id", id);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/loginPro.jsp";
	}

}
