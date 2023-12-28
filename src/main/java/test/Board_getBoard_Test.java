package test;

import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

public class Board_getBoard_Test {

	public static void main(String[] args) {
		// 1. dto에 조회 할 seq 값만 할당 후 dao.getBoard(dto)
		M_BoardDTO dto = new M_BoardDTO();
		dto.setId(4);
		
		// 2. dao 메서드 호출 getBoard(dto)
		M_BoardDAO dao = new M_BoardDAO();
		
		// 리턴으로 돌려 받는 변수 선언
		M_BoardDTO board = new M_BoardDTO();
		
		board = dao.getBoard(dto);
		
		System.out.println(board);
	}

}
