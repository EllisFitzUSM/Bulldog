import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter;

/**
 * Numeric Document Filter which prevents characters other than numbers from being typed into a text field.
 * @author DeepSeek
 */
class NumericDocumentFilter extends javax.swing.text.DocumentFilter {

    /**
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset  the offset into the document to insert the content &gt;= 0.
     *    All positions that track change at or after the given location
     *    will move.
     * @param string the string to insert
     * @param attr      the attributes to associate with the inserted
     *   content.  This may be null if there are no attributes.
     * @throws javax.swing.text.BadLocationException
     */
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                             String string, AttributeSet attr) throws javax.swing.text.BadLocationException {
        if (string.matches("\\d*")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    /**
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset Location in Document
     * @param length Length of text to delete
     * @param text Text to insert, null indicates no text to insert
     * @param attrs AttributeSet indicating attributes of inserted text,
     *              null is legal.
     * @throws javax.swing.text.BadLocationException
     */
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                        String text, AttributeSet attrs) throws javax.swing.text.BadLocationException {
        if (text.matches("\\d*")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}