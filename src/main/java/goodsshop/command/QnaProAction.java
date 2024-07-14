package goodsshop.command;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class QnaProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		try {
			// 폼에서 입력 후 넘어온 qna 내용
			String qnaWriter = request.getParameter("qna_writer");
			String goodsTitle = request.getParameter("goods_title");
			String qnaContent = request.getParameter("qna_content");
			int goodsId = Integer.parseInt(request.getParameter("goods_id"));
			byte qora = Byte.parseByte(request.getParameter("qora"));
			byte reply = 0; // 답변여부 미답변

			// qna를 추가하기 위한 정보작성
			QnaDataBean qna = new QnaDataBean();
			qna.setGoods_id(goodsId);
			qna.setGoods_title(goodsTitle);
			qna.setQna_content(qnaContent);
			qna.setQna_writer(qnaWriter);
			qna.setReply(reply);
			qna.setReg_date(new Timestamp(System.currentTimeMillis()));
			qna.setQora(qora);

			// qna를 테이블에 추가
			QnaDBBean qnaProcess = QnaDBBean.getInstance();
			int check = qnaProcess.insertArticle(qna);

			request.setAttribute("check", Integer.valueOf(check));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("check", Integer.valueOf(-1)); // 에러 발생 -1 설정
		}
		return "/qna/qnaPro.jsp";
	}
}
