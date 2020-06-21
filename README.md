# springSecurity
Web applications are vulnerable to security threats because they are exposed to the open world of the Internet. Access to certain Web pages, files, or other classified resources must be restricted to authorized personnel only. Of course, there are several layers of security that are often applied, such as firewall, proxy server, JVM security, and so forth. But, to control access, there must be some security restriction at the application level as well. Therefore, Spring Security, a part of the Spring Framework, is only an advice or provision to apply a level of security at the Java Application stratum

Spring Security
Spring Security is a framework that enables a programmer to impose security restrictions to Spring-framework–based Web applications 
through JEE components. In short,
it is a library that can be used, 
extended to customize as per the programmer's needs. 
Because it is a member of the same Spring family, 
it goes smoothly hand in hand with the Spring Web MVC. 
Its primary area of operation is to handle authentication and authorization at the Web request level as well as the method invocation level. 
Perhaps. The greatest advantage of this framework is that it is powerful yet highly customizable in its implementation.
Although it follows Spring's convention over configuration,
programmers can choose between default provisions or customizing it according to their needs.

Authentication
Authentication means that, while accessing certain restricted resources,
the user actually is the right person to do so. 
There are two processes to make sure that the user is authentic: identification and verification. 
For example
, a user is authenticated through their username and password,
which is typically stored in a database; LDAP (Lightweight Directory Access Protocol,
a lightweight protocol for accessing directory services); or CAS (Central Authentication Service, 
a single sign-on protocol for the Web).
Spring Security also has required an interface to encode the password to make it more secure.

Authorization
Authorization determines the extent of a user's right to access restricted resources.
It ensures that a user is allowed to access only those parts of the resource that one has been authorized to use.
For example, 
an ADMIN user has unlimited access to application properties and can change or manipulate them—for good or for worse. 
A normal USER or a GUEST user, on the other hand, 
has more controlled access and does not enjoy the same rights as the ADMIN user.
This is called user role authorization. 
In any Web application, this is done through URL-based security. 
Spring provides filters to ensure the role of securing an application.

But, URL-based security is not a very clever mechanism and often can be misused.
Malicious users can manipulate the URL and gain access to a method that actually is meant for an administrative user. 
Because, in a URL-based system, 
restricted method access invocations are sent through hyperlinks, it quite easy to re-create the same method invocation from the URL and send it to the server. The server may naively execute the restricted operations without verifying the role of the user who invoked the request. Therefore, to tackle this problem, Spring offers method-level security. This simply means that only certain authorized users can invoke restricted methods and simply re-creating the URL and sending it to the server will not execute them.


Security in a Spring MVC Application
Because Spring Security tightly integrates with the Spring Framework and other commonly used authentication mechanisms, such as HTTP basic authentication, X.509 certificate, form-based login, and so on, it has comprehensive support for both Web applications as well as method-level security. Securing Web applications is perhaps the most common concern and here we'll glimpse that aspect of Spring Security.

While employing Spring Security, one can completely eliminate the need of an XML security configuration and instead apply an annotation to configure it. We can write a simple configuration class such as follows:

package org.mano.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web
   .configuration.*;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends
      WebSecurityConfigurerAdapter {
   // ...
}


The annotation @EnableWebSecurity enables Web security; otherwise, it remains disabled by default. Now, to configure the security, we can either implements the interface called WebSecurityConfigurer or extend the more convenient class called WebSecurityConfigurerAdapter. The advantage of extending the adapter class is that we can configure Web security by overriding only those parts that we are interested in; others can remain their default form. There are three variations of the configure method that we can override to configure and secure the application:

void configure( AuthenticationManagerBuilder auth): To configure user details services
void configure( HttpSecurity http): To configure how requests are secured by interceptors
void configure( WebSecurity web): To configure Spring Security's filter chain
The default filter chain is fine for most needs. So, we may configure the other two in the following manner.



public class SecurityConfiguration extends
      WebSecurityConfigurerAdapter {

   @Autowired
   private CustomUserDetailsService userDetailsService;
   @Override
   protected void configure(AuthenticationManagerBuilder auth)
         throws Exception {
      auth.userDetailsService(userDetailsService)
         .passwordEncoder(getPasswordEncoder());
   }

   private PasswordEncoder getPasswordEncoder() {
      return new PasswordEncoder() {
         @Override
         matches(CharSequence rawPassword, String
               encodedPassword) {
            // Verifies whether encoded password obtained matches
            // with the rawPassword CharSequence
         }
         @Override
         public String encode(CharSequence rawPassword) {
            // Code for password encoding
         }
      };
   }

   // ...
}


The PasswordEncoder is a service interface provided by the Spring Security framework for encoding passwords.

The interceptor's secure method configuration may look like this:

public class SecurityConfiguration extends
      WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws
         Exception {
      http.authorizeRequests()
      .antRequest().authenticated()
      .and().formLogin().and().httpBasic();
   }

   // ...
}
This simple configuration specifies how HTTP requests are secured. The chain of method enforces that all HTTP requests are authenticated via a login form. The form-based login is a predefined login page provided by Spring Security.

When overriding the configure(AuthenticationManagerBuilder auth) method, we can use in-memory user storage as follows:



public class SecurityConfiguration extends
      WebSecurityConfigurerAdapter {
   @Override
   protected void configure(AuthenticationManagerBuilder auth)
         throws Exception {
      auth.inMemoryAuthentication()
         .withUser("user").password("password").roles("USER").and()
         .withUser("admin").password("password").roles("USER",
            "ADMIN");
   }
   // ...
   
   
}


Conclusion
This is a glimpse of Spring Security and how it is configured in a Web application. Of course, there is more to it as we delve deeper. We'll take that up in a separate article. The key theme of Spring Security is that it handles authentication and authorization at the Web request level and at the method invocation level. This is just another level of security we can apply to a Web application by using the Spring framework. However, one must remember that security in essence is never comprehensive; only provision security can be optimal.

