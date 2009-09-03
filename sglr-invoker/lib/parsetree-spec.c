#include <parsetree-spec.h>

#include <pdbtypes.h>

#include <stdlib.h>

/* NOTE: This stuff is not declared in the 'proper' order, so old compilers may have problems with it (so people should upgrade them and stop using ancient junk). */
A2PType generateParseTreeSpec(){
	A2PType emptyTypeArray[1] = {NULL};
	
	A2PType intType = A2PintegerType();
	
	A2PType stringType = A2PstringType();
	A2PType stringChild[2] = {stringType, NULL};
	char *stringLabel[2] = {"string", NULL};
	
	/* --------------------- */
	/* ----- ParseTree ----- */
	/* --------------------- */
	
	/* Char */
	A2PType charRange = A2PabstractDataType("CharRange"); /* CharRange */
	A2PType startChild[2] = {intType, NULL};
	char *startLabel[2] = {"start", NULL};
	A2PType single = A2PconstructorTypeWithLabels("single", charRange, startChild, startLabel); /* character */
	A2PlinkNativeTypeToADT(charRange, intType, single);
	A2PType startEndChildren[3] = {intType, intType, NULL};
	char *startEndLabels[3] = {"start", "end", NULL};
	A2PconstructorTypeWithLabels("range", charRange, startEndChildren, startEndLabels); /* range */
	A2PType charRanges = A2PlistType(charRange); /* CharRanges */
	
	/* Symbol */
	A2PType symbol = A2PabstractDataType("Symbol"); /* Symbol */
	A2PType symbolChild[2] = {symbol, NULL};
	char *symbolLabel[2] = {"symbol", NULL};
	A2PconstructorTypeWithLabels("lit", symbol, stringChild, stringLabel); /* lit */
	A2PconstructorTypeWithLabels("cilit", symbol, stringChild, stringLabel); /* cilit */
	A2PconstructorTypeWithLabels("cf", symbol, symbolChild, symbolLabel); /* cf */
	A2PconstructorTypeWithLabels("lex", symbol, symbolChild, symbolLabel); /* lex */
	A2PconstructorType("empty", symbol, emptyTypeArray); /* empty */
	A2PType symbols = A2PlistType(symbol); /* symbols */
	A2PType symbolsChild[2] = {symbols, NULL};
	char *symbolsLabel[2] = {"symbols", NULL};
	A2PconstructorTypeWithLabels("seq", symbol, symbolsChild, symbolsLabel); /* seq */
	A2PconstructorTypeWithLabels("opt", symbol, symbolChild, symbolLabel); /* opt */
	A2PType rhsLhsChildren[3] = {symbol, symbol, NULL};
	char *rhsLhsLabels[3] = {"symbol", "symbol", NULL};
	A2PconstructorTypeWithLabels("alt", symbol, rhsLhsChildren, rhsLhsLabels); /* alt */
	A2PType headRestChildren[3] = {symbol, symbols, NULL};
	char *headRestLabels[3] = {"head", "rest", NULL};
	A2PconstructorTypeWithLabels("tuple", symbol, headRestChildren, headRestLabels); /* tuple */
	A2PconstructorTypeWithLabels("sort", symbol, stringChild, stringLabel); /* sort */
	A2PconstructorTypeWithLabels("iter", symbol, symbolChild, symbolLabel); /* iter-plus */
	A2PconstructorTypeWithLabels("iter-star", symbol, symbolChild, symbolLabel); /* iter-star */
	A2PType symbolSeparatorChildren[3] = {symbol, symbol, NULL};
	char *symbolSeparatorLabels[3] = {"symbol", "separator", NULL};
	A2PconstructorTypeWithLabels("iter-sep", symbol, symbolSeparatorChildren, symbolSeparatorLabels); /* iter-plus-sep */
	A2PconstructorTypeWithLabels("iter-star-sep", symbol, symbolSeparatorChildren, symbolSeparatorLabels); /* iter-star-sep */
	A2PType symbolNumberChildren[3] = {symbol, intType, NULL};
	char *symbolNumberLabels[3] = {"symbol", "number", NULL};
	A2PconstructorTypeWithLabels("iter-n", symbol, symbolNumberChildren, symbolNumberLabels); /* iter-n */
	A2PType symbolSeparatorNumberChildren[4] = {symbol, symbol, intType, NULL};
	char *symbolSeparatorNumberLabels[4] = {"symbol", "separator", "number", NULL};
	A2PconstructorTypeWithLabels("iter-sep-n", symbol, symbolSeparatorNumberChildren, symbolSeparatorNumberLabels); /* iter-sep-n */
	A2PType symbolsSymbolChildren[3] = {symbols, symbol, NULL};
	char *symbolsSymbolLabels[3] = {"symbols", "symbol", NULL};
	A2PconstructorTypeWithLabels("func", symbol, symbolsSymbolChildren, symbolsSymbolLabels); /* func */
	A2PType sortParametersChildren[3] = {stringType, symbols, NULL};
	char *sortParametersLabels[3] = {"sort", "parameters", NULL};
	A2PconstructorTypeWithLabels("parameterized-sort", symbol, sortParametersChildren, sortParametersLabels); /* parameterized-sort */
	A2PconstructorTypeWithLabels("strategy", symbol, rhsLhsChildren, rhsLhsLabels); /* strategy */
	A2PconstructorTypeWithLabels("varsym", symbol, symbolChild, symbolLabel); /* var-sym */
	A2PconstructorType("layout", symbol, emptyTypeArray); /* layout */
	A2PType rangesChild[2] = {charRanges, NULL};
	char *rangesLabel[2] = {"ranges", NULL};
	A2PconstructorTypeWithLabels("char-class", symbol, rangesChild, rangesLabel); /* char-class */
	
	/* Associativity */
	A2PType associativity = A2PabstractDataType("Associativity");
	A2PconstructorType("left", associativity, emptyTypeArray); /* left */
	A2PconstructorType("right", associativity, emptyTypeArray); /* right */
	A2PconstructorType("assoc", associativity, emptyTypeArray); /* assoc */
	A2PconstructorType("non-assoc", associativity, emptyTypeArray); /* non-assoc */
	
	/* Attr */
	A2PType attr = A2PabstractDataType("Attr"); /* Attr */
	A2PType assocChild[2] = {associativity, NULL};
	char *assocLabel[2] = {"assoc", NULL};
	A2PconstructorTypeWithLabels("assoc", attr, assocChild, assocLabel); /* assoc */
	A2PType termChild[2] = {A2PvalueType(), NULL};
	char *termLabel[2] = {"term", NULL};
	A2PconstructorTypeWithLabels("term", attr, termChild, termLabel); /* term */
	A2PType modulenameChild[2] = {stringType, NULL};
	char *modulenameLabel[2] = {"moduleName", NULL};
	A2PconstructorTypeWithLabels("id", attr, modulenameChild, modulenameLabel); /* id */
	A2PconstructorType("bracket", attr, emptyTypeArray); /* bracket */
	A2PconstructorType("reject", attr, emptyTypeArray); /* reject */
	A2PconstructorType("prefer", attr, emptyTypeArray); /* prefer */
	A2PconstructorType("avoid", attr, emptyTypeArray); /* avoid */
	A2PType attrs = A2PlistType(attr); /* Attrs */
	
	/* Attributes */
	A2PType attributes = A2PabstractDataType("Attributes"); /* Attributes */
	A2PconstructorType("no-attrs", attributes, emptyTypeArray); /* no-attrs */
	A2PType attrsChild[2] = {attrs, NULL};
	char *attrsLabel[2] = {"attrs", NULL};
	A2PconstructorTypeWithLabels("attrs", attributes, attrsChild, attrsLabel); /* attrs */
	
	/* Production */
	A2PType production = A2PabstractDataType("Production");
	A2PType lhsRhsAttributesChildren[4] = {symbols, symbol, attributes, NULL};
	char *lhsRhsAttributesLabels[4] = {"lhs", "rhs", "attributes", NULL};
	A2PconstructorTypeWithLabels("prod", production, lhsRhsAttributesChildren, lhsRhsAttributesLabels); /* Default */
	A2PType rhsChild[2] = {symbol, NULL};
	char *rhsLabel[2] = {"rhs", NULL};
	A2PconstructorTypeWithLabels("list", production, rhsChild, rhsLabel); /* List */
	
	/* Tree */
	A2PType tree = A2PabstractDataType("Tree"); /* Tree */
	A2PType args = A2PlistType(tree); /* Args */
	A2PType prodArgsChildren[3] = {production, args, NULL};
	char *prodArgsLabels[3] = {"prod", "args", NULL};
	A2PconstructorTypeWithLabels("appl", tree, prodArgsChildren, prodArgsLabels); /* appl*/
	A2PType symbolCyclelengthChildren[3] = {symbol, intType, NULL};
	char *symbolCyclelengthLabels[3] = {"symbol", "cycleLength", NULL};
	A2PconstructorTypeWithLabels("cycle", tree, symbolCyclelengthChildren, symbolCyclelengthLabels); /* cycle */
	A2PType alternatives = A2PsetType(tree); /* Alteratives */
	A2PType alternativesChild[2] = {alternatives, NULL};
	char *alternativesLabel[2] = {"alternatives", NULL};
	A2PconstructorTypeWithLabels("amb", tree, alternativesChild, alternativesLabel); /* amb */
	A2PType characterChild[2] = {intType, NULL};
	char *characterLabel[2] = {"character", NULL};
	A2PType character = A2PconstructorTypeWithLabels("char", tree, characterChild, characterLabel); /* char */
	A2PlinkNativeTypeToADT(tree, intType, character);
	
	/* ParseTree */
	A2PType parseTree = A2PabstractDataType("ParseTree"); /* ParseTree */
	A2PType topAmbcntChildren[3] = {tree, intType, NULL};
	char *topAmbcntLabels[3] = {"top", "amb_cnt", NULL};
	A2PconstructorTypeWithLabels("parsetree", parseTree, topAmbcntChildren, topAmbcntLabels); /* ParseTree */
	
	/* --------------------- */
	/* ------ Summary ------ */
	/* --------------------- */
	
	/* Area */
	A2PType area = A2PabstractDataType("Area"); /* Area */
	A2PType blBcElEcOLChildren[7] = {intType, intType, intType, intType, intType, intType, NULL};
	char *blBcElEcOLLabels[7] = {"beginLine", "beginColumn", "endLine", "endColumn", "offset", "length", NULL};
	A2PconstructorTypeWithLabels("area", area, blBcElEcOLChildren, blBcElEcOLLabels); /* area */
	
	/* Location */
	A2PType location = A2PabstractDataType("Location"); /* Location */
	A2PType filenameChild[2] = {stringType, NULL};
	char *filenameLabel[2] = {"filename", NULL};
	A2PconstructorTypeWithLabels("file", location, filenameChild, filenameLabel); /* file */
	A2PType areaChild[2] = {area, NULL};
	char *areaLabel[2] = {"area", NULL};
	A2PconstructorTypeWithLabels("area", location, areaChild, areaLabel); /* area */
	A2PType filenameAreaChildren[3] = {stringType, area, NULL};
	char *filenameAreaLabels[3] = {"filename", "area", NULL};
	A2PconstructorTypeWithLabels("area-in-file", location, filenameAreaChildren, filenameAreaLabels); /* area-in-file */
	
	/* Subject */
	A2PType subject = A2PabstractDataType("Subject"); /* Subject */
	A2PType descriptionChild[2] = {stringType, NULL};
	char *descriptionLabel[2] = {"description", NULL};
	A2PconstructorTypeWithLabels("subject", subject, descriptionChild, descriptionLabel); /* subject */
	A2PType descriptionLocationChildren[3] = {stringType, location, NULL};
	char *descriptionLocationLabels[3] = {"description", "location", NULL};
	A2PconstructorTypeWithLabels("localized", subject, descriptionLocationChildren, descriptionLocationLabels); /* localized */
	A2PType subjects = A2PlistType(subject);
	
	/* Error */
	A2PType error = A2PabstractDataType("Error"); /* Error */
	A2PType descriptionSubjectsChildren[3] = {stringType, subjects, NULL};
	char *descriptionSubjectsLabels[3] = {"description", "subjects", NULL};
	A2PconstructorTypeWithLabels("info", error, descriptionSubjectsChildren, descriptionSubjectsLabels); /* info */
	A2PconstructorTypeWithLabels("warning", error, descriptionSubjectsChildren, descriptionSubjectsLabels); /* warning */
	A2PconstructorTypeWithLabels("error", error, descriptionSubjectsChildren, descriptionSubjectsLabels); /* error */
	A2PconstructorTypeWithLabels("fatal", error, descriptionSubjectsChildren, descriptionSubjectsLabels); /* fatal */
	A2PType errors = A2PlistType(error); /* Errors */
	
	/* Summary */
	A2PType producerIdErrorsChildren[4] = {stringType, stringType, errors, NULL};
	char *producerIdErrorsLabels[4] = {"producer", "id", "errors", NULL};
	A2PconstructorTypeWithLabels("summary", parseTree, producerIdErrorsChildren, producerIdErrorsLabels);
	
	return parseTree;
}

