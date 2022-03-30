package cn.stylefeng.guns.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FawWebService
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 14:41
 * @Version 1.0
 **/
@Configuration
public class FawWebService {

    @Bean
    public ServletRegistrationBean wsServlet(){
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Autowired
    private FawApiService fawApiService;

    @Autowired
    @Qualifier(Bus.DEFAULT_BUS_ID)
    private SpringBus bus;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, fawApiService);
        endpoint.publish("/fawApiService");
        return endpoint;
    }

}
