sdfdir = $(pkgdatadir)/sdf

TBSCRIPTS_src = \
	$(wildcard *.tb.src) \
	$(wildcard *.h) \
	$(wildcard *.idef)

TBSCRIPTS = $(TBSCRIPTS_src:.tb.src=.tb)

sdf_DATA = $(TBSCRIPTS)

EXTRA_DIST = $(TBSCRIPTS_src) standard.sdf.actions.src

%.tb : %.tb.src Makefile
	sed "s@__PREFIX__@${prefix}@g;s@__PGEN_PREFIX__@${PGEN_PREFIX}@g" < $< > $@

SDF_LIBRARY_LOCATION = ${SDF_LIBRARY_PREFIX}/share/sdf-library/library
ASF_LIBRARY_LOCATION = ${ASF_LIBRARY_PREFIX}/share/asf-library/library

standard.sdf.actions: standard.sdf.actions.src Makefile
	sed "s@__SDF_LIBRARY__@${SDF_LIBRARY_LOCATION}@g; \
	s@__ASF_LIBRARY__@${ASF_LIBRARY_LOCATION}@g;" < $< > $@
