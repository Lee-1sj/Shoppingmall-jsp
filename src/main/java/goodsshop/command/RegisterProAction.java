package goodsshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.LogonDBBean;
import goodsshop.bean.LogonDataBean;
import goodsshop.process.CommandAction;

public class RegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// 회원가입 정보
		LogonDataBean member = new LogonDataBean();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setReg_date(new Timestamp(System.currentTimeMillis()));
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
		System.out.println("RegisterProAction passwd=" + request.getParameter("passwd"));
		System.out.println("RegisterProAction id=" + request.getParameter("id"));
		// 회원가입처리
		LogonDBBean dbPro = LogonDBBean.getInstance();
		dbPro.insertMember(member);

		return "/member/registerPro.jsp";
	}

}
