all:
	rm -f *.java
	upgrade-adt -i EditorData.adt -o EditorData.adt.1
	adt-to-java \
		-i EditorData.adt.1 \
		-p nl.cwi.sen.metastudio.adt.editordata \
		-n EditorData
	rm EditorData.adt.1
