package com.example.lachamba;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan
public class Configuracion extends WsConfigurerAdapter{
    //toma el xsd, recupera el esquema que utilizamos para los
    //mensajes validos, vincula el contrato y lo pone a 
    //dispocision
    @Bean
    public XsdSchema lachambaSchema(){
        return new SimpleXsdSchema(new ClassPathResource("esquema.xsd"));
    }
    //hara registros, tengamos puntos de entrada para donde 
    //contactar, esto da el contexto de la aplicacion
    @Bean
    public ServletRegistrationBean messageDispacherServelet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }
    //para saber donde se estara ejecutando, es para configurar
    //la contruccion del wsdl, permite consumir el servicio, 
    //debe tener el portType, uri, y final es el namespace de 
    //como construirlo, donde se situa con el namespace 
    //particular
    @Bean(name = "esquema")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema lachambaSchema){
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("esquemaPort");
        wsdl.setLocationUri("/ws/esquema");
        wsdl.setTargetNamespace("http://www.example.com/lachamba");
        wsdl.setSchema(lachambaSchema);
        return wsdl;
    }
    //este metodo habilitara el cross origin, es un filtro cors
    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }}
