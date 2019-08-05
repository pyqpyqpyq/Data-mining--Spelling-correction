package assignment1;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GED {

	public int global(String A, String B) {
		int[][] dp = new int[A.length() + 1][B.length() + 1];
		
		dp[0][0] = 0;
		for (int i = 1; i <= A.length(); i++)
			dp[i][0] = i * (-1);
		for (int j = 1; j <= B.length(); j++)
			dp[0][j] = j * (-1);
		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				dp[i][j] = Math.max(dp[i - 1][j] - 1, Math.max(dp[i][j - 1] - 1, dp[i - 1][j - 1] +equal(A.charAt(i-1),B.charAt(j-1))));
				//System.out.print(dp[i][j]+" ");
			}
			//System.out.println();
		}
		return dp[A.length()][B.length()];
	}

	public int equal(char a,char b){
		if(a==b){
			return 1;
		}else 
			return -1;
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
			int bestDis = -10000;
			ArrayList<String> matchList = new ArrayList<String>();
			for (String string : dictList) {
				statics = new GED().global(s, string);
				
				if (statics > bestDis) {
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