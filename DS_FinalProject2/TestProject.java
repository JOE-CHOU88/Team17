//http://localhost:8080/DS_FinalProject2/TestProject
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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


@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private WebPage page;
	private KeywordList keywords;
	private WordCounter counter;
	
	private static final long serialVersionUID = 1L;

    public TestProject() {
        super();
        keywords = new KeywordList();
        try {
	        String pwdJ = "C:\\Users\\Danny\\git\\Team17d\\DS_FinalProject2\\keyword.txt";
		    String pwdL = "/Users/ashleylai/git/Team17/DS_FinalProject2/keyword.txt";
			File file = new File(pwdJ);		
			Scanner scanner = new Scanner(file);
		
			while(scanner.hasNextLine()){
				
				double weight = Double.parseDouble(scanner.next());
				String name = scanner.next();
				keywords.add(new Keyword(name, weight));
				//test
				System.out.printf("%.2f %s\n", weight, name);
			}
			scanner.close();
        }catch(FileNotFoundException e){
        	System.out.println(e.getMessage());
        }
        // TODO Auto-generated constructor stub
    }

	
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
		System.out.println("1<--------->");
		String keyword = request.getParameter("keyword");
		System.out.println("keyword: " + keyword);
		GoogleQuery google = new GoogleQuery(keyword + "%20藝文中心"); //%20兩廳院%20現代舞
//		String k = java.net.URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
//		System.out.println("---------");
//		System.out.println(k);
		//System.out.println("2<--------->");
		System.out.println("User input keyword: " + request.getParameter("keyword"));
		HashMap<String, String> query = google.query();
		
		//String[][] s = new String[query.size()][2];
		//request.setAttribute("query", s);
		int num = 0;
		WebList webList = new WebList();
		for(Entry<String, String> entry : query.entrySet()) {
			String sublinkInfo;
		    String key = entry.getKey();
		    String value = entry.getValue();
		    value = value.substring(7, value.indexOf("&"));
		    System.out.println();
		    System.out.println(key);  //title
		    //System.out.println(value); //url
		    //s[num][0] = key;
		    //s[num][1] = value;
		    
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
				    
				    
				    //test
				    System.out.println("decodedValue: "+decodedValue);
				    //counter = new WordCounter(page.url);
				    
				    // construct a webtree
				    WebTree tree = new WebTree(page);
				    HtmlMatcher matcher = new HtmlMatcher(page.url);
				    HashMap<String, String> children = matcher.match();
					//String[][] cs = new String[children.size()][2];
					//int cnum=0;
					ArrayList<String> checkDuplicate = new ArrayList<String> ();
					for(Entry<String, String> entry2 : children.entrySet()) {
						String ckey = entry2.getKey();
					    String cvalue = entry2.getValue();
					    System.out.println("TestProject126:");
					    System.out.println("child title: " + ckey);  //title
					    System.out.println("child url: " + cvalue); //url
					    //check if the sublink appears before
					    if(! checkDuplicate.contains(cvalue)) {
					    	WebNode child = new WebNode(new WebPage(cvalue, ckey));
						    tree.root.addChild(child);
						    //tree.root.nodeScore += child.nodeScore;
						    System.out.println("<<----------------->>");
						    //cs[cnum][0] = ckey;
						    //cs[cnum][1] = cvalue;
					    	checkDuplicate.add(cvalue);
					    }
					    
					    //cnum++;
					}
					
					
					/*
					for (int i=0; i<cs.size(); i++) {
						//test
						System.out.println("cs[i][1]: "+cs[i][1]);
						tree.root.addChild(new WebNode(new WebPage(cs[i][1], cs[i][0])));
						//page.setScore(keywords);
					}
					*/
					tree.setPostOrderScore(keywords);
					//test
					webList.getLst().add(page);
				    webList.add(page);
					System.out.println("tree.eularPrintTree()");
					sublinkInfo = tree.eularPrintTree();
				    
					
					
					//count the running time of each website within 20 sec
					endTime   = System.currentTimeMillis() / 1000;
			    	long totalTime = endTime - startTime;
			    	System.out.println("Total run time: " + totalTime + " sec");
					
					//page.setScore(keywords);
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
		
		String[][] sortedWebList = new String[webList.getLst().size()][4];
		request.setAttribute("sortedWebList", sortedWebList);
		int count=0;
		int maxSizeOfTitle=20;
		for(int j=webList.getLst().size()-1;j>=0;j--) {
			System.out.println("========="+j+"=============");
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
			//System.out.println("sublinkInfo:\n" + sublinkInfo);
			System.out.println();
			count++;

			
		}
		
		
		request.getRequestDispatcher("searchResult.jsp").forward(request, response); 
		//System.out.println("test:");

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}