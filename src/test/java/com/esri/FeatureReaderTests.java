package com.esri;

import org.quantum.shapefile.FeatureReader;
import org.quantum.shapefile.SimpleFeature;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author ike
 * @date 2021 年 07 月 02 日 17:42
 */
public class FeatureReaderTests {

    @Test
    public void reads() throws IOException {
        File file = new File("C:\\Users\\jpx10\\Desktop\\test\\c430424_衡东县.shp");


        FeatureReader featureReader = new FeatureReader(file, Charset.forName("utf-8"));

        while (featureReader.hasNext()){
            SimpleFeature feature = featureReader.next();

            System.out.println(feature.getRecordMap().toString());
            System.out.println(feature.getGeometry().toString());
        }
    }
}
