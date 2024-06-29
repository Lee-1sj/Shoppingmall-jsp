package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.LogonDBBean;
import goodsshop.bean.LogonDataBean;
import goodsshop.process.CommandAction;

public class ModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// 입력 값 확인
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		if (id == null || id.isEmpty() || passwd == null || passwd.isEmpty() || name == null || name.isEmpty()
				|| address == null || address.isEmpty() || tel == null || tel.isEmpty()) {
			// 입력 값이 부족할 경우 처리
			request.setAttribute("check", Integer.valueOf(-1)); // 예: -1은 입력 값 부족을 의미
			return "/member/modifyPro.jsp";
		}

		// 수정할 회원 정보 설정
		LogonDataBean member = new LogonDataBean();
		member.setId(id);
		member.setPasswd(passwd);
		member.setName(name);
		member.setAddress(address);
		member.setTel(tel);

		// 수정할 회원 정보를 가지고 수정 처리 후 성공여부 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check = manager.updateMember(member);

		// 디버깅을 위한 로그
		System.out.println("ModifyProAction: id=" + id + ", check=" + check);

		request.setAttribute("check", Integer.valueOf(check));
		return "/member/modifyPro.jsp";
	}
}
