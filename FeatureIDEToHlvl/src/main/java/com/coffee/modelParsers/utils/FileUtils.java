package com.coffee.modelParsers.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/**
	 * This method is responsible of reading the correspondent files from a directory path.
	 * @param directoryPath String that contains the path to a specific directory.
	 * @return A List<File> that contains File objects that correspond to the directory specified in the path.
	 */
	public static List<File> readFileFromDirectory(String directoryPath) {
		File Dir = new File(directoryPath);
		List<File> fileList = new ArrayList<File>();
		File[] lista_Archivos = Dir.listFiles();
		if (lista_Archivos != null) {
			for (int i = 0; i < lista_Archivos.length; i++) {
				if (lista_Archivos[i].isFile()) {
					fileList.add(lista_Archivos[i]);
				}

			}
		}
		return fileList;

	}

	/**
	 * Method that is responsible to write the HLVL code received into a specific file.hlvl
	 * @param path path to reach the specified file.hlvl that the program is writing into.
	 * @param program HLVL code that is going to be written inside the file.hlvl
	 */
	public static void writeHLVLProgram(String path, String program) {
		try {
			System.out.println("escribiendo en archivo: "+path);
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println(program);
			salida.close();
			bw.close();
			fw.close();
		} catch (java.io.IOException ioex) {
			System.out.println("se presento el error: " + ioex.toString());
		}
	}
}
