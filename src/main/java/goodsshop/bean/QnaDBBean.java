package goodsshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goodsshop.bean.QnaDataBean;
import dbcon.DBUtil;
import goodsshop.bean.QnaDBBean;

public class QnaDBBean {
	private static QnaDBBean instance = new QnaDBBean();

	// .jsp페이지에서 DB연동 빈인 BoardDBBean클래스의 메소드에 접근시 필요
	public static QnaDBBean getInstance() {
		return instance;
	}

	private QnaDBBean() {
	}

	// qna 테이블에 글을 추가 - 사용자가 작성하는 글
	public int insertArticle(QnaDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "";
		int group_id = 1;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select max(qna_id) from qna");
			rs = pstmt.executeQuery();
			if (rs.next())
				x = rs.getInt(1);
			if (x > 0)
				group_id = rs.getInt(1) + 1;
			// 쿼리를 작성 :board테이블에 새로운 레코드 추가
			sql = "insert into qna values(qna_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getGoods_id());
			pstmt.setString(2, article.getGoods_title());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, group_id);
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();
			x = 1; // 레코드 추가 성공
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return x;
	} // end of insertArticle()

	// qna테이블에 글을 추가 - 관리자가 작성한 답변
	@SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article, int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = 0;
		String sql = "";

		try {
			conn = DBUtil.getConnection();

			// 쿼리를 작성: qna 테이블에 새로운 레코드 추가
			sql = "INSERT INTO qna (qna_id, goods_id, goods_title, qna_writer, qna_content, group_id, qora, reply, reg_date) ";
			sql += "VALUES (qna_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getGoods_id());
			pstmt.setString(2, article.getGoods_title());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, article.getGroup_id());
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			// 기존 pstmt를 닫고 새로운 pstmt 생성
			pstmt.close();

			sql = "UPDATE qna SET reply = ? WHERE qna_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, qna_id);
			pstmt.executeUpdate();

			x = 1; // 레코드 추가 성공
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, conn);
		}
		return x;
	}

	// qna 테이블에 저장된 전체 글의 수를 얻어냄
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from qna");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return count;
	} // end of getArticleCount()

	// 특정 상품에 대해 작성한 qna 글의 수를 얻어냄
	public int getArticleCount(int goods_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from qna where goods_id = ?");
			pstmt.setInt(1, goods_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return count;
	} // end of getArticleCount(goods_id)

	// 지정한 수에 해당하는 qna 글의 수를 얻어냄
	public List<QnaDataBean> getArticles(int count) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaDataBean> articleList = null;// 글목록을 저장하는 객체

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM (SELECT * FROM qna ORDER BY group_id DESC, qora ASC) WHERE ROWNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			rs = pstmt.executeQuery();

			if (rs.next()) { // ResultSet이 레코드를 가짐
				articleList = new ArrayList<QnaDataBean>(count);
				do {
					QnaDataBean article = new QnaDataBean();
					article.setQna_id(rs.getInt("qna_id"));
					article.setGoods_id(rs.getInt("goods_id"));
					article.setGoods_title(rs.getString("goods_title"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_content(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getByte("qora"));
					article.setReply(rs.getByte("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));

					// List객체에 데이터저장빈인 QnaDataBean객체를 저장
					articleList.add(article);
				} while (rs.next() && articleList.size() < count);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return articleList; // List객체의 레퍼런스를 리턴
	} // end of getArticles(count)

	// 특정 책에 대해 작성한 qna글을 지정한 수 만큼 얻어냄
	public List<QnaDataBean> getArticles(int count, int goods_id) {
		List<QnaDataBean> articleList = new ArrayList<>();
		String sql = "SELECT * FROM qna WHERE goods_id = ? ORDER BY group_id DESC, qora ASC LIMIT ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, goods_id);
			pstmt.setInt(2, count);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					QnaDataBean article = new QnaDataBean();
					article.setQna_id(rs.getInt("qna_id"));
					article.setGoods_id(rs.getInt("goods_id"));
					article.setGoods_title(rs.getString("goods_title"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_content(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getByte("qora"));
					article.setReply(rs.getByte("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));

					// List객체에 데이터저장빈인 QnaDataBean객체를 저장
					articleList.add(article);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return articleList; 
	}

	// QnA 글 수정폼에서 사용할 글의 내용
	public QnaDataBean updateGetArticle(int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaDataBean article = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from qna where qna_id = ?");
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new QnaDataBean();
				article.setQna_id(rs.getInt("qna_id"));
				article.setGoods_id(rs.getInt("goods_id"));
				article.setGoods_title(rs.getString("goods_title"));
				article.setQna_writer(rs.getString("qna_writer"));
				article.setQna_content(rs.getString("qna_content"));
				article.setGroup_id(rs.getInt("group_id"));
				article.setQora(rs.getByte("qora"));
				article.setReply(rs.getByte("reply"));
				article.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, conn);
		}
		return article;
	} // end of updateGetArticle(qna_id)

	// QnA글 수정 수정처리에서 사용
	public int updateArticle(QnaDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			conn = DBUtil.getConnection();
			String sql = "update qna set qna_content=? where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getQna_content());
			pstmt.setInt(2, article.getQna_id());
			int i = pstmt.executeUpdate();

			if (i > 0) {
				result = 1; // 업데이트 성공
			} else {
				result = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, conn);
		}
		return result;
	} // end of updateArticle(QnaDataBean article)

	// QnA글 수정삭제처리시 사용
	public int deleteArticle(int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM qna WHERE qna_id=?");
			pstmt.setInt(1, qna_id);

			int i = pstmt.executeUpdate();
			if (i > 0) {
				result = 1; // 삭제 성공
			} else {
				result = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, conn);
		}
		return result;
	} // end of deleteArticle()
}
