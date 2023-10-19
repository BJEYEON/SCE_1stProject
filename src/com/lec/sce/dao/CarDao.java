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
import com.lec.sce.dto.CarDto;

public class CarDao {
	private DataSource ds;
	// 싱글톤
	private static CarDao instance = new CarDao();
	public static CarDao getInstance() {
		return instance;
	}
	private CarDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 차량목록
	public ArrayList<CarDto> CarList(){
		ArrayList<CarDto> lists = new ArrayList<CarDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CAR ORDER BY CNUM";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnum          = rs.getInt("cnum");
				String cname    = rs.getString("cname");
				String coil    = rs.getString("coil");
				String cengine    = rs.getString("cengine");
				String ctrance   = rs.getString("ctrance");
				String chp    = rs.getString("chp");
				String ccc    = rs.getString("ccc");
				String cyear    = rs.getString("cyear");
				String cimage    = rs.getString("cimage");
				lists.add(new CarDto(cnum, cname, coil, cengine, ctrance, chp, ccc, cyear, cimage));
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
	// (2) 차량대수
	public int carTotCnt() {
		int carCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM CAR";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			carCnt = rs.getInt(1);
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
		return carCnt;
	}
		
	// (3) 차량상세정보
	public CarDto getCar(int cnum) {
		CarDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM CAR WHERE CNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//String cnum = rs.getString("cnum");
				String cname = rs.getString("cname");
				String coil = rs.getString("coil");
				String cengine = rs.getString("cengine");
				String ctrance = rs.getString("ctrance");
				String chp = rs.getString("chp");
				String ccc = rs.getString("ccc");
				String cyear = rs.getString("cyear");
				String cimage = rs.getString("cimage");
				dto = new CarDto(cnum, cname, coil, cengine, ctrance, chp, ccc, cyear, cimage);
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
}
