package com.sidarenka.alien.tag;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The Class FooterTag.
 */
public class FooterTag extends TagSupport {
    
    /** The Constant FOOTER_MESSAGE. */
    private static final String FOOTER_MESSAGE="Olga Sidarenka: final project";
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(FOOTER_MESSAGE);
        } catch (IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }

    }
