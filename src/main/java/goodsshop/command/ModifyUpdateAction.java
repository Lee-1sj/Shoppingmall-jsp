package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goodsshop.bean.LogonDBBean;
import goodsshop.bean.LogonDataBean;
import goodsshop.process.CommandAction;

public class ModifyUpdateAction implements CommandAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");

        // 디버깅 로그 추가
        System.out.println("ModifyUpdateAction invoked");
        System.out.println("ID: " + id);
        System.out.println("Password: " + passwd);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Tel: " + tel);

        // 회원 정보를 수정하기 위한 정보를 얻어냄
        LogonDBBean manager = LogonDBBean.getInstance();
        LogonDataBean member = new LogonDataBean();
        member.setId(id);
        member.setPasswd(passwd);
        member.setName(name);
        member.setAddress(address);
        member.setTel(tel);

        int result = manager.updateMember(member);
        
        HttpSession session = request.getSession();
        session.setAttribute("result", result);

        if (result == 1) {
        	response.sendRedirect("/my-shoppingmall/index.do?success=true"); // 성공 페이지로 리다이렉트
            return null; // 리다이렉트를 사용했으므로 null 반환
            //return "/index.do";
        } else {
            return "/member/modifyForm.jsp"; // 실패 시 다시 폼 페이지로 이동
        }
    }
}
