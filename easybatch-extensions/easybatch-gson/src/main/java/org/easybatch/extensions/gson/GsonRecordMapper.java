/**
 * The MIT License
 *
 *   Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */
package org.easybatch.extensions.gson;

import com.google.gson.Gson;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Record;
import org.easybatch.json.JsonRecord;

import static org.easybatch.core.util.Utils.checkNotNull;

/**
 * Mapper that uses <a href="https://code.google.com/p/google-gson/">Google Gson</a>
 * to map json records to domain objects.
 *
 * @param <T> Target domain object class.
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class GsonRecordMapper<T> implements RecordMapper<JsonRecord, Record<T>> {

    private Gson mapper;
    private Class<T> type;

    /**
     * Create a new {@link GsonRecordMapper}.
     *
     * @param mapper to use
     * @param type   of target object
     */
    public GsonRecordMapper(final Gson mapper, final Class<T> type) {
        checkNotNull(mapper, "Gson mapper");
        checkNotNull(type, "target type");
        this.mapper = mapper;
        this.type = type;
    }

    @Override
    public Record<T> processRecord(final JsonRecord record) throws Exception {
        return new GenericRecord<>(record.getHeader(), mapper.fromJson(record.getPayload(), type));
    }

}
