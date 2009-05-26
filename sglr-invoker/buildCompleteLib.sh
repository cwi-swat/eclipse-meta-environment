#!/bin/sh

# Find all .a files located in dependencies.
ALL_ARCHIVES=""
for dep in ${ALL_DEPS}; do
   prefix=`pkg-config --variable prefix ${dep}`
   for library in `find ${PREFIX}/lib/lib*.a`; do
      if [ "`echo '${ALL_ARCHIVES}' | grep ${library}`" == "" ]; then
         ALL_ARCHIVES="${ALL_ARCHIVES} ${library}"
      fi
   done
done

# Extract all .o files from the .a files. 
mkdir -p ${TOP_DIR}/libbuild
cd ${TOP_DIR}/libbuild
for archive in ${ALL_ARCHIVES}; do 
   ar -x ${archive}
done
cd -
ALL_OBJECTS="`find ../libbuild/*.o | grep -v SGLRInvoker`"

echo $ALL_OBJECTS
