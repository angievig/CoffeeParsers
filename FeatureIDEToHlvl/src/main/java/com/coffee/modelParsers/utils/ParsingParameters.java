package com.coffee.modelParsers.utils;

/**
 * Parameters for the splot to HLVL parameters
 * @author Angela Villota
 * Modified by Juan Reyes - 2019-02-15
 * January 2019
 */

public class ParsingParameters {
	/**
	 * Private constant that represents the extension type of the file that will contain
	 * the HLVL code.
	 */
	private static final String HLVL_EXT=".hlvl";
	/**
	 * Attribute that contains the path to the file that is going to be parsed.
	 */
	private String inputPath;
	/**
	 *  Attribute that contains the path where the Hlvl file will be generated.
	 */
	private String outputPath;
	/**
	 * String with the name of the model (the same of the file).
	 */
	private String targetName;
	

	/*
	 *  Getters and Setters
	 */
	/**
	 * Method that returns the path to reach the output file that contains the Hlvl code.
	 * @return String that contains the output path where the file is stored.
	 */
	public String getOutputPath() {
		return outputPath+"/"+targetName+ HLVL_EXT; // Symbol "/" was added between outputPath and targetName
	}
	/**
	 * Method that sets a new output path to reach the file that contains the Hlvl code.
	 * @param outputPath A string that contains the new output path.
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	/**
	 * Method that returns the path to reach the File that is going to be parsed.
	 * @return String that contains the path to the File that is going to be parsed.
	 */
	public String getInputPath() {
		return inputPath;
	}
	/**
	 * Method that sets a new value for the path to reach the File that is going to be parsed.
	 * @param inputPath String that contains the new path to the File to be parsed.
	 */
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	/**
	 * Method that returns the name of the model and/or file.
	 * @return String with the name of the model and/or file.
	 */
	public String getTargetName() {
		return targetName;
	}
	/**
	 * Method that sets a new value to the attribute targetName
	 * @param targetName String with the new value for the attribute.
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

}
