package sglr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IInvoker{
	byte[] parseFromString(String inputString, String parseTableName);
	byte[] parseFromStream(InputStream inputStringStream, String parseTableName) throws IOException;
	byte[] parseFromFile(File inputFile, String parseTableName) throws IOException;
}
