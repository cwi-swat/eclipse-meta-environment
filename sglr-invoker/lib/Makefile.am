include $(top_srcdir)/Makefile.meta

lib_LTLIBRARIES = libSGLRInvoker.la libCompleteSGLR.la

AM_CPPFLAGS = -I$(srcdir)

noinst_HEADERS = sglr_SGLRInvoker.h

libSGLRInvoker_la_SOURCES = SGLRInvoker.c
libSGLRInvoker_la_LDFLAGS  = -avoid-version -no-undefined $(AM_LDFLAGS)

INCLUDES= \
        -I$(top_srcdir)/lib \
	$(JDK_INCLUDES) \
        $(ATERM_CFLAGS) \
        $(ERROR_SUPPORT_CFLAGS) \
        $(PT_SUPPORT_CFLAGS) \
        $(PTABLE_SUPPORT_CFLAGS) \
        $(SGLR_CFLAGS) \
        $(C_LIBRARY_CFLAGS)

libSGLRInvoker_la_LIBADD = \
        -L$(top_srcdir)/lib \
	$(JDK_LIBS) \
        $(ATERM_LIBS) \
        $(ERROR_SUPPORT_LIBS) \
        $(PT_SUPPORT_LIBS) \
        $(PTABLE_SUPPORT_LIBS) \
        $(SGLR_LIBS) \
        $(C_LIBRARY_LIBS)

libCompleteSGLR_la_LDFLAGS  = -avoid-version -no-undefined $(AM_LDFLAGS)
libCompleteSGLR_la_LIBADD = $(shell \
                        export TOP_DIR="$(top_srcdir)"; \
                        export ALL_DEPS="$(PACKAGE_ALL_DEPS)"; \
                        export PREFIX="$(prefix)"; \
			export PKG_CONFIG_PATH="${PKG_CONFIG_PATH}"; \
                        . $(top_srcdir)/buildCompleteLib.sh)
libCompleteSGLR_la_SOURCES = SGLRInvoker.c

CLEANFILES += ../libbuild/*.o
