package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.LogonDBBean;
import goodsshop.bean.LogonDataBean;
import goodsshop.process.CommandAction;
import work.crypt.BCrypt;
import work.crypt.SHA256;

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
            request.setAttribute("check", -1); // -1은 입력 값 부족을 의미
            System.out.println("ModifyProAction: 입력 값 부족");
            return "/member/modifyPro.jsp";
        }

        // 데이터베이스에서 현재 비밀번호 가져오기
        LogonDBBean manager = LogonDBBean.getInstance();
        LogonDataBean member = manager.getMember(id);

        // 비밀번호 확인
        SHA256 sha = SHA256.getInstance();
        String shaPass = sha.getSha256(passwd.getBytes());
        if (!BCrypt.checkpw(shaPass, member.getPasswd())) {
            request.setAttribute("check", 0); // 비밀번호 틀림
            return "/member/modifyPro.jsp";
        }

        // 수정할 회원 정보 설정
        member.setPasswd(passwd); 
        member.setName(name);
        member.setAddress(address);
        member.setTel(tel);

        // 회원 정보 수정 처리
        int check = manager.updateMember(member);

        System.out.println("ModifyProAction: id=" + id + ", check=" + check);

        request.setAttribute("check", check);
        return "/member/modifyPro.jsp";
	}
}
