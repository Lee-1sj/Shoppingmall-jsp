package goodsshop.command;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import goodsshop.bean.MngrDBBean;
import goodsshop.bean.MngrDataBean;
import goodsshop.process.CommandAction;

public class GoodsRegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");// 한글 인코딩

		String filename = "";
		String realFolder = "";// 웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/goodsimage"; // 파일 업로드 폴더 지정
		String encType = "utf-8"; // 인코딩타입
		int maxSize = 10 * 1024 * 1024; // 최대 업로될 파일크기 10Mb

		MultipartRequest imageUp = null;

		// 웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);

		try {
			// 파일 업로드를 수행하는 MultipartRequest 객체 생성
			imageUp = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

			// <input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();

			// 파일 정보가 있다면
			while (files.hasMoreElements()) {
				// input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
				String name = (String) files.nextElement();

				// 서버에 저장된 파일 이름
				filename = imageUp.getFilesystemName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 폼으로부터 넘어온 정보중 파일이 아닌 정보를 얻어냄
		MngrDataBean goods = new MngrDataBean();
		String goods_kind = imageUp.getParameter("goods_kind");
		String goods_title = imageUp.getParameter("goods_title");
		String goods_price = imageUp.getParameter("goods_price");
		String goods_count = imageUp.getParameter("goods_count");
		String goods_size = imageUp.getParameter("goods_size");
		String goods_content = imageUp.getParameter("goods_content");
		String discount_rate = imageUp.getParameter("discount_rate");

		goods.setGoods_kind(goods_kind);
		goods.setGoods_title(goods_title);
		goods.setGoods_price(Integer.parseInt(goods_price));
		goods.setGoods_count(Integer.parseInt(goods_count));
		goods.setGoods_size(goods_size);
		goods.setGoods_image(filename);
		goods.setGoods_content(goods_content);
		goods.setDiscount_rate(Integer.parseInt(discount_rate));
		goods.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("Goods Information: " + goods);
		// DB연동 - 넘어온 정보를 테이블의 레코드로 추가
		MngrDBBean goodsProcess = MngrDBBean.getInstance();
		goodsProcess.insertGoods(goods);

		request.setAttribute("goods_kind", goods_kind);
		return "/mngr/productProcess/goodsRegisterPro.jsp";
	}

}
