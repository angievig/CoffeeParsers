package com.coffee.modelParsers.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/**
	 * Read
	 * File de dichos archivos
	 * @param directoryPath: ruta del directorio 
	 * @return List: lista de objetos File de los archivos encontrados en el directorio
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
	 * Recibe una cadena y la escribe en un archivo de extension hlvl
	 * 
	 * @param program: 
	 * @param path: 
	 */
	public static void writeHLVLProgram(String path, String program) {
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println(program);
			salida.close();
		} catch (java.io.IOException ioex) {
			System.out.println("se presento el error: " + ioex.toString());
		}
	}
}
