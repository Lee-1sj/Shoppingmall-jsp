package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class QnaReplyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		try {
			// qna_id를 숫자로 변환
			int qna_id = Integer.parseInt(request.getParameter("qna_id"));

			// qna_id에 해당하는 QnA를 가져옴
			QnaDBBean qnaProcess = QnaDBBean.getInstance();
			QnaDataBean qna = qnaProcess.updateGetArticle(qna_id);

			if (qna == null) {
				throw new Exception("QnA not found for qna_id: " + qna_id);
			}

			// QnA답변에 필요한 정보를 얻어냄
			int goods_id = qna.getGoods_id();
			String goods_title = qna.getGoods_title();
			String qna_content = qna.getQna_content();
			byte qora = 2; // 답변글

			request.setAttribute("qna_id", qna_id);
			request.setAttribute("goods_id", goods_id);
			request.setAttribute("goods_title", goods_title);
			request.setAttribute("qna_content", qna_content);
			request.setAttribute("qora", qora);
			request.setAttribute("type", 0);

			return "/mngr/qnaProcess/qnaReplyForm.jsp";

		} catch (NumberFormatException e) {
			// qna_id가 잘못된 경우 예외 처리
			request.setAttribute("error", "Invalid QnA ID format.");
			return "/error.jsp"; 
		} catch (Exception e) {
			// 기타 예외 처리
			request.setAttribute("error", e.getMessage());
			return "/error.jsp"; 
		}
	}
}
