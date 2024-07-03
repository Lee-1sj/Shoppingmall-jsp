package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.CartDBBean;
import goodsshop.process.CommandAction;

public class CartUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));

		// cart_id에 해당하는 buy_count의 값을 수정
		CartDBBean goodsProcess = CartDBBean.getInstance();
		goodsProcess.updateCount(cart_id, buy_count);

		request.setAttribute("type", Integer.valueOf(1));
		return "/cart/cartUpdatePro.jsp";
	}

}
