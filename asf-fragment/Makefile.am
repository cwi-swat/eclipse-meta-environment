include $(top_srcdir)/Makefile.fragment.meta

ACLOCAL_AMFLAGS = -I .

copyDir=\
	if [ -d $1/$2 ]; then \
	   cp -rf $1/$2 ${top_builddir}/install; \
	fi

dummy:

installDir: dummy
	mkdir -p ${top_builddir}/install
	for dep in ${PACKAGE_ALL_DEPS}; do \
		prefix=`pkg-config --variable prefix $${dep}`; \
		$(call copyDir,$${prefix},bin); \
		$(call copyDir,$${prefix},lib); \
		$(call copyDir,$${prefix},share); \
		$(call copyDir,$${prefix},libexec); \
	done
	set -x ; \
	SEDCMD="" ; \
	for dep in ${PACKAGE_ALL_DEPS}; do \
		prefix=`pkg-config --variable prefix $${dep}`; \
		newpath=`printf "%-$${#prefix}s" ".." | tr " " "/"` ; \
		SEDCMD="$${SEDCMD} -e s@$${prefix}@$${newpath}@g"  ; \
	done ; \
	for file in `find install -type f`; do \
   	  sed $${SEDCMD} < $${file} > $${file}.tmp ;\
	  mv $${file}.tmp $${file} ; \
	done


${JAVA_JAR}: installDir
	jar -cMf ${JAVA_JAR} META-INF/MANIFEST.MF install
