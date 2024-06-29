package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.MngrDBBean;
import goodsshop.process.CommandAction;

public class GoodsDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		String goods_kind = request.getParameter("goods_kind");
		
		//DB연동 - book_id에 해당하는 상품을 삭제
		MngrDBBean goodsProcess = MngrDBBean.getInstance();
		goodsProcess.deleteGoods(goods_id); 
		
		request.setAttribute("goods_kind", goods_kind);
		return "/mngr/productProcess/goodsDeletePro.jsp";
	}

}
