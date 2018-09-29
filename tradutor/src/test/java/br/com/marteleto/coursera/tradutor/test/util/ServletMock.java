package br.com.marteleto.coursera.tradutor.test.util;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
@SuppressWarnings("deprecation")
public class ServletMock implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mock private HttpServletRequest httpServletRequestMock;
	@Mock private HttpServletResponse httpServletResponse;
	@Mock private HttpSession httpSessionMock;
	@Mock private ServletContext servletContextMock;
	@Mock private RequestDispatcher requestDispatcherMock;
	@Mock private FilterChain filterChainMock; 
	private HttpServletResponseWrapperMock wrapper ;
	private final Map<String, Object> sessionAttributes = new ConcurrentHashMap<String, Object>();
	private final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();
	private final Map<String, String> parameters = new ConcurrentHashMap<String, String>();
	
	private static ServletMock instance;
	
	public ServletMock () {
		MockitoAnnotations.initMocks(this);
		wrapper = new HttpServletResponseWrapperMock(httpServletResponse);
		
		Mockito.when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);
		Mockito.when(httpSessionMock.getServletContext()).thenReturn(servletContextMock);
		
		Mockito.when(httpServletRequestMock.getRemoteAddr()).thenReturn("127.0.0.1");
		Mockito.when(httpServletRequestMock.getProtocol()).thenReturn("HTTP");
		Mockito.when(httpServletRequestMock.getRequestURI()).thenReturn("/forum/");
		Mockito.when(httpServletRequestMock.getMethod()).thenReturn("POST");
		Mockito.when(httpServletRequestMock.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/forum/"));
		Mockito.when(httpServletRequestMock.getHeader("referer")).thenReturn("http://localhost:8080/forum/");
		
		

        mockAttributes();
        mockParameters();
        mockSessionAttributes();
	}
	
	private void mockAttributes() {
		// Mock setAttribute
    	Mockito.doAnswer(new Answer<Void>() {
    	    @Override
    	    public Void answer(InvocationOnMock invocation) throws Throwable {
    	        String key = invocation.getArgument(0);
    	        Object value = invocation.getArgument(1);
    	        attributes.put(key, value);
    	        return null;
    	    }
    	}).when(httpServletRequestMock).setAttribute(Mockito.anyString(), Mockito.anyObject());
    	// Mock getAttribute
    	Mockito.doAnswer(new Answer<Object>() {
    	    @Override
    	    public Object answer(InvocationOnMock invocation) throws Throwable {
    	        String key = invocation.getArgument(0);
    	        Object value = attributes.get(key);
    	        return value;
    	    }
    	}).when(httpServletRequestMock).getAttribute(Mockito.anyString());
	}
	
	private void mockSessionAttributes() {
		// Mock setAttribute
    	Mockito.doAnswer(new Answer<Void>() {
    	    @Override
    	    public Void answer(InvocationOnMock invocation) throws Throwable {
    	        String key = invocation.getArgument(0);
    	        Object value = invocation.getArgument(1);
    	        sessionAttributes.put(key, value);
    	        return null;
    	    }
    	}).when(httpSessionMock).setAttribute(Mockito.anyString(), Mockito.anyObject());
    	// Mock getAttribute
    	Mockito.doAnswer(new Answer<Object>() {
    	    @Override
    	    public Object answer(InvocationOnMock invocation) throws Throwable {
    	        String key = invocation.getArgument(0);
    	        Object value = sessionAttributes.get(key);
    	        return value;
    	    }
    	}).when(httpSessionMock).getAttribute(Mockito.anyString());
	}
	
	private void mockParameters() {
    	// Mock getParameter
    	Mockito.doAnswer(new Answer<Object>() {
    	    @Override
    	    public Object answer(InvocationOnMock invocation) throws Throwable {
    	        String key = invocation.getArgument(0);
    	        Object value = parameters.get(key);
    	        return value;
    	    }
    	}).when(httpServletRequestMock).getParameter(Mockito.anyString());
	}
	
	private static ServletMock getInstance() {
		if (instance == null) {
			instance = new ServletMock();
		}
		return instance;
	}
	
	public static HttpServletRequest getHttpServletRequest() {
		return getInstance().httpServletRequestMock;
	}

	public static HttpServletResponse getHttpServletResponse() {
		return getInstance().wrapper;
	}

	public static ServletContext getServletContext() {
		return getInstance().servletContextMock;
	}
	
	public static FilterChain getFilterChainMock() {
		return getInstance().filterChainMock;
	}

	public static void addParameter(String key, String value) {
		getInstance().parameters.put(key, value);
	}
	
	public static void removeParameter(String key) {
		getInstance().parameters.remove(key);
	}
	
	public static void addAttribute(String key, Object value) {
		getInstance().attributes.put(key, value);
	}
	
	public static void removeAttribute(String key) {
		getInstance().attributes.remove(key);
	}
	
	public static void addSessionAttribute(String key, Object value) {
		getInstance().sessionAttributes.put(key, value);
	}
	
	public static void removeSessionAttribute(String key) {
		getInstance().sessionAttributes.remove(key);
	}
	
	public static void clearParameters() {
		getInstance().parameters.clear();
	}
	
	public static void clearAttributes() {
		getInstance().attributes.clear();
	}
	
	public static void clearSessionAttributes() {
		getInstance().sessionAttributes.clear();
	}
	
	public static void clear() {
		clearAttributes();
		clearParameters();
		clearSessionAttributes();
	}
}
