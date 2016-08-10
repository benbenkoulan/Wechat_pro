/**
 * @Title: ReqLocationModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.req;

/**
 * @ClassName: ReqLocationModel
 * @Description: 地理位置消息
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:05:16
 */

public class ReqLocationModel extends ReqBaseModel {
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;
    /**
     * @return location_X
     */
    public String getLocation_X() {
        return Location_X;
    }
    /**
     * @param location_X 要设置的 location_X
     */
    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }
    /**
     * @return location_Y
     */
    public String getLocation_Y() {
        return Location_Y;
    }
    /**
     * @param location_Y 要设置的 location_Y
     */
    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }
    /**
     * @return scale
     */
    public String getScale() {
        return Scale;
    }
    /**
     * @param scale 要设置的 scale
     */
    public void setScale(String scale) {
        Scale = scale;
    }
    /**
     * @return label
     */
    public String getLabel() {
        return Label;
    }
    /**
     * @param label 要设置的 label
     */
    public void setLabel(String label) {
        Label = label;
    }

}
