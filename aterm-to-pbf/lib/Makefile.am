lib_LTLIBRARIES = libaterm2pbf.la 
include_HEADERS = $(PUBLIC_INCL)
noinst_HEADERS  = $(PRIVATE_INCL)

AM_CPPFLAGS = -I$(srcdir) 

libaterm2pbf_la_SOURCES = $(ALLSRCS)
libaterm2pbf_la_CFLAGS = 
libaterm2pbf_la_LDFLAGS = -avoid-version -no-undefined

ALLSRCS = aterm2pbf.c hashtable.c pdbtypes.c byteencoding.c

PUBLIC_INCL = aterm2pbf.h pdbtypes.h

PRIVATE_INCL = hashtable.h byteencoding.h

INCLUDES = $(ATERM_CFLAGS)

libaterm2pbf_la_LIBADD = \
	-L$(top_builddir)/src \
        $(ATERM_LIBS)
