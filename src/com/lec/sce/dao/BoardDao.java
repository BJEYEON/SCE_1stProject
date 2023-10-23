package com.lec.sce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.sce.dto.BoardDto;

public class BoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	// 싱글톤
	private static BoardDao instance = new BoardDao();
	public static BoardDao getInstance() {
		return instance;
	}
	private BoardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<BoardDto> listBoard(int startRow, int endRow){
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM\r\n" + 
				"  (SELECT ROWNUM RN, A.* FROM (SELECT B.*, MNAME FROM BOARD B, MEMBER M\r\n" + 
				"                              WHERE B.MID=M.MID \r\n" + 
				"                              ORDER BY BGROUP DESC, BSTEP) A)\r\n" + 
				"  WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    bnum      = rs.getInt("bnum");
				String mid      = rs.getString("mid");
				String mname    = rs.getString("mname");
				String btitle   = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate= rs.getTimestamp("bdate");
				String bimage	= rs.getString("bimage");
				int    bhit     = rs.getInt("bhit");
				int    bgroup   = rs.getInt("bgroup");
				int    bstep    = rs.getInt("bstep");
				int    bindent  = rs.getInt("bindent");
				String bip      = rs.getString("bip");
				dtos.add(new BoardDto(bnum, mid, mname, btitle, bcontent, bdate, bimage, bhit, bgroup, bstep, bindent, bip));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}
	// (2) 글갯수
	public int getBoardTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM BOARD";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// (3) 글쓰기(원글쓰기)
	public int writeBoard(BoardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (BNUM, MID, BTITLE, BCONTENT, BIMAGE, BHIT, BGROUP, BSTEP, BINDENT, BIP)" + 
				"    VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, 0, BOARD_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getBtitle());
			pstmt.setString(3, dto.getBcontent());
			pstmt.setString(4, dto.getBimage());
			pstmt.setString(5, dto.getBip());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("원글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 원글쓰기 실패 :");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (4) hit 1회 올리기
	public void hitUp(int bnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 조회수 up 실패");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// (5) 글번호(fid)로 글전체 내용(BoardDto) 가져오기 - 글상세보기, 글수정뷰, 답변글쓰기뷰용
	public BoardDto getBoardNotHitUp(int bnum) {
		BoardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT B.*, MNAME" + 
				"  FROM BOARD B, MEMBER M WHERE B.MID=M.MID AND BNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mid = rs.getString("mid");
				String mname = rs.getString("mname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				String bimage = rs.getString("bimage");
				int    bhit = rs.getInt("bhit");
				int    bgroup= rs.getInt("bgroup");
				int    bstep= rs.getInt("bstep");
				int    bindent= rs.getInt("bindent");
				String bip = rs.getString("bip");
				dto = new BoardDto(bnum, mid, mname, btitle, bcontent, bdate, bimage, bhit, bgroup, bstep, bindent, bip);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dto;
	}
	// (6) 글 수정하기(bid, btitle, bcontent, Bimage, bdate(SYSDATE), bip 수정)
	public int modifyBoard(BoardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD" + 
				"    SET BTITLE = ?," + 
				"        BCONTENT = ?," + 
				"        BIMAGE = ?," + 
				"        BDATE = SYSDATE," + 
				"        BIP = ?" + 
				"    WHERE BNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBtitle());
			pstmt.setString(2, dto.getBcontent());
			pstmt.setString(3, dto.getBimage());
			pstmt.setString(4, dto.getBip());
			pstmt.setInt(5, dto.getBnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공":"글번호(bid) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 수정 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (7) 글 삭제하기(bnum로)
	public int deleteBoard(int bnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE BNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(bnum) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 삭제 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (8) 글 삭제하기(삭제하고자 하는 글의 답변글들도 모두 삭제하고 삭제된 글 갯수 return)
	public int deleteBoard(int bgroup, int bstep, int bindent) {
		int deleteCnt = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE BGROUP = ? AND (BSTEP>=? AND " + 
				"    BSTEP<(SELECT NVL(MIN(BSTEP),9999) " + 
				"          FROM BOARD WHERE BGROUP=? AND BSTEP>? AND BINDENT<=?))";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, bstep);
			pstmt.setInt(3, bgroup);
			pstmt.setInt(4, bstep);
			pstmt.setInt(5, bindent);
			deleteCnt = pstmt.executeUpdate();
			System.out.println(deleteCnt>=SUCCESS? deleteCnt+"개 글삭제성공":"글삭제실패");
			//postDelete(deleteCnt, bgroup, bstep); // 글삭제시 bstep 재조정
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return deleteCnt;
	}
	// (9) 답변글 쓰기 전 단계(원글의 fgroup과 같고, 원글의 fstep보다 크면 fstep을 하나 증가하기)
	private void preReplyBoardStep(int bgroup, int bstep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BSTEP = BSTEP +1" + 
					" WHERE BGROUP=? AND BSTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, bstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " preReplyStep에서 오류");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// (10) 답변글 쓰기
			//  답변글쓴이    : mid, btitle, bcontent, bimage
			//  시스템적으로 : mip
			//  원글             : mgroup, mstep, mindent
	public int reply(BoardDto dto) {
		int result = FAIL;
		preReplyBoardStep(dto.getBgroup(), dto.getBstep());
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (BNUM, MID, BTITLE, BCONTENT, BIMAGE, BGROUP, BSTEP, BINDENT, BIP)" + 
				"  VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getBtitle());
			pstmt.setString(3, dto.getBcontent());
			pstmt.setString(4, dto.getBimage());
			pstmt.setInt(5, dto.getBgroup());
			pstmt.setInt(6, dto.getBstep() + 1);
			pstmt.setInt(7, dto.getBindent() + 1);
			pstmt.setString(8, dto.getBip());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("답변글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 답변글쓰기 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (11) 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 글 모두 삭제하기
	public int preWithdrawalMemberStep(String mid) {
		int cntBoard = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE MID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			cntBoard = pstmt.executeUpdate();
			System.out.println(cntBoard+"개글 삭제 성공(회원탈퇴전)");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "탈퇴 전 글 삭제 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return cntBoard;
	}
}
