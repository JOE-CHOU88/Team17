import java.util.ArrayList;

public class ScoreList extends ArrayList<WebPage>{
		private  String url;
		private  String name;
		private  ArrayList<WebPage> lst;
		public ScoreList() {
			this.lst = new ArrayList<WebPage>();
		}

		@Override
		public boolean add(WebPage e) {
		  return super.add(e);
		}
		//quick sort
		public void sort(){
			if(lst.isEmpty()) {
				System.out.println("InvalidOperation");
			}
			else {
				for(int i=0;i<lst.size();i++) {
					if(lst.get(i).score<0) {
						lst.remove(lst.get(i));
						i--;
					}
				}
				quickSort(0, lst.size()-1);
			}	
		}
		private void quickSort(int leftbound, int rightbound){
			//1. implement quickSort algorithm
			if(leftbound<rightbound) {
				double pivot=lst.get(leftbound).score;
				int i = leftbound;
				int j = rightbound;
				while(true) {
					while(lst.get(i).score<=pivot&&i<j) {
						i++;
					}
					while(lst.get(j).score>pivot&&j>1) {
						j--;
					}
					if(i>=j) {
						break;
					}
					swap(i,j);
				}
				swap(leftbound,j);
				quickSort(leftbound, j-1);
				quickSort(j+1, rightbound);
			}
		}
		
		
		private void swap(int aIndex, int bIndex){
			WebPage temp = lst.get(aIndex);
			lst.set(aIndex, lst.get(bIndex));
			lst.set(bIndex, temp);
		}
		
		public  void output(){
			//TODO: write output and remove all element logic here...
			if(lst.isEmpty()) {
				System.out.println("InvalidOperation");
			}
			else {
				String sb = "";
				for(int i=0; i<lst.size();i++){
					WebPage k = lst.get(i);
					if(i>0) {
						sb +=" ";
					}
					sb+=Double.toString(k.score);
				}
				
				System.out.println(sb.toString());	
			}	
			System.out.println(lst);

		}

		public String getUrl() {
			return url;
		}

		public String getName() {
			return name;
		}
		public ArrayList<WebPage> getLst() {
			return lst;
		}

	}