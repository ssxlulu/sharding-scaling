/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.avalon566.shardingscaling.mysql;

import info.avalon566.shardingscaling.core.sync.reader.LogPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Binlog Position.
 *
 * @author avalon566
 * @author yangyi
 */
@Data
@AllArgsConstructor
public class BinlogPosition implements LogPosition<BinlogPosition> {

    private String serverId;

    private String filename;

    private long position;

    @Override
    public final int compareTo(final BinlogPosition binlogPosition) {
        if (null == binlogPosition) {
            return 1;
        }
        long o1 = toLong();
        long o2 = binlogPosition.toLong();
        if (o1 == o2) {
            return 0;
        } else if (o1 > o2) {
            return 1;
        } else {
            return -1;
        }
    }

    private long toLong() {
        return Long.valueOf(filename.substring(filename.lastIndexOf(".") + 1)) << 32 | position;
    }
}
