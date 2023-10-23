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
import javax.servlet.http.HttpSession;

import com.lec.sce.dao.BoardDao;
import com.lec.sce.dao.MemberDao;
import com.lec.sce.dto.BoardDto;
import com.lec.sce.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("boardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String bimage = "";
		int result = BoardDao.FAIL;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", 
												new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			bimage = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession httpSession = request.getSession();
			MemberDto member = (MemberDto)httpSession.getAttribute("member");
			if(member!=null) {
				String mid = member.getMid(); // 로그인 한 사람의 mId
				String btitle = mRequest.getParameter("btitle");
				String bcontent = mRequest.getParameter("bcontent");
				String bip = request.getRemoteAddr();
				BoardDao boardDao = BoardDao.getInstance();
				BoardDto boardDto = new BoardDto(0, mid, null, btitle, bcontent,null, bimage, 0, 0, 0, 0, bip);
				result = boardDao.writeBoard(boardDto);
				// joinMember결과에 따라 적절히 request.setAttribute
				if(result == BoardDao.SUCCESS) { // 글쓰기
					request.setAttribute("boaredResult", "글쓰기 성공");
				}else {
					request.setAttribute("boaredResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("boaredResult", "로그인 한 사람만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("boaredResult", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		if(bimage!=null && result==MemberDao.SUCCESS) {
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
