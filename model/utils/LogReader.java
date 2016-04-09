package model.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.beans.Lock;
import model.beans.Transaction;

// TODO: fix log reader
public final class LogReader {
	public static ArrayList<Transaction> getTransactions(String fileName) throws IOException {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
			stringBuffer.append("\n");
		}
		fileReader.close();
		
		String[] arr = stringBuffer.toString().split("\n");
		Transaction t;
		Lock lock;
		
		int nQueries;
		for (int i = 0; i < arr.length; i+=3) {
			nQueries = Integer.parseInt(arr[i+2]);
			if (arr[i+1].equals(Lock.EXCLUSIVE.toString()))
				lock = Lock.EXCLUSIVE;
			else lock = Lock.SHARED;
			
			ArrayList<String> queries = new ArrayList<String>();
			for (int j = 3; i < 3+nQueries; i++) {
				queries.add(arr[i+j]);
			}
				t = new Transaction(lock, arr[i+1], queries);
		}
		
		return transactions;
	}
}
