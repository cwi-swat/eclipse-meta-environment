#include <asfix-spec.h>

#include <pdbtypes.h>

#include <stdlib.h>

/* TODO Add summay. */

/* NOTE: This stuff is not declared in the 'proper' order, so old compilers may have problems with it (so people should upgrade them and stop using ancient junk). */
A2PType generateAsfixSpec(){
	A2PType emptyTypeArray[1] = {NULL};
	
	A2PType iType = integerType();
	A2PType intChild[2] = {iType, NULL};
	
	A2PType sType = stringType();
	A2PType stringChild[2] = {sType, NULL};
	
	A2PType vType = valueType();
	A2PType valueChild[2] = {vType, NULL};
	
	/* Char */
	A2PType charRange = abstractDataType("CharRange"); /* CharRange */
	constructorType("char", charRange, intChild); /* character */           /* TODO Fix this. */
	A2PType rangeChildren[3] = {iType, iType, NULL};
	constructorType("range", charRange, rangeChildren); /* range */
	A2PType charRanges = listType(charRange); /* CharRanges */
	
	/* Symbol */
	A2PType symbol = abstractDataType("Symbol"); /* Symbol */
	A2PType symbolChild[2] = {symbol, NULL};
	constructorType("lit", symbol, stringChild); /* lit */
	constructorType("cilit", symbol, stringChild); /* cilit */
	constructorType("cf", symbol, symbolChild); /* cf */
	constructorType("lex", symbol, symbolChild); /* lex */
	constructorType("empty", symbol, emptyTypeArray); /* empty */
	A2PType symbols = listType(symbol); /* symbols */
	A2PType symbolsChild[2] = {symbols, NULL};
	constructorType("seq", symbol, symbolsChild); /* seq */
	constructorType("opt", symbol, symbolChild); /* opt */
	A2PType symbolSymbolChildren[3] = {symbol, symbol, NULL};
	constructorType("alt", symbol, symbolSymbolChildren); /* alt */
	A2PType symbolSymbolsChildren[3] = {symbol, symbols, NULL};
	constructorType("tuple", symbol, symbolSymbolsChildren); /* tuple */
	constructorType("sort", symbol, stringChild); /* sort */
	constructorType("iter", symbol, symbolChild); /* iter-plus */
	constructorType("iter-star", symbol, symbolChild); /* iter-star */
	constructorType("iter-sep", symbol, symbolSymbolChildren); /* iter-plus-sep */
	constructorType("iter-star-sep", symbol, symbolSymbolChildren); /* iter-star-sep */
	A2PType symbolIntChildren[3] = {symbol, iType, NULL};
	constructorType("iter-n", symbol, symbolIntChildren); /* iter-n */
	A2PType symbolSymbolIntChildren[3] = {symbol, iType, NULL};
	constructorType("iter-sep-n", symbol, symbolSymbolIntChildren); /* iter-sep-n */
	A2PType symbolsSymbolChildren[3] = {symbols, symbol, NULL};
	constructorType("func", symbol, symbolsSymbolChildren); /* func */
	A2PType stringSymbolsChildren[3] = {sType, symbols, NULL};
	constructorType("parameterized-sort", symbol, stringSymbolsChildren); /* parameterized-sort */
	constructorType("strategy", symbol, symbolSymbolChildren); /* strategy */
	constructorType("varsym", symbol, symbolChild); /* var-sym */
	constructorType("layout", symbol, emptyTypeArray); /* layout */
	A2PType charRangesChild[2] = {charRanges, NULL};
	constructorType("char-class", symbol, charRangesChild); /* char-class */
	
	/* Associativity */
	A2PType associativity = abstractDataType("Associativity");
	constructorType("left", associativity, emptyTypeArray); /* left */
	constructorType("right", associativity, emptyTypeArray); /* right */
	constructorType("assoc", associativity, emptyTypeArray); /* assoc */
	constructorType("nonAssoc", associativity, emptyTypeArray); /* non- assoc */
	
	/* Attr */
	A2PType attr = abstractDataType("Attr"); /* Attr */
	constructorType("assoc", attr, stringChild); /* assoc */
	constructorType("term", attr, valueChild); /* term */
	constructorType("id", attr, stringChild); /* id */
	constructorType("bracket", attr, emptyTypeArray); /* bracket */
	constructorType("reject", attr, emptyTypeArray); /* reject */
	constructorType("prefer", attr, emptyTypeArray); /* prefer */
	constructorType("avoid", attr, emptyTypeArray); /* avoid */
	A2PType attrs = listType(attr); /* Attrs */
	
	/* Attributes */
	A2PType attributes = abstractDataType("Attributes"); /* Attributes */
	constructorType("no-attrs", attributes, emptyTypeArray); /* no-attrs */
	A2PType attrsChild[2] = {attrs, NULL};
	constructorType("attrs", attributes, attrsChild); /* attrs */
	
	/* Production */
	A2PType production = abstractDataType("Production");
	A2PType symbolSymbolAttributesChildren[4] = {symbol, symbol, attributes, NULL};
	constructorType("prod", production, symbolSymbolAttributesChildren); /* Default */
	constructorType("list", production, symbolChild); /* List */
	
	/* Tree */
	A2PType tree = abstractDataType("Tree"); /* Tree */
	A2PType args = listType(tree); /* Args */
	A2PType productionArgsChildren[3] = {production, args, NULL};
	constructorType("appl", tree, productionArgsChildren); /* appl*/
	constructorType("cycle", tree, symbolIntChildren); /* cycle */
	A2PType argsChild[2] = {args, NULL};
	constructorType("amb", tree, argsChild); /* amb */
	constructorType("character", tree, intChild); /* char */                /* TODO Fix this. */
	
	/* ParseTree */
	A2PType top = abstractDataType("top"); /* top */
	A2PType treeIntChildren[3] = {tree, iType, NULL};
	A2PType parseTree = constructorType("parsetree", top, treeIntChildren); /* ParseTree */
	
	return parseTree;
}
