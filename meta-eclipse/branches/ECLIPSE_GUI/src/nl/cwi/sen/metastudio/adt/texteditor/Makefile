all:
	rm -f *.java
	upgrade-adt -i TextEditor.adt -o TextEditor.adt.1
	adt-to-java \
		-i TextEditor.adt.1 \
		-p nl.cwi.sen.metastudio.adt.texteditor \
		-n TextEditor
	rm TextEditor.adt.1
