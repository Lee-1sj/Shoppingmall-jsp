package goodsshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goodsshop.bean.MngrDataBean;
import dbcon.DBUtil;
import goodsshop.bean.MngrDBBean;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class MngrDBBean {
	// MngrDBBean 전역 객체 생성 <- 한개의 객체만 생성해서 공유
	private static MngrDBBean instance = new MngrDBBean();

	// MngrDBBean객체를 리턴하는 메소드
	public static MngrDBBean getInstance() {
		return instance;
	}

	private MngrDBBean() {
	}

	// 관리자 인증 메소드
	public int userCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		SHA256 sha = SHA256.getInstance();
		try {
			conn = DBUtil.getConnection();

			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());

			pstmt = conn.prepareStatement("select managerPasswd from manager where managerId = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("managerPasswd");
				if (BCrypt.checkpw(shaPass, dbpasswd))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			} else// 해당 아이디 없으면 수행
				x = -1;// 아이디 없음

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return x;
	} // end of userCheck()

	// 굿즈 등록 메소드
	public void insertGoods(MngrDataBean goods) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into goods(goods_id,goods_kind,goods_title,goods_price,";
			sql += "goods_count,goods_size,goods_image,goods_content,";
			sql += "discount_rate,reg_date) values (goods_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGoods_kind());
			pstmt.setString(2, goods.getGoods_title());
			pstmt.setInt(3, goods.getGoods_price());
			pstmt.setInt(4, goods.getGoods_count());
			pstmt.setString(5, goods.getGoods_size());
			pstmt.setString(6, goods.getGoods_image());
			pstmt.setString(7, goods.getGoods_content());
			pstmt.setInt(8, goods.getDiscount_rate());
			pstmt.setTimestamp(9, goods.getReg_date());

			// SQL 쿼리와 매개변수 값 로그 출력
			System.out.println("Executing SQL: " + pstmt.toString());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, conn);
		}
	} // end of insertGoods()

	// 이미 등록된 상품을 검증
	public int registedGoodsConfirm(String kind, String title, String size) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = DBUtil.getConnection();

			String sql = "select goods_title from goods ";
			sql += "where goods_kind = ? and goods_title = ? and goods_size = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, title);
			pstmt.setString(3, size);

			rs = pstmt.executeQuery();

			if (rs.next())
				x = 1; // 해당 상품이 이미 등록되어 있음
			else
				x = -1;// 해당 상품이 이미 등록되어 있지 않음

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return x;
	} // end of registedGoodsConfirm()

	// 전체등록된 상품의 수를 얻어내는 메소드
	public int getGoodsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from goods");
			rs = pstmt.executeQuery();
			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return x;
	} // end of getGoodsCount()

	// 해당 분류의 상품의 수를 얻어내는 메소드
	public int getGoodsKindCount(String goods_kind) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		int kind = Integer.parseInt(goods_kind);

		try {
			conn = DBUtil.getConnection();
			String query = "select count(*) from goods where goods_kind=" + kind;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return x;
	} // end of getGoodsKindCount()

	// 상품의 이름을 얻어냄
	public String getGoodsTitle(int goods_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String x = "";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select goods_title from goods where goods_id = " + goods_id);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getString(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return x;
	} // end of getGoodsTitle()

	// 분류별 또는 전체 등록된 상품의 정보를 얻어내는 메소드
	public List<MngrDataBean> getGoods(String goods_kind) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MngrDataBean> goodsList = null;

		try {
			conn = DBUtil.getConnection();

			String sql1 = "select * from goods ";
			String sql2 = "select * from goods ";
			sql2 += "where goods_kind = ? order by reg_date desc";

			if (goods_kind.equals("all") || goods_kind.equals("")) {
				pstmt = conn.prepareStatement(sql1);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, goods_kind);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				goodsList = new ArrayList<MngrDataBean>();
				do {
					MngrDataBean goods = new MngrDataBean();
					goods.setGoods_id(rs.getInt("goods_id"));
					goods.setGoods_kind(rs.getString("goods_kind"));
					goods.setGoods_title(rs.getString("goods_title"));
					goods.setGoods_price(rs.getInt("goods_price"));
					goods.setGoods_count(rs.getInt("goods_count"));
					goods.setGoods_size(rs.getString("goods_size"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setDiscount_rate(rs.getInt("discount_rate"));
					goods.setReg_date(rs.getTimestamp("reg_date"));

					goodsList.add(goods);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return goodsList;
	} // end of getGoods()

	// 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간 상품 목록을 얻어내는 메소드
	public MngrDataBean[] getGoods(String goods_kind, int count) {
		String sql = "SELECT * FROM (SELECT * FROM goods WHERE goods_kind = ? ORDER BY reg_date DESC) WHERE ROWNUM <= ?";
		List<MngrDataBean> tempList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, goods_kind);
			pstmt.setInt(2, count);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					MngrDataBean goods = new MngrDataBean();
					goods.setGoods_id(rs.getInt("goods_id"));
					goods.setGoods_kind(rs.getString("goods_kind"));
					goods.setGoods_title(rs.getString("goods_title"));
					goods.setGoods_price(rs.getInt("goods_price"));
					goods.setGoods_count(rs.getInt("goods_count"));
					goods.setGoods_size(rs.getString("goods_size"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setDiscount_rate(rs.getInt("discount_rate"));
					goods.setReg_date(rs.getTimestamp("reg_date"));

					tempList.add(goods);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (!tempList.isEmpty()) {
			return tempList.toArray(new MngrDataBean[0]);
		}
		return null;
	}

	// goodsId에 해당하는 상품의 정보를 얻어내는 메소드로
	// 등록된 상품을 수정하기 위해 수정폼으로 읽어들기이기 위한 메소드
	public MngrDataBean getGoods(int goodsId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean goods = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from goods where goods_id = ?");
			pstmt.setInt(1, goodsId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				goods = new MngrDataBean();

				goods.setGoods_kind(rs.getString("goods_kind"));
				goods.setGoods_title(rs.getString("goods_title"));
				goods.setGoods_price(rs.getInt("goods_price"));
				goods.setGoods_count(rs.getInt("goods_count"));
				goods.setGoods_size(rs.getString("goods_size"));
				goods.setGoods_image(rs.getString("goods_image"));
				goods.setGoods_content(rs.getString("goods_content"));
				goods.setDiscount_rate(rs.getInt("discount_rate"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return goods;
	}

	// 등록된 상품의 정보를 수정시 사용하는 메소드
	public void updateGoods(MngrDataBean goods, int goodsId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn = DBUtil.getConnection();
			sql = "update goods set goods_kind=?,goods_title=?,goods_price=?";
			sql += ",goods_count=?,goods_size=?,goods_image=?,goods_content=?,discount_rate=?";
			sql += " where goods_id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, goods.getGoods_kind());
			pstmt.setString(2, goods.getGoods_title());
			pstmt.setInt(3, goods.getGoods_price());
			pstmt.setInt(4, goods.getGoods_count());
			pstmt.setString(5, goods.getGoods_size());
			pstmt.setString(6, goods.getGoods_image());
			pstmt.setString(7, goods.getGoods_content());
			pstmt.setInt(8, goods.getDiscount_rate());
			pstmt.setInt(9, goodsId);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, conn);
		}
	}

	// goodsId에 해당하는 상품의 정보를 삭제시 사용하는 메소드
	public void deleteGoods(int goodsId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from goods where goods_id=?");
			pstmt.setInt(1, goodsId);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, conn);
		}
	}
}
