package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		
		String filePath = BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//총 게시글 개수 구하는 메소드
	public int selectListCount(Connection conn) {
		int listCount = 0;
		ResultSet rset = null;
		
		Statement stmt = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return listCount;
	}

	//게시글 리스트 조회
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			/*
			 * boardLimit(한페이지에 보여져야할 게시글개수)가 10일때
			 * currentPage : 1  - 시작값 : 1 - 끝값 :10
			 * currentPage : 2  - 시작값 : 11 - 끝값 : 20
			 * 
			 * 시작값은 (currnetPage -1) * boardLimit + 1
			 * 끝값 (시작값+boardLimit) - 1 
			 * */
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit()+1;
			int endRow = (startRow+pi.getBoardLimit()) - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_NO")
								  ,rset.getString("CATEGORY_NAME")
								  ,rset.getString("BOARD_TITLE")
								  ,rset.getString("USER_ID")
								  ,rset.getInt("COUNT")
								  ,rset.getDate("CREATE_DATE")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		//[Board [boardNo=119, boardType=0, category=null, boardTitle=운동, boardContent=쇠질 하러 가실분, boardWriter=admin, count=9, createDate=2023-04-08, status=null, titleImg=null], Board [boardNo=118, boardType=0, category=null, boardTitle=공통, boardContent=다시해보자, boardWriter=admin, count=20, createDate=2023-04-07, status=null, titleImg=null],
		System.out.println("CATEGORY_NAME 가져왔나요 dao에서 확인중:"+list);
		return list;
	}
	
	public ArrayList<Category> categoryList(Connection conn){
		ArrayList<Category> list = new ArrayList<>();
		ResultSet rset = null;
		Statement stmt = null;
		
		String sql = prop.getProperty("categoryList");
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Category(rset.getInt("CATEGORY_NO"),
									  rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}
	

	public int insertBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4,Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertAttachment(Connection conn,Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
		
				
		
	}

	//조회수 증가
	public int increaseCount(Connection conn, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//게시글 정보 조회
	public Board selectBoard(Connection conn, int bno) {
		
		Board b = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO")
							 ,rset.getString("CATEGORY_NAME")
							 ,rset.getString("BOARD_TITLE")
							 ,rset.getString("BOARD_CONTENT")
							 ,rset.getString("USER_ID")
							 ,rset.getDate("CREATE_DATE")
							 ,rset.getInt("USER_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	//첨부파일 정보 조회
	public Attachment selectAttachment(Connection conn, int bno) {
		Attachment at = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO")
								   ,rset.getString("ORIGIN_NAME")
								   ,rset.getString("CHANGE_NAME")
								   ,rset.getString("FILE_PATH"));	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
				
		return at;
	}
	//게시글 수정 메소드
	public int updateBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4,b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//기존파일 정보 수정하기
	public int updateAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
				
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//새로운 파일정보 넣기
	public int newInsertAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
				
		String sql = prop.getProperty("newInsertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	//게시글 삭제 메소드
	public int deleteBoard(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			
			//pstmt로 ?? 뚫힌거 받아오잖아! 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}


	//사진게시판 게시글 내용 입력메소드
	public int insertPhotoBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertPhotoBoard");
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			//물음표 채워주기
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));
			
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
			
		return result;
	}


	//사진게시판 첨부파일입력메소드 
	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {

		int result = 1; //이것도 1로 해놔야함... 0으로 해서 오류남
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachmentList");
		
		System.out.println(list);
		//물음표 채워주는 부분을 여러번 반복하는 것 뿐임.

			//try문 안에 for문이 들어가는게 더 깔끔하다고 함 
			try {
				
			for(Attachment at: list) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel()); //여기서 오류났나봐
				
				result *= pstmt.executeUpdate();//하나라도 0이 되면 fail 되게끔 곱해준다.
			  }
			
			} catch (SQLException e) {
				//캐치블록에서 0으로 바꾸기 
				//처음부터 예외발생하여 result가 1인채로 넘어가는 것을 방지
				result = 0;
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
	
			
		return result;
	}


	public ArrayList<Board> selectPhotoBoardList(Connection conn) {
	
		//conn만 가져와서 조회해오면 되잖아
		
		ArrayList<Board> list = new ArrayList<Board>();
		//PreparedStatement가 필요가 없다.
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql  = prop.getProperty("selectAttachmentList");
		
		//내꺼 문제가 있어서 썜껄로 함 
		//selectPhotoBoardList
		
		try {
			
			//항상 인수가 없는 Connection 클래스의 CreateStatement() 메소드를 호출
			stmt = conn.createStatement();   //createStatement 에서 막혀서 코드봄
			
			rset = stmt.executeQuery(sql);
			
			////[Board [boardNo=134, boardType=0, category=제발 단축키 쓰세요, boardTitle=제에발, boardContent=null, boardWriter=1, count=0, createDate=2023-04-12, status=null], 
			while(rset.next()) {
				//SELECT BOARD_NO, BOARD_TYPE, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER,COUNT, CREATE_DATE
				
				list.add(new Board(rset.getInt("BOARD_NO")
						, rset.getString("BOARD_TITLE")
						,rset.getInt("COUNT")
						,rset.getString("TITLEIMG")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//생성 역순
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		} 
	
		
		return list;
	}


	public ArrayList<Attachment> selectPhotoAttachmentList(Connection conn) {
		
		ArrayList<Attachment> aList = new ArrayList<Attachment>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPhotoAttachmentList");
		
		
		try {
			//물음표를 채울필요가 없어
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				
				aList.add(new Attachment(
						rset.getInt("FILE_NO")
						,rset.getInt("REF_BNO")
						,rset.getString("ORIGIN_NAME")
						,rset.getString("CHANGE_NAME")
						,rset.getString("FILE_PATH")
						,rset.getDate("UPLOAD_DATE")
						,rset.getInt("FILE_LEVEL")
						,rset.getString("STATUS")));
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return aList;
	}


	//이게 쌤꺼 쿼리 불러오는 거 
	public Board detailPhotoBoard(Connection conn, int bno) {
		
		
		Board b = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("detailPhotoBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				b=  new Board(rset.getInt("BOARD_NO")
						 ,rset.getString("CATEGORY_NAME")
						,rset.getString("BOARD_TITLE")
						,rset.getString("BOARD_CONTENT")
						,rset.getInt("USER_ID")
						,rset.getDate("CREATE_DATE"));
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
	
		
		
		return  b;
	}


	//사진게시판상세보기 첨부파일 조회메소드  쌤이랑 한거
	public ArrayList<Attachment> selectAttachmentList(Connection conn, int bno) {
	
		ArrayList<Attachment> list = new ArrayList<>();
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		//그대로 재사용
		//기존 첨부파일 조회쿼리문 이용하기 (해당 게시글 번호에 딸려있는 첨부파일 전부 조회해와야하기때문에)
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {	
				
				list.add(new Attachment(rset.getInt("FILE_NO")
								   ,rset.getString("ORIGIN_NAME")
								   ,rset.getString("CHANGE_NAME")
								   ,rset.getString("FILE_PATH")));


				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		

		return list;
	}

}
