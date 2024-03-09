package dev.kush.jasperdemo1;

import net.sf.jasperreports.engine.*;

public class FontSelector extends JRDefaultScriptlet {
    @Override
    public void beforeDetailEval() throws JRScriptletException {
        String fieldValue = (String)this.getFieldValue("fontName");
        String fontName = "EnglishFont"; // default font

        // Simple language detection logic (for demonstration purposes)
        if (fieldValue != null && fieldValue.matches("[\\u0A80-\\u0AFF]+")) {
            fontName = "GujaratiFont";
        }

        this.setVariableValue("DYNAMIC_FONT_NAME", fontName);
    }
}
