# $Id: Makefile.am 19991 2006-07-28 14:29:28Z jurgenv $

include $(top_srcdir)/Makefile.java.meta

%.idef: %.idef.src Makefile
	sed -e 's@__PKGDATA__@$(datadir)@g'\
	    -e 's@__LOCAL_JARS__@$(LOCAL_JARS)@g'\
	    -e "s@__EXTERNAL_JARS__@$(EXTERNAL_JARS)@g"\
	    -e 's@__JARFILE__@$(JAVA_JAR)@g'\
	     < $< > $@
