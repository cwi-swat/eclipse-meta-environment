asfdir = $(pkgdatadir)/asf

TBSCRIPTS_src = \
	$(wildcard *.tb.src) \
	$(wildcard *.h) \
	$(wildcard *.idef)

TBSCRIPTS = $(TBSCRIPTS_src:.tb.src=.tb)

asf_DATA = $(TBSCRIPTS)

EXTRA_DIST = $(TBSCRIPTS_src) standard.asfsdf.actions.src

pkgdata_DATA = $(TBSCRIPTS) standard.asfsdf.actions.src

CLEANFILES = standard.asfsdf.actions.src

%.tb : %.tb.src Makefile
	sed "s@__PREFIX__@${prefix}@g;s@__SDF_PRETTY_PREFIX__@${SDF_PRETTY_PREFIX}@g" < $< > $@

standard.asfsdf.actions: standard.asfsdf.actions.src Makefile
	sed "s@__PREFIX__@${prefix}@g" < $< > $@
