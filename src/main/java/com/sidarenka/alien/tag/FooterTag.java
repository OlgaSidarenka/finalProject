package com.sidarenka.alien.tag;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class FooterTag extends TagSupport {
    private static final String FOOTER_MESSAGE="Olga Sidarenka: final project";
    private static final long serialVersionUID = 1L;

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
