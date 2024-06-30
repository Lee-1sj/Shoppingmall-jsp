package goodsshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.CartDBBean;
import goodsshop.bean.CartDataBean;
import goodsshop.process.CommandAction;

public class InsertCartAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// 장바구니에 추가할 정보를 파라미터에서 받아냄
		int buy_count = Integer.parseInt(request.getParameter("buy_count"));
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		String goods_title = request.getParameter("goods_title");
		String goods_image = request.getParameter("goods_image");
		int buy_price = (int) Float.parseFloat(request.getParameter("buy_price"));
		String buyer = request.getParameter("buyer");

		// 장바구니에 추가하기 위한 정보구성
		CartDataBean cart = new CartDataBean();
		cart.setGoods_id(goods_id);
		cart.setGoods_image(goods_image);
		cart.setGoods_title(goods_title);
		cart.setBuy_count(buy_count);
		cart.setBuy_price(buy_price);
		cart.setBuyer(buyer);

		System.out.println("cart.toString()= " + cart.toString());
		// 장바구니에 추가
		CartDBBean goodsProcess = CartDBBean.getInstance();
		goodsProcess.insertCart(cart);

		return "/cart/insertCart.jsp";
	}

}
