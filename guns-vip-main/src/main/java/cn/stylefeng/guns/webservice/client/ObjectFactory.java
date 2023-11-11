
package cn.stylefeng.guns.webservice.client;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.stylefeng.guns.webservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.stylefeng.guns.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resp }
     * 
     */
    public Resp createResp() {
        return new Resp();
    }

    /**
     * Create an instance of {@link Req }
     * 
     */
    public Req createReq() {
        return new Req();
    }

    /**
     * Create an instance of {@link Resp.MsgHeader }
     * 
     */
    public Resp.MsgHeader createRespMsgHeader() {
        return new Resp.MsgHeader();
    }

    /**
     * Create an instance of {@link Req.MsgHeader }
     * 
     */
    public Req.MsgHeader createReqMsgHeader() {
        return new Req.MsgHeader();
    }

    /**
     * Create an instance of {@link Req.MsgBody }
     * 
     */
    public Req.MsgBody createReqMsgBody() {
        return new Req.MsgBody();
    }

}
