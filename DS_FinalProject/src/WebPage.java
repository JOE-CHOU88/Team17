
import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	public String content;
	public WebPage(String url,String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
		this.setContent("");
		System.out.println(name);
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0;
//		3.calculate score
		for(Keyword k : keywords){	
			score += k.weight * counter.countKeyword(k.name);
			// System.out.println(score);
		}
		score+=counter.countKeyword(name);
	}
	public double getScore(){
		return score;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
