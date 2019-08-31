package q;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class Item {
	private String type="";
	private String key="";
	private TreeMap <String,String> items = new TreeMap <String,String>();
	private ArrayList <Author> authors=new ArrayList<Author>();
	

	
	public ArrayList<Author> getAuthors() {
		return authors;
	}


	
	
	private void convertAuthors() throws DataFormatException{
		String str;
		if(items.containsKey("author")) {
			str=items.get("author");
			items.remove("author");
		}else if(items.containsKey("editor")) {
			str=items.get("editor");
			items.remove("editor");
		}else {
			throw new DataFormatException("No author or editor found???: "+type+" "+ key);
		}
		Pattern p=Pattern.compile("\\s*and\\s*");
	    String author[] = p.split(str);
		for(String a: author) {
			authors.add(new Author(a));
		}
		if(authors.isEmpty()) throw new DataFormatException(key+" "+type+" ArrayList authors <String> is empty");
	}
	
	
	public Item(String str) throws DataFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
           //System.out.println(str);		 
		   //String str="@article{IEEEexample:article_typical,  author        = "S. Zhang and C. Zhu and J. K. O. Sin and P. K. T. Mok",  title         = "A Novel Ultrathin Elevated Channel Low-temperature                    Poly-{Si} {TFT}",  journal       = IEEE_J_EDL,  volume        = "20",  month         = nov,  year          = "1999",  pages         = "569-571"}";
		   String tmp;
		   Pattern pattern;
		   Matcher matcher;
		   str.toLowerCase();
		   
		 //type
		   for(String s :Conditions.getTypes()){
			   	tmp="\\s*"+s+"\\s*";
				pattern=Pattern.compile(tmp);
				matcher=pattern.matcher(str);	
				if(matcher.find()) type=s;
				
			}
			if(type.isEmpty()) throw new DataFormatException("Incorrect type-> "+ "!"+str+"!");
			
			
			//key
			tmp="([^\\{]*\\{\\s*)([^\\s,]*)(,)";
			pattern=Pattern.compile(tmp);
			matcher=pattern.matcher(str);
			if(matcher.find()) {
				key=matcher.group(2);
				
				str=str.substring(matcher.end()-1); //obcinam tak ze zostaje przecinek
			}
			if(key.isEmpty()) throw new DataFormatException("Incorrect key->" + "!"+str+"!");
			
			
			// group -> value ,group is title, inproceedings, unpublished itp. Put them to TreeMap
	    	tmp="(,\\s*)([^\\s]*)(\\s*=\\s*)";
	        pattern=Pattern.compile(tmp);
	        matcher=pattern.matcher(str);
	        
	        while(matcher.find()){
		        String keyG=matcher.group(2);
			    
			    int beginIndex=matcher.end();
			    int endIndex;
			    Matcher matcher1=matcher; //Need to see one step foreward Don't want to change matcher
			    if(matcher1.find()) {
			    	endIndex=matcher1.start();
			    }else {
			    	endIndex=str.length()-1; // str.lenght-1='}' .substring will take everything before this index 
			    }
				String value=str.substring(beginIndex, endIndex);
				items.put(keyG, value);
	        }
	        
	        
	        itemsCheck();
	        convertAuthors();
	}

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public TreeMap<String, String> getItems() {
		return items;
	}

	
	private void itemsCheck() throws DataFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		boolean flag;
		String [] both=Conditions.getArray(type,ArrayType.both);
//		//Nie moze być innego typu niz dopuszczalne typy w Conditions
//		flag=true;
//		for(String s : items.keySet()){
//			for(String k : both) {
//				if(s==k) flag=false;			
//			}
//		}
//		if(flag) throw new DataFormatException("Error while first check.");
		
		//Wszystkie typy obligatory muszą się pojawić 
		for(String s : Conditions.getArray(type,ArrayType.obligatory)){
			flag=false;
			for(String k : both){
				if(s==k) flag=true;			
			}
			if(flag==false)  throw new DataFormatException("Error while second check.");
			
		}	
		int i=0;
		if(items.get("author")!=null) i++;
		if(items.get("editor")!=null) i++;
		if(i==2) throw new DataFormatException("There are both author and editor fields");
	}
	
	public void print() {
		String s=Parser.getSign();
		
		System.out.format("+"+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+"+"+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+"+%n");
		String head=s+" %-143s "+s+"%n";
		System.out.format(head, type +" "+ "("+key+")");
		System.out.format("+"+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+"+"+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+"+%n");
		
		String leftAlignFormat = s+" %-20s "+s+" %-120s "+s+"%n";
		
		for(int i=0;i<authors.size();i++) {
			String a=authors.get(i).getFirst()+" "+authors.get(i).getLast();
			if(i==0) {
				System.out.format(leftAlignFormat, "author", "* "+a);
			}else {
				System.out.format(leftAlignFormat," " , "* "+a);
			}	
		}
		
		for(Map.Entry<String,String> item : items.entrySet()) {
			System.out.format(leftAlignFormat, item.getKey(), item.getValue());
		}
		System.out.format("+"+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+"+"+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+"+%n");
		System.out.println(); 
	}	
	
}
