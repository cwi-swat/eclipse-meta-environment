#include <pdbtypes.h>

#include <stdlib.h>

A2PType generateAsfixSpec(){
	A2PType emptyTypeArray[1] = {NULL};
	
	A2PType iType = integerType();
	A2PType intChild[2] = {iType, NULL};
	A2PType intADT = abstractDataType("int");
	
	A2PType sType = stringType();
	A2PType stringChild[2] = {sType, NULL};
	A2PType stringADT = abstractDataType("str");
	
	/* Char */
	A2PType charRange = abstractDataType("CharRange"); /* CharRange */
	A2PType character = constructorType("start", charRange, intChild); /* character */
	A2PType startContainer = constructorType("start", intADT, intChild);
	A2PType endContainer = constructorType("end", intADT, intChild);
	A2PType rangeChildren[3] = {startContainer, endContainer, NULL};
	A2PType range = constructorType("range", charRange, rangeChildren); /* range */
	A2PType charRanges = listType(charRange); /* CharRanges */
	
	/* Symbol */
	A2PType symbol = abstractDataType("Symbol"); /* Symbol */
	A2PType stringContainerADT = abstractDataType("stringContainer");
	A2PType stringContainer = constructorType("string", stringContainerADT, stringChild);
	A2PType stringContainerChild[2] = {stringContainer, NULL};
	A2PType lit = constructorType("lit", symbol, stringContainerChild); /* lit */
	A2PType cilit = constructorType("cilit", symbol, stringContainerChild); /* cilit */
	A2PType symbolChild[2] = {symbol, NULL};
	A2PType symbolContainerADT = abstractDataType("containedSymbol");
	A2PType symbolContainer = constructorType("symbol", symbolContainerADT, symbolChild);
	A2PType symbolContainerChild[2] = {symbolContainer, NULL};
	A2PType cf = constructorType("cf", symbol, symbolContainerChild); /* cf */
	A2PType lex = constructorType("lex", symbol, symbolContainerChild); /* lex */
	A2PType empty = constructorType("empty", symbol, emptyTypeArray); /* empty */
	A2PType seq = constructorType("seq", symbol, symbolContainerChild); /* seq */
	A2PType opt = constructorType("opt", symbol, symbolContainerChild); /* opt */
	A2PType lhsADT = abstractDataType("rhs");
	A2PType rhsADT = abstractDataType("lhs");
	A2PType lhsSymbol = constructorType("lhs", lhsADT, symbolChild);
	A2PType rhsSymbol = constructorType("rhs", rhsADT, symbolChild);
	A2PType lhsRhsChildren[3] = {lhsSymbol, rhsSymbol, NULL};
	A2PType alt = constructorType("alt", symbol, lhsRhsChildren); /* alt */
	A2PType headSymbolADT = abstractDataType("head");
	A2PType restSymbolADT = abstractDataType("rest");
	A2PType headSymbol = constructorType("head", headSymbolADT, symbolChild);
	A2PType restSymbol = constructorType("rest", restSymbolADT, symbolChild);
	A2PType tupleChildren[3] = {headSymbol, restSymbol, NULL};
	A2PType tuple = constructorType("tuple", symbol, tupleChildren); /* tuple */
	A2PType sort = constructorType("sort", symbol, stringChild); /* sort */
	A2PType iterPlus = constructorType("iter", symbol, symbolContainerChild); /* iter-plus */
	A2PType iterStar = constructorType("iter-star", symbol, symbolContainerChild); /* iter-star */
	A2PType seperatorADT = abstractDataType("seperator");
	A2PType seperator = constructorType("seperator", seperatorADT, symbolContainerChild);
	A2PType symbolSepChildren[3] = {symbolContainer, seperator, NULL};
	A2PType iterPlusSep = constructorType("iter-sep", symbol, symbolSepChildren); /* iter-plus-sep */
	A2PType iterStarSep = constructorType("iter-star-sep", symbol, symbolSepChildren); /* iter-star-sep */
	A2PType numberADT = abstractDataType("number");
	A2PType number = constructorType("number", numberADT, intChild);
	A2PType symbolNumberChildren[3] = {symbolContainer, number, NULL};
	A2PType iterN = constructorType("iter-n", symbol, symbolNumberChildren); /* iter-n */
	A2PType symbolSepNumberChildren[4] = {symbolContainer, seperator, number, NULL};
	A2PType iterSepN = constructorType("iter-sep-n", symbol, symbolSepNumberChildren); /* iter-sep-n */
	A2PType symbolSymbolChildren[3] = {symbolContainer, symbolContainer, NULL};
	A2PType func = constructorType("func", symbol, symbolSymbolChildren); /* func */
	A2PType sortContainerADT = abstractDataType("sortContainer");
	A2PType parametersADT = abstractDataType("parameters");
	A2PType sortContainer = constructorType("sort", sortContainerADT, stringChild);
	A2PType parameters = constructorType("parameters", parametersADT, symbolContainerChild);
	A2PType sortParametersChildren[3] = {sortContainer, parameters, NULL};
	A2PType parameterizedSort = constructorType("parameterized-sort", symbol, sortParametersChildren); /* parameterized-sort */
	A2PType strategy = constructorType("strategy", symbol, lhsRhsChildren); /* strategy */
	A2PType varSym = constructorType("varsym", symbol, symbolContainerChild); /* var-sym */
	A2PType layout = constructorType("layout", symbol, emptyTypeArray); /* layout */
	A2PType charRangesChild[2] = {charRanges, NULL};
	A2PType rangesADT = abstractDataType("ranges");
	A2PType ranges = constructorType("ranges", rangesADT, charRangesChild);
	A2PType rangesChild[2] = {ranges, NULL};
	A2PType charClass = constructorType("char-class", symbol, rangesChild); /* char-class */
	A2PType symbols = listType(symbol); /* symbols */
	
	/* Associativity */
	A2PType associativity = abstractDataType("Associativity");
	A2PType left = constructorType("left", associativity, emptyTypeArray); /* left */
	A2PType right = constructorType("right", associativity, emptyTypeArray); /* right */
	A2PType assoc = constructorType("assoc", associativity, emptyTypeArray); /* assoc */
	A2PType nonAssoc = constructorType("nonAssoc", associativity, emptyTypeArray); /* non- assoc */
	
	/* Attr */
	A2PType attr = abstractDataType("Attr"); /* Attr */
	A2PType assocChild[2] = {associativity, NULL};
	A2PType assocStringADT = abstractDataType("assoc");
	A2PType assocString = constructorType("assoc", assocStringADT, assocChild);
	A2PType assocStringChild[2] = {assocString, NULL};
	A2PType assocAttr = constructorType("assoc", attr, assocStringChild); /* assoc */
	A2PType valueChild[] = {valueType(), NULL};
	A2PType valueStringADT = abstractDataType("valueString");
	A2PType valueString = constructorType("value", valueStringADT, valueChild);
	A2PType valueStringChild[2] = {valueString, NULL};
	A2PType term = constructorType("term", attr, valueStringChild); /* term */
	A2PType moduleNameADT = abstractDataType("module-name");
	A2PType moduleName = constructorType("module-name", moduleNameADT, stringChild);
	A2PType moduleNameChild[2] = {moduleName, NULL};
	A2PType id = constructorType("id", attr, moduleNameChild); /* id */
	A2PType bracket = constructorType("bracket", attr, emptyTypeArray); /* bracket */
	A2PType reject = constructorType("reject", attr, emptyTypeArray); /* reject */
	A2PType prefer = constructorType("prefer", attr, emptyTypeArray); /* prefer */
	A2PType avoid = constructorType("avoid", attr, emptyTypeArray); /* avoid */
	A2PType attrs = listType(attr); /* Attrs */
	
	/* Attributes */
	A2PType attributes = abstractDataType("Attributes"); /* Attributes */
	A2PType attributesCons = constructorType("no-attrs", attributes, emptyTypeArray); /* no-attrs */
	A2PType attrsContainerADT = abstractDataType("attrsContainer");
	A2PType attrsChild[2] = {attrs, NULL};
	A2PType attrsContainer = constructorType("attrs", attrsContainerADT, attrsChild);
	A2PType attrsContainerChild[2] = {attrsContainer, NULL};
	A2PType attrsCons = constructorType("attrs", attributes, attrsContainerChild); /* attrs */
	
	/* Production */
	A2PType production = abstractDataType("Production");
	A2PType attributesChildADT = abstractDataType("attributedChild");
	A2PType attributesChild[2] = {attributes, NULL};
	A2PType attributesContainer = constructorType("attributes", attributesChildADT, attributesChild);
	A2PType prodChildren[4] = {lhsSymbol, rhsSymbol, attributesContainer, NULL};
	A2PType prod = constructorType("prod", production, prodChildren); /* Default */
	A2PType rhsContainerADT = abstractDataType("rhsContainer");
	A2PType rhsSymbolChild[2] = {rhsSymbol, NULL};
	A2PType rhsContainer = constructorType("list", rhsContainerADT, rhsSymbolChild);
	A2PType listChildren[2] = {rhsContainer, NULL};
	A2PType list = constructorType("list", production, listChildren); /* List */
	
	/* Tree */
	A2PType tree = abstractDataType("Tree"); /* Tree */
	A2PType args = listType(tree); /* Args */
	A2PType prodContainerADT = abstractDataType("prodContainer");
	A2PType prodContainerChildren[2] = {prodContainerADT, NULL};
	A2PType prodContainer = constructorType("prod", prodContainerADT, prodContainerChildren);
	A2PType argsContainerADT = abstractDataType("argsContainer");
	A2PType argsContainerChildren[2] = {argsContainerADT, NULL};
	A2PType argsContainer = constructorType("args", argsContainerADT, argsContainerChildren);
	A2PType applChildren[3] = {prodContainer, argsContainer, NULL};
	A2PType appl = constructorType("appl", tree, applChildren); /* appl*/
	A2PType cycleLengthContainerADT = abstractDataType("cycleLengthContainer");
	A2PType cycleLengthChildren[2] = {iType, NULL};
	A2PType cycleLengthContainer = constructorType("cycle-length", cycleLengthContainerADT, cycleLengthChildren);
	A2PType cycleChildren[3] = {symbolContainer, cycleLengthContainer, NULL};
	A2PType cycle = constructorType("cycle", tree, cycleChildren); /* cycle */
	A2PType ambChildren[2] = {argsContainer, NULL};
	A2PType amb = constructorType("amb", tree, ambChildren); /* amb */
	A2PType characterCons = constructorType("character", tree, intChild); /* char */
	
	/* ParseTree */
	A2PType top = abstractDataType("top"); /* top */
	A2PType topContainerADT = abstractDataType("topContainer");
	A2PType topContainerChildren[2] = {tree, NULL};
	A2PType topContainer = constructorType("top", topContainerADT, topContainerChildren);
	A2PType ambCntContainerADT = abstractDataType("ambCntContainer");
	A2PType ambCntContainer = constructorType("amb-cnt", ambCntContainerADT, intChild);
	A2PType parseTreeChildren[3] = {topContainer, ambCntContainer, NULL};
	A2PType parseTree = constructorType("parsetree", top, parseTreeChildren); /* ParseTree */
	
	return parseTree;
}
