package writers;

import java.io.IOException;
import java.util.HashMap;

import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Tag;

public class FunctionWriter extends BaseWriter {
	
	public FunctionWriter(){}
	
	public static void write(MethodDoc doc, Tag webref) throws IOException
	{
		String anchor = getAnchor(doc);
		TemplateWriter templateWriter = new TemplateWriter();
		
		HashMap<String, String> vars = new HashMap<String, String>();
		String syntax = templateWriter.writeLoop("Function.Syntax.partial.html", getSyntax(doc, ""));
		
		vars.put("examples", getExamples( doc ));
		vars.put("description", basicText(doc));
		vars.put("name", doc.name() + "()");
		vars.put("syntax", syntax);
		vars.put("usage", getUsage(doc));
		vars.put("returns", importedName(doc.returnType().toString()));
		vars.put("parameters", getParameters(doc));
		vars.put("related", getRelated(doc));
		
		templateWriter.write("Function.template.html", vars, anchor);
	}	
}