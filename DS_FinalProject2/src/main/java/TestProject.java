//http://localhost:8080/DS_FinalProject2/TestProject
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private WebPage page;
	private KeywordList keywords;
	private WordCounter counter;
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			System.out.println(request);
			System.out.println();
			System.out.println(response);
			return;
		}
		
		
		//@SuppressWarnings("deprecation")
		//System.out.println("1<--------->");
		String keyword = request.getParameter("keyword");
		System.out.println("keyword: " + keyword);
		GoogleQuery google = new GoogleQuery(keyword + "%20藝文中心"); //%20兩廳院%20現代舞
//		String k = java.net.URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
//		System.out.println("---------");
//		System.out.println(k);
		//System.out.println("2<--------->");
		System.out.println("User input keyword: " + request.getParameter("keyword"));
		HashMap<String, String> query = google.query();
		
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		WebList webList = new WebList();
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    value = value.substring(7, value.indexOf("&"));
		    System.out.println();
		    System.out.println(key);  //title
		    //System.out.println(value); //url
		    s[num][0] = key;
		    s[num][1] = value;
		    
		  //count the running time of each web site. If too long, then stop
	    	long startTime = System.currentTimeMillis() / 1000;
	    	long endTime   = startTime + 10;
//	    	while ((System.currentTimeMillis() / 1000) < endTime) {
//		    ExecutorService executor = Executors.newSingleThreadExecutor();
//	        Future<String> future = executor.submit(new Task());
			    //construct a webpage
			    try {		    	
			    	//try to decode the url
			    	URLDecode decoder = new URLDecode(value);
			    	String decodedValue = decoder.decode();
			    	
				    page = new WebPage(decodedValue, key);
				    webList.getLst().add(page);
				    webList.add(page);
				    
				    System.out.println(decodedValue);
				    keywords = new KeywordList();
				    counter = new WordCounter(page.url);
				    
				    //establish keyword list (keywords)
				    String pwdJ = "C:\\Users\\Danny\\git\\team17c\\DS_FinalProject2\\keyword.txt";
				    String pwdL = "/Users/ashleylai/git/Team17/DS_FinalProject2/keyword.txt";
					File file = new File(pwdL);		
					Scanner scanner = new Scanner(file);
				
					while(scanner.hasNextLine()){
						String operation = scanner.next();
						
						switch (operation){
							case "add":
								double weight = Double.parseDouble(scanner.next());
								//System.out.print(weight);
								String name = scanner.next();
								//System.out.print(name);
								int count = counter.countKeyword(name);
								//System.out.print(count);
								keywords.add(new Keyword(name, count, weight));
								System.out.printf("%.2f %s %d", weight, name, count);
								//System.out.println("3<--------->");
								// lst.output();
								System.out.println();
								break;
							case "sort":
								keywords.sort();
								break;
							case "output":
								keywords.output();
								break;
							default:
								System.out.println("InvalidOperation");
								System.out.println("^^");
								break;
						}	
					}
					scanner.close();
			    	
					//count the running time of each website within 20 sec
					endTime   = System.currentTimeMillis() / 1000;
			    	long totalTime = endTime - startTime;
			    	System.out.println("Total run time: " + totalTime + " sec");
					
					//construct a webpage (continue)
					
					page.setScore(keywords);
			    }catch(RuntimeException e) {
			    	System.out.println("runtime error");
			    }catch(Exception e) {		    	
					System.out.println("URL may not be linked or other errors!");
				}finally{
					num++;
				}
	    	//}
		}
		System.out.println();
		System.out.println("webList size: " + webList.size());
		System.out.println();
		webList.sort();
//		webList.output();
//		System.out.println(webList.size());
		
		String[][] sortedWebList = new String[webList.getLst().size()][3];
		request.setAttribute("sortedWebList", sortedWebList);
		int count=0;
		int maxSizeOfTitle=25;
		for(int j=webList.getLst().size()-1;j>=0;j--) {
			if(webList.getLst().get(j).name.length() > maxSizeOfTitle) {
				sortedWebList[count][0] = webList.getLst().get(j).name.substring(0,maxSizeOfTitle) + "...";
			}else {
				sortedWebList[count][0] = webList.getLst().get(j).name;
			}
			System.out.println(sortedWebList[count][0]);
			sortedWebList[count][1] = webList.getLst().get(j).url;
			System.out.println(sortedWebList[count][1]);
			sortedWebList[count][2] = String.format("%.1f",webList.getLst().get(j).score);
			System.out.println("Each web total score: " + sortedWebList[count][2]);
			System.out.println();
			count++;
		}
		
		
		request.getRequestDispatcher("searchResult.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}