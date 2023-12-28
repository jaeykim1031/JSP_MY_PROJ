package m_board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.MyJavaUtil;

public class M_BoardDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// sql 쿼리 상수 처리
	private final String BOARD_INSERT =
			"insert into m_board ( id, m_title, m_write, m_cont )"
			+ " values ((select nvl(max(id),0) +1 from m_board ), ?, ?, ?)";

	private final String BOARD_LIST = "select * from m_board order by id desc";
	
	private final String BOARD_GET = "select * from m_board where id = ?";

	private final String BOARD_UPDATE = "update m_board set m_title = ?, m_write = ?, m_cont = ? where id = ?";

	private final String BOARD_DELETE = "delete m_board where id = ?";

	private final String ADD_CNT = "update m_board set cnt = cnt + 1 where id = ?";

	// 입력
	public void insertBoard(M_BoardDTO dto) {
		try {
			
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);

			pstmt.setString(1, dto.getM_title());
			pstmt.setString(2, dto.getM_write());
			pstmt.setString(3, dto.getM_cont());

			pstmt.executeUpdate();
			System.out.println("insert 성공");

		} catch (Exception e) {
			System.out.println("insert 실패");
			e.printStackTrace();

		} finally {
			MyJavaUtil.close(pstmt, conn);
		}

	}

	// 전체 레코드 출력
	public List<M_BoardDTO> getBoardList(M_BoardDTO dto) {
		System.out.println("getBoardList  호출됨 ");
		
		List<M_BoardDTO> boardlist = new ArrayList<>();

		try {
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_LIST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				M_BoardDTO board = new M_BoardDTO();

				board.setId(rs.getInt("ID"));
				board.setM_title(rs.getString("M_TITLE"));
				board.setM_write(rs.getString("M_WRITE"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("cnt"));

				boardlist.add(board);
			}
			System.out.println("DB select 성공");
			
		} catch (Exception e) {
			System.out.println("DB select 실패");
			e.printStackTrace();
		} finally {
			MyJavaUtil.close(rs, pstmt, conn);
		}
		return boardlist;
	}

	// 글 상세 내용
	public M_BoardDTO getBoard(M_BoardDTO dto) {

		addCNT(dto);

		M_BoardDTO board = new M_BoardDTO();

		try {
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);

			pstmt.setInt(1, dto.getId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				board.setId(rs.getInt("ID"));
				board.setM_title(rs.getString("TITLE"));
				board.setM_write(rs.getString("WRITE"));
				board.setM_cont(rs.getString("M_CONT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));

			}
		} catch (Exception e) {
			System.out.println("내용 조회 실패");
			e.printStackTrace();
		} finally {
			MyJavaUtil.close(rs, pstmt, conn);
		}
		return board;
	}

	// 글 수정
	public void updateBoard(M_BoardDTO dto) {

		try {
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);

			pstmt.setString(1, dto.getM_title());
			pstmt.setString(2, dto.getM_write());
			pstmt.setString(3, dto.getM_cont());
			pstmt.setInt(4, dto.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(" 게시글 수정 실패 ");
			e.printStackTrace();

		} finally {
			MyJavaUtil.close(pstmt, conn);
		}
	}

	// 글 삭제
	public void deleteBoard(M_BoardDTO dto) {
		try {
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);

			pstmt.setInt(1, dto.getId());

			pstmt.executeUpdate(); 

		} catch (Exception e) {
			System.out.println("게시글 삭제 실패");
			e.printStackTrace();

		} finally {
			MyJavaUtil.close(pstmt, conn);
		}
	}

	// 조회수 증가
	public void addCNT(M_BoardDTO dto) {

		try {
			conn = MyJavaUtil.getConnection();
			pstmt = conn.prepareStatement(ADD_CNT);
			pstmt.setInt(1, dto.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" 조회수 증가 실패");
			e.printStackTrace();
		} finally {
			MyJavaUtil.close(pstmt, conn);
		}

	}

}
