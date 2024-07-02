package goodsshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.CartDBBean;
import goodsshop.bean.CartDataBean;
import goodsshop.process.CommandAction;

public class CartListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");
		
		List<CartDataBean> cartLists = null;
		int count = 0;

		//해당 buyer의 장바구니 목록의 수를 얻어냄
		CartDBBean goodsProcess = CartDBBean.getInstance();
		count = goodsProcess.getListCount(buyer);
		
		if(count > 0){//해당 buyer의 장바구니 목록이 있으면 수행
			//해당 buyer의 장바구니 목록을 얻어냄
			cartLists = goodsProcess.getCart(buyer, count);
			request.setAttribute("cartLists", cartLists);
		}
		
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(1));
		return "/cart/cartList.jsp";
	}

}
