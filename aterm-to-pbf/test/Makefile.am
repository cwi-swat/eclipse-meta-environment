include $(top_srcdir)/Makefile.meta

TESTS = testtypes
check_PROGRAMS = testtypes

EXTRA_DIST += testtypes.c

INCLUDES = $(ATERM_CFLAGS)

AM_LDFLAGS  += -L$(top_builddir)/lib 
LDADD       += -laterm2pbf
AM_CPPFLAGS += \
  -I$(top_srcdir)/lib \
  -DSRCDIR="\"$(top_srcdir)/test/\""

testtypes_SOURCES = testtypes.c
testtypes_LDADD=\
        -L$(top_builddir)/lib -laterm2pbf \
        $(ATERM_LIBS)

