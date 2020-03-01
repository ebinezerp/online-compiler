package online.compiler.sample2.util;

public class SourceCodeParse {
	
	
	public String data;
	
	public SourceCodeParse(String data) {
		this.data = data;
	}
	
	
	public String getClassName() {
		int index = data.indexOf("public class");
		int flowerBracketIndex;
		String filename = null;

		if (index < 0) {
			index = data.indexOf("class");
			flowerBracketIndex = data.indexOf("{", index + 6);
			filename = data.substring(index + 6, flowerBracketIndex).trim();
		} else {
			flowerBracketIndex = data.indexOf("{", index + 13);
			filename = data.substring(index + 13, flowerBracketIndex).trim();
		}
		return filename;
	}

}
