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
import com.lec.kinterior.dto.ConsultantDto;
public class ConsultantDao {
	public static final int FAIL = 0; // 견적문의 글쓰기 실패
	public static final int SUCCESS = 1; // 견적문의 글쓰기 성공
	private DataSource ds;
	private static ConsultantDao instance = new ConsultantDao();
	public static ConsultantDao getInstance() {
		return instance;
	}
	private ConsultantDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 1. 견적문의 글 목록 출력
	public ArrayList<ConsultantDto> consultantList(int startRow, int endRow){
		ArrayList<ConsultantDto> consultants = new ArrayList<ConsultantDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT V.*, " + 
					"    (SELECT mNAME FROM MEMBER M WHERE V.mID = M.mID) mNAME," + 
					"    (SELECT aNAME FROM ADMIN A WHERE V.aID = A.aID) aNAME" + 
					"        FROM (SELECT ROWNUM RN, C.*" + 
					"                FROM (SELECT * FROM CONSULTANT ORDER BY cGROUP DESC, cINDENT, cRDATE DESC)C)V" + 
					"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cid = rs.getInt("cid");
				String mid = rs.getString("mid");
				String mname = rs.getString("mname");
				String aid = rs.getString("aid");
				String aname = rs.getString("aname");
				String ctitle = rs.getString("ctitle");
				String ccontent = rs.getString("ccontent");
				String cfilename = rs.getString("cfilename");
				Date crdate = rs.getDate("crdate");
				int cgroup = rs.getInt("cgroup");
				int cstep = rs.getInt("cstep");
				int cindent = rs.getInt("cindent");
				String cip = rs.getString("cip");
				consultants.add(new ConsultantDto(cid, mid, mname, aid, aname, ctitle, ccontent, cfilename, crdate, cgroup, cstep, cindent, cip));
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
		return consultants;
	}
	// 2. 견적문의 글 갯수
	public int conTotCnt() {
		int conTotCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*)CNT FROM CONSULTANT";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			conTotCnt = rs.getInt("cnt");
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
		return conTotCnt;
	}
	// 3. 견적문의 글쓰기(원글쓰기)
	public int writeConsultant(ConsultantDto consDto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)\r\n" + 
					"    VALUES (CONSULTANT_SEQ.NEXTVAL,?, ?, ?, ?, ?, CONSULTANT_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, consDto.getMid());
			pstmt.setString(2, consDto.getAid());
			pstmt.setString(3, consDto.getCtitle());
			pstmt.setString(4, consDto.getCcontent());
			pstmt.setString(5, consDto.getCfilename());
			pstmt.setString(6, consDto.getCip());
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
	// 4. 견적문의 글번호(cid)로 전체내용(consultantDto) 가져오기 (상세보기용)
	public ConsultantDto getConsultantDto(int cid) {
		ConsultantDto cdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT V.*, (SELECT mNAME FROM MEMBER M WHERE V.mID = M.mID) mNAME," + 
					"    (SELECT aNAME FROM ADMIN A WHERE V.aID = A.aID) aNAME" + 
					"    FROM (SELECT * FROM CONSULTANT)V" + 
					"    WHERE cID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mid = rs.getString("mid");
				String mname = rs.getString("mname");
				String aid = rs.getString("aid");
				String aname = rs.getString("aname");
				String ctitle = rs.getString("ctitle");
				String ccontent = rs.getString("ccontent");
				String cfilename = rs.getString("cfilename");
				Date crdate = rs.getDate("crdate");
				int cgroup = rs.getInt("cgroup");
				int cstep = rs.getInt("cstep");
				int cindent = rs.getInt("cindent");
				String cip = rs.getString("cip");
				cdto = new ConsultantDto(cid, mid, mname, aid, aname, ctitle, ccontent, cfilename, crdate, cgroup, cstep, cindent, cip);
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
		return cdto;
	}
	// 4. 견적문의 글번호(cid)로 전체내용(consultantDto) 가져오기 (답변글, 수정용)
		public ConsultantDto consModify_reply(int cid) {
			ConsultantDto cdto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT V.*, (SELECT mNAME FROM MEMBER M WHERE V.mID = M.mID) mNAME," + 
						"    (SELECT aNAME FROM ADMIN A WHERE V.aID = A.aID) aNAME" + 
						"    FROM (SELECT * FROM CONSULTANT)V" + 
						"    WHERE cID = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String mid = rs.getString("mid");
					String mname = rs.getString("mname");
					String aid = rs.getString("aid");
					String aname = rs.getString("aname");
					String ctitle = rs.getString("ctitle");
					String ccontent = rs.getString("ccontent");
					String cfilename = rs.getString("cfilename");
					Date crdate = rs.getDate("crdate");
					int cgroup = rs.getInt("cgroup");
					int cstep = rs.getInt("cstep");
					int cindent = rs.getInt("cindent");
					String cip = rs.getString("cip");
					cdto = new ConsultantDto(cid, mid, mname, aid, aname, ctitle, ccontent, cfilename, crdate, cgroup, cstep, cindent, cip);
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
			return cdto;
		}
	// 5. 견적문의 글 수정하기
	public int consultantModify(ConsultantDto cdto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CONSULTANT SET " + 
				"        cTITLE = ?," + 
				"        cCONTENT = ?," + 
				"        cFILENAME = ?," + 
				"        cIP = ?" + 
				"    WHERE cID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cdto.getCtitle());
			pstmt.setString(2, cdto.getCcontent());
			pstmt.setString(3, cdto.getCfilename());
			pstmt.setString(4, cdto.getCip());
			pstmt.setInt(5, cdto.getCid());
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
	// 6. 견적문의 글 삭제하기
	public int deleteConsultant(int cgroup, int cstep, int cindent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CONSULTANT " + 
					"                WHERE cGROUP = ? AND (cSTEP >=? AND cSTEP < (SELECT NVL(MIN(cSTEP),99) " + 
					"                    FROM CONSULTANT WHERE cGROUP = ? AND cSTEP > ? AND cINDENT <=?))";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cgroup);
			pstmt.setInt(2, cstep);
			pstmt.setInt(3, cgroup);
			pstmt.setInt(4, cstep);
			pstmt.setInt(5, cindent);
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
	// 7. 견적문의 답변글 쓰기 전단계
	private void preReplystep(int cgroup, int cstep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CONSULTANT SET cSTEP = cSTEP + 1 WHERE cGROUP = ? AND cSTEP > ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cgroup);
			pstmt.setInt(2, cstep);
			pstmt.executeUpdate();
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
	}
	// 8. 견적문의 답변글 쓰기
	public int consultantReply(ConsultantDto cdto) {
		int result = FAIL;
		preReplystep(cdto.getCgroup(), cdto.getCstep());
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)" + 
					"    VALUES (CONSULTANT_SEQ.NEXTVAL, ?, ?, " + 
					"            ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cdto.getMid());
			pstmt.setString(2, cdto.getAid());
			pstmt.setString(3, cdto.getCtitle());
			pstmt.setString(4, cdto.getCcontent());
			pstmt.setString(5, cdto.getCfilename());
			pstmt.setInt(6, cdto.getCgroup());
			pstmt.setInt(7, cdto.getCstep()+1);
			pstmt.setInt(8, cdto.getCindent()+1);
			pstmt.setString(9, cdto.getCip());
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
	// 9. 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 견적문의 글 모두 삭제하기
	public int preWithdrawalMemberStep(String mid) {
		int consultantCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CONSULTANT WHERE mID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			consultantCnt = pstmt.executeUpdate();
			System.out.println(consultantCnt + "개 글 삭제 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"글 삭제 실패");
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return consultantCnt;
	}
}
