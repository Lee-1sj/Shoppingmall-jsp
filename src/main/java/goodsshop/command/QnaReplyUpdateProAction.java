package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class QnaReplyUpdateProAction implements CommandAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");

        try {
            // 파라미터에서 qna_id와 qna_content 추출 및 형변환
            int qna_id = Integer.parseInt(request.getParameter("qna_id"));
            String qna_content = request.getParameter("qna_content");
            // QnA 답변글 수정 정보 설정
            QnaDataBean qna = new QnaDataBean();
            qna.setQna_id(qna_id);
            qna.setQna_content(qna_content);
            // QnA 답변글 수정 처리
            QnaDBBean qnaProcess = QnaDBBean.getInstance();
            int check = qnaProcess.updateArticle(qna);
            // 요청 속성에 결과 값 설정
            request.setAttribute("check", Integer.valueOf(check));
            return "/mngr/qnaProcess/qnaReplyUpdatePro.jsp";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid QnA ID format.");
            return "/error.jsp"; 
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing the request.");
            return "/error.jsp"; 
        }
    }
}
