package goodsshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.MngrDBBean;
import goodsshop.bean.MngrDataBean;
import goodsshop.bean.QnaDBBean;
import goodsshop.bean.QnaDataBean;
import goodsshop.process.CommandAction;

public class GoodsContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<QnaDataBean> qnaLists;
		String goods_kind = request.getParameter("goods_kind");
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));

		// goods_id에 해당하는 상품을 얻어냄
		MngrDBBean goodsProcess = MngrDBBean.getInstance();
		MngrDataBean goods = goodsProcess.getGoods(goods_id);

		// book_id에 해당하는 상품의 QnA 수를 얻어냄
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int count = qnaProcess.getArticleCount(goods_id);

		if (count > 0) {// QnA가 있으면 수행
			/// book_id에 해당하는 상품의 QnA를 얻어냄
			qnaLists = qnaProcess.getArticles(count, goods_id);
			request.setAttribute("qnaLists", qnaLists);
		}

		request.setAttribute("goods", goods);
		request.setAttribute("goods_id", Integer.valueOf(goods_id));
		request.setAttribute("book_kind", goods_kind);
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(1));
		return "/shop/goodsContent.jsp";
	}

}
