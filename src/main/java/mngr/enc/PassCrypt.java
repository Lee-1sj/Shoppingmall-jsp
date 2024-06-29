package mngr.enc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class PassCrypt {
    private static PassCrypt instance = new PassCrypt();
    
	public static PassCrypt getInstance() {
		return instance;
	}
	    
	private PassCrypt() {}
	
    public void cryptProcess(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        
        //SHA-256를 사용하는 SHA256클래스의 객체를 얻어낸다.
        SHA256 sha = SHA256.getInstance();
        
        try {
            conn = DBUtil.getConnection();
            
            pstmt = conn.prepareStatement(
                	"select managerId, managerPasswd from manager");
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	String id = rs.getString("managerId");
            	String orgPass = rs.getString("managerPasswd");
            	
            	String shaPass = sha.getSha256(orgPass.getBytes());

            	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
            	
            	pstmt = conn.prepareStatement(
                    "update manager set managerPasswd=? where managerId=?");
                pstmt.setString(1, bcPass);
                pstmt.setString(2, id);
                pstmt.executeUpdate();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	DBUtil.closeResource(rs, pstmt, conn);
        }
    }
}