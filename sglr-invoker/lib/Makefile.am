lib_LTLIBRARIES = libSGLRInvoker.la

AM_CPPFLAGS = -I$(srcdir)

libSGLRInvoker_la_SOURCES = SGLRInvoker.c

include_HEADERS = sglr_SGLRInvoker.h

INCLUDES= \
        -I$(top_srcdir)/lib \
        $(ATERM_CFLAGS) \
        $(CONFIG_SUPPORT_CFLAGS) \
        $(ERROR_SUPPORT_CFLAGS) \
        $(PT_SUPPORT_CFLAGS) \
        $(PTABLE_SUPPORT_CFLAGS) \
        $(C_LIBRARY_CFLAGS)

libSGLRInvoker_la_LIBADD = \
        -L$(top_builddir)/lib \
        $(ATERM_LIBS) \
        $(CONFIG_SUPPORT_LIBS) \
        $(ERROR_SUPPORT_LIBS) \
        $(PT_SUPPORT_LIBS) \
        $(PTABLE_SUPPORT_LIBS) \
        $(C_LIBRARY_LIBS)

SGLRInvoker_LDADD = \
        -L$(top_builddir)/lib -lSGLRInvoker \
        $(ATERM_LIBS) \
        $(CONFIG_SUPPORT_LIBS) \
        $(ERROR_SUPPORT_LIBS) \
        $(PT_SUPPORT_LIBS) \
        $(PTABLE_SUPPORT_LIBS) \
        $(C_LIBRARY_LIBS)
