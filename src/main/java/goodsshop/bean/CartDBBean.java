package goodsshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goodsshop.bean.CartDataBean;
import dbcon.DBUtil;
import goodsshop.bean.CartDBBean;

public class CartDBBean {
	private static CartDBBean instance = new CartDBBean();

	public static CartDBBean getInstance() {
		return instance;
	}

	private CartDBBean() {
	}

	// [장바구니에 담기]를 클릭하면 수행되는 것으로 cart 테이블에 새로운 레코드를 추가
	public void insertCart(CartDataBean cart) {
		String sql = "INSERT INTO cart (cart_id, buyer, goods_id, goods_title, buy_price, buy_count, goods_image) "
				+ "VALUES (cart_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cart.getBuyer());
			pstmt.setInt(2, cart.getGoods_id());
			pstmt.setString(3, cart.getGoods_title());
			pstmt.setInt(4, cart.getBuy_price());
			pstmt.setInt(5, cart.getBuy_count());
			pstmt.setString(6, cart.getGoods_image());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// id에 해당하는 레코드의 수를 얻어내는 메소드
	public int getListCount(String id) {
		String sql = "SELECT COUNT(*) FROM cart WHERE buyer = ?";
		int count = 0;

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return count;
	}

	// id에 해당하는 레코드의 목록을 얻어내는 메소드
	public List<CartDataBean> getCart(String id, int count) {
		String sql = "SELECT * FROM cart WHERE buyer = ?";
		List<CartDataBean> lists = new ArrayList<>(count);

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CartDataBean cart = new CartDataBean();
					cart.setCart_id(rs.getInt("cart_id"));
					cart.setGoods_id(rs.getInt("goods_id"));
					cart.setGoods_title(rs.getString("goods_title"));
					cart.setBuy_price(rs.getInt("buy_price"));
					cart.setBuy_count(rs.getInt("buy_count"));
					cart.setGoods_image(rs.getString("goods_image"));

					lists.add(cart);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lists;
	}

	// 장바구니에서 수량을 수정시 실행되는 메소드
	public void updateCount(int cart_id, byte count) {
		String sql = "UPDATE cart SET buy_count = ? WHERE cart_id = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, count);
			pstmt.setInt(2, cart_id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 장바구니에서 cart_id에대한 레코드를 삭제하는 메소드
	public void deleteList(int cart_id) {
		String sql = "DELETE FROM cart WHERE cart_id = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, cart_id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// id에 해당하는 모든 레코드를 삭제하는 메소드로 [장바구니 비우기]단추를 클릭시 실행된다.
	public void deleteAll(String id) {
		String sql = "DELETE FROM cart WHERE buyer = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
