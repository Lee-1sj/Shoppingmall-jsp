package goodsshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbcon.DBUtil;

public class BuyDBBean {
	private static BuyDBBean instance = new BuyDBBean();

	public static BuyDBBean getInstance() {
		return instance;
	}

	private BuyDBBean() {
	}

	// bank 테이블에 있는 전체 레코드를 얻어내는 메소드
	public List<String> getAccount() {
		List<String> accountList = new ArrayList<>();

		String sql = "SELECT account, bank, name FROM bank";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String account = rs.getString("account") + " " + rs.getString("bank") + " " + rs.getString("name");
				accountList.add(account);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return accountList;
	}

	@SuppressWarnings("resource")
	public void insertBuy(List<CartDataBean> lists, String id, String account, String deliveryName, String deliveryTel,
			String deliveryAddress) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Timestamp reg_date = null;
		String sql = "";
		String maxDate = " ";
		String number = "";
		String todayDate = "";
		String compareDate = "";
		long buyId = 0;
		int nowCount;

		try {
			conn = DBUtil.getConnection();
			reg_date = new Timestamp(System.currentTimeMillis());
			todayDate = reg_date.toString();
			compareDate = todayDate.substring(0, 4) + todayDate.substring(5, 7) + todayDate.substring(8, 10);

			pstmt = conn.prepareStatement("select max(buy_id) from buy");
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getLong(1) > 0) {
				Long val = Long.valueOf(rs.getLong(1));
				maxDate = val.toString().substring(0, 8);
				number = val.toString().substring(8);
				if (compareDate.equals(maxDate)) {
					if ((Integer.parseInt(number) + 1) < 10000) {
						buyId = Long.parseLong(maxDate + (Integer.parseInt(number) + 1 + 10000));
					} else {
						buyId = Long.parseLong(maxDate + (Integer.parseInt(number) + 1));
					}
				} else {
					compareDate += "00001";
					buyId = Long.parseLong(compareDate);
				}
			} else {
				compareDate += "00001";
				buyId = Long.parseLong(compareDate);
			}

			// 하나의 트랜잭션으로 처리
			conn.setAutoCommit(false);
			for (CartDataBean cart : lists) {
				// 해당 아이디에 대한 cart테이블 레코드를 가져온 후 buy테이블에 추가
				sql = "insert into buy (buy_id, buyer, goods_id, goods_title, buy_price, buy_count, goods_image, buy_date, account, deliveryName, deliveryTel, deliveryAddress) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, buyId);
				pstmt.setString(2, id);
				pstmt.setInt(3, cart.getGoods_id());
				pstmt.setString(4, cart.getGoods_title());
				pstmt.setInt(5, cart.getBuy_price());
				pstmt.setInt(6, cart.getBuy_count());
				pstmt.setString(7, cart.getGoods_image());
				pstmt.setTimestamp(8, reg_date);
				pstmt.setString(9, account);
				pstmt.setString(10, deliveryName);
				pstmt.setString(11, deliveryTel);
				pstmt.setString(12, deliveryAddress);
				pstmt.executeUpdate();
				pstmt.close();

				// 상품이 구매되었으므로 goods 테이블의 상품수량을 재조정함
				pstmt = conn.prepareStatement("select goods_count from goods where goods_id = ?");
				pstmt.setInt(1, cart.getGoods_id());
				rs = pstmt.executeQuery();
				rs.next();
				nowCount = rs.getInt(1) - 1;
				rs.close();
				pstmt.close();

				sql = "update goods set goods_count = ? where goods_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nowCount);
				pstmt.setInt(2, cart.getGoods_id());
				pstmt.executeUpdate();
				pstmt.close();
			}

			// cart 테이블에서 해당 아이디의 모든 레코드를 삭제
			pstmt = conn.prepareStatement("delete from cart where buyer = ?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
	}

	// id에 해당하는 buy 테이블의 레코드 수를 얻어내는 메소드
	public int getListCount(String id) throws SQLException {
		int x = 0;
		String sql = "select count(*) from buy where buyer=?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
	}

	// buy 테이블의 전체 레코드 수를 얻어내는 메소드
	public int getListCount() {
		int x = 0;
		String sql = "select count(*) from buy";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
	}

	// id에 해당하는 buy 테이블의 구매 목록을 얻어내는 메소드
	public List<BuyDataBean> getBuyList(String id) {
		List<BuyDataBean> lists = new ArrayList<>();
		String sql = "select * from buy where buyer = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BuyDataBean buy = new BuyDataBean();
					buy.setBuy_id(rs.getLong("buy_id"));
					buy.setGoods_id(rs.getInt("goods_id"));
					buy.setGoods_title(rs.getString("goods_title"));
					buy.setBuy_price(rs.getInt("buy_price"));
					buy.setBuy_count(rs.getByte("buy_count"));
					buy.setGoods_image(rs.getString("goods_image"));
					buy.setSanction(rs.getString("sanction"));

					lists.add(buy);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lists;
	}

	// buy 테이블의 전체 목록을 얻어내는 메소드
	public List<BuyDataBean> getBuyList() {
		List<BuyDataBean> lists = new ArrayList<>();
		String sql = "select * from buy";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				BuyDataBean buy = new BuyDataBean();

				buy.setBuy_id(rs.getLong("buy_id"));
				buy.setBuyer(rs.getString("buyer"));
				buy.setGoods_id(rs.getInt("goods_id"));
				buy.setGoods_title(rs.getString("goods_title"));
				buy.setBuy_price(rs.getInt("buy_price"));
				buy.setBuy_count(rs.getByte("buy_count"));
				buy.setGoods_image(rs.getString("goods_image"));
				buy.setBuy_date(rs.getTimestamp("buy_date"));
				buy.setAccount(rs.getString("account"));
				buy.setDeliveryName(rs.getString("deliveryName"));
				buy.setDeliveryTel(rs.getString("deliveryTel"));
				buy.setDeliveryAddress(rs.getString("deliveryAddress"));
				buy.setSanction(rs.getString("sanction"));

				lists.add(buy);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lists;
	}
}