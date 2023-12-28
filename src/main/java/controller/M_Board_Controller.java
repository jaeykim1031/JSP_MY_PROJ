package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import m_board.M_BoardDTO;
import m_board.M_BoardDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("*.do")

public class M_Board_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public M_Board_Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path 변수값 : " + path);

		if (path.equals("/insertBoard.do")) {

			String m_title = request.getParameter("m_title");
			String m_write = request.getParameter("m_write");
			String m_cont = request.getParameter("m_cont");

			// 클라이언트에서 받은 값을 DTO에 setter 설정
			M_BoardDTO dto = new M_BoardDTO();
			dto.setM_title(m_title);
			dto.setM_write(m_write);
			dto.setM_cont(m_cont);

			// DAO에 insert
			M_BoardDAO dao = new M_BoardDAO();
			dao.insertBoard(dto);

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/getBoardList.do")) {
			System.out.println("내부 블락 : " + path);
			M_BoardDTO dto = new M_BoardDTO();
			M_BoardDAO dao = new M_BoardDAO();

			List<M_BoardDTO> boardList = new ArrayList<>();

			boardList = dao.getBoardList(dto);
			HttpSession session = request.getSession();

			session.setAttribute("boardList", boardList);
			
			response.sendRedirect("getBoardList.jsp");

		} else if (path.equals("/getBoard.do")) {
			System.out.println("/getBoard.do 요청");

			int id = Integer.parseInt(request.getParameter("id"));

			M_BoardDTO dto = new M_BoardDTO();
			dto.setId(id);

			M_BoardDAO dao = new M_BoardDAO();

			M_BoardDTO board = new M_BoardDTO();
			board = dao.getBoard(dto);

			HttpSession session = request.getSession();
			session.setAttribute("board", board);

			response.sendRedirect("getBoard.jsp");

		} else if (path.equals("/updateBoard.do")) {
			System.out.println("/updateBoard.do 요청");

			String title = request.getParameter("m_title");
			String write = request.getParameter("m_write");
			String cont = request.getParameter("m_cont");
			int id = Integer.parseInt(request.getParameter("id"));


			M_BoardDTO dto = new M_BoardDTO();
			
			dto.setM_title(title);
			dto.setM_write(write);
			dto.setM_cont(cont);
			dto.setId(id);

			M_BoardDAO dao = new M_BoardDAO();
			dao.updateBoard(dto);

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/deleteBoard.do")) {
			System.out.println("/deleteBoard.do 요청");
			// 로직 처리

			String s_id = request.getParameter("id");
			int id = Integer.parseInt(s_id);

			// 2. 변수의 값을 BoardDTO에 전달
			M_BoardDTO dto = new M_BoardDTO();
			dto.setId(id);

			// 3. BoardDAO의 메서드 호출 : deleteBoard(dto)
			M_BoardDAO dao = new M_BoardDAO();
			dao.deleteBoard(dto);

			// 4. View page로 이동
			response.sendRedirect("getBoardList.do");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
