package DTO;

import enumerations.NotationType;

public class TransformerInDTO {
	private String inputPath;
	private NotationType solverEditorType;
	private String outputPath;
	
	
	/**
	 * @return the solverEditorType
	 */
	public NotationType getNotationType() {
		return solverEditorType;
	}
	/**
	 * @param solverEditorType the solverEditorType to set
	 */
	public void setNotationType(NotationType solverEditorType) {
		this.solverEditorType = solverEditorType;
	}
	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}
	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	/**
	 * @return the inputPath
	 */
	public String getInputPath() {
		return inputPath;
	}
	/**
	 * @param inputPath the inputPath to set
	 */
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}





}
