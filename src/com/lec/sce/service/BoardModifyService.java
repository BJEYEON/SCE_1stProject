package com.lec.sce.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lec.sce.dao.BoardDao;
import com.lec.sce.dao.MemberDao;
import com.lec.sce.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
				String path = request.getRealPath("boardUp");
				int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
				String bimage = "", dbFileName = null;
				int result = BoardDao.FAIL;
				try {
					MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
					Enumeration<String> params = mRequest.getFileNames();
					String param = params.nextElement();
					bimage = mRequest.getFilesystemName(param);
					dbFileName = mRequest.getParameter("dbFileName");
					if(bimage==null) {
						bimage = dbFileName;
					}
					// mId, fTitle, fContent,  fileName, fIp
					int bnum = Integer.parseInt(mRequest.getParameter("bnum"));
					String btitle = mRequest.getParameter("btitle");
					String bcontent = mRequest.getParameter("bcontent");
					String bip = request.getRemoteAddr();
					BoardDao boardDao = BoardDao.getInstance();
					BoardDto boardDto = new BoardDto(bnum, null, null, btitle, bcontent, null, bimage, 0, 0, 0, 0, bip);
					result = boardDao.modifyBoard(boardDto);
					if(result == BoardDao.SUCCESS) { 
						request.setAttribute("boaredResult", "글수정 성공");
					}else {
						request.setAttribute("boaredResult", "글수정 실패");
					}
					//★mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set : modify,reply★
					request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
					request.setAttribute("boaredResult", "글수정 실패");
				}
				// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy (파일 수정을 안 했거나, 예외가 떨어질 경우 복사 안 함)
				if(dbFileName!=null && !bimage.equals(dbFileName) && result==MemberDao.SUCCESS) { 
					InputStream  is = null;
					OutputStream os = null;
					try {
						File serverFile = new File(path+"/"+bimage);
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("E:/start/08_1stProject/SCE/WebContent/boardUp/"+bimage);
						byte[] bs = new byte[(int)serverFile.length()];
						while(true) {
							int nByteCnt = is.read(bs);
							if(nByteCnt==-1) break;
							os.write(bs, 0, nByteCnt);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} // try
				}// 파일 복사 if

	}

}
