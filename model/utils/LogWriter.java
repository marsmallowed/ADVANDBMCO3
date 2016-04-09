package model.utils;

import java.io.File;

// TODO: fix log writers
public final class LogWriter {
	private static File log;
	
	public static File getLog() {
		return log;
	}
	
	public static void createLog(String fileName) {
		log = new File(fileName + ".log");
	}
	
	public static boolean logExists() {
		if (log != null && log.exists())
			return true;
		else return false;
	}
}
