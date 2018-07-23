package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/FileSelection")
public class FileSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	//存储路径
	private  String uploadDirectory = "D:/upload" ;
	private  String userName ="";
	private  String basePath = uploadDirectory+File.separator+userName;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public FileSelection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//先获取upload目录下所有文件的文件名，再保存；跳转到download.jsp列表展示
		
		//初始化map集合Map<唯一文件名，简短文件名>
		Map<String,String> fileNames = new HashMap<String,String>();
		
		//目录
		File directory = new File(basePath);
		
		
		//目录下所有文件名
		String[] list=directory.list();
		
		 
		//遍历所有文件
		  if(list!=null&&list.length>0) {
			for(int i=0;i<list.length;i++)
			{
				String fileName = list[i];
				String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
				fileNames.put(fileName, shortName);
			}
		  }
		  
		  //保存到request域
		  request.setAttribute("fileName", fileNames);
		 
		  //跳转到download.jsp
		  request.getRequestDispatcher("/download.jsp").forward(request, response);
	}

}
