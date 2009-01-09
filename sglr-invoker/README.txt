Using this library you can invoke SGLR through JNI.

Just import the library and tell Java where to find the libraries.
Either:
1. Set java.library.path with -Djava.library.path=${PLACE_WHERE_YOU_INSTALLED_THIS_STUFF}/lib:${YOUR_META_INSTALL_DIR}/lib
2. Append them to your LD_LIBARY_PATH.

For an example of how to use it see the demo implementation (sgrl.demo.SGLRInvokerDemo).

-----------
The demo application can be started like this (depending on how you want to set the library path):
1. 'java -Djava.library.path=${PLACE_WHERE_YOU_INSTALLED_THIS_STUFF}/lib:${YOUR_META_INSTALL_DIR}/lib sgrl.demo.SGLRInvokerDemo'
2. 'java sgrl.demo.SGLRInvokerDemo'

-----------
Note:
Since Java's default stack size is a lot smaller then that of C (256KB vs 1MB), loading BAF encoded parse tables doesn't always work (recursion is evil...).
Either you can increase the stack size to 1MB with -Xss1024k, or (preferably) convert the trm.tbl from BAF to SAF (cat ${your.trm.tbl} | baffle -wS > ${your.saf.trm.tbl}).

Note2:
The library that is located in ./lib is compiled for Linux_x86, if you want to run it on a different platform (which has gcc), just modify and run the compile.sh script to recompile it (otherwise you'll need to figure it out yourself; the code in this should be as portable as Java itself (though the aterm library and such are not)).
