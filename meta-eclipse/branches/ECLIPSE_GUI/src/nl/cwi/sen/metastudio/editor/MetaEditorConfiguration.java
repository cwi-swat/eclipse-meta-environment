package nl.cwi.sen.metastudio.editor;

import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class MetaEditorConfiguration extends SourceViewerConfiguration {
	private MetaEditor _editor;
	private MetaEditorDoubleClickStrategy _doubleClickStrategy;
	private ColorManager _colorManager;

	public MetaEditorConfiguration(MetaEditor editor, ColorManager colorManager) {
		_colorManager = colorManager;
		_editor = editor;
	}
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (_doubleClickStrategy == null)
			_doubleClickStrategy = new MetaEditorDoubleClickStrategy(_editor);
		return _doubleClickStrategy;
	}
}