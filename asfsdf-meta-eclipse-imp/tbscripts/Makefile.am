SUBDIRS=asf sdf

EXTRA_DIST=$(wildcard *.tb)
EXTRA_DIST+=$(wildcard *.src)

TB_INCLUDE_PATH=`echo ${TOOLBUSFLAGS} | sed 's: -I:,:g;'`


% : %.src Makefile
	sed "s@__SCRIPT_PATH__@${prefix}/src/tbscripts@g;\
	     s@__INCLUDE_PATH__@${TB_INCLUDE_PATH},${prefix}/tbscripts,${prefix}/tbscripts/sdf,${prefix}/tbscripts/asf@g;" < $< > $@
