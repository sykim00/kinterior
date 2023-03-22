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
import com.lec.kinterior.dto.ReviewBoardDto;
public class ReviewBoardDao {
	public static final int FAIL = 0; // 리뷰 글쓰기 실패
	public static final int SUCCESS = 1; // 리뷰 글쓰기 성공
	private DataSource ds;
	private static ReviewBoardDao instance = new ReviewBoardDao();
	public static ReviewBoardDao getInstance() {
		return instance;
	}
	private ReviewBoardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 1. 리뷰글 목록 출력
	public ArrayList<ReviewBoardDto> reviewList(int startRow, int endRow){
		ArrayList<ReviewBoardDto> reviews = new ArrayList<ReviewBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, R.* FROM (SELECT R.*, mNAME FROM REVIEW_BOARD R, MEMBER M " + 
				"                                WHERE R.mID = M.mID ORDER BY rID DESC)R)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("rid");
				String mid = rs.getString("mid");
				String rtitle = rs.getString("rtitle");
				String rcontent = rs.getString("rcontent");
				String rphoto = rs.getString("rphoto");
				Date rdate = rs.getDate("rdate");
				int rhit = rs.getInt("rhit");
				String rip = rs.getString("rip");
				reviews.add(new ReviewBoardDto(rid, mid, rtitle, rcontent, rphoto, rdate, rhit, rip));
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
		return reviews;
	}
	// 2. 리뷰글 갯수
	public int reviewTotCnt() {
		int reviewTotCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*)CNT FROM REVIEW_BOARD";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			reviewTotCnt = rs.getInt("cnt");
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
		return reviewTotCnt;
	}
	// 3. 리뷰글쓰기(원글쓰기)
	public int writeReview(ReviewBoardDto rdto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW_BOARD (rID, mID, rTITLE, rCONTENT, rPHOTO, rIP)" + 
				"    VALUES (REVIEWBOARD_SEQ.NEXTVAL,?, ?, " + 
				"            ?, " + 
				"            ? , ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rdto.getMid());
			pstmt.setString(2, rdto.getRtitle());
			pstmt.setString(3, rdto.getRcontent());
			pstmt.setString(4, rdto.getRphoto());
			pstmt.setString(5, rdto.getRip());
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
	// 4. 리뷰글번호(rid)로 전체내용(reviewBoard) 가져오기 (상세보기용)
	public ReviewBoardDto getReivewBoard(int rid) {
		ReviewBoardDto rdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, mNAME FROM REVIEW_BOARD R, MEMBER M WHERE R.mID = M.mID AND rID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mid = rs.getString("mid");
				String rtitle = rs.getString("rtitle");
				String rcontent = rs.getString("rcontent");
				String rphoto = rs.getString("rphoto");
				Date rdate = rs.getDate("rdate");
				int rhit = rs.getInt("rhit");
				String rip = rs.getString("rip");
				rdto = new ReviewBoardDto(rid, mid, rtitle, rcontent, rphoto, rdate, rhit, rip);
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
		return rdto;
	}
	// 4-1. 리뷰글번호(rid)로 전체내용(reviewBoard) 가져오기 (수정용)
	public ReviewBoardDto review_Modify(int rid) {
		ReviewBoardDto rdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, mNAME FROM REVIEW_BOARD R, MEMBER M WHERE R.mID = M.mID AND rID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mid = rs.getString("mid");
				String rtitle = rs.getString("rtitle");
				String rcontent = rs.getString("rcontent");
				String rphoto = rs.getString("rphoto");
				Date rdate = rs.getDate("rdate");
				int rhit = rs.getInt("rhit");
				String rip = rs.getString("rip");
				rdto = new ReviewBoardDto(rid, mid, rtitle, rcontent, rphoto, rdate, rhit, rip);
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
		return rdto;
	}
	// 5. 리뷰글(rid) HIT수 올리기
	public void hitUpReview(int rid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW_BOARD SET rHIT = rHIT +1 WHERE rID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "조회수 올리기 실패");
		}
	}
	// 6. 리뷰글 수정하기
	public int reviewModify(ReviewBoardDto rdto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW_BOARD SET " + 
					"        rTITLE = ?," + 
					"        rCONTENT = ?," + 
					"        rPHOTO = ?," + 
					"        rIP = ?" + 
					"    WHERE rID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rdto.getRtitle());
			pstmt.setString(2, rdto.getRcontent());
			pstmt.setString(3, rdto.getRphoto());
			pstmt.setString(4, rdto.getRip());
			pstmt.setInt(5, rdto.getRid());
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
	// 7. 리뷰글 삭제하기
	public int deleteReivew(int rid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REVIEW_BOARD WHERE rID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + rid +"번 글 삭제 실패");
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
	// 8. 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 리뷰글 모두 삭제하기
	public int preWithdrawalMemberStep(String mid) {
		int reviewCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REVIEW_BOARD WHERE mID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			reviewCnt = pstmt.executeUpdate();
			System.out.println(reviewCnt + "개 글 삭제 성공");
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
		return reviewCnt;
	}
}
