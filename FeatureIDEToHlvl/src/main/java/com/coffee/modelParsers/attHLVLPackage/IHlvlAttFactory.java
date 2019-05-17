package com.coffee.modelParsers.attHLVLPackage;

import com.coffee.modelParsers.basicHLVLPackage.IHlvlBasicFactory;

public interface IHlvlAttFactory extends IHlvlBasicFactory {

	public String getAtt(String identifier, AttType type);
}
