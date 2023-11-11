
package cn.stylefeng.guns.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msgHeader">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="transID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="interfaceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="resultType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msgHeader"
})
@XmlRootElement(name = "resp")
public class Resp {

    @XmlElement(required = true)
    protected Resp.MsgHeader msgHeader;

    /**
     * ��ȡmsgHeader���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Resp.MsgHeader }
     *     
     */
    public Resp.MsgHeader getMsgHeader() {
        return msgHeader;
    }

    /**
     * ����msgHeader���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Resp.MsgHeader }
     *     
     */
    public void setMsgHeader(Resp.MsgHeader value) {
        this.msgHeader = value;
    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="transID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="interfaceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="resultType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "transID",
        "count",
        "messageID",
        "comment",
        "interfaceID",
        "resultType",
        "resultCode",
        "resultMessage"
    })
    public static class MsgHeader {

        @XmlElement(required = true)
        protected String transID;
        @XmlElement(required = true)
        protected String count;
        @XmlElement(required = true)
        protected String messageID;
        @XmlElement(required = true)
        protected String comment;
        @XmlElement(required = true)
        protected String interfaceID;
        @XmlElement(required = true)
        protected String resultType;
        @XmlElement(required = true)
        protected String resultCode;
        @XmlElement(required = true)
        protected String resultMessage;

        /**
         * ��ȡtransID���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransID() {
            return transID;
        }

        /**
         * ����transID���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransID(String value) {
            this.transID = value;
        }

        /**
         * ��ȡcount���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCount() {
            return count;
        }

        /**
         * ����count���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCount(String value) {
            this.count = value;
        }

        /**
         * ��ȡmessageID���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMessageID() {
            return messageID;
        }

        /**
         * ����messageID���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMessageID(String value) {
            this.messageID = value;
        }

        /**
         * ��ȡcomment���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComment() {
            return comment;
        }

        /**
         * ����comment���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComment(String value) {
            this.comment = value;
        }

        /**
         * ��ȡinterfaceID���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInterfaceID() {
            return interfaceID;
        }

        /**
         * ����interfaceID���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInterfaceID(String value) {
            this.interfaceID = value;
        }

        /**
         * ��ȡresultType���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResultType() {
            return resultType;
        }

        /**
         * ����resultType���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResultType(String value) {
            this.resultType = value;
        }

        /**
         * ��ȡresultCode���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResultCode() {
            return resultCode;
        }

        /**
         * ����resultCode���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResultCode(String value) {
            this.resultCode = value;
        }

        /**
         * ��ȡresultMessage���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResultMessage() {
            return resultMessage;
        }

        /**
         * ����resultMessage���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResultMessage(String value) {
            this.resultMessage = value;
        }

    }

}
