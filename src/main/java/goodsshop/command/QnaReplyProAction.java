package goodsshop.command;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class QnaReplyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		try {
			// 파라미터에서 값 추출 및 형변환
			int qna_id = Integer.parseInt(request.getParameter("qna_id"));
			int goods_id = Integer.parseInt(request.getParameter("goods_id"));
			String qna_writer = request.getParameter("qna_writer");
			String goods_title = request.getParameter("goods_title");
			String qna_content = "[답변]:" + request.getParameter("qna_content");
			byte qora = Byte.parseByte(request.getParameter("qora"));
			byte reply = 1; // 답변 여부 설정 (1: 답변함)

			// QnaDataBean 객체 생성 및 값 설정
			QnaDataBean qna = new QnaDataBean();
			qna.setQna_id(qna_id);
			qna.setGoods_id(goods_id);
			qna.setGoods_title(goods_title);
			qna.setQna_content(qna_content);
			qna.setQna_writer(qna_writer);
			qna.setGroup_id(qna_id);
			qna.setReply(reply);
			qna.setReg_date(new Timestamp(System.currentTimeMillis()));
			qna.setQora(qora);

			// QnA 데이터베이스 처리 객체 생성 및 답변 글 삽입
			QnaDBBean qnaProcess = QnaDBBean.getInstance();
			int check = qnaProcess.insertArticle(qna, qna_id);

			// 요청 속성에 결과 값 설정
			request.setAttribute("check", check);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Invalid input format.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "An error occurred while processing the request.");
		}

		return "/mngr/qnaProcess/qnaReplyPro.jsp";
	}
}
