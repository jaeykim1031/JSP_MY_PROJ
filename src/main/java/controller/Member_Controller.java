package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import m_board.M_BoardDTO;
import member.MemberDAO;
import member.MemberDTO;
import m_board.M_BoardDAO;


@WebServlet("*.us")
public class Member_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Member_Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		// 로그인 
		if (path.equals("/login.us"))
		{
			System.out.println("로그인 요청");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPassword(password);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO user = new MemberDTO();
			
			user = dao.login(dto);
				
				if(user == null)
				{
					System.out.println("로그인 실패");
					response.sendRedirect("LoginForm.jsp");
				}
				else
				{
					System.out.println("로그인 성공");
					HttpSession session = request.getSession();
					session.setAttribute("id", user.getId());
					session.setAttribute("role", user.getRole());
					
					response.sendRedirect("LoginForm.jsp");
				}
			
		}
		else if(path.equals("/logout.us")) {
			System.out.println("로그아웃 요청");
			
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("http://localhost:8181/JSP_MY_PROJ");
		}
		
		else if (path.equals("/insertMember.us"))
		{
			System.out.println("insertMember.us 요청 처리");
			
			//클라이언트 요청
			String id = request.getParameter("id"); 
			String password = request.getParameter("password"); 
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");
			String role = request.getParameter("role");
			
			// setter 입력
			MemberDTO dto = new MemberDTO(); 
			dto.setId(id); 
			dto.setPassword(password); 
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(addr);
			dto.setRole(role);
			dto.setId(id);
			
			MemberDAO dao = new MemberDAO();
			dao.insertMember(dto);
			
			response.sendRedirect("getMemberList.us");
		}
		else if (path.equals("/getMember.us")) {
			System.out.println("/getMemberList.us 요청");
			
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			List<MemberDTO> memberList = new ArrayList<>();
			memberList = dao.getMemberList(dto);
			
			HttpSession session = request.getSession();
			session.setAttribute("memberList", memberList); 
			
			response.sendRedirect("getMemberList.jsp"); 
		}
		
		else if (path.equals("/getMember.us"))
		{
			System.out.println("/getMember.us 요청");
			
			String id = request.getParameter("id");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			
			MemberDAO dao = new MemberDAO();
			
			MemberDTO member = new MemberDTO();
			member = dao.getMember(dto);
			
			HttpSession session = request.getSession(); 
			session.setAttribute("member", member); 
			
			response.sendRedirect("getMember.jsp"); 
		}
		else if (path.equals("/updateMember.us"))
		{
			System.out.println("/updateMember.us 요청");

			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");
			String role = request.getParameter("role");
			String id = request.getParameter("id"); 
			
			MemberDTO dto = new MemberDTO(); 
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(addr);
			dto.setRole(role);
			dto.setId(id); 
			
			MemberDAO dao = new MemberDAO (); 
			dao.updateMember(dto); 
			
			response.sendRedirect("getMemberList.us");
		}
		else if (path.equals("/deleteMember.us")
		{
			System.out.println("/deleteMember.us 요청");
			
			String id = request.getParameter("id"); 
			MemberDTO dto = new MemberDTO(); 
			dto.setId(id); 
			
			MemberDAO dao = new MemberDAO(); 
			dao.deleteMember(dto); 

			response.sendRedirect("getMemberList.us"); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
