package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileRead {

	public static void main(String[] args) {
		try {
			String filepath="C/Users/Dell/Desktop/correct.txt";
			String fString="C/Users/Dell/Desktop/misspell.txt";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath)), "UTF-8"));
			BufferedReader bufferedReader1= new BufferedReader(new InputStreamReader(new FileInputStream(new File(fString)),"UTF-8"));
			String stringA=null;
			String stringB=null;
			int i=0,j=0;
			while((stringA=bufferedReader.readLine())!=null) {
				i++;
				while((stringB=bufferedReader1.readLine())!=null) {
					j++;
					System.out.println(stringA+" "+stringB+": "+new GED().global(stringA, stringB));
				}
			}
			bufferedReader.close();
			bufferedReader1.close();
		System.out.println(i+"---"+j);
	}
		catch (IOException e) {
			// TODO: handle exception
		}
	}

}
