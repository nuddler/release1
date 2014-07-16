//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.16 at 11:36:19 AM CEST 
//


package amg.net.filewalker;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for report complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="report">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="walkingTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="wholeProccesingTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="singleProcessTimeList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "report", propOrder = {
    "walkingTime",
    "wholeProccesingTime",
    "singleProcessTimeList"
})
public class Report {

    protected long walkingTime;
    protected long wholeProccesingTime;
    @XmlElement(type = Long.class)
    protected List<Long> singleProcessTimeList;

    /**
     * Gets the value of the walkingTime property.
     * 
     */
    public long getWalkingTime() {
        return walkingTime;
    }

    /**
     * Sets the value of the walkingTime property.
     * 
     */
    public void setWalkingTime(long value) {
        this.walkingTime = value;
    }

    /**
     * Gets the value of the wholeProccesingTime property.
     * 
     */
    public long getWholeProccesingTime() {
        return wholeProccesingTime;
    }

    /**
     * Sets the value of the wholeProccesingTime property.
     * 
     */
    public void setWholeProccesingTime(long value) {
        this.wholeProccesingTime = value;
    }

    /**
     * Gets the value of the singleProcessTimeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the singleProcessTimeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSingleProcessTimeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSingleProcessTimeList() {
        if (singleProcessTimeList == null) {
            singleProcessTimeList = new ArrayList<Long>();
        }
        return this.singleProcessTimeList;
    }

}
