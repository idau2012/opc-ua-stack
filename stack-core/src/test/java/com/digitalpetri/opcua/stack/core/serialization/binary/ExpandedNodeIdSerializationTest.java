/*
 * Copyright 2015 Kevin Herron
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.digitalpetri.opcua.stack.core.serialization.binary;

import java.util.UUID;

import com.digitalpetri.opcua.stack.core.types.builtin.ByteString;
import com.digitalpetri.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExpandedNodeIdSerializationTest extends BinarySerializationFixture {

    @DataProvider
    public Object[][] getExpandedNodeIds() {
        return new Object[][]{
                {new ExpandedNodeId(0, 0, null, 0)},
                {new ExpandedNodeId(0, 1, null, 0)},
                {new ExpandedNodeId(0, 255, null, 0)},
                {new ExpandedNodeId(0, 256, null, 0)},
                {new ExpandedNodeId(1, 65535, null, 0)},
                {new ExpandedNodeId(1, 65536, null, 0)},
                {new ExpandedNodeId(255, 65535, null, 0)},
                {new ExpandedNodeId(255, 65536, null, 0)},
                {new ExpandedNodeId(1234, 567890, null, 0)},
                {new ExpandedNodeId(0, "hello, world", null, 0)},
                {new ExpandedNodeId(1, "hello, world", null, 0)},
                {new ExpandedNodeId(0, UUID.randomUUID(), null, 0)},
                {new ExpandedNodeId(1, UUID.randomUUID(), null, 0)},
                {new ExpandedNodeId(0, new ByteString(new byte[]{1, 2, 3, 4}), null, 0)},
                {new ExpandedNodeId(1, new ByteString(new byte[]{1, 2, 3, 4}), null, 0)},
                {new ExpandedNodeId(0, 0, "test", 0)},
                {new ExpandedNodeId(0, 0, "test", 0)},
                {new ExpandedNodeId(0, 0, null, 1)},
                {new ExpandedNodeId(0, 0, null, 1)},
                {new ExpandedNodeId(0, 0, "test", 1)},
        };
    }

    @Test(dataProvider = "getExpandedNodeIds", description = "ExpandedNodeId is round-trip serializable.")
    public void testExpandedNodeIdRoundTrip(ExpandedNodeId nodeId) throws Exception {
        encoder.encodeExpandedNodeId(null, nodeId);
        ExpandedNodeId decoded = decoder.decodeExpandedNodeId(null);

        assertEquals(decoded, nodeId);
    }

}
