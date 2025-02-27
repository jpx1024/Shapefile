package org.quantum.shapefile.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 */
public class PolygonFeatureWritable extends PolygonWritable
{
    public final Attributes attributes = new Attributes();

    public PolygonFeatureWritable()
    {
    }

    @Override
    public void write(final DataOutput dataOutput) throws IOException
    {
        super.write(dataOutput);
        attributes.write(dataOutput);
    }

    @Override
    public void readFields(final DataInput dataInput) throws IOException
    {
        super.readFields(dataInput);
        attributes.readFields(dataInput);
    }
}
