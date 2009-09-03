lib_LTLIBRARIES = libaterm2pbf.la 
include_HEADERS = $(PUBLIC_INCL)
noinst_HEADERS  = $(PRIVATE_INCL)

AM_CPPFLAGS = -I$(srcdir) 

libaterm2pbf_la_SOURCES = $(ALLSRCS)
libaterm2pbf_la_CFLAGS = 
libaterm2pbf_la_LDFLAGS = -avoid-version -no-undefined

ALLSRCS = byteencoding.c indexedset.c a2p-hashtable.c a2p-hashset.c stringutils.c typeutils.c pdbtypes.c aterm2pbf.c doublekeyedindexedset.c

PUBLIC_INCL = pdbtypes.h aterm2pbf.h a2p-hashtable.h

PRIVATE_INCL = indexedset.h byteencoding.h a2p-hashset.h stringutils.h typeutils.h doublekeyedindexedset.h

INCLUDES = $(ATERM_CFLAGS)

libaterm2pbf_la_LIBADD = \
	-L$(top_builddir)/lib \
        $(ATERM_LIBS)
