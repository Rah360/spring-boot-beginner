package come.rahullearnings;

import come.rahullearnings.dto.MessagePayload;
import come.rahullearnings.filter.LoggingFilter;
import come.rahullearnings.service.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication


@RestController
@RequestMapping("api/v1/messages")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final MessageService messageService;
    public Application(MessageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping
    public String getHello(){
        return "helloWorld";
    }

    @PostMapping
    public String postHello(@RequestBody MessagePayload msg){
        return messageService.processMessage(msg);
    }

    @DeleteMapping("/{id}")
    public String postMapping(@PathVariable Long id){

    return messageService.deleteMessage(id);
    }

//    1. What is a @Bean?
//    In standard Java, you create an object like this: MessageService service = new MessageService();. You own it, you manage it.
//
//    In Spring, a Bean is simply an object that is instantiated, assembled, and managed by the Spring IoC (Inversion of Control) container. When you put @Bean over a method, you are telling Spring:
//
//            "Hey Manager, I’m going to show you how to create this object. Once the method returns, take that object and put it in your bucket. Whenever any other part of the app needs it, you give it to them."
//
//            2. What is FilterRegistrationBean<LoggingFilter>?
//    In your previous step, you used @Component on the LoggingFilter. That was "Auto-pilot" mode.
//
//    FilterRegistrationBean is the "Manual Control" wrapper.
//
//    It is a special class provided by Spring Boot specifically to register Filters.
//
//    The <LoggingFilter> part (Generics) just tells Java, "This registration bucket is specifically designed to hold a LoggingFilter type."
//
//            3. What does registrationBean.setFilter(...) do?
//    This is where you "marry" the registration settings to your actual filter code.
//
//    Java
//registrationBean.setFilter(new LoggingFilter());
//    You are telling the registration wrapper: "When the web server starts up, I want you to use this specific instance of my logging code as the middleware."
//
//    Wait, why do this instead of @Component?
//    If you use @Component, the filter is global (runs on every single URL). By using this setFilter method in a @Bean, you can then call addUrlPatterns("/api/v1/*") to say, "Only run this filter for my API, not for my static images or login pages."
//
//            4. What is registrationBean.setOrder(1)?
//    This is the most "Enterprise" part of the code, and you saw this in your workplace catapult project.
//
//    Imagine you have three filters:
//
//    LoggingFilter (Records the request)
//
//    AuthFilter (Checks the password)
//
//    CorsFilter (Checks security headers)
//
//    Which one should run first? If AuthFilter fails and stops the request, but LoggingFilter was supposed to run second, you will have zero logs for that failed login attempt.
//
//    setOrder(1) says: "Put me at the very front of the line."
//
//    setOrder(2) would run after 1.
//
//    By explicitly setting the order, you ensure that your logging starts before any other logic potentially crashes or blocks the request.
    @Bean // "Hey Spring, listen to this blueprint"
public FilterRegistrationBean<LoggingFilter> loggingFilterRegistration() {

    // 1. Create the 'Registry Form'
    FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();

    // 2. Attach your class to the form
    registrationBean.setFilter(new LoggingFilter());

    // 3. Set the 'Where' and 'When'
    registrationBean.addUrlPatterns("/api/v1/*");
    registrationBean.setOrder(1);

    // 4. Hand the form back to Spring
    return registrationBean;
}
}
