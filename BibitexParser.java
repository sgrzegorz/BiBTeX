package q;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibitexParser {
	private static BibitexParser instance;
	private BibitexParser(){};
	private boolean insideOfAParagraph=false;
    private int leftbrackets = 0;
	private int rightbrackets = 0;
    public List<String> paragraphList = new ArrayList<String>();
    
	public static BibitexParser getInstance() {
		if(instance==null) {
			instance=new BibitexParser(){};
		}
		return instance;
	}
	
	public List<String> getParagraphList() {
		return paragraphList;
	}
    
	public void parseParagraphs() throws Exception{
		String file;
		if(Parser.getPath()!=null) 
			file=Parser.getPath();
		else 
			throw new ClassCastException("Parser hasn't got path, or it may be not initialised");
		
		
	    BufferedReader br=new BufferedReader(new FileReader(new File(file)));
		
		String block="";
		String line=br.readLine();
		while(line!=null)	
		{	
			if(!insideOfAParagraph) {
				line=nextIndex(line);
				
			}
			if(insideOfAParagraph) {
				while(line.length()!=0) {
					char c=line.charAt(0);
					if(c=='{') leftbrackets++;
					if(c=='}') rightbrackets++;
					block+=c;	
				    line=removeFirstChar(line); //wyrzucam z line pierwszy char c 
	
				    if(leftbrackets==rightbrackets && leftbrackets>0){
						paragraphList.add(block);
						block="";
						insideOfAParagraph=false;
						leftbrackets=0;
						rightbrackets=0;
					}
				    
				}
			}
			if(line.length()==0)line=br.readLine();
			
		}	
	br.close();
	}
	
	private String nextIndex(String inputStr) throws Exception{
		
		int beginIndex=9999999;
		String []t= {"string","article","book","inproceedings","booklet","inbook","incollection","manual","mastersthesis", "phdthesis" ,"techreport" ,"misc", "unpublished" ,"conference"};
        for(String s : t) {
        	String k="\\s*@\\s*"+s+"\\s*";
        	int index=indexOf(inputStr,k);
        	if(index < beginIndex && index!=-1) beginIndex=index;
        }
        if(beginIndex==9999999)beginIndex=-1;
        
        if(beginIndex!=-1) {
        	insideOfAParagraph=true;
        	//System.out.println(beginIndex + inputStr);
        	return inputStr.substring(beginIndex+1);
        }
        return "";
		
		
	}
	

	private  String removeFirstChar(String s) {
		if(s.length()!=0) return s.substring(1);
		return "";
	}
	
	
	private int indexOf(String inputStr, String patternStr) throws Exception{
		inputStr=inputStr.toLowerCase();
	    Pattern pattern = Pattern.compile(patternStr);
	    Matcher matcher = pattern.matcher(inputStr);
	    if(matcher.find()){    	
	    	return matcher.start();
	    }
	    return -1;
	}  

}

