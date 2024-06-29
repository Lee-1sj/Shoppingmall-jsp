package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.MngrDBBean;
import goodsshop.process.CommandAction;

public class QnaFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		String goods_kind = request.getParameter("goods_kind");
		int goods_id = 0;
		try {
			goods_id = Integer.parseInt(request.getParameter("goods_id"));
		} catch (NumberFormatException e) {
			System.err.println("Invalid goods_id: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid goods_id parameter");
			return null; 
		}

		// goods_id에 해당하는 goods_title을 얻어냄
		MngrDBBean goodsProcess = MngrDBBean.getInstance();
		String goods_title = null;
		try {
			goods_title = goodsProcess.getGoodsTitle(goods_id);
		} catch (Exception e) {
			System.err.println("Error fetching goods title: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching goods title");
			return null; 
		}

		request.setAttribute("goods_kind", goods_kind);
		request.setAttribute("goods_id", Integer.valueOf(goods_id));
		request.setAttribute("goods_title", goods_title);
		request.setAttribute("qora", Integer.valueOf(1));
		request.setAttribute("type", Integer.valueOf(1));
		return "/qna/qnaForm.jsp";
	}
}
