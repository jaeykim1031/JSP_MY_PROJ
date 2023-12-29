package member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.MyJavaUtil;

public class MemberDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private final String MEMBER_LOGIN = "select * from member where id = ? and password = ?";
	
	private final String MEMBER_INSERT = 
			"insert into member (id, password, phone, email, addr, role) "
			+ "values (?, ? , ? , ?, ?, ?)";
			    
	private final String MEMBER_LIST = "select * from member order by id desc" ; 
		
	private final String MEMBER_GET = "select id, phone, email, regdate, addr, role from member where id = ?" ; 
		
	private final String MEMBER_UPDATE = "update member set phone= ?, email= ? , addr = ?, role = ? where id = ?"; 
		
	private final String MEMBER_DELETE = "delete member where id = ?";

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
	
	public void insertMember (MemberDTO dto) 
	{
		System.out.println("= insertMember 기능 처리 =");
			
		try 
		{
				
			conn = MyJavaUtil.getConnection() ;
			pstmt= conn.prepareStatement(MEMBER_INSERT); 
				
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getAddr());
			pstmt.setString(6, dto.getRole());
				
			//pstmt 를 실행해서 DB에 저장 
			pstmt.executeUpdate(); 
				
			System.out.println("Member 테이블에 값이 잘 insert 되었습니다. ");
				
		}
			
		catch (Exception e) 
		{
			System.out.println("Member 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} 
			
		finally 
		{
			MyJavaUtil.close(pstmt, conn);
		}
	}
	
	public List<MemberDTO> getMemberList(MemberDTO dto) 
	{
		//중요 : ArryList 는 While 블락 밖에서 선언
		//      ArryList에 저장되는  BoardDTO 선언은 while 블락 내부에서 선언 
		
		List<MemberDTO> MemberList = new ArrayList<>(); 
		
		try 
		{
			conn = MyJavaUtil.getConnection(); 	//conn 객체 활성화 : Oracle , XE , HR12 , 1234 
			//BOARD_LIST = "select * from board order by seq desc" 
			pstmt = conn.prepareStatement(MEMBER_LIST) ; 
			
			// pstmt 를 실행후 rs 에 레코드를 저장 
			rs = pstmt.executeQuery(); 
			
			//System.out.println("DB Select 성공");
			
			// rs의 각 레코드를 BoardDTO에 저장 ==> 각 각의 DTO를 ArrayList에 저장 
				// if , do ~ while   <===>  while
				//rs.next() : 다음 레코드가 존재하면 true, 커서가 다음레코드로 이동 
				
			while (rs.next()) 
			{
				// BoardDTO 객체 생성 
				MemberDTO member = new MemberDTO();
				    // 루프 블락 내에 선언 
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setRegdate(rs.getDate("REGDATE"));
				member.setAddr(rs.getString("ADDR"));
				member.setRole(rs.getString("ROLE"));
				
				MemberList.add(member); 	
			}
			
					
		}
		
		catch (Exception e) 
		{
			System.out.println("DB Select 실패");
			e.printStackTrace();     // 실패 할 경우 콘솔에 오류 정보 출력 
		}
		
		finally 
		{
			//사용한 객체 반납 : rs, pstmt, conn 
			MyJavaUtil.close(rs, pstmt, conn);
		}
			
		return MemberList; 	
	}


	//Member 상세 조회 : getMember(dto) 
	public MemberDTO getMember(MemberDTO dto) 
	{
		System.out.println("getMember 메소드 호출 성공");
				
		MemberDTO member = new MemberDTO(); 
		
		try 
		{
			conn = MyJavaUtil.getConnection();
			//"select id, phone, email, addr, role from member where id = ?" ;
			pstmt = conn.prepareStatement(MEMBER_GET); 
			pstmt.setString(1, dto.getId());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				member.setId(rs.getString("ID"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setRegdate(rs.getDate("REGDATE"));
				member.setAddr(rs.getString("ADDR"));
				member.setRole(rs.getString("ROLE"));
				
				System.out.println("RS 의 레코드를 dto 저장 성공 ");
			}
			
		}
		
		catch (Exception e) 
		{
			System.out.println("글 상세조회 실패  ");
			e.printStackTrace();
		}
		
		finally 
		{
			MyJavaUtil.close(rs, pstmt, conn);
		}
				
		return  member; 
	}


	// 회원 정보 수정 메소드 : updateMembers(dto)
	public void updateMember(MemberDTO dto) 
	{
		System.out.println("updateMember 메소드 호출됨");
		
		try 
		{
			conn = MyJavaUtil.getConnection(); 
			//BOARD_UPDATE = "update member set title= ?, write= ? , content = ? where id = ?"
			pstmt = conn.prepareStatement(MEMBER_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getAddr());
			pstmt.setString(4, dto.getRole());
			pstmt.setString(5, dto.getId());
			
			//쿼리를 실행
			pstmt.executeUpdate(); 		//insert, update, delete 구문일때 실행 
			
			System.out.println("DB 업테이트 성공 ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
			
		}
		
		finally 
		{
			MyJavaUtil.close(pstmt, conn);
		}
		
	}


	// 회원 삭제 메소드 : deleteMembers(dto) 
	public void deleteMember(MemberDTO dto) 
	{
		
		try 
		{
			conn = MyJavaUtil.getConnection(); 
			// BOARD_DELETE = "delete board where seq = ?"
			pstmt = conn.prepareStatement(MEMBER_DELETE); 
			
			// ? 변수값 할당. 
			pstmt.setString(1, dto.getId());
			
			// 쿼리 실행 
			pstmt.executeUpdate();   // insert, update, delete 
			
			System.out.println("DB의 레코드 삭제 성공");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
			
		}
		
		finally 
		{
			MyJavaUtil.close(pstmt, conn);
		}
		
	}

}
