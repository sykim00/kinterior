package com.lec.kinterior.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.lec.kinterior.dto.MemberDto;
public class MemberDao {
	public static final int FAIL = 0; // 회원가입 실패, 정보수정 실패
	public static final int SUCCESS = 1; // 회원가입 성공, 정보수정 성공
	public static final int MEMBER_EXISTENT = 0; // 아이디 중복
	public static final int MEMBER_NONEXISTENT = 1; // 아이디 중복x
	public static final int LOGIN_FAIL = 0; // 로그인 실패
	public static final int LOGIN_SUCCESS = 1; // 로그인 성공
	private DataSource ds;
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 1. 아이디 중복확인
	public int midConfirm(String mid) {
		int result = MEMBER_EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE mID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = MEMBER_EXISTENT;
			}else {
				result = MEMBER_NONEXISTENT;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 2. 회원가입
	public int joinMember(MemberDto member) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)" + 
					"    VALUES (?, ?, ?, ?, ?,?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMpw());
			pstmt.setString(3, member.getMname());
			pstmt.setString(4, member.getMtel());
			pstmt.setDate(5, member.getMbirth());
			pstmt.setString(6, member.getMemail());
			pstmt.setString(7, member.getMgender());
			pstmt.setString(8, member.getMaddress());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 3. 전체 등록된 회원수
	public int getMemberTot() {
		int memberTot = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*)CNT FROM MEMBER";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			memberTot = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return memberTot;
	}
	// 4. 로그인
	public int memberLogin(String mid, String mpw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE mID = ? AND mPW = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			}else {
				result = LOGIN_FAIL;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 5.mid로 dto가져오기 (로그인 성공시 session에 넣기 위해)
	public MemberDto getMember(String mid) {
		MemberDto mdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE mID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mpw = rs.getString("mpw");
				String mname = rs.getString("mname");
				String mtel = rs.getString("mtel");
				Date mbirth = rs.getDate("mbirth");
				String memail = rs.getString("memail");
				String mgender = rs.getString("mgender");
				String maddress = rs.getString("maddress");
				Date mrdate = rs.getDate("mrdate");
				mdto = new MemberDto(mid, mpw, mname, mtel, mbirth, memail, mgender, maddress, mrdate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return mdto;
	}
	// 6. 회원정보 수정
	public int memberModify(MemberDto member) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET " + 
				"        mPW = ?," + 
				"        mNAME = ?," + 
				"        mTEL = ?," + 
				"        mBIRTH = ?," + 
				"        mEMAIL = ?," + 
				"        mGENDER = ?," + 
				"        mADDRESS = ?" + 
				"    WHERE mID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMpw());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMtel());
			pstmt.setDate(4, member.getMbirth());
			pstmt.setString(5, member.getMemail());
			pstmt.setString(6, member.getMgender());
			pstmt.setString(7, member.getMaddress());
			pstmt.setString(8, member.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	//  7. 회원리스트
	public ArrayList<MemberDto> getMemberList(int startRow, int endRow){
		ArrayList<MemberDto> mdtos = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY mRDATE DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mid = rs.getString("mid");
				String mpw = rs.getString("mpw");
				String mname = rs.getString("mname");
				String mtel = rs.getString("mtel");
				Date mbirth = rs.getDate("mbirth");
				String memail = rs.getString("memail");
				String mgender = rs.getString("mgender");
				String maddress = rs.getString("maddress");
				Date mrdate = rs.getDate("mrdate");
				mdtos.add(new MemberDto(mid, mpw, mname, mtel, mbirth, memail, mgender, maddress, mrdate));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return mdtos;
	}
	// 8. 회원탈퇴
	public int deleteMember(String mid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM MEMBER WHERE mID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
