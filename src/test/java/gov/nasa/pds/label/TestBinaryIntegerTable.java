// Copyright 2019, California Institute of Technology ("Caltech").
// U.S. Government sponsorship acknowledged.
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// * Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
// * Redistributions must reproduce the above copyright notice, this list of
// conditions and the following disclaimer in the documentation and/or other
// materials provided with the distribution.
// * Neither the name of Caltech nor its operating division, the Jet Propulsion
// Laboratory, nor the names of its contributors may be used to endorse or
// promote products derived from this software without specific prior written
// permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package gov.nasa.pds.label;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import java.io.File;
import java.util.List;
import org.testng.annotations.Test;
import gov.nasa.pds.label.object.DataObject;
import gov.nasa.pds.label.object.FieldDescription;
import gov.nasa.pds.label.object.TableObject;
import gov.nasa.pds.label.object.TableRecord;

public class TestBinaryIntegerTable {

  private static final String BINARY_INTEGER_LABEL =
      "src/test/resources/data_type_tests/BinaryIntegerTable.xml";

  @Test
  public void testFieldValues() throws Exception {
    Label label = Label.open(new File(BINARY_INTEGER_LABEL));
    List<DataObject> objects = label.getObjects();
    assertEquals(objects.size(), 1); // Only 1 table object.
    TableObject table = (TableObject) objects.get(0);

    FieldDescription[] fields = table.getFields();
    assertEquals(fields.length, 14);

    TableRecord record;

    //
    // First record, all zero values.
    //
    assertNotNull(record = table.readNext());

    // 8 bit unsigned
    assertEquals(record.getShort(1), 0);

    // 16, 32, 64 bit unsigned MSB
    assertEquals(record.getInt(2), 0);
    assertEquals(record.getLong(3), 0);
    // assertEquals(record.getBigInteger(4), BigInteger.ZERO);

    // 16, 32, 64 bit unsigned LSB
    assertEquals(record.getInt(5), 0);
    assertEquals(record.getLong(6), 0);
    // assertEquals(record.getBigInteger(7), BigInteger.ZERO);

    // 8 bit signed
    assertEquals(record.getByte(8), 0);

    // 16, 32, 64 bit signed MSB
    assertEquals(record.getShort(9), 0);
    assertEquals(record.getInt(10), 0);
    assertEquals(record.getLong(11), 0);

    // 16, 32, 64 bit signed LSB
    assertEquals(record.getShort(12), 0);
    assertEquals(record.getInt(13), 0);
    assertEquals(record.getLong(14), 0);

    //
    // Second record, all one values.
    //
    assertNotNull(record = table.readNext());

    // 8 bit unsigned
    assertEquals(record.getShort(1), 1);

    // 16, 32, 64 bit unsigned MSB
    assertEquals(record.getInt(2), 1);
    assertEquals(record.getLong(3), 1);
    // assertEquals(record.getBigInteger(4), BigInteger.ONE);

    // 16, 32, 64 bit unsigned LSB
    assertEquals(record.getInt(5), 1);
    assertEquals(record.getLong(6), 1);
    // assertEquals(record.getBigInteger(7), BigInteger.ONE);

    // 8 bit signed
    assertEquals(record.getByte(8), 1);

    // 16, 32, 64 bit signed MSB
    assertEquals(record.getShort(9), 1);
    assertEquals(record.getInt(10), 1);
    assertEquals(record.getLong(11), 1);

    // 16, 32, 64 bit signed LSB
    assertEquals(record.getShort(12), 1);
    assertEquals(record.getInt(13), 1);
    assertEquals(record.getLong(14), 1);

    //
    // Third record, maximum values.
    //
    assertNotNull(record = table.readNext());

    // 8 bit unsigned
    assertEquals(record.getShort(1), (short) 0xFF);

    // 16, 32, 64 bit unsigned MSB
    assertEquals(record.getInt(2), 0xFFFF);
    assertEquals(record.getLong(3), 0xFFFFFFFFL);
    // assertEquals(record.getBigInteger(4), BigInteger.ZERO ???);

    // 16, 32, 64 bit unsigned LSB
    assertEquals(record.getInt(5), 0xFFFF);
    assertEquals(record.getLong(6), 0xFFFFFFFFL);
    // assertEquals(record.getBigInteger(7), BigInteger.ZERO ???);

    // 8 bit signed
    assertEquals(record.getByte(8), (byte) 0x7F);

    // 16, 32, 64 bit signed MSB
    assertEquals(record.getShort(9), (short) 0x7FFF);
    assertEquals(record.getInt(10), 0x7FFFFFFF);
    assertEquals(record.getLong(11), 0x7FFFFFFFFFFFFFFFL);

    // 16, 32, 64 bit signed LSB
    assertEquals(record.getShort(12), (short) 0x7FFF);
    assertEquals(record.getInt(13), 0x7FFFFFFF);
    assertEquals(record.getLong(14), 0x7FFFFFFFFFFFFFFFL);

    //
    // Fourth record, minimum values.
    //
    assertNotNull(record = table.readNext());

    // 8 bit unsigned
    assertEquals(record.getShort(1), (short) 0);

    // 16, 32, 64 bit unsigned MSB
    assertEquals(record.getInt(2), 0);
    assertEquals(record.getLong(3), 0L);
    // assertEquals(record.getBigInteger(4), BigInteger.ZERO);

    // 16, 32, 64 bit unsigned LSB
    assertEquals(record.getInt(5), 0);
    assertEquals(record.getLong(6), 0L);
    // assertEquals(record.getBigInteger(7), BigInteger.ZERO);

    // 8 bit signed
    assertEquals(record.getByte(8), (byte) 0x80);

    // 16, 32, 64 bit signed MSB
    assertEquals(record.getShort(9), (short) 0x8000);
    assertEquals(record.getInt(10), 0x80000000);
    assertEquals(record.getLong(11), 0x8000000000000000L);

    // 16, 32, 64 bit signed LSB
    assertEquals(record.getShort(12), (short) 0x8000);
    assertEquals(record.getInt(13), 0x80000000);
    assertEquals(record.getLong(14), 0x8000000000000000L);

    //
    // No more records.
    //
    assertNull(table.readNext());
  }

}
