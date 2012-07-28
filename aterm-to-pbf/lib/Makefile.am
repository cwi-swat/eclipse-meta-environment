lib_LTLIBRARIES = libaterm2pbf.la 
include_HEADERS = $(PUBLIC_INCL)
noinst_HEADERS  = $(PRIVATE_INCL)

AM_CPPFLAGS = -I$(srcdir) 

libaterm2pbf_la_SOURCES = $(ALLSRCS)
libaterm2pbf_la_CFLAGS = 
libaterm2pbf_la_LDFLAGS = -avoid-version -no-undefined

ALLSRCS = a2p-byteencoding.c a2p-indexedset.c a2p-hashtable.c a2p-hashset.c a2p-stringutils.c a2p-typeutils.c pdbtypes.c aterm2pbf.c a2p-doublekeyedindexedset.c

PUBLIC_INCL = pdbtypes.h aterm2pbf.h a2p-hashtable.h

PRIVATE_INCL = a2p-indexedset.h a2p-byteencoding.h a2p-hashset.h a2p-stringutils.h a2p-typeutils.h a2p-doublekeyedindexedset.h

INCLUDES = $(ATERM_CFLAGS)

libaterm2pbf_la_LIBADD = \
	-L$(top_builddir)/lib \
        $(ATERM_LIBS)
