package com.nirup.aadl.statistics;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.osate.aadl2.Element;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.ui.dialogs.Dialog;

public class DoStatistics extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			//Get the active editor
			IEditorInput temp = HandlerUtil.getActiveWorkbenchWindowChecked(event)
											.getActivePage()
											.getActiveEditor()
											.getEditorInput();
			//get the first element
			Element root = AadlUtil.getElement(((FileEditorInput)temp).getFile());
			
			//Traverse through file using root element and pass it to my switch
			Statistics stats = new Statistics();
			stats.defaultTraversal(root);
			
			String output = stats.getOutput();
			Dialog.showInfo("Model Statistics", output);
		} catch (NullPointerException e) {
			//No active editor (Not caught by condition in plugin.xml  for some reason)
			//do nada
		}
		
		return null;
	}

}