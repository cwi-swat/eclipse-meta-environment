SUBDIRS = sdf asf

TBSCRIPTS = $(wildcard *.tb)

EXTRA_DIST = $(TBSCRIPTS) asfsdf-meta.props.src

pkgdata_DATA = $(TBSCRIPTS) asfsdf-meta.props

CLEANFILES = \
	asfsdf-meta.props

TB_INCLUDE_PATH=`echo "${TOOLBUSFLAGS}" | sed 's:-I::g' | sed 's:[ ][ ]*:,:g'`

%.tb : %.tb.src Makefile
	sed "s@__PREFIX__@${prefix}@g" < $< > $@

asfsdf-meta.props: asfsdf-meta.props.src Makefile
	sed "s@__SCRIPT_PATH__@${prefix}/share/asfsdf-meta-eclipse-imp@g;\
	s@__INCLUDE_PATH__@${TB_INCLUDE_PATH},${prefix}/share/asfsdf-meta-eclipse-imp,${prefix}/share/asfsdf-meta-eclipse-imp/sdf,${prefix}/share/asfsdf-meta-eclipse-imp/asf@g;" < $< > $@
