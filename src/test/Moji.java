package test;

public class Moji {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String str = "𠮷田 太郎";
		int startIndex = 0;
		int endIndex = 2;

		String subStr = str.substring(startIndex, endIndex);

		System.out.println(subStr); // => "𠮷"
		
		int startIndexSurrogate = str.offsetByCodePoints(0, startIndex); // => 0
		int endIndexSurrogate = str.offsetByCodePoints(0, endIndex); // => 3

		String subStrSurrogate = str.substring(startIndexSurrogate, endIndexSurrogate); // => "𠮷田"
		
		System.out.println(subStrSurrogate); 
	}

}
