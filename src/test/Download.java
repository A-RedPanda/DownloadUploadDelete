package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */

@WebServlet("/Download")
public class Download extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 设置下载文件所在目录
	private  String uploadDirectory = "D:/upload" ;
	private  String userName ="";
	private  String basePath = uploadDirectory+File.separator+userName;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 将收到请求设置为UTF-8格式
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		// 获取上传文件名
		String filename = request.getParameter("fileName");
		
		System.out.println(basePath);

		// 以流的形式读入文件
		InputStream in = new FileInputStream(new File(basePath, filename));

		// 下载转码
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if (userAgent.contains("msie") || userAgent.contains("edge") || userAgent.contains("trident")) {
			// win10 ie edge 浏览器 和其他系统的ie
			filename = URLEncoder.encode(filename, "UTF-8");
		} else {
			filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		}

		// 使用浏览器进行下载
		response.addHeader("Content-type", "appllication/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

		// 定义流输出
		OutputStream out = response.getOutputStream();

		// 定义临时缓冲区
		byte[] buffer = new byte[1024];
		int len = -1;

		// 将流中的数据读出
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}

		// 关闭流
		out.close();
		in.close();
	}
}
