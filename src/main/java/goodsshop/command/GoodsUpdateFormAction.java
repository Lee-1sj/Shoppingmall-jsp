package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.MngrDBBean;
import goodsshop.bean.MngrDataBean;
import goodsshop.process.CommandAction;

public class GoodsUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		String goods_kind = request.getParameter("goods_kind");

		// DB연동 goods_id에 해당하는 상품을 얻내서 goods에 저장
		MngrDBBean goodsProcess = MngrDBBean.getInstance();
		MngrDataBean goods = goodsProcess.getGoods(goods_id);

		request.setAttribute("goods_id", goods_id);
		request.setAttribute("goods_kind", goods_kind);
		request.setAttribute("goods", goods);
		request.setAttribute("type", 0);
		return "/mngr/productProcess/goodsUpdateForm.jsp";
	}

}
