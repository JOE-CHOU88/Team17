



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		if(request.getParameter("keyword")== null) {
//			String requestUri = request.getRequestURI();
//			request.setAttribute("requestUri", requestUri);
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//			return;
//		}
//		ArrayList<Keyword> travelKey = searchList();
//		ScoreList webList = new ScoreList();
//		
//		//GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
//		String k = java.net.URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
//		GoogleQuery google = new GoogleQuery(k);
////		Map<String, String> query = google.query();
////		Map<Integer, String> summarize = google.content();
//		ArrayList<String> titleList = new ArrayList<String>();
//		ArrayList<String> urlList = new ArrayList<String>();
//		ArrayList<String> contentList = new ArrayList<String>();
//		//request.setAttribute("query", s);
//		for(Entry<String, String> entry : query.entrySet()) {
//		    String key = entry.getKey();
//		    String value = entry.getValue();
//		    titleList.add(key);
//		    urlList.add(value);
//		}
//		titleList.remove(0);
//	    urlList.remove(0);
//		
//		for(Map.Entry<Integer, String> entry : summarize.entrySet()) {
//		    String value = entry.getValue();
//		    contentList.add(value);
//		}
//		for (int i=0;i<contentList.size();i++) {
//			 String key = titleList.get(i);
//			 String value = urlList.get(i);
//			 String content = contentList.get(i);
//			 String url = value.substring(7);
//
//			 url = url.split("&")[0];
//			 url = url.split("%")[0];
//			 WebPage web = new WebPage(url,key);
//			 web.setContent(content);
//			 webList.getLst().add(web);
//			 webList.add(web);
//			try { 
//				 web.setScore(travelKey);
//			}catch (FileNotFoundException e) {
//				 System.out.println("找不到");
//			}catch(NullPointerException e){
//				 System.out.println("空值");
//			}catch(IllegalStateException e){
//				 System.out.println("傳輸問題");
//			}
//			catch(Exception e) {
//				 System.out.println("其他問題");
//			}
//		}		
//		webList.sort(); //排序
//		//webList.output();
//
//		String[][] sortedQuery = new String[webList.getLst().size()][3];
//		request.setAttribute("sortedQuery", sortedQuery);	
//		int count=0;
//		for(int j=webList.getLst().size()-1;j>=0;j--) {
//			sortedQuery[count][0] = webList.getLst().get(j).getName();
//			sortedQuery[count][1] = webList.getLst().get(j).getUrl();
//			sortedQuery[count][2] = webList.getLst().get(j).getContent();
//			count++;
//		}
//		request.getRequestDispatcher("searchResult.jsp")
//		 .forward(request, response);
//		}
//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		HashMap<String, String> query = google.query();
		
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}
		request.getRequestDispatcher("searchResult.jsp")
		 .forward(request, response); 
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	public ArrayList<Keyword> searchList() {
		ArrayList<Keyword> searchKey = new ArrayList<Keyword>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Float> weight = new ArrayList<Float>();
		name.add("表演");
		name.add("搜尋時間");
		name.add("售票系統");
		name.add("舞蹈");
		name.add("舞團");
		weight.add((float) 0.3);
		weight.add((float) 0.5);
		weight.add((float) 0.3);
		weight.add((float) 0.8);
		weight.add((float) 0.8);
		int numOfKeywords = 5;
		for(int i =0;i<numOfKeywords;i++){
			Keyword k = new Keyword(name.get(i), weight.get(i));//store key
			searchKey.add(k);
		}
		return searchKey;
	}

}