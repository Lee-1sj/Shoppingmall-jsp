package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class QnaReplyUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		try {
			// 파라미터에서 qna_id 추출 및 형변환
			int qna_id = Integer.parseInt(request.getParameter("qna_id"));

			// 주어진 qna_id에 해당하는 수정할 qna 답변을 가져옴
			QnaDBBean qnaProcess = QnaDBBean.getInstance();
			QnaDataBean qna = qnaProcess.updateGetArticle(qna_id);

			// 요청 속성에 qna 데이터 설정
			request.setAttribute("qna", qna);
			request.setAttribute("qna_id", Integer.valueOf(qna_id));
			request.setAttribute("type", Integer.valueOf(0));
			
			return "/mngr/qnaProcess/qnaReplyUpdateForm.jsp";
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
