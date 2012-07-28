package sglr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IInvoker{
	public final static int FILTERS_DEFAULT = 0x00000000;
	public final static int FILTERS_REMOVE_CYCLES = 0x00000001;
	public final static int FILTERS_DIRECT_PREFERENCE = 0x00000002;
	public final static int FILTERS_INDIRECT_PREFERENCE = 0x00000004;
	public final static int FILTERS_PREFERENCE_COUNT = 0x00000008;
	public final static int FILTERS_INJECTION_COUNT = 0x00000010;
	public final static int FILTERS_PRIORITY = 0x00000020;
	public final static int FILTERS_REJECT = 0x00000040;

	byte[] parseFromData(byte[] inputData, String parseTableName);
	byte[] parseFromString(String inputString, String parseTableName);
	byte[] parseFromStream(InputStream inputStringStream, String parseTableName) throws IOException;
	byte[] parseFromFile(File inputFile, String parseTableName) throws IOException;
	
	byte[] parseFromData(byte[] inputData, String parseTableName, int filterFlags);
	byte[] parseFromString(String inputString, String parseTableName, int filterFlags);
	byte[] parseFromStream(InputStream inputStringStream, String parseTableName, int filterFlags) throws IOException;
	byte[] parseFromFile(File inputFile, String parseTableName, int filterFlags) throws IOException;
}
