asfdir = $(pkgdatadir)/asf

TBSCRIPTS_src = \
	$(wildcard *.tb.src) \
	$(wildcard *.h) \
	$(wildcard *.idef)

TBSCRIPTS = $(TBSCRIPTS_src:.tb.src=.tb)

asf_DATA = $(TBSCRIPTS)

EXTRA_DIST= $(TBSCRIPTS_src) 

%.tb : %.tb.src Makefile
	sed "s@__SDF_PRETTY_PREFIX__@${SDF_PRETTY_PREFIX}@g" < $< > $@
