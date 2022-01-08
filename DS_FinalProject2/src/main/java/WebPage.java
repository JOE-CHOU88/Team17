import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String url, String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);	
	}
	
	public void setScore(KeywordList keywords) throws IOException{
		score = 0;
//		3.calculate score
//		for(int i=0; i<keywords; i++){	
//			score += k.weight * counter.countKeyword(k.name);
//			// System.out.println(score);
//		}
		score=keywords.outputScore();
		
	}

	
}