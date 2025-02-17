package com.gmail.goosius.townycultures.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import com.gmail.goosius.townycultures.TownyCultures;

public class FileMgmt {

	/**
	 * Pass a file and it will return it's contents as a string.
	 *
	 * @param file File to read.
	 *
	 * @return Contents of file. String will be empty in case of any errors.
	 */
	public static String convertFileToString(File file) {

		if (file != null && file.exists() && file.canRead() && !file.isDirectory()) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try (InputStream is = new FileInputStream(file)) {
				Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
				reader.close();
			} catch (IOException e) {
				TownyCultures.severe("Exception " + e.getStackTrace());
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	/**
	 * Writes the contents of a string to a file.
	 *
	 * @param source String to write.
	 * @param file   File to write to.
	 */
	public static void stringToFile(String source, File file) {

		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

			out.write(source);
			out.close();

		} catch (IOException e) {
			TownyCultures.severe("Exception " + e.getStackTrace());
		}
	}

	/**
	 * Checks a filePath to see if it exists, if it doesn't it will attempt
	 * to create the file at the designated path.
	 *
	 * @param filePath {@link String} containing a path to a file.
	 * @return True if the folder exists or if it was successfully created.
	 */
	public static boolean checkOrCreateFile(String filePath) {
		File file = new File(filePath);
		if (!checkOrCreateFolder(file.getParentFile().getPath())) {
			return false;
		}
		try {
			return file.exists() || file.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Checks a folderPath to see if it exists, if it doesn't it will attempt
	 * to create the folder at the designated path.
	 *
	 * @param folderPath {@link String} containing a path to a folder.
	 * @return True if the folder exists or if it was successfully created.
	 */
	public static boolean checkOrCreateFolder(String folderPath) {
		File file = new File(folderPath);
		return file.exists() || file.mkdirs() || file.isDirectory();
	}

	public static File unpackResourceFile(String filePath, String resource, String defaultRes) {
		// open a handle to yml file
		File file = new File(filePath);

		if ((file.exists())/* && (!filePath.contains(FileMgmt.fileSeparator() + defaultRes))*/)
			return file;

		String resString;

		/*
		 * create the file as it doesn't exist,
		 * or it's the default file
		 * so refresh just in case.
		 */
		checkOrCreateFile(filePath);

		// Populate a new file
		try {
			resString = convertStreamToString("/" + resource);
			FileMgmt.stringToFile(resString, filePath);

		} catch (IOException e) {
			// No resource file found
			try {
				resString = convertStreamToString("/" + defaultRes);
				FileMgmt.stringToFile(resString, filePath);
			} catch (IOException e1) {
				// Default resource not found
				e1.printStackTrace();
			}
		}

		return file;
		
	}
	
	// pass a resource name and it will return it's contents as a string
	public static String convertStreamToString(String name) throws IOException {
		
		if (name != null) {
			Writer writer = new StringWriter();
			InputStream is = FileMgmt.class.getResourceAsStream(name);

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (NullPointerException e) {
					//Failed to open a stream
					throw new IOException();
				}
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	//writes a string to a file making all newline codes platform specific
	public static void stringToFile(String source, String FileName) {

		if (source != null) {
			// Save the string to file (*.yml)
			stringToFile(source, new File(FileName));
		}

	}

}
