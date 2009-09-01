#include <asfix-spec.h>

#include <pdbtypes.h>

#include <stdlib.h>

/* TODO Add summary. */

/* NOTE: This stuff is not declared in the 'proper' order, so old compilers may have problems with it (so people should upgrade them and stop using ancient junk). */
A2PType generateAsfixSpec(){
	A2PType emptyTypeArray[1] = {NULL};
	
	A2PType iType = integerType();
	
	A2PType sType = stringType();
	A2PType stringChild[2] = {sType, NULL};
	char *stringLabel[2] = {"string", NULL};
	
	/* Char */
	A2PType charRange = abstractDataType("CharRange"); /* CharRange */
	A2PType startChild[2] = {iType, NULL};
	char *startLabel[2] = {"start", NULL};
	constructorTypeWithLabels("single", charRange, startChild, startLabel); /* character */           /* TODO Fix this. */
	A2PType startEndChildren[3] = {iType, iType, NULL};
	char *startEndLabels[3] = {"start", "end", NULL};
	constructorTypeWithLabels("range", charRange, startEndChildren, startEndLabels); /* range */
	A2PType charRanges = listType(charRange); /* CharRanges */
	
	/* Symbol */
	A2PType symbol = abstractDataType("Symbol"); /* Symbol */
	A2PType symbolChild[2] = {symbol, NULL};
	char *symbolLabel[2] = {"symbol", NULL};
	constructorTypeWithLabels("lit", symbol, stringChild, stringLabel); /* lit */
	constructorTypeWithLabels("cilit", symbol, stringChild, stringLabel); /* cilit */
	constructorTypeWithLabels("cf", symbol, symbolChild, symbolLabel); /* cf */
	constructorTypeWithLabels("lex", symbol, symbolChild, symbolLabel); /* lex */
	constructorType("empty", symbol, emptyTypeArray); /* empty */
	A2PType symbols = listType(symbol); /* symbols */
	A2PType symbolsChild[2] = {symbols, NULL};
	char *symbolsLabel[2] = {"symbols", NULL};
	constructorTypeWithLabels("seq", symbol, symbolsChild, symbolsLabel); /* seq */
	constructorTypeWithLabels("opt", symbol, symbolChild, symbolLabel); /* opt */
	A2PType rhsLhsChildren[3] = {symbol, symbol, NULL};
	char *rhsLhsLabels[3] = {"symbol", "symbol", NULL};
	constructorTypeWithLabels("alt", symbol, rhsLhsChildren, rhsLhsLabels); /* alt */
	A2PType headRestChildren[3] = {symbol, symbols, NULL};
	char *headRestLabels[3] = {"head", "rest", NULL};
	constructorTypeWithLabels("tuple", symbol, headRestChildren, headRestLabels); /* tuple */
	constructorTypeWithLabels("sort", symbol, stringChild, stringLabel); /* sort */
	constructorTypeWithLabels("iter", symbol, symbolChild, symbolLabel); /* iter-plus */
	constructorTypeWithLabels("iter-star", symbol, symbolChild, symbolLabel); /* iter-star */
	A2PType symbolSeparatorChildren[3] = {symbol, symbol, NULL};
	char *symbolSeparatorLabels[3] = {"symbol", "separator", NULL};
	constructorTypeWithLabels("iter-sep", symbol, symbolSeparatorChildren, symbolSeparatorLabels); /* iter-plus-sep */
	constructorTypeWithLabels("iter-star-sep", symbol, symbolSeparatorChildren, symbolSeparatorLabels); /* iter-star-sep */
	A2PType symbolNumberChildren[3] = {symbol, iType, NULL};
	char *symbolNumberLabels[3] = {"symbol", "number", NULL};
	constructorTypeWithLabels("iter-n", symbol, symbolNumberChildren, symbolNumberLabels); /* iter-n */
	A2PType symbolSeparatorNumberChildren[4] = {symbol, symbol, iType, NULL};
	char *symbolSeparatorNumberLabels[4] = {"symbol", "separator", "number", NULL};
	constructorTypeWithLabels("iter-sep-n", symbol, symbolSeparatorNumberChildren, symbolSeparatorNumberLabels); /* iter-sep-n */
	A2PType symbolsSymbolChildren[3] = {symbols, symbol, NULL};
	char *symbolsSymbolLabels[3] = {"symbols", "symbol", NULL};
	constructorTypeWithLabels("func", symbol, symbolsSymbolChildren, symbolsSymbolLabels); /* func */
	A2PType sortParametersChildren[3] = {sType, symbols, NULL};
	char *sortParametersLabels[3] = {"sort", "parameters", NULL};
	constructorTypeWithLabels("parameterized-sort", symbol, sortParametersChildren, sortParametersLabels); /* parameterized-sort */
	constructorTypeWithLabels("strategy", symbol, rhsLhsChildren, rhsLhsLabels); /* strategy */
	constructorTypeWithLabels("varsym", symbol, symbolChild, symbolLabel); /* var-sym */
	constructorType("layout", symbol, emptyTypeArray); /* layout */
	A2PType rangesChild[2] = {charRanges, NULL};
	char *rangesLabel[2] = {"ranges", NULL};
	constructorTypeWithLabels("char-class", symbol, rangesChild, rangesLabel); /* char-class */
	
	/* Associativity */
	A2PType associativity = abstractDataType("Associativity");
	constructorType("left", associativity, emptyTypeArray); /* left */
	constructorType("right", associativity, emptyTypeArray); /* right */
	constructorType("assoc", associativity, emptyTypeArray); /* assoc */
	constructorType("nonAssoc", associativity, emptyTypeArray); /* non- assoc */
	
	/* Attr */
	A2PType attr = abstractDataType("Attr"); /* Attr */
	A2PType assocChild[2] = {associativity, NULL};
	char *assocLabel[2] = {"assoc", NULL};
	constructorTypeWithLabels("assoc", attr, assocChild, assocLabel); /* assoc */
	A2PType termChild[2] = {valueType(), NULL};
	char *termLabel[2] = {"term", NULL};
	constructorTypeWithLabels("term", attr, termChild, termLabel); /* term */
	A2PType modulenameChild[2] = {sType, NULL};
	char *modulenameLabel[2] = {"moduleName", NULL};
	constructorTypeWithLabels("id", attr, modulenameChild, modulenameLabel); /* id */
	constructorType("bracket", attr, emptyTypeArray); /* bracket */
	constructorType("reject", attr, emptyTypeArray); /* reject */
	constructorType("prefer", attr, emptyTypeArray); /* prefer */
	constructorType("avoid", attr, emptyTypeArray); /* avoid */
	A2PType attrs = listType(attr); /* Attrs */
	
	/* Attributes */
	A2PType attributes = abstractDataType("Attributes"); /* Attributes */
	constructorType("no-attrs", attributes, emptyTypeArray); /* no-attrs */
	A2PType attrsChild[2] = {attrs, NULL};
	char *attrsLabel[2] = {"attrs", NULL};
	constructorTypeWithLabels("attrs", attributes, attrsChild, attrsLabel); /* attrs */
	
	/* Production */
	A2PType production = abstractDataType("Production");
	A2PType lhsRhsAttributesChildren[4] = {symbols, symbol, attributes, NULL};
	char *lhsRhsAttributesLabels[4] = {"lhs", "rhs", "attributes", NULL};
	constructorTypeWithLabels("prod", production, lhsRhsAttributesChildren, lhsRhsAttributesLabels); /* Default */
	A2PType rhsChild[2] = {symbol, NULL};
	char *rhsLabel[2] = {"rhs", NULL};
	constructorTypeWithLabels("list", production, rhsChild, rhsLabel); /* List */
	
	/* Tree */
	A2PType tree = abstractDataType("Tree"); /* Tree */
	A2PType args = listType(tree); /* Args */
	A2PType prodArgsChildren[3] = {production, args, NULL};
	char *prodArgsLabels[3] = {"prod", "args", NULL};
	constructorTypeWithLabels("appl", tree, prodArgsChildren, prodArgsLabels); /* appl*/
	A2PType symbolCyclelengthChildren[3] = {symbol, iType, NULL};
	char *symbolCyclelengthLabels[3] = {"symbol", "cycleLength", NULL};
	constructorTypeWithLabels("cycle", tree, symbolCyclelengthChildren, symbolCyclelengthLabels); /* cycle */
	A2PType alternatives = setType(tree); /* Alteratives */
	A2PType alternativesChild[2] = {alternatives, NULL};
	char *alternativesLabel[2] = {"alternatives", NULL};
	constructorTypeWithLabels("amb", tree, alternativesChild, alternativesLabel); /* amb */
	A2PType characterChild[2] = {iType, NULL};
	char *characterLabel[2] = {"character", NULL};
	constructorTypeWithLabels("char", tree, characterChild, characterLabel); /* char */                /* TODO Fix this. */
	
	/* ParseTree */
	A2PType top = abstractDataType("top"); /* top */
	A2PType topAmbcntChildren[3] = {tree, iType, NULL};
	char *topAmbcntLabels[3] = {"top", "amb_cnt", NULL};
	A2PType parseTree = constructorTypeWithLabels("parsetree", top, topAmbcntChildren, topAmbcntLabels); /* ParseTree */
	
	return parseTree;
}
