sdfdir = $(pkgdatadir)/sdf

TBSCRIPTS_src = \
	$(wildcard *.tb.src) \
	$(wildcard *.h) \
	$(wildcard *.idef)

TBSCRIPTS = $(TBSCRIPTS_src:.tb.src=.tb)

sdf_DATA = $(TBSCRIPTS)

EXTRA_DIST= $(TBSCRIPTS_src) 

%.tb : %.tb.src Makefile
	sed "s@__PREFIX__@${prefix}@g;s@__PGEN_PREFIX__@${PGEN_PREFIX}@g" < $< > $@
