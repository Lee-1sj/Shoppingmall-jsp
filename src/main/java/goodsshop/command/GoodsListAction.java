package goodsshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsshop.bean.MngrDBBean;
import goodsshop.bean.MngrDataBean;
import goodsshop.process.CommandAction;

public class GoodsListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<MngrDataBean> goodsList = null;
		String goods_kind = request.getParameter("goods_kind");
		int count = 0;
		
		//DB연동 - 전체 상품의 수를 얻어냄
		MngrDBBean goodsProcess = MngrDBBean.getInstance();
        count = goodsProcess.getGoodsCount(); 
        
        if (count > 0){//상품이 있으면 수행
        	//상품전체를 테이블에서 얻어내서 bookList에 저장
        	goodsList = goodsProcess.getGoods(goods_kind);
        	//bookList를 뷰에서 사용할 수 있도록 request속성에 저장
        	request.setAttribute("goodsList", goodsList);
        }
       
        //뷰에서 사용할 속성
        request.setAttribute("count", count);
        request.setAttribute("goods_kind", goods_kind);
        request.setAttribute("type", 0);
		return "/mngr/productProcess/goodsList.jsp";
	}

}
