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
import com.lec.sce.dto.TestCarApplyDto;

public class TestCarApplyDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	// 싱글톤
	private static TestCarApplyDao instance = new TestCarApplyDao();
	public static TestCarApplyDao getInstance() {
		return instance;
	}
	private TestCarApplyDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 시승신청 목록
	public ArrayList<TestCarApplyDto> applyList(int startRow, int endRow){
		ArrayList<TestCarApplyDto> lists = new ArrayList<TestCarApplyDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + 
				"    FROM (SELECT ROWNUM RN, A.*" + 
				"        FROM (SELECT T.*, CNAME FROM TESTCARAPPLY T, CAR C WHERE T.CNUM=C.CNUM ORDER BY TNUM DESC) A)  " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tnum          = rs.getInt("tnum");
				int cnum          = rs.getInt("cnum");
				String tname    = rs.getString("tname");
				String ttel    = rs.getString("ttel");
				String tgender    = rs.getString("tgender");
				String tarea    = rs.getString("tarea");
				String thall    = rs.getString("thall");
				String tselmind    = rs.getString("tselmind");
				String temail    = rs.getString("temail");
				String cname = rs.getString("cname");
				lists.add(new TestCarApplyDto(tnum, cnum, tname, ttel, tgender, tarea, thall, tselmind, temail, cname));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return lists;
	}
	// (2) 시승신청대수
	public int getApplyTotCnt() {
		int applyCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM TESTCARAPPLY";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			applyCnt = rs.getInt(1);
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
		return applyCnt;
	}
	// (3)시승신청
	public int apply(TestCarApplyDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TESTCARAPPLY (TNUM, CNUM, TNAME, TTEL, TGENDER, TAREA, THALL, TSELMIND, TEMAIL)" + 
				"    VALUES (TESTCARAPPLY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getCnum());
			pstmt.setString(2, dto.getTname());
			pstmt.setString(3, dto.getTtel());
			pstmt.setString(4, dto.getTgender());
			pstmt.setString(5, dto.getTarea());
			pstmt.setString(6, dto.getThall());
			pstmt.setString(7, dto.getTselmind());
			pstmt.setString(8, dto.getTemail());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("시승신청 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 시승신청 실패 :");
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
	
}
