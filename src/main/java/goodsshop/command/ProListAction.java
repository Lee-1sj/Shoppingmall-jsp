package goodsshop.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import goodsshop.bean.MngrDBBean;
import goodsshop.bean.MngrDataBean;
import goodsshop.process.CommandAction;

public class ProListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<MngrDataBean> goodsList = null;
		int count = 0;
		String goods_kind = request.getParameter("goods_kind");

		if (goods_kind == null || goods_kind.isEmpty()) {
			// 기본값 설정 또는 에러 처리
			throw new IllegalArgumentException("Invalid goods_kind parameter");
		}

		MngrDBBean goodsProcess = MngrDBBean.getInstance();

		try {
			// kind 값이 all이면 전체 상품의 수를 얻어냄
			if (goods_kind.equals("all")) {
				count = goodsProcess.getGoodsCount();
			} else { // all이 아니면 해당 카테고리의 상품수를 얻어냄
				count = goodsProcess.getGoodsKindCount(goods_kind);
			}
			if (count > 0) { // 상품이 있으면 수행
				// 상품목록을 얻어냄
				goodsList = goodsProcess.getGoods(goods_kind);
				request.setAttribute("goodsList", goodsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 해당 뷰에서 사용할 속성
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("goods_kind", goods_kind);
		request.setAttribute("type", Integer.valueOf(1));

		return "/shop/showList.jsp";
	}
}
