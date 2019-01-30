package transformation;

import javax.xml.transform.TransformerException;

import DTO.TransformerInDTO;
import enumerations.NotationType;



public class MainTransformation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TransformerInDTO inDTO = new TransformerInDTO();
		inDTO.setInputPath("models/SPLOTModels/Ecommerce.sxfm");
		inDTO.setOutputPath("models/PL-HLCLModels/Ecommerce.hlcl");
		inDTO.setNotationType(NotationType.PLHLCL);
		
		FeatureModelSPLOTransformer transformer = new FeatureModelSPLOTransformer();
		try {
			transformer.transform(inDTO);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
