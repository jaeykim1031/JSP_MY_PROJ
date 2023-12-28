package test;

import java.util.ArrayList;
import java.util.List;

import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

public class M_Board_getBoardList_Test {
	public static void main(String[] args) {
		
		M_BoardDTO dto = new M_BoardDTO(); 
		
		M_BoardDAO dao = new M_BoardDAO(); 
		
		List<M_BoardDTO> boardList = new ArrayList<>(); 
		
		boardList = dao.getBoardList(dto); 
		
		for ( M_BoardDTO k : boardList) {
			System.out.println(k);
		}
	}
}
