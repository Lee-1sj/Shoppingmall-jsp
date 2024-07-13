package goodsshop.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import goodsshop.bean.MngrDBBean;
import goodsshop.process.CommandAction;

public class ManagerLoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩

        // 넘어온 요청의 데이터를 얻어냄
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");

        // DB와 연동해서 사용자의 인증을 처리
        MngrDBBean dbPro = MngrDBBean.getInstance();
        int check = dbPro.userCheck(id, passwd);

        // JSON 응답 준비
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        if (check == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            json.put("success", true);
            json.put("message", "로그인 성공");
        } else if (check == 0) {
            json.put("success", false);
            json.put("message", "비밀번호가 잘못되었습니다.");
        } else if (check == -1) {
            json.put("success", false);
            json.put("message", "아이디가 존재하지 않습니다.");
        }

        out.print(json.toString());
        out.flush();
        return null; // 뷰 페이지를 반환하지 않음
	}

}
