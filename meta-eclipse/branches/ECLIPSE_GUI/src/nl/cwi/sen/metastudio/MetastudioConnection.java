package nl.cwi.sen.metastudio;

import metastudio.graph.MetaGraphFactory;
import nl.cwi.sen.metastudio.adt.editordata.EditorDataFactory;
import nl.cwi.sen.metastudio.adt.texteditor.TextEditorFactory;
import nl.cwi.sen.metastudio.bridge.UserEnvironmentBridge;

public class MetastudioConnection {
	private UserEnvironmentBridge bridge;
	private aterm.pure.PureFactory factory;
	private EditorDataFactory editorDataFactory;
	private TextEditorFactory textEditorFactory;
	private MetaGraphFactory metaGraphFactory;

	public MetastudioConnection(
		UserEnvironmentBridge bridge,
		aterm.pure.PureFactory factory) {
		this.bridge = bridge;
		this.factory = factory;
	}

	public UserEnvironmentBridge getBridge() {
		return bridge;
	}

	public aterm.pure.PureFactory getPureFactory() {
		return factory;
	}

	public EditorDataFactory getEditorDataFactory() {
		return editorDataFactory;
	}

	public TextEditorFactory getTextEditorFactory() {
		return textEditorFactory;
	}

	public MetaGraphFactory getMetaGraphFactory() {
		return metaGraphFactory;
	}

	public void setEditorDataFactory(EditorDataFactory dataFactory) {
		this.editorDataFactory = dataFactory;
	}

	public void setMetaGraphFactory(MetaGraphFactory graphFactory) {
		this.metaGraphFactory = graphFactory;
	}

	public void setTextEditorFactory(TextEditorFactory editorFactory) {
		this.textEditorFactory = editorFactory;
	}

}
