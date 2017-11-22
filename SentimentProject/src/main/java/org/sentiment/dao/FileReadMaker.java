package org.sentiment.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.sentiment.object.ObjectLists;


public interface FileReadMaker {
	public ObjectLists getReadFile(String ExcelFilePath) throws IOException;
}
