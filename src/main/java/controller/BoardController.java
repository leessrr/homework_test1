package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.BoardDAO;
import model.BoardDTO;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlsession;
	
	//글목록보기
		@RequestMapping("list.htm") //    /customer/notice.htm
		public String notices(String pg , String f , String q , Model model) throws ClassNotFoundException , SQLException {
			System.out.println("여기옴?");
			 //************************************************
			 //1단계(일반적인 방법)
			 //HttpServletRequest req
			 //String _page = req.getParameter("pg");
			 
			 //2단계(Spring 방법) 가장 많이 (기본값, 강제사항)
			 //@RequestParam(value="pg" , requried=true) String pg
			 
			 //3단계 
			 //String pg , String f , String q
			 //jsp?pg=100&f=10&q=1
			 //전제조건 단 parameter [넘어오는 변수] 과 [함수안에 변수명]이 같다면
			 //자동 매핑
			 //**************************************************

			//게시판 기본 설정(기본값 처리)/////////////
			int page = 1;
			String field = "ename";
			String query ="%%";
			//////////////////////////////////////
			if(pg != null && pg.equals("")){
				page = Integer.parseInt(pg);
			}
			if(f != null && f.equals("")){
				field = f;
			}
			if(q != null && q.equals("")){
				query = q;
			}
			
			System.out.println(page + " / " + field + " / " + query);
			
			//JdbcTemplate 사용시
			//List<Notice> list= noticeDao.getNotices(page, field, query);
			
			//Mybatis 적용코드
			BoardDAO boardDao = sqlsession.getMapper(BoardDAO.class);
			List<BoardDTO> list= boardDao.getBoard(page, field, query);
			
			
			model.addAttribute("list", list); //자동 forward
			model.addAttribute("pg", page);                               
												//단 notices(Model model) 함수의 parameter
			//기존 Fullname 방식
			//return "notice.jsp";
			
			
			return "list";
		}
		
		 // 글등록 화면 처리
		 @RequestMapping(value = "empReg.htm", method = RequestMethod.GET)
		 public String empReg() {
		  
		 /* //이전 ..
		  //return "noticeReg.jsp"; // view이름
		  
		  //Tiles
		  return  "customer.noticeReg";*/
		
			 return "regForm";
		   
		 }

		 // 글등록 처리(실제 글등록 처리)
		 @RequestMapping(value = "empReg.htm", method = RequestMethod.POST)
		 public String noticeReg(BoardDTO b, HttpServletRequest request)
		   throws IOException, ClassNotFoundException, SQLException {
			 
			  //Mybatis 적용
			 BoardDAO boardDao = sqlsession.getMapper(BoardDAO.class);
			 System.out.println("reg옴?" + b.getEname());
			 
			  boardDao.insert(b);
			  System.out.println("인서트 후");
			  
			  return "redirect:list.htm";
			  //return "notice.htm";   
			  //http://localhost:8090/SpringMVC_Basic_WebSiteBasic_Annotation_JdbcTemplate_06/customer/notice.htm
			  //요청 주소값 변화
		 }
		 
		 @RequestMapping("/read.htm")
			public String read(int num, int pg, Model model) throws ClassNotFoundException, SQLException{
				BoardDAO boardDao = sqlsession.getMapper(BoardDAO.class);
				//상세 조회
				BoardDTO dto = boardDao.getDetail(num);
				//model 필요한 것 담고
				model.addAttribute("b", dto);
				model.addAttribute("pg", pg);
				
				return "read"; // read.jsp
			}
		 
		 @RequestMapping(value = "empEdit.htm", method = RequestMethod.GET)
		 public String noticeEdit(int empno, int pg, Model model)
		   throws ClassNotFoundException, SQLException {
		  //noticeEdit.htm?seq=10	 
		  // 수정페이지는 기존 데이터 출력
		  
		  //jdbcTemplate 
		  //Notice notice = noticeDao.getNotice(seq);
		  
		  //Mybatis 적용
			 BoardDAO boardDao = sqlsession.getMapper(BoardDAO.class);
			 BoardDTO board = boardDao.getDetail(empno);
		  
		  model.addAttribute("board", board);
		  model.addAttribute("pg", pg);
		  
		  //이전
		  //return "noticeEdit.jsp";
		 
		  //Tiles
		  //return "customer.noticeEdit";
		  return "boardEdit";
		 }

		 //게시판 실제 수정처리
		 @RequestMapping(value = "empEdit.htm", method = RequestMethod.POST)
		 public String noticeEdit(int empno, String job ,HttpServletRequest request) throws ClassNotFoundException,
		   SQLException, IOException {
		 //jdbcTemplate
		 //noticeDao.update(n);
		
		 //Mybatis 적용
		  BoardDAO boardDao = sqlsession.getMapper(BoardDAO.class);
		  System.out.println("여긴옴?" + empno);
		  boardDao.update(empno, job);
		  System.out.println("두번째" + job);
		  
		  
		  return "redirect:list.htm";
	    	 
		 }
		 
		 //글삭제
		 @RequestMapping("deleteform.htm")
		 public String noticeDel(int empno) throws ClassNotFoundException,
		   SQLException {
		  //jdbcTemplate
		  //noticeDao.delete(seq);
		  
		  
		  //MyBatis 적용
		  BoardDAO boardDao = sqlsession.getMapper(BoardDAO.class);
		  boardDao.delete(empno);
		  
		  return "redirect:list.htm"; //리스트 화면 (controller 타서 데이터 출력)
		 }
}
