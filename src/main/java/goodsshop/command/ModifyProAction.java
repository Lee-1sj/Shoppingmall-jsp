package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goodsshop.bean.LogonDBBean;
import goodsshop.bean.LogonDataBean;
import goodsshop.process.CommandAction;
import work.crypt.BCrypt;

public class ModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// 입력 값 확인
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		HttpSession session = request.getSession();
		if (id == null || id.isEmpty() || passwd == null || passwd.isEmpty()) {
			session.setAttribute("check", -1); // 입력 값 부족
			return "/member/modifyPro.jsp";
		}

		// 데이터베이스에서 현재 비밀번호 가져오기
		LogonDBBean manager = LogonDBBean.getInstance();
		LogonDataBean member = manager.getMember(id);

		if (member == null) {
			session.setAttribute("check", -2); // 회원 정보 없음 의미
			return "/member/modifyPro.jsp";
		}

		// 비밀번호 확인
		if (!BCrypt.checkpw(passwd, member.getPasswd())) { // 입력된 비밀번호와 저장된 해시된 비밀번호 비교
			session.setAttribute("check", 0); // 비밀번호 틀림
			return "/member/modifyPro.jsp";
		}

		// 비밀번호 일치하면 수정 폼으로 이동
		session.setAttribute("check", 1); // 성공
		session.setAttribute("member", member); // 수정할 회원 정보를 세션에 저장
		session.setAttribute("id", id); 
		session.setAttribute("passwd", passwd);
		return "/member/modifyPro.jsp";
	}

}
