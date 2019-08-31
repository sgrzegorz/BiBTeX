package q;

import java.util.TreeMap;
import java.util.regex.Pattern;

public class StringManager {
	private TreeMap <String,String> map = new TreeMap <String,String>();
	
	
	public StringManager() {
		String left="[\\{\\(\\\"]";
	    String right="[\\}\\)\\\"]";
		Pattern p=p.compile("\\s*@string+\\s*\\{\\*s[\\\"\\{\\(]([^s])[\\)\\\"\\}]\\s*=\\s*[\\{\\\"\\(]([^\\s]*)\\s*}\\s*"))

		for(String str : BibitexParser.getInstance().getParagraphList()) {
			
		}
	}
	
}
