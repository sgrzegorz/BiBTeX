package q;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Library {
	private List<Item> library = new ArrayList<Item>();
	private static Library instance;
	private Library() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  {
		for(String s: BibitexParser.getInstance().getParagraphList()) {
			try {
				library.add(new Item(s)); 
			}catch(Exception e){ //Item wasn't created
				System.out.println(e);
			}
		}
	}
	
	public static void main(String [] args)throws Exception{ 
           Parser p=new Parser(args);
		   BibitexParser pr=BibitexParser.getInstance();
		   pr.parseParagraphs();	
		   List <Item> lib=Library.getInstance().getLibrary();
		   lib.get(0).print();
		   Library.getInstance().search();
		   /*
		   for(Item i :Library.getInstance().library){
			   
			   System.out.println("->"+i.getType()+" "+i.getKey());
			   for (Map.Entry<String, String> entry : i.getItems().entrySet()){
				     System.out.println("!"+entry.getKey()+"!" +"<--->" +"!"+ entry.getValue()+"!");
			   }
		   }  
		   */
	}
	
	
	public static Library getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(instance==null) {
			instance=new Library(){};
		}
		return instance;
	}

	public List<Item> getLibrary() {
		return library;
	}

	public void search() {
	
	
		if(!Parser.getCategories().isEmpty()) {
			
			for(Item i :library){
				for(String category :Parser.getCategories()) {
				
					if(i.getType().equals(category)) i.print();
				}
				 
				
			 } 
			
		}
		if(!Parser.getAuthors().isEmpty()) {
			for(Item i :library){
				   
				List<Author> common = new ArrayList<Author>(i.getAuthors());
				common.retainAll(Parser.getAuthors());
				if(!common.isEmpty()) i.print();
				
			 } 
		}
		
		
	}
	
	

   
    
}
