//http://localhost:8080/DS_FinalProject2/TestProject
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		HashMap<String, String> query = google.query();
		
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    //System.out.println(key);  //title
		    //System.out.println(value); //url
		    s[num][0] = key;
		    s[num][1] = value;
		    
		    //construct a webpage
		    page = new WebPage(value, key);
		    keywords = new KeywordList();
		    counter = new WordCounter(page.url);
			File file = new File("keyword.txt");		
			Scanner scanner = new Scanner(file);
		
			while(scanner.hasNextLine()){
				String operation = scanner.next();
				
				switch (operation){
					case "add":
						double weight = Double.parseDouble(scanner.next());
						System.out.println(weight);
						String name = scanner.next();
						System.out.println(name);
						int count = counter.countKeyword(name);
						System.out.println(count);
						keywords.add(new Keyword(name, count, weight));
						
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
		    num++;
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