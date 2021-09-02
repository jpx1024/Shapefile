package org.quantum.shapefile;

import com.esri.core.geometry.Geometry;
import org.quantum.shapefile.dbf.DBFReader;
import org.quantum.shapefile.shp.ShpReader;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author ike
 * @date 2021 年 07 月 02 日 16:43
 */
public class FeatureReader {

    private File shpFile;

    ShpReader shpReader;

    DBFReader dbfReader;

    private Charset charset;

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public FeatureReader(File shpFile, Charset charset) throws IOException {
        this.shpFile = shpFile;
        this.charset = charset;

        shpReader = new ShpReader(new DataInputStream(new FileInputStream(this.shpFile)));

        String dbfName = this.shpFile.getName().substring(0, this.shpFile.getName().indexOf(".")) + ".dbf";
        File dbfFile = new File(String.format("%s/%s", this.shpFile.getParent(), dbfName));
        dbfReader = new DBFReader(new DataInputStream(new BufferedInputStream(new FileInputStream(dbfFile))), charset);
    }

    public FeatureReader(InputStream shpInputStream, InputStream dbfInputStream) throws IOException {
        this.shpReader = new ShpReader(new DataInputStream(shpInputStream));
        this.dbfReader = new DBFReader(new DataInputStream(dbfInputStream), this.charset);
    }

    public boolean hasNext() throws IOException {
        return shpReader.hasMore();
    }

    public SimpleFeature next() throws IOException {
        SimpleFeature feature = new SimpleFeature();
        Geometry geometry = null;

        switch (shpReader.getHeader().shapeType){
            case 1:
                geometry = shpReader.readPoint();
                break;
            case 3:
//                geometry = shpReader.readPolylineMWritable();
                break;
            case 5:
                geometry = shpReader.readPolygon();
                break;
            default:
                throw new IOException("Unexpected value: " + shpReader.getHeader().shapeType);
        }

        feature.setRecordMap(dbfReader.readRecordAsMap());
        feature.setGeometry(geometry);
        return feature;
    }
}
