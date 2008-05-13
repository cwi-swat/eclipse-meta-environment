SUBDIRS = sdf asf

TBSCRIPTS_src = \
	$(wildcard *.tb.src) \
	$(wildcard *.h)

TBSCRIPTS = $(TBSCRIPTS_src:.tb.src=.tb)

EXTRA_DIST = $(TBSCRIPTS_src) asfsdf-meta.props.src

pkgdata_DATA = $(TBSCRIPTS) asfsdf-meta.props

CLEANFILES = \
	asfsdf-meta.props

%.tb : %.tb.src Makefile
	sed "s@__PREFIX__@${prefix}@g" < $< > $@

asfsdf-meta.props: asfsdf-meta.props.src Makefile
	sed "s@__PREFIX__@${prefix}@g" < $< > $@
