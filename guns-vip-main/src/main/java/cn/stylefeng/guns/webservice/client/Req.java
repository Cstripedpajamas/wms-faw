
package cn.stylefeng.guns.webservice.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
 *                   &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="transID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="interfaceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="msgBody" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lineCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="mtlno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="storageLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="getDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "msgHeader",
    "msgBody"
})
@XmlRootElement(name = "req")
public class Req {

    @XmlElement(required = true)
    protected Req.MsgHeader msgHeader;
    @XmlElement(required = true)
    protected List<Req.MsgBody> msgBody;

    /**
     * 获取msgHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Req.MsgHeader }
     *     
     */
    public Req.MsgHeader getMsgHeader() {
        return msgHeader;
    }

    /**
     * 设置msgHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Req.MsgHeader }
     *     
     */
    public void setMsgHeader(Req.MsgHeader value) {
        this.msgHeader = value;
    }

    /**
     * Gets the value of the msgBody property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the msgBody property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMsgBody().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Req.MsgBody }
     * 
     * 
     */
    public List<Req.MsgBody> getMsgBody() {
        if (msgBody == null) {
            msgBody = new ArrayList<Req.MsgBody>();
        }
        return this.msgBody;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="lineCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="mtlno" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="storageLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="getDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "code",
        "lineCode",
        "mtlno",
        "storageLocation",
        "getDate",
        "qty"
    })
    public static class MsgBody {

        @XmlElement(required = true)
        protected String code;
        @XmlElement(required = true)
        protected String lineCode;
        @XmlElement(required = true)
        protected String mtlno;
        @XmlElement(required = true)
        protected String storageLocation;
        @XmlElement(required = true)
        protected String getDate;
        @XmlElement(required = true)
        protected String qty;

        /**
         * 获取code属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * 设置code属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * 获取lineCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLineCode() {
            return lineCode;
        }

        /**
         * 设置lineCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLineCode(String value) {
            this.lineCode = value;
        }

        /**
         * 获取mtlno属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMtlno() {
            return mtlno;
        }

        /**
         * 设置mtlno属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMtlno(String value) {
            this.mtlno = value;
        }

        /**
         * 获取storageLocation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStorageLocation() {
            return storageLocation;
        }

        /**
         * 设置storageLocation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStorageLocation(String value) {
            this.storageLocation = value;
        }

        /**
         * 获取getDate属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGetDate() {
            return getDate;
        }

        /**
         * 设置getDate属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGetDate(String value) {
            this.getDate = value;
        }

        /**
         * 获取qty属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQty() {
            return qty;
        }

        /**
         * 设置qty属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQty(String value) {
            this.qty = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="transID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="interfaceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "receiver",
        "sender",
        "transID",
        "count",
        "messageID",
        "comment",
        "interfaceID"
    })
    public static class MsgHeader {

        @XmlElement(required = true)
        protected String receiver;
        @XmlElement(required = true)
        protected String sender;
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

        /**
         * 获取receiver属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReceiver() {
            return receiver;
        }

        /**
         * 设置receiver属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReceiver(String value) {
            this.receiver = value;
        }

        /**
         * 获取sender属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSender() {
            return sender;
        }

        /**
         * 设置sender属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSender(String value) {
            this.sender = value;
        }

        /**
         * 获取transID属性的值。
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
         * 设置transID属性的值。
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
         * 获取count属性的值。
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
         * 设置count属性的值。
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
         * 获取messageID属性的值。
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
         * 设置messageID属性的值。
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
         * 获取comment属性的值。
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
         * 设置comment属性的值。
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
         * 获取interfaceID属性的值。
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
         * 设置interfaceID属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInterfaceID(String value) {
            this.interfaceID = value;
        }

    }

}
