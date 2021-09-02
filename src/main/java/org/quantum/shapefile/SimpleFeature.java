package org.quantum.shapefile;

import com.esri.core.geometry.Geometry;

import java.util.Map;

/**
 * @author ike
 * @date 2021 年 07 月 02 日 16:54
 */
public class SimpleFeature {

    private Map<String, Object> recordMap;

    private Geometry geometry;

    public Map<String, Object> getRecordMap() {
        return recordMap;
    }

    public void setRecordMap(Map<String, Object> recordMap) {
        this.recordMap = recordMap;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
