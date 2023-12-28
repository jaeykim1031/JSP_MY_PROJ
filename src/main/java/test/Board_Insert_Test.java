package test;

import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

public class Board_Insert_Test {

	public static void main(String[] args) {
		
		// 1. DTO 객체 생성 후 값 입력 
		M_BoardDTO dto = new M_BoardDTO();
		
			// dto에 setter를 사용해서 값 입력
			dto.setM_title("TEST TITLE 444 ");
			dto.setM_write("user2");
			dto.setM_cont("TEST CONTENT2231312");
		
		// 2. DAO 객체에 insertBoard(dto)
		M_BoardDAO dao = new M_BoardDAO();
		
		dao.insertBoard(dto);
		
		
		
		
	}

}
