package com.esri.mapreduce;

import org.quantum.shapefile.io.PointWritable;
import org.quantum.shapefile.mapreduce.PointInputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class PointReaderTest extends MapreduceFS
{
    @Test
    public void testPointReader() throws IOException, URISyntaxException, InterruptedException
    {
        final Path shp = getPath("/testpoint.shp");
        final FileSplit fileSplit = getFileSplit(shp);
        final PointInputFormat pointInputFormat = new PointInputFormat();
        final TaskAttemptContext taskAttemptContext = new TaskAttemptContextImpl(m_jobConfig, new TaskAttemptID());
        final RecordReader<LongWritable, PointWritable> recordReader = pointInputFormat.createRecordReader(fileSplit, taskAttemptContext);
        assertTrue(recordReader.nextKeyValue());
        assertEquals(1L, recordReader.getCurrentKey().get());
        assertPointValues(recordReader.getCurrentValue());
        assertFalse(recordReader.nextKeyValue());
        recordReader.close();
    }
}
