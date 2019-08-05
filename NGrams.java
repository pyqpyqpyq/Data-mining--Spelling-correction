package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NGrams {
	private String[] SplitString(String str) {
		if (str==null&&str=="") {
			return null;
		}
		String[] s = new String[str.length()-1];
		for(int i = 0;i<str.length()-1;i++){
			s[i] = str.substring(i, i+2);
		}
		return s;
	}
	public int Ngramdistance(String A, String B) {
		A="#"+A+"#";
		B="#"+B+"#";
		int result=0;
		int common = 0;
	    String[] strings = SplitString(A);
	    String[] strings2 = SplitString(B);
	    int lenA = strings.length;
		int lenB = strings2.length;
		
	    for (int i = 0; i<strings.length;i++) {
	    	for(int j = 0; j< strings2.length;j++){
	    		if (strings[i].equals(strings2[j])) {
					common++;
				}
	        
	    }
	    }
	    result=lenA+lenB-2*common;
	    return result;
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		ArrayList<String> list3 = new ArrayList<>();
		ArrayList<String> dictList = new ArrayList<String>();
		String fString = "D:\\correct.txt";
		String  filepath= "D:\\misspell.txt";
		String dictionary = "D:\\dict.txt";
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(filepath)), "UTF-8"));
		BufferedReader bufferedReader1 = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(fString)), "UTF-8"));
		BufferedReader dictBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(dictionary))));
		String stringA = null;
		String stringB = null;
		String dictToken = null;
		int i = 0, j = 0, statics = 0,temp=0;
		while ((stringA = bufferedReader.readLine()) != null) {
			i++;
			list.add(stringA.trim()); 
		}
		while ((stringB = bufferedReader1.readLine()) != null) {
			j++;
			list2.add(stringB.trim());
		}
		if(list.size()!=list2.size()){
			System.out.println(list.size()+"vs "+list2.size());
			return;
		}
	
		while ((dictToken = dictBr.readLine()) != null) {
			dictList.add(dictToken.trim());
		}
		
		int corNum = 0;
		
		int totalNum = 0;
		
		for (int r = 0; r < list.size(); r++) {
			
			String s = list.get(r);
			String correct = list2.get(r);
			int bestDis = 10000;
			ArrayList<String> matchList = new ArrayList<String>();
			for (String string : dictList) {
				statics = new NGrams().Ngramdistance(s, string);
				if (statics < bestDis) {
					bestDis = statics;
					matchList.clear();
					matchList.add(string);
				} else if (statics == bestDis) {
					matchList.add(string);
				}
			}
			System.out.println(s+" "+"match list"+": "+matchList.toString());
			totalNum = totalNum + matchList.size();
			if (matchList.contains(correct))
				corNum++;
			else{
				System.out.println(correct+"   "+matchList.toString());
			}
		}
		System.out.println(corNum);
		System.out.println(totalNum);
		System.out.println(list.size());
		System.out.println("Precision:" + (double) corNum / totalNum);
		System.out.println("Recall:" + (double) corNum / list.size());
		bufferedReader.close();
		bufferedReader1.close();
		dictBr.close();
	}

}
