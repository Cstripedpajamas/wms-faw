
package cn.stylefeng.guns.webservice.client;

import java.util.ArrayList;
import java.util.List;
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
     * ��ȡmsgHeader���Ե�ֵ��
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
     * ����msgHeader���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡcode���Ե�ֵ��
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
         * ����code���Ե�ֵ��
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
         * ��ȡlineCode���Ե�ֵ��
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
         * ����lineCode���Ե�ֵ��
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
         * ��ȡmtlno���Ե�ֵ��
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
         * ����mtlno���Ե�ֵ��
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
         * ��ȡstorageLocation���Ե�ֵ��
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
         * ����storageLocation���Ե�ֵ��
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
         * ��ȡgetDate���Ե�ֵ��
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
         * ����getDate���Ե�ֵ��
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
         * ��ȡqty���Ե�ֵ��
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
         * ����qty���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡreceiver���Ե�ֵ��
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
         * ����receiver���Ե�ֵ��
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
         * ��ȡsender���Ե�ֵ��
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
         * ����sender���Ե�ֵ��
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

    }

}
