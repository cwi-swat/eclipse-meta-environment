package nl.cwi.sen.metastudio;

import metastudio.graph.MetaGraphFactory;
import nl.cwi.sen.metastudio.adt.editordata.EditorDataFactory;
import nl.cwi.sen.metastudio.adt.texteditor.TextEditorFactory;
import nl.cwi.sen.metastudio.bridge.UserEnvironmentBridge;

public class MetastudioConnection {
	private static UserEnvironmentBridge _bridge;
	private static aterm.pure.PureFactory _factory;
	private static EditorDataFactory _editorDataFactory;
	private static TextEditorFactory _textEditorFactory;
	private static MetaGraphFactory _metaGraphFactory;

	public MetastudioConnection() {
	}

	public MetastudioConnection(
		UserEnvironmentBridge bridge,
		aterm.pure.PureFactory factory,
		EditorDataFactory editorDataFactory,
		TextEditorFactory textEditorFactory,
		MetaGraphFactory metaGraphFactory) {
		_bridge = bridge;
		_factory = factory;
		_editorDataFactory = editorDataFactory;
		_textEditorFactory = textEditorFactory;
		_metaGraphFactory = metaGraphFactory;
	}

	public UserEnvironmentBridge getBridge() {
		return _bridge;
	}

	public aterm.pure.PureFactory getPureFactory() {
		return _factory;
	}

	public EditorDataFactory getEditorDataFactory() {
		return _editorDataFactory;
	}

	public TextEditorFactory getTextEditorFactory() {
		return _textEditorFactory;
	}

	public MetaGraphFactory getMetaGraphFactory() {
		return _metaGraphFactory;
	}
}
