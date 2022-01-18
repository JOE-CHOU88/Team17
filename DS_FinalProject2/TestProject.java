//http://localhost:8080/DS_FinalProject2/TestProject
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	//private WebPage page;
	private KeywordList keywords;	//self-defined keywords
	private URLDecode decoder;
	
	private static final long serialVersionUID = 1L;
	//constructor
    public TestProject() {
        super();
        keywords = new KeywordList();	//initialize the keywordList
        try {
	        String pwdJ = "C:\\Users\\Danny\\git\\Team17d\\DS_FinalProject2\\keyword.txt";
		    String pwdL = "/Users/ashleylai/git/Team17/DS_FinalProject2/keyword.txt";
			File file = new File(pwdL);		
			Scanner scanner = new Scanner(file);
			//print Self-defined keyword list:
			System.out.println("Self-defined keyword list:");
			while(scanner.hasNextLine()){
				
				double weight = Double.parseDouble(scanner.next());
				String name = scanner.next();
				keywords.add(new Keyword(name, weight));
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
			return;
		}
		
		
		//@SuppressWarnings("deprecation")
		String keyword = new String(request.getParameter("keyword")); //.replace(" ", "+")
		GoogleQuery google = new GoogleQuery(keyword + "+dance"); //+youTube
		System.out.println("User input keyword (after googleQuery): " + String.format("%s", keyword));
		System.out.println();
		
		WebPage page;
		WebList webList = new WebList();
		HashMap<String, String> query = google.query();
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    value = value.substring(7, value.indexOf("&"));
		    System.out.println();
		    System.out.println(key);  //title
		    //System.out.println(value); //URL
		    
		    //count the running time of each web site. If too long, then stop
	    	long startTime = System.currentTimeMillis() / 1000;
	    	long endTime   = startTime + 10;
			
		    try {		    	
		    	//try to decode the URL
		    	decoder = new URLDecode(value);
		    	String decodedValue = decoder.decode();
		    	//construct a webPage
			    page = new WebPage(decodedValue, key, ""); //fill in
			    System.out.println("decodedValue: "+decodedValue);
			    
			    // construct a webTree
			    WebTree tree = new WebTree(page);
			    HtmlMatcher matcher = new HtmlMatcher(page.url);
			    HashMap<String, String> children = matcher.match();
			    //update picture URL
			    String picUrl = matcher.findPic();
			    page.setPicUrl(picUrl);
			    System.out.println("picUrl: " + picUrl);

				ArrayList<String> checkDuplicate = new ArrayList<String> ();
				for(Entry<String, String> entry2 : children.entrySet()) {
					String ckey = entry2.getKey();
				    String cvalue = entry2.getValue();
				    //check if the sublink appears before
				    if(! checkDuplicate.contains(cvalue)) {
				    	WebNode child = new WebNode(new WebPage(cvalue, ckey, ""));
					    tree.root.addChild(child);
				    	checkDuplicate.add(cvalue);
				    	
				    }
				}
				tree.setPostOrderScore(keywords);
				//webList.getLst().add(tree);
			    webList.add(tree);
				
				//count the running time of each website within 20 sec.
				endTime   = System.currentTimeMillis() / 1000;
		    	long totalTime = endTime - startTime;
		    	System.out.println("Total run time: " + totalTime + " sec");
				
		    }catch(RuntimeException e) {
		    	System.out.println("runtime error");
		    	
		    }catch(Exception e) {		    	
				System.out.println("URL may not be linked or other errors!");
				System.out.println(e.getMessage());
				
			}
		}
		System.out.println();
		System.out.println("webList size: " + webList.size());
		System.out.println();
		webList.sort();
		
		String[][] sortedWebList = new String[18][5]; //webList.getLst().size()
		request.setAttribute("sortedWebList", sortedWebList);
		int count=0;
		int maxSizeOfTitle=20;
		for(int j=webList.getLst().size()-1;j>=webList.getLst().size()-1-17;j--) {
			System.out.println("========="+(count+1)+"=============");
			if(webList.getLst().get(j).root.webPage.name.length() > maxSizeOfTitle) {
				sortedWebList[count][0] = webList.getLst().get(j).root.webPage.name.substring(0,maxSizeOfTitle) + "...";
			
			}else {
				sortedWebList[count][0] = webList.getLst().get(j).root.webPage.name;

			}
			System.out.println(sortedWebList[count][0]);
			sortedWebList[count][1] = webList.getLst().get(j).root.webPage.url;
			System.out.println(sortedWebList[count][1]);
			sortedWebList[count][2] = String.format("%.1f",webList.getLst().get(j).root.nodeScore);
			System.out.println("Each web total score: " + sortedWebList[count][2]);
			sortedWebList[count][3] = webList.getLst().get(j).eularPrintTree();
			sortedWebList[count][4] = webList.getLst().get(j).root.webPage.picUrl;
			System.out.println("Each web pic url: " + sortedWebList[count][4]);
			System.out.println();
			count++;
			
		}		
		request.getRequestDispatcher("searchResult.jsp").forward(request, response); 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}