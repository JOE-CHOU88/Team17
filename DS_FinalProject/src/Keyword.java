public class Keyword {
	public String name;
    public int count;
    public float weight;
    
    public Keyword(String name,float weight){
		this.name = name;
		this.weight =weight;
    }
    
    @Override
    public String toString(){
    	return "["+name+","+weight+"]";
    }
    

//    public int getCount()
//    {
//    	return count;
//    }
    public String getName()
    {
    	return name;
    }
    public float getWeight()
    {
    	return weight;
    }
}