package q;

import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class Author {
	public String first="", last="", von="", jr="";
	
	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	private enum Type {
		UP,DOWN,CASELESS;
	}
	
	
	public Author(String word) throws DataFormatException {
		if(word.contains(",")) vonLastFirst(word);
		else firstVonLast(word);
		
	}
	
	
	
	private void firstVonLast(String word) throws DataFormatException  {
		
		
		//format word -> " word   " -> "word"
		for(int i=0;i<word.length();i++) {
			if(word.charAt(i)!=' ') {
				word=word.substring(i);
				break;
			}
		}
		for(int i=word.length()-1;i>=0;i--) {
			if(word.charAt(i)!=' ') {
				word=word.substring(0,i+1);
				break;
			}
		}
		
	
		Pattern p = Pattern.compile("\\s+");
		String words[]=p.split(word);
		Type cases[]=new Type[words.length];
		for(int i=0;i<words.length;i++) {cases[i]=getCase(words[i]);}
		
		//change CASELESS to UP or DOWN
		for(int i=0;i<cases.length;i++) {
			if(cases[i]==Type.CASELESS) {
				if(i-1>=0 && cases[i-1] ==Type.UP) {
					cases[i]=Type.UP;
					break;
				}
				if(i+1<cases.length && cases[i+1]==Type.UP) {
					cases[i]=Type.UP;
					break;
				}
				cases[i]=Type.DOWN;
			}
		}
		
	
		int i=0;
		while(i<cases.length &&cases[i]==Type.UP)
		{
			if(i!=cases.length-1)
				first+=words[i]+" ";			
			else 
				last+=words[i];
			i++;	
		}
	
		
		
		int start=i, end=-1;
		for(i=cases.length-1;i>=0;i--) {
			if(cases[i]==Type.DOWN &&i!=cases.length-1){
				end=i;
				break;
			}
		}
		for(i=start;i<words.length;i++) {
			if(i>=start && i<=end) {
				if(end==words.length-1) last+=words[i];
				else von+=words[i]+" ";
			}
			else last+=words[i]+" ";
		}
		
		if(first=="" && von!="") {
			first=von;
			von="";
		}
	}
	
	private void vonLastFirst(String word) throws DataFormatException {
		int comma1  = word.indexOf(",");
	    int comma2 = word.indexOf(",", comma1+1);
	    
	    if(comma2==-1) {
	    	first=word.substring(comma1+1);
	    }else {
	    	first=word.substring(comma2+1);
	    	jr=word.substring(comma1+1,comma2);
	    }
	    word=word.substring(0,comma1);
		Pattern p = Pattern.compile("\\s+");
		String words[]=p.split(word);
		Type cases[]=new Type[words.length];
		for(int i=0;i<words.length;i++) {cases[i]=getCase(words[i]);}
		
		int i=cases.length-1;
		while(i>=0) {
			if(cases[i]==Type.DOWN) break;
			i--;
		}
		int end=i;
		
		if(end==-1) {
			last=word;
			return;
		}else {
			for(i=0;(i<=end);i++) {
				if(i==cases.length-1) {
					last+=word;
					return;
				}
				else von+=words[i]+" ";
			}
			for(i=end+1;(i<cases.length);i++) {
				last+=words[i]+=" ";
			}
		}
	}
	 
	private Type getCase(String word) throws DataFormatException {
		//System.out.println("!"+word+"!");
		int i=0;
		boolean braceFound=false;
		while(i<word.length()){
			char c=word.charAt(i);
			if(c=='{') braceFound=true;
			if(c=='}') braceFound=false;
			if(!braceFound) {
				if('0'<=c && c<='9' || ('a'<=c && c<='z')) return Type.DOWN;
				if('A'<=c&& c<='Z') return Type.UP;
			}
			i++;
		}
		return Type.CASELESS;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((jr == null) ? 0 : jr.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((von == null) ? 0 : von.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (jr == null) {
			if (other.jr != null)
				return false;
		} else if (!jr.equals(other.jr))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		if (von == null) {
			if (other.von != null)
				return false;
		} else if (!von.equals(other.von))
			return false;
		return true;
	}

}
