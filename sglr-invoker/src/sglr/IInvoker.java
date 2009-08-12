package sglr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IInvoker{
	public final static int FILTERS_DEFAULT = 0;
	public final static int FILTERS_REMOVE_CYCLES = 1;
	public final static int FILTERS_DIRECT_PREFERENCE = 1 << 1;
	public final static int FILTERS_INDIRECT_PREFERENCE = 1 << 2;
	public final static int FILTERS_PREFERENCE_COUNT = 1 << 3;
	public final static int FILTERS_INJECTION_COUNT = 1 << 4;
	public final static int FILTERS_PRIORITY = 1 << 5;
	public final static int FILTERS_REJECT = 1 << 6;
	
	byte[] parseFromString(String inputString, String parseTableName);
	byte[] parseFromStream(InputStream inputStringStream, String parseTableName) throws IOException;
	byte[] parseFromFile(File inputFile, String parseTableName) throws IOException;
	
	byte[] parseFromString(String inputString, String parseTableName, int filterFlags);
	byte[] parseFromStream(InputStream inputStringStream, String parseTableName, int filterFlags) throws IOException;
	byte[] parseFromFile(File inputFile, String parseTableName, int filterFlags) throws IOException;
}
