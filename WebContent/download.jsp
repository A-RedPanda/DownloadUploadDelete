<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>下载列表</title>
 
  </head>
  
  <body>    
    <table border="1" align="center">
        <tr>
            <th>序号</th>
            <th>文件名</th>
            <th>下载</th>
            <th>删除</th>
        </tr>
        
        <c:forEach var="en" items="${requestScope.fileName}" varStatus="vs">
            <tr>
                <td>${vs.count }</td>
                <td>${en.value }</td>
                <td>	
					<!-- 构建一个地址  -->
                    <c:url var="url" value="Download">
                    <c:param name="fileName" value="${en.key}"></c:param>
                    </c:url>
                    <!-- 使用上面地址 -->
                    <a>${url}</a>
                    <a href="${url}">文件下载</a>
                </td>
                <td>
                	<!-- 构建一个地址  -->
                    <c:url var="url" value="FileDelete">
                    <c:param name="fileName" value="${en.key}"></c:param>
                    </c:url>
                    <!-- 使用上面地址 -->
                	<a href="${url }">文件删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>          
  </body>
</html>