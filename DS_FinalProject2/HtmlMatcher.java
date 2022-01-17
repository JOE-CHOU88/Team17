import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlMatcher {
	private String urlStr;
    private String content;
    
    public HtmlMatcher(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(3000); //test if it takes too long to link
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String retVal = "";
	
		String line = null;
		long startTime = System.currentTimeMillis() / 1000;
		long endTime   = startTime + 10;
		while((line=br.readLine())!=null) {
			retVal += line;
			if((System.currentTimeMillis() / 1000) > endTime) {
		    	return "runtime error";
		    }

		}
		return retVal;
    }
    
    public HashMap<String, String> match() throws IOException {
		if(content==null) {
			content= fetchContent();

		}
		if (content != "runtime error") {
			HashMap<String, String> retVal = new HashMap<String, String>();
			Document doc = Jsoup.parse(content);
			Elements lis = doc.select("a");
			for(Element li : lis) {
				try {
					String citeUrl = li.attr("href");
					citeUrl = citeUrl.substring(citeUrl.indexOf("http"), citeUrl.indexOf("&"));
					URLDecode decoder = new URLDecode(citeUrl);
			    	String decodedValue = decoder.decode();
					String title = li.text();
					retVal.put(title, decodedValue);
					
				} catch (IndexOutOfBoundsException e) {
					e.getMessage();
	
				}
			}
			return retVal;
			
		}else {
			return null;
			
		}

	}
  
    public String findPic() throws Exception{
    	if(content==null) {
			content= fetchContent();

		}
		
		if (content != "runtime error") {
			String retPicUrl = "";
			
			Document doc = Jsoup.parse(content);
			Elements lis = doc.select("link[rel=\"icon\"]");
			System.out.println("lis:");
			System.out.println(lis);
			
			for(Element li : lis) {
				try {
					String citeUrl = li.attr("href");
					//some links just have relative path
					if(citeUrl.indexOf("http") == -1) {
						if(urlStr.contains(".com.tw")) {
							citeUrl = urlStr.substring(0, urlStr.indexOf(".com.tw")+7) + citeUrl;
							
						}else if(urlStr.contains(".com")){
							citeUrl = urlStr.substring(0, urlStr.indexOf(".com")+4) + citeUrl;
							
						}
						
					}
					//citeUrl=citeUrl.substring(citeUrl.indexOf("http"), citeUrl.indexOf("&"));
					URLDecode decoder = new URLDecode(citeUrl);
					retPicUrl = decoder.decode();
					
					if (HtmlMatcher.testConnection(retPicUrl) != 200) {
						retPicUrl = "";
					}
					
				} catch (IndexOutOfBoundsException e) {
					e.getMessage();
	
				}
	
			}
			return retPicUrl;
			
		}else {
			return null;
			
		}
    }
    
    public static int testConnection(String address) throws Exception {
    	int status = 404;
    	try {
    		URL urlObj = new URL(address);
    		HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
    		oc.setUseCaches(false);
    		oc.setConnectTimeout(3000); // 設定超時時間
    		status = oc.getResponseCode();// 請求狀態
    		if (200 == status) {
    			// 200是請求地址順利連通。。
    			return status;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return status;
    }
}