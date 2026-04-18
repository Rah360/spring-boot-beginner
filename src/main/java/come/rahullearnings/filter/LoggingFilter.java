package come.rahullearnings.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
//1. What is jakarta.servlet?
//Think of this as the Standard Web Specification for Java.
//        Long ago, it was called javax.servlet. It is a set of rules (interfaces) that tells web servers (like Tomcat) how to talk to Java code. Spring Boot is actually built on top of this. When you use jakarta.servlet, you are using the industry-standard way to handle HTTP requests in the Java ecosystem.
//
//        2. Why is LoggingFilter implementing Filter?
//        In Java, an interface (like Filter) is a contract.
//By saying implements Filter, you are promising the Java compiler: "I guarantee this class has a doFilter method." The underlying web server (Tomcat) only knows how to talk to objects of type Filter. Because you implemented that interface, Tomcat can now plug your class into its "Request Pipeline."
//
//        3. What does @Component do?
//This is the "Spring, please manage this!" button.
//        In Node, you manually require or import a middleware and plug it in. In Spring, @Component tells the Spring Container: "Hey, during startup, create an instance of this class and keep it in your 'Bean' bucket." Because it's a Filter and has @Component, Spring automatically finds it and adds it to the web security chain without you writing a single line of manual "plug-in" code.
//
//        4. What is this "Casting" line?
//HttpServletRequest req = (HttpServletRequest) request;
//
//The Problem: The doFilter method is very generic. It accepts a ServletRequest, which could be any kind of request (even non-HTTP ones).
//
//The Solution: Since we know we are building a web app, we want the "Premium" features (like .getMethod(), .getRequestURI(), or .getCookies()). These features only exist in the HttpServletRequest subclass.
//
//The Action: This line is called Type Casting. You are telling Java: "I know this is a generic request, but treat it specifically as an HTTP request so I can access the URL and Method."
//
//        5. When are those Exceptions thrown?
//IOException: This happens if there is a low-level network failure. For example, if the user closes their browser/connection while your filter is still trying to read the data or write a response.
//
//        ServletException: This is a "Wrapper" error. If your Controller (or a service it calls) crashes with a logical error, Spring catches it and often throws it back up the chain as a ServletException to tell the server, "Something went wrong with the web logic."
//@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        System.out.println("LOGGING MIDDLEWARE: " + req.getMethod() + " " + req.getRequestURI());

        // CRITICAL: This line passes the request to the next "Middleware" or the Controller
        // In Node, this is the same as calling next()
        filterChain.doFilter(servletRequest, servletResponse);

        // Logic after the request finishes
        System.out.println("LOGGING MIDDLEWARE: Finished processing request.");
    }
}
