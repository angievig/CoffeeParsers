package com.coffee.modelParsers.ExpresionHLVLPackage;

import com.coffee.modelParsers.basicHLVLPackage.HlvlBasicKeys;

public class HlvlExpressionParser implements HlvlExpresionFunctions, HlvlExpressionKeys, HlvlBasicKeys {

	@Override
	public String createAnd(String param1, String param2) {
		String result = OPEN_CALL+param1 +" "+HlvlExpressionKeys.AND+" "+param2+CLOSE_CALL;
		return result;
	}

	@Override
	public String createOr(String param1, String param2) {
		String result = OPEN_CALL+param1 +" "+HlvlExpressionKeys.OR+" "+param2+CLOSE_CALL;
		return result;
	}

	@Override
	public String createImp(String param1, String param2) {
		String result = OPEN_CALL+param1 +" "+HlvlExpressionKeys.IMP+" "+param2+CLOSE_CALL;
		return result;
	}

	@Override
	public String createNot(String param1) {
		String result = HlvlExpressionKeys.NOT+param1;
		return result;
	}

}
