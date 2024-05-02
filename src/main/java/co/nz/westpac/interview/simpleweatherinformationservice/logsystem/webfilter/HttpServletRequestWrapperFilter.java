package co.nz.westpac.interview.simpleweatherinformationservice.logsystem.webfilter;

import co.nz.westpac.interview.simpleweatherinformationservice.logsystem.wrappers.BodyReaderHttpServletRequestWrapper;
import co.nz.westpac.interview.simpleweatherinformationservice.logsystem.wrappers.BodyReaderHttpServletResponseWrapper;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@WebFilter(filterName = "httpServletRequestWrapperFilter", urlPatterns = {"/*"})
public class HttpServletRequestWrapperFilter extends HttpFilter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest requestWrapper = null;
        HttpServletResponse responesWrapper =null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            requestWrapper = new BodyReaderHttpServletRequestWrapper(httpRequest);
        }
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            responesWrapper = new BodyReaderHttpServletResponseWrapper(httpResponse);
        }
        if (null == requestWrapper || null == responesWrapper ) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, responesWrapper);
        }
    }


    @Override
    public void destroy() {

    }
}

