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
import com.lec.kinterior.dto.InteriorDto;
public class InteriorDao {
	public static final int FAIL = 0; // 시공사례 글쓰기 실패
	public static final int SUCCESS = 1; // 시공사례 글쓰기 성공
	private DataSource ds;
	private static InteriorDao instance = new InteriorDao();
	public static InteriorDao getInstance() {
		return instance;
	}
	private InteriorDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 1. 시공사례 글 목록 출력
	public ArrayList<InteriorDto> interiorList(int startRow, int endRow){
		ArrayList<InteriorDto> interiors = new ArrayList<InteriorDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + 
					"    (SELECT ROWNUM RN, P.* FROM (SELECT P.*, aNAME FROM INTERIOR_PRODUCT P, ADMIN A" + 
					"                                 WHERE P.aID = A.aID ORDER BY pRDATE DESC)P)" + 
					"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pid = rs.getInt("pid");
				String aid = rs.getString("aid");
				String ptitle = rs.getString("ptitle");
				String pcontent = rs.getString("pcontent");
				String pfilename = rs.getString("pfilename");
				Date prdate = rs.getDate("prdate");
				interiors.add(new InteriorDto(pid, aid, ptitle, pcontent, pfilename, prdate));
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
		return interiors;
	}
	// 2. 시공사례글쓰기(원글쓰기)
	public int writeInterior(InteriorDto idto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO INTERIOR_PRODUCT (pID, aID, pTITLE, pCONTENT, pFILENAME)" + 
				"    VALUES (INTERIOR_PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idto.getAid());
			pstmt.setString(2, idto.getPtitle());
			pstmt.setString(3, idto.getPcontent());
			pstmt.setString(4, idto.getPfilename());
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
	// 3. 시공사례글번호(pid)로 전체내용(InteriorDto) 가져오기 (상세보기용)
	public InteriorDto getInteriorContents(int pid) {
		InteriorDto idto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT P.*, aNAME FROM INTERIOR_PRODUCT P, ADMIN A WHERE P.aID = A.aID AND pID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aid = rs.getString("aid");
				String ptitle = rs.getString("ptitle");
				String pcontent = rs.getString("pcontent");
				String pfilename = rs.getString("pfilename");
				Date prdate = rs.getDate("prdate");
				idto = new InteriorDto(pid, aid, ptitle, pcontent, pfilename, prdate);
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
		return idto;
	}
	// 3-1. 시공사례글번호(pid)로 전체내용(InteriorDto) 가져오기 (수정용)
		public InteriorDto interiorModify_view(int pid) {
			InteriorDto idto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT P.*, aNAME FROM INTERIOR_PRODUCT P, ADMIN A WHERE P.aID = A.aID AND pID = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String aid = rs.getString("aid");
					String ptitle = rs.getString("ptitle");
					String pcontent = rs.getString("pcontent");
					String pfilename = rs.getString("pfilename");
					Date prdate = rs.getDate("prdate");
					idto = new InteriorDto(pid, aid, ptitle, pcontent, pfilename, prdate);
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
			return idto;
		}
	// 4. 시공사례글 수정하기
	public int interiorModify(InteriorDto idto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE INTERIOR_PRODUCT SET " + 
				"        pTITLE = ?," + 
				"        pCONTENT = ?," + 
				"        pFILENAME = ?" + 
				"    WHERE pID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idto.getPtitle());
			pstmt.setString(2, idto.getPcontent());
			pstmt.setString(3, idto.getPfilename());
			pstmt.setInt(4, idto.getPid());
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
	// 5. 시공사례글 삭제하기
	public int deleteInterior(int pid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM INTERIOR_PRODUCT WHERE pID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + pid +"번 글 삭제 실패");
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
	public int interiorTotCnt() {
		int interiorTotCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*)CNT FROM INTERIOR_PRODUCT";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			interiorTotCnt = rs.getInt("cnt");
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
		return interiorTotCnt;
	}
}