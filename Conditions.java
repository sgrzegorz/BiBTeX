package q;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Conditions {
	private static String[] types= {"article","book","conference","inproceedings","booklet","inbook","incollection","manual","mastersthesis", "phdthesis" ,"techreport" ,"misc", "unpublished" ,"conference"};
	private static String[] articleObligatory  = {"author", "title", "journal", "year"};
	private static String[] articleOptional = {"volume", "number", "pages", "month", "note", "key"};
	private static String[] bookObligatory =  {"author","editor", "title", "publisher", "year"};
	public static String[] bookOptional = {"volume", "series", "address", "edition", "month", "note", "key"};
	//inprocedings=conference
	private static String[] inproceedingsObligatory ={"author", "title", "booktitle", "year"};
	private static String[] inproceedingsOptional= {"editor", "volume","number", "series", "pages", "address", "month", "organization", "publisher", "note", "key"};//"volume","number"
	private static String[] bookletObligatory= {"title"};
	private static String[] bookletOptional= {"author", "howpublished", "address", "month", "year", "note", "key"};
	private static String[] inbookObligatory= {"author" , "editor", "title", "chapter","pages", "publisher", "year"};//"author" , "editor",chapter lub pages
	private static String[] inbookOptional= {"volume","number", "series", "type", "address", "edition", "month", "note", "key"};
	private static String[] incollectionObligatory= {"author", "title", "booktitle", "publisher", "year"};
	private static String[] incollectionOptional= {"editor", "volume","number", "series", "type", "chapter", "pages", "address", "edition", "month", "note", "key"}; //"volume","number"
	private static String[] manualObligatory= {"title"};
	private static String[] manualOptional= {"author", "organization", "address", "edition", "month", "year", "note", "key"};
	private static String[] mastersthesisObligatory= {"author", "title", "school", "year"};
	private static String[] mastersthesisOptional= {"type", "address", "month", "note", "key"};
	private static String[] phdthesisObligatory= {"author", "title", "school", "year"};
	private static String[] phdthesisOptional= {"type", "address", "month", "note", "key"};
	private static String[] techreportObligatory= {"author", "title", "institution", "year"};
	private static String[] techreportOptional= {"editor", "volume" ,"number", "series", "address", "month", "organization", "publisher", "note", "key"};// volume lub number,
	private static String[] miscObligatory= {};
	private static String[] miscOptional= {"author", "title", "howpublished", "month", "year", "note", "key"};
	private static String[] unpublishedObligatory= {"author", "title", "note"};
	private static String[] unpublishedOptional= {"month", "year", "key"};

	public static String[] getArray(String type,ArrayType arrayType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		type=type.toLowerCase();
		type=type.substring(0, 1).toUpperCase() + type.substring(1);
		
		String [] getOptional= (String[]) Conditions.class.getMethod("get"+type+"Optional").invoke(Conditions.class);
		String [] getObligatory= (String[]) Conditions.class.getMethod("get"+type+"Obligatory").invoke(Conditions.class);
	
		if(arrayType==ArrayType.optional) 	return getOptional;
	    if(arrayType==ArrayType.obligatory) return getObligatory;
		
		ArrayList <String> a=new ArrayList<String>(Arrays.asList(getOptional));
		for(String s: getObligatory) {a.add(s);}
		return a.toArray(new String[0]);
		
	}
	


	public static String[] getArticleObligatory() {
		return articleObligatory;
	}



	public static String[] getArticleOptional() {
		return articleOptional;
	}



	public static String[] getBookObligatory() {
		return bookObligatory;
	}



	public static String[] getBookOptional() {
		return bookOptional;
	}



	public static String[] getInproceedingsObligatory() {
		return inproceedingsObligatory;
	}



	public static String[] getInproceedingsOptional() {
		return inproceedingsOptional;
	}



	public static String[] getBookletObligatory() {
		return bookletObligatory;
	}



	public static String[] getBookletOptional() {
		return bookletOptional;
	}



	public static String[] getInbookObligatory() {
		return inbookObligatory;
	}



	public static String[] getInbookOptional() {
		return inbookOptional;
	}



	public static String[] getIncollectionObligatory() {
		return incollectionObligatory;
	}



	public static String[] getIncollectionOptional() {
		return incollectionOptional;
	}



	public static String[] getManualObligatory() {
		return manualObligatory;
	}



	public static String[] getManualOptional() {
		return manualOptional;
	}



	public static String[] getMastersthesisObligatory() {
		return mastersthesisObligatory;
	}



	public static String[] getMastersthesisOptional() {
		return mastersthesisOptional;
	}



	public static String[] getPhdthesisObligatory() {
		return phdthesisObligatory;
	}



	public static String[] getPhdthesisOptional() {
		return phdthesisOptional;
	}



	public static String[] getTechreportObligatory() {
		return techreportObligatory;
	}



	public static String[] getTechreportOptional() {
		return techreportOptional;
	}



	public static String[] getMiscObligatory() {
		return miscObligatory;
	}



	public static String[] getMiscOptional() {
		return miscOptional;
	}



	public static String[] getUnpublishedObligatory() {
		return unpublishedObligatory;
	}



	public static String[] getUnpublishedOptional() {
		return unpublishedOptional;
	}



	public static String[] getTypes() {
		return types;	}
	
	

	
		
	
	
}
