import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException, RuntimeException{
		
    	URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
		String line = null;
		
		//count the running time of fetching each web site. If too long, then stop
		long startTime = System.currentTimeMillis() / 1000;
		long endTime   = startTime + 10;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		    if((System.currentTimeMillis() / 1000) > endTime) {
		    	throw new RuntimeException("runtime error");
		    	
		    }
		}
	
		return retVal;
    }
    
    public int countKeyword(String keyword) throws IOException, RuntimeException{
		if (content == null){
		    content = fetchContent();
		}
		content = content.toUpperCase();
		//System.out.println(content);
		keyword = keyword.toUpperCase();
		//System.out.println(keyword);
	
		int retVal = 0; 
		// calculates appearances of keyword
		while(content.indexOf(keyword) != -1) {
			content = content.substring(content.indexOf(keyword) + keyword.length());
			retVal += 1;
			//System.out.println(content);
		}
		
		return retVal;
	}
}
