package gov.nasa.pds.label;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import gov.nasa.pds.label.object.ArrayObject;
import gov.nasa.pds.label.object.DataObject;
import gov.nasa.pds.label.object.GenericObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.testng.annotations.Test;

public class TestFileSizeNotNeeded {

	/**
	 * Tests that the file_size attribute is not needed in a label
	 * to recognize tables declared in the label. This tests issue
	 * PDS-340 (https://oodt.jpl.nasa.gov/jira/browse/PDS-340?filter=11220).
	 * @throws Exception
	 */
	@Test
	public void testFileSizeNotNeeded() throws Exception {
		createDataFile("src/test/resources/mvn_lpw.cdf", 230296);
		Label label = Label.open(new File("src/test/resources/mvn_lpw.xml"));
		List<DataObject> objects = label.getObjects();
		assertEquals(objects.size(), 5); // Header, Array_2D, Array_1D, Array_2D, Array_1D

		assertTrue(objects.get(0) instanceof GenericObject);
		checkArray(objects.get(1), 2);
		checkArray(objects.get(2), 1);
		checkArray(objects.get(3), 2);
		checkArray(objects.get(4), 1);
	}

	private void checkArray(DataObject obj, int numAxes) {
		assertTrue(obj instanceof ArrayObject);

		ArrayObject array = (ArrayObject) obj;
		assertEquals(array.getAxes(), numAxes);
	}

	private void createDataFile(String path, long size) throws IOException {
		File f = new File(path);
		f.deleteOnExit();
		OutputStream out = new FileOutputStream(f);
		try {
			byte[] buf = new byte[65536];
			while (size > 0) {
				int chunkSize = (size > buf.length ? buf.length : (int) size);
				out.write(buf, 0, chunkSize);
				size -= chunkSize;
			}
		} finally {
			out.close();
		}
	}

}
