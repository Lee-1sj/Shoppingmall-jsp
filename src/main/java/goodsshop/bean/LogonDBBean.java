package goodsshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbcon.DBUtil;
import work.crypt.SHA256;
import work.crypt.BCrypt;

public class LogonDBBean {

	// LogonDBBean 전역 객체 생성 <- 한개의 객제만 생성해서 공유
	private static LogonDBBean instance = new LogonDBBean();

	// LogonDBBean객체를 리턴하는 메소드
	public static LogonDBBean getInstance() {
		return instance;
	}

	private LogonDBBean() {
	}

	// 회원 가입 처리에서 사용하는 메소드
	public void insertMember(LogonDataBean member) {
		String sql = "insert into member (id, password, name, reg_date, address, tel) values (?,?,?,?,?,?)";
		SHA256 sha = SHA256.getInstance();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			String orgPass = member.getPasswd();
			System.out.println("member.getPasswd()=" + member.getPasswd());
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			pstmt.setString(1, member.getId());
			pstmt.setString(2, bcPass);
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getTel());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 로그인 폼 처리의 사용자 인증 처리에서 사용하는 메소드
	public int userCheck(String id, String passwd) {
		int x = -1;

		SHA256 sha = SHA256.getInstance();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select passwd from member where id = ?")) {

			String shaPass = sha.getSha256(passwd.getBytes());
			pstmt.setString(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) { // 해당 아이디가 있으면 수행
					String dbpasswd = rs.getString("passwd");
					if (BCrypt.checkpw(shaPass, dbpasswd)) {
						x = 1; // 인증 성공
					} else {
						x = 0; // 비밀번호 틀림
					}
				} else {
					x = -1; // 아이디 없음
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
	}

	// 아이디 중복 확인에서 아이디의 중복 여부를 확인하는 메소드
	public int confirmId(String id) {
		int x = -1;
		String sql = "select id from member where id = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) { // 아이디 존재
					x = 1; // 같은 아이디 있음
				} else {
					x = -1; // 같은 아이디 없음
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
	}

	// 주어진 id에 해당하는 회원 정보를 얻어내는 메소드
	public LogonDataBean getMember(String id) {
		LogonDataBean member = null;
		String sql = "select * from member where id = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) { // 해당 아이디에 대한 레코드가 존재
					member = new LogonDataBean(); // 데이터 저장빈 객체 생성
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return member; // 데이터 저장빈 객체 member 리턴
	}

	// 주어진 id, passwd에 해당하는 회원 정보를 얻어내는 메소드
	public LogonDataBean getMember(String id, String passwd) {
		LogonDataBean member = null;
		String sql = "select * from member where id = ?";
		SHA256 sha = SHA256.getInstance();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			String shaPass = sha.getSha256(passwd.getBytes());

			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) { // 해당 아이디에 대한 레코드가 존재
					String dbpasswd = rs.getString("passwd");
					// 사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
					if (BCrypt.checkpw(shaPass, dbpasswd)) {
						member = new LogonDataBean(); // 데이터저장빈 객체생성
						member.setId(rs.getString("id"));
						member.setName(rs.getString("name"));
						member.setReg_date(rs.getTimestamp("reg_date"));
						member.setAddress(rs.getString("address"));
						member.setTel(rs.getString("tel"));
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return member; // 데이터 저장빈 객체 member 리턴
	}

	// 회원 정보 수정을 처리하는 메소드
	public int updateMember(LogonDataBean member) {
		int x = -1;
		SHA256 sha = SHA256.getInstance(); // 오타 수정

		String selectSql = "select passwd from member where id = ?";
		String updateSql = "update member set name=?, address=?, tel=? where id=?";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement selectPstmt = conn.prepareStatement(selectSql)) {

			String shaPass = sha.getSha256(member.getPasswd().getBytes());

			selectPstmt.setString(1, member.getId());
			try (ResultSet rs = selectPstmt.executeQuery()) {
				if (rs.next()) { // 해당 아이디가 있으면 수행
					String dbpasswd = rs.getString("passwd");
					if (BCrypt.checkpw(shaPass, dbpasswd)) {
						try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
							updatePstmt.setString(1, member.getName());
							updatePstmt.setString(2, member.getAddress());
							updatePstmt.setString(3, member.getTel());
							updatePstmt.setString(4, member.getId());
							updatePstmt.executeUpdate();
							x = 1; // 회원정보 수정 처리 성공
						}
					} else {
						x = 0; // 회원정보 수정 처리 실패
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
	}

	// 회원 정보를 삭제하는 메소드
	public int deleteMember(String id, String passwd) {
		int x = -1;
		SHA256 sha = SHA256.getInstance();
		String selectSql = "select passwd from member where id = ?";
		String deleteSql = "delete from member where id = ?";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement selectPstmt = conn.prepareStatement(selectSql)) {
			String shaPass = sha.getSha256(passwd.getBytes());
			selectPstmt.setString(1, id);
			try (ResultSet rs = selectPstmt.executeQuery()) {
				if (rs.next()) {
					String dbpasswd = rs.getString("passwd");
					if (BCrypt.checkpw(shaPass, dbpasswd)) {
						try (PreparedStatement deletePstmt = conn.prepareStatement(deleteSql)) {
							deletePstmt.setString(1, id);
							deletePstmt.executeUpdate();
							x = 1; // 회원탈퇴처리 성공
						}
					} else {
						x = 0; // 회원탈퇴 처리 실패
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
	}
}