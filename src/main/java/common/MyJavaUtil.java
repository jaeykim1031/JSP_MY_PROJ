package common;

import java.sql.*;

public class MyJavaUtil {

	public MyJavaUtil() {
	}

	public static Connection getConnection() {
		Connection conn = null;

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "C##HR28", "1234");

		} catch (Exception e) {
			System.out.println("DB 연결이 실패 했습니다. ");
			e.printStackTrace();
		}

		return conn;
	}

	public static void close(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println("pstmt 제거 실패");
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("conn 제거 실패");
			}
		}
	}

	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("rs 제거 중 오류 발생");
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					System.out.println("pstmt 제거 중 오류 발생. ");
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println("conn 제거 중 오류 발생 ");
				}
			}

		}

	}

}
