package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goodsshop.bean.LogonDBBean;
import goodsshop.bean.LogonDataBean;
import goodsshop.process.CommandAction;

public class ModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String passwd = (String) session.getAttribute("passwd");
		
		 // 디버깅 로그
        System.out.println("ModifyFormAction invoked");
        System.out.println("ID: " + id);
        System.out.println("Password: " + passwd);

		// 회원 정보를 수정하기 위한 정보를 얻어냄
		LogonDBBean manager = LogonDBBean.getInstance();
		LogonDataBean m = manager.getMember(id, passwd);

		request.setAttribute("m", m);
		request.setAttribute("id", id);
		request.setAttribute("type", Integer.valueOf(1));
		return "/member/modifyForm.jsp";
	}

}
