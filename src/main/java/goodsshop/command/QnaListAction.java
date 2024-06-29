package goodsshop.command;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class QnaListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<QnaDataBean> qnaLists = new ArrayList<>();
		try {
			// DB연동 - 상품QnA의 수를 얻어낸다.
			QnaDBBean qnaProcess = QnaDBBean.getInstance();
			int count = qnaProcess.getArticleCount();

			if (count > 0) { // 상품QnA가 있으면 수행
				// 지정한 수만큼의 상품QnA를 얻어냄
				qnaLists = qnaProcess.getArticles(count);
			}

			request.setAttribute("qnaLists", qnaLists);
			request.setAttribute("count", count);
			request.setAttribute("type", 0);
		} catch (Exception e) {
			request.setAttribute("error", "An error occurred while processing QnA list: " + e.getMessage());
			return "/error.jsp"; 
		}

		return "/mngr/qnaProcess/qnaList.jsp";
	}
}
