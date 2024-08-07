package goodsshop.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.MngrDBBean;
import goodsshop.bean.MngrDataBean;
import goodsshop.process.CommandAction;

public class ShopMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<MngrDataBean> goodsLists = new ArrayList<>();

		MngrDBBean goodsProcess = MngrDBBean.getInstance();

		// 카테고리별 최신의 상품 3개씩 얻어내서 List에 저장
		String[] categories = { "100", "200" };
		for (String category : categories) {
			MngrDataBean[] goodsList = goodsProcess.getGoods(category, 3);
			if (goodsList != null) {
				for (MngrDataBean goods : goodsList) {
					goodsLists.add(goods);
				}
			}
		}

		request.setAttribute("goodsLists", goodsLists);
		request.setAttribute("type", Integer.valueOf(1));
		return "/shop/shopMain.jsp";
	}
}
