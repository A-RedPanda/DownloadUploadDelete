package test;

import java.io.File;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class FileDelete
 */

@WebServlet("/FileDelete")
public class FileDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 设置下载文件所在目录
	private  String uploadDirectory = "D:/upload" ;
	private  String userName ="";
	private  String basePath = uploadDirectory+File.separator+userName;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 设置编码格式
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		// 获取要删除文件名
		String filename = request.getParameter("fileName");
		System.out.println(filename);

		//文件所在路径
        File file = new File(basePath+File.separator+filename);
       
        //删除确认
        int res=JOptionPane.showConfirmDialog(null, "是否继续删除文件", "是否继续删除", JOptionPane.YES_NO_OPTION);
                       
        if(res==JOptionPane.YES_OPTION)  //点击“是”后 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        {                  
        	if (file.exists() && file.isFile()) 
            {
                if (file.delete()) 
                {
                    System.out.println("删除单个文件" + filename + "成功！");
                } 
                else 
                {
                    System.out.println("删除单个文件" + filename + "失败！");  
                }
            } 
            else 
            {
                System.out.println("删除单个文件失败：" + filename + "不存在或不是文件！");
            }    
                        
        }

        
		 //跳转到download.jsp
		 request.getRequestDispatcher("/FileSelection").forward(request, response);
	}

}
