package splot2HLVL;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import fm.FeatureModelException;
import utils.ParsingParameters;

class TestSplot2HLVL {
	@Test
	void testImputString() {
		String splotData= "<!-- This model was created online using SPLOT's Feature Model Editor (http://www.splot-research.org) on Thu, Jan 17, 2019 - 4:20 AM  -->\n" + 
			"<feature_model name=\"Graph Product Line\">\n" + 
			"<meta>\n" + 
			"<data name=\"description\">A simple graph product line</data>\n" + 
			"<data name=\"creator\">Don Batory</data>\n" + 
			"<data name=\"address\"></data>\n" + 
			"<data name=\"email\">batory@cs.utexas.edu</data>\n" + 
			"<data name=\"phone\"></data>\n" + 
			"<data name=\"website\">http://www.cs.utexas.edu/~dsb/</data>\n" + 
			"<data name=\"organization\">University of Texas at Austin</data>\n" + 
			"<data name=\"department\"></data>\n" + 
			"<data name=\"date\"></data>\n" + 
			"<data name=\"reference\">Don S. Batory. Feature models, grammars, and propositional formulas. In Software Product Lines, 9th International Conference, SPLC 2005, Rennes, France, September 26-29, 2005, Proceedings, volume 3714 of Lecture Notes in Computer Science, pages 7-20. Springer, 2005</data>\n" + 
			"</meta>\n" + 
			"<feature_tree>\n" + 
			":r GPL(_r)\n" + 
			"	:m Gtp(_r_3)\n" + 
			"		:g (_r_3_4) [1,1] \n" + 
			"			: directed(_r_3_4_5)\n" + 
			"			: undirected(_r_3_4_6)\n" + 
			"	:o Weight(_r_10)\n" + 
			"		:g (_r_10_11) [1,1] \n" + 
			"			: weighted(_r_10_11_12)\n" + 
			"			: unweighted(_r_10_11_13)\n" + 
			"	:o Search(_r_15)\n" + 
			"		:g (_r_15_16) [1,1] \n" + 
			"			: BFS(_r_15_16_17)\n" + 
			"			: DFS(_r_15_16_18)\n" + 
			"	:m Algorithms(_r_19)\n" + 
			"		:g (_r_19_20) [1,*] \n" + 
			"			: connected(_r_19_20_22)\n" + 
			"			: stronglyc(_r_19_20_23)\n" + 
			"			: cycle(_r_19_20_24)\n" + 
			"			: mstprim(_r_19_20_25)\n" + 
			"			: mstkruskal(_r_19_20_26)\n" + 
			"			: shortest(_r_19_20_27)\n" + 
			"</feature_tree>\n" + 
			"<constraints>\n" + 
			"constraint11:_r_10_11_13 or ~_r_19_20_25\n" + 
			"constraint_17:~_r_19_20_23 or ~_r_19_20_27\n" + 
			"constraint10:_r_3_4_6 or ~_r_19_20_25\n" + 
			"constraint13:_r_3_4_5 or ~_r_19_20_27\n" + 
			"constraint12:~_r_19_20_25 or ~_r_19_20_26\n" + 
			"constraint9:_r_10_11_13 or ~_r_19_20_26\n" + 
			"constraint6:_r_3_4_6 or ~_r_19_20_23\n" + 
			"constraint_13:~_r_19_20_22 or ~_r_19_20_23\n" + 
			"constraint5:_r_15 or ~_r_19_20_22\n" + 
			"constraint8:_r_3_4_6 or ~_r_19_20_26\n" + 
			"constraint_15:~_r_19_20_26 or ~_r_19_20_27\n" + 
			"constraint_16:~_r_19_20_25 or ~_r_19_20_27\n" + 
			"constraint7:_r_15_16_18 or ~_r_19_20_24\n" + 
			"</constraints>\n" + 
			"</feature_model>\n" + 
			"";
		String result ="model  hlvlData_generated\n" + 
				"elements: \n" + 
				"	boolean GPL\n" + 
				"	boolean Gtp\n" + 
				"	boolean directed\n" + 
				"	boolean undirected\n" + 
				"	boolean Weight\n" + 
				"	boolean weighted\n" + 
				"	boolean unweighted\n" + 
				"	boolean Search\n" + 
				"	boolean BFS\n" + 
				"	boolean DFS\n" + 
				"	boolean Algorithms\n" + 
				"	boolean connected\n" + 
				"	boolean stronglyc\n" + 
				"	boolean cycle\n" + 
				"	boolean mstprim\n" + 
				"	boolean mstkruskal\n" + 
				"	boolean shortest\n" + 
				"relations:\n" + 
				"	r0: coreElements(GPL)\n" + 
				"	r1:decomposition(GPL,[Gtp])<1>\n" + 
				"	r2:group(Gtp,[directed, undirected])[1,1]\n" + 
				"	r3:decomposition(GPL,[Weight])<0>\n" + 
				"	r4:group(Weight,[weighted, unweighted])[1,1]\n" + 
				"	r5:decomposition(GPL,[Search])<0>\n" + 
				"	r6:group(Search,[BFS, DFS])[1,1]\n" + 
				"	r7:decomposition(GPL,[Algorithms])<1>\n" + 
				"	r8:group(Algorithms,[connected, stronglyc, cycle, mstprim, mstkruskal, shortest])[1,*]\n" + 
				"	r9:expression(~mstprim OR unweighted )\n" + 
				"	r10:expression(~stronglyc OR ~shortest )\n" + 
				"	r11:expression(~mstprim OR undirected )\n" + 
				"	r12:expression(~shortest OR directed )\n" + 
				"	r13:expression(~mstprim OR ~mstkruskal )\n" + 
				"	r14:expression(~mstkruskal OR unweighted )\n" + 
				"	r15:expression(~stronglyc OR undirected )\n" + 
				"	r16:expression(~connected OR ~stronglyc )\n" + 
				"	r17:expression(~connected OR Search )\n" + 
				"	r18:expression(~mstkruskal OR undirected )\n" + 
				"	r19:expression(~mstkruskal OR ~shortest )\n" + 
				"	r20:expression(~mstprim OR ~shortest )\n" + 
				"	r21:expression(~cycle OR DFS )\n" + 
				"operations: \n" + 
				"validModel,numberOfConfigurations";

		Splot2HlvlParser parser = new Splot2HlvlParser();
		try {
			//System.out.println(parser.parser(splotData));
			assertEquals(result, parser.parser(splotData));
		} catch (FeatureModelException | IOException e) {
	
			e.printStackTrace();
			fail();
		}
		
	
	}
	
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
				"model test0_generated\n" + 
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
				"\tr0: coreElements(GPL )\n" + 
				"\tr1: decomposition(GPL, [Gtp] )<1>\n" + 
				"\tr2: group( Gtp, [directed, undirected] )[1,1]\n" + 
				"\tr3: decomposition(GPL, [Weight] )<0>\n" + 
				"\tr4: group( Weight, [weighted, unweighted] )[1,1]\n" + 
				"\tr5: decomposition(GPL, [Search] )<0>\n" + 
				"\tr6: group( Search, [BFS, DFS] )[1,1]\n" + 
				"\tr7: decomposition(GPL, [Algorithms] )<1>\n" + 
				"\tr8: group( Algorithms, [connected, stronglyc, cycle, mstprim, mstkruskal, shortest] )[0,*]\n" + 
				"\tr9: expression( ~mstprim OR unweighted )\n" + 
				"\tr10: expression( ~stronglyc OR ~shortest )\n" + 
				"\tr11: expression( ~mstprim OR undirected )\n" + 
				"\tr12: expression( ~shortest OR directed )\n" + 
				"\tr13: expression( ~mstprim OR ~mstkruskal )\n" + 
				"\tr14: expression( ~mstkruskal OR unweighted )\n" + 
				"\tr15: expression( ~stronglyc OR undirected )\n" + 
				"\tr16: expression( ~connected OR ~stronglyc )\n" + 
				"\tr17: expression( ~connected OR Search )\n" + 
				"\tr18: expression( ~mstkruskal OR undirected )\n" + 
				"\tr19: expression( ~mstkruskal OR ~shortest )\n" + 
				"\tr20: expression( ~mstprim OR ~shortest \n" + 
				"\tr21: expression( ~cycle OR DFS )\n" + 
				"operations: \n" + 
				"validModel,numberOfConfigurations\n";
		System.out.println(parser.getProgram());
		System.out.println(program);
		assertSame(program, parser.getProgram()); 
		
	}

}
