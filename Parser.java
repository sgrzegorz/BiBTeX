package q;

import java.text.ParseException;
import java.util.ArrayList;

public class Parser {
	private static String path;
	private static ArrayList <String> categories=new ArrayList <String>();
	private static ArrayList <Author> authors = new ArrayList <Author>();
	private static String sign;
	
	public static String getSign() {
		return sign;
	}


	public static String getPath() {
		return path;
	}


	public static ArrayList<String> getCategories() {
		return categories;
	}


	public static ArrayList<Author> getAuthors() {
		return authors;
	}


	public Parser(String [] args) throws Exception{
		//args[0]=[path]
	    
		if(args.length==0) {
			System.out.println("Correct syntax:");
			System.out.println("java [path] [character] -c [category1] and [category2] and [category3] ...");
			System.out.println("Where categories are: article, book, conference, inproceedings, booklet, inbook, incollection, manual, mastersthesis, phdthesis, techreport, misc, unpublished, conference");
			System.out.println("Another correct syntax:");
			System.out.println("java [path] [character] -a [author1] and [author2] and [author3] ...");
			System.out.println("--------------------------------------------------");
			System.out.println("[character] - is string of length 1 which will be used for arrayBounds");
			System.out.println("--------------------------------------------------");
			System.out.println("NOTE \"and\" must be separated from args by empty space, see INCORRECT examples below:");
			System.out.println("java [path] -a [author1]and [author2] ...  -> ERROR");
			System.out.println("java [path] -a and[author1] and [author2] ... -> ERROR");
			System.exit(0);
		}
		path=args[0];
		
		//args[1]=[character]
		if(args[1].length()==1) {
			sign=args[1];
		}else {
			throw new Exception("[character] is incorrect");
		}
	    
		//args[2]= -c/-a
		if(args[2].equals("-c")) {
			
			for(int i=3;i<args.length;i++) {
				
				if(!args[i].equals("and")) {
					Parser.categories.add(args[i]);
				}
			}
		}else if(args[2].equals("-a")){
			
			String str="";
			for(int i=3;i<args.length;i++) {
				if(!args[i].equals("and")) {
					authors.add(new Author(str));
					str="";
				}else {
					str+=args[i]+" ";
				}
			}
			
		}else {
			throw new Exception("Incorrect arguments");
		}
			
	}
}
	
	
	
	

