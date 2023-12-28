package member;

import java.sql.*;
import common.MyJavaUtil;

public class MemberDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private final String MEMBER_LOGIN = "select * from member where id = ?";

	// 로그인 메서드
	public MemberDTO login(MemberDTO dto) {
		MemberDTO member = null;

		try {
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_LOGIN);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				member = new MemberDTO();

				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setRegdate(rs.getDate("REGDATE"));
				member.setAddr(rs.getString("ADDR"));
				member.setRole(rs.getString("ROLE"));
			}

		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		} finally {
			MyJavaUtil.close(rs, pstmt, conn);
		}
		return member;
	}

}
