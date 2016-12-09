package filters;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class HelloWorldTag implements Tag {

	private Tag parent;

	private PageContext pageContext;

	public HelloWorldTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		try {
			pageContext.getOut().write("Hello World!");
		} catch (java.io.IOException e) {
			throw new JspTagException("IO Error: " + e.getMessage());
		}
		return EVAL_PAGE; //��ܷ|�~��������jsp page
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return SKIP_BODY; //��ܤ����楻�餺�e
	}

	@Override
	public Tag getParent() {

		return parent;
	}

	@Override
	public void release() {

	}

	@Override
	public void setPageContext(PageContext pageContext) {
		// TODO Auto-generated method stub
		this.pageContext = pageContext;
	}

	@Override
	public void setParent(Tag parent) {
		// TODO Auto-generated method stub
		this.parent = parent;

	}
}
