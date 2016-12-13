package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
	urlPatterns = { "/*" },
	initParams = { @WebInitParam(
		name = "ENCODING",
		value = "UTF-8") })
public class EncodingFilter implements Filter {

	private String ENCODING;

	public void init(FilterConfig config) throws ServletException {
		ENCODING = config.getInitParameter("ENCODING");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		chain.doFilter(req, res);

	}

}