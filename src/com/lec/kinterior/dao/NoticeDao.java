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
import com.lec.kinterior.dto.NoticeDto;
public class NoticeDao {
	public static final int FAIL = 0; // 공지사항 글쓰기 실패
	public static final int SUCCESS = 1; // 공지사항 글쓰기 성공
	private DataSource ds;
	private static NoticeDao instance = new NoticeDao();
	public static NoticeDao getInstance() {
		return instance;
	}
	private NoticeDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 1. 공지사항 글 목록 출력
	public ArrayList<NoticeDto> noticeList(int startRow, int endRow){
		ArrayList<NoticeDto> notice = new ArrayList<NoticeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, N.* FROM (SELECT N.*, aNAME FROM NOTICE_BOARD N, ADMIN A " + 
				"                                 WHERE N.aID = A.aID ORDER BY nRDATE DESC)N)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int nid = rs.getInt("nid");
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Date nrdate = rs.getDate("nrdate");
				notice.add(new NoticeDto(nid, aid, ntitle, ncontent, nrdate));
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
		return notice;
	}
	// 2. 공지사항 글쓰기
	public int writeNotice(NoticeDto ndto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICE_BOARD (nID, aID, nTITLE, nCONTENT)" + 
					"    VALUES (NOTICEBOARD_SEQ.NEXTVAL, ?, ?, " + 
					"            ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ndto.getAid());
			pstmt.setString(2, ndto.getNtitle());
			pstmt.setString(3, ndto.getNcontent());
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
	// 3. 공지사항글번호(nID)로 전체내용(NoticeDto) 가져오기 (상세보기용)
	public NoticeDto getNoticeContent(int nid) {
		NoticeDto ndto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, aNAME FROM NOTICE_BOARD N, ADMIN A WHERE N.aID = A.aID AND nID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Date nrdate = rs.getDate("nrdate");
				ndto = new NoticeDto(nid, aid, ntitle, ncontent, nrdate);
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
		return ndto;
	}
	// 3-1. 공지사항글번호(nID)로 전체내용(consultantDto) 가져오기 (수정용)
	public NoticeDto noticeModify_View(int nid) {
		NoticeDto ndto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, aNAME FROM NOTICE_BOARD N, ADMIN A WHERE N.aID = A.aID AND nID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				Date nrdate = rs.getDate("nrdate");
				ndto = new NoticeDto(nid, aid, ntitle, ncontent, nrdate);
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
		return ndto;
	}
	// 4. 공지사항글 수정하기
	public int noticeModify(NoticeDto ndto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE_BOARD SET " + 
				"        nTITLE = ?," + 
				"        nCONTENT = '?" + 
				"    WHERE nID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ndto.getNtitle());
			pstmt.setString(2, ndto.getNcontent());
			pstmt.setInt(3, ndto.getNid());
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
	// 5. 공지사항글 삭제하기
	public int deleteNotice(int nid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICE_BOARD WHERE nID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + nid +"번 글 삭제 실패");
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
