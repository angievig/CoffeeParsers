package splot2HLVL;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.ParsingParameters;

class TestSplot2HLVL {

	@Test
	void testParse() {
		// for all files in the folder
		// First we create a parameters object
		int i=0;
		ParsingParameters params= new ParsingParameters();
		
		params.setInputPath("test-data/SplotFiles/Splot_GLP.xml");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("test"+i);
		
		//Now we create the parser object
		Splot2HlvlParser parser=	new Splot2HlvlParser(params);
		
		try {
			parser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String program=
				"model test0\n" + 
				"elements: \n" + 
				"\tboolean GPL\n" + 
				"\tboolean Gtp\n" + 
				"\tboolean directed\n" + 
				"\tboolean undirected\n" + 
				"\tboolean Weight\n" + 
				"\tboolean weighted\n" + 
				"\tboolean unweighted\n" + 
				"\tboolean Search\n" + 
				"\tboolean BFS\n" + 
				"\tboolean DFS\n" + 
				"\tboolean Algorithms\n" + 
				"\tboolean connected\n" + 
				"\tboolean stronglyc\n" + 
				"\tboolean cycle\n" + 
				"\tboolean mstprim\n" + 
				"\tboolean mstkruskal\n" + 
				"\tboolean shortest\n" + 
				"relations:\n" + 
				"\tr0: coreElements(GPL)\n" + 
				"\tr1: decomposition(GPL, [Gtp])<1>\n" + 
				"\tr2: group( Gtp, [directed, undirected])[1,1]\n" + 
				"\tr3: decomposition(GPL, [Weight])<0>\n" + 
				"\tr4: group( Weight, [weighted, unweighted])[1,1]\n" + 
				"\tr5: decomposition(GPL, [Search])<0>\n" + 
				"\tr6: group( Search, [BFS, DFS])[1,1]\n" + 
				"\tr7: decomposition(GPL, [Algorithms])<1>\n" + 
				"\tr8: group( Algorithms, [connected, stronglyc, cycle, mstprim, mstkruskal, shortest])[0,*]\n" + 
				"\tr9: expression( ~mstprim OR unweighted)\n" + 
				"\tr10: expression( ~stronglyc OR ~shortest)\n" + 
				"\tr11: expression( ~mstprim OR undirected)\n" + 
				"\tr12: expression( ~shortest OR directed)\n" + 
				"\tr13: expression( ~mstprim OR ~mstkruskal)\n" + 
				"\tr14: expression( ~mstkruskal OR unweighted)\n" + 
				"\tr15: expression( ~stronglyc OR undirected)\n" + 
				"\tr16: expression( ~connected OR ~stronglyc)\n" + 
				"\tr17: expression( ~connected OR Search)\n" + 
				"\tr18: expression( ~mstkruskal OR undirected)\n" + 
				"\tr19: expression( ~mstkruskal OR ~shortest)\n" + 
				"\tr20: expression( ~mstprim OR ~shortest)\n" + 
				"\tr21: expression( ~cycle OR DFS)\n" + 
				"operations:\n" + 
				"validModel,numberOfConfigurations\n";
		//assertSame(program, parser.getProgram()); 
		
		
	}

}
