package com.inqool.oai.provider.datasource;

import com.inqool.oai.provider.exception.IdDoesNotExistPmhException;
import com.inqool.oai.provider.exception.NoRecordsMatchPmhException;

import javax.enterprise.context.RequestScoped;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Dummy implementation of OaiDataSource that always returns one fixed record data.
 * Might be useful for quick start testing or seeing how to return data from real data source.
 * @author Lukas Jane (inQool) 17. 6. 2015.
 */
@RequestScoped
public class DummyOaiDataSource implements OaiDataSource {

    @Override
    public Map<String, List<String>> getRecordDataToMap(String identifier) throws IdDoesNotExistPmhException {
        Map<String, List<String>> resultMap = new HashMap<>();
        resultMap.put("title", Collections.singletonList("Sample title"));
        resultMap.put("creator", Collections.singletonList("Sample creator"));
        resultMap.put("lastModified", Collections.singletonList("2015-06-17"));
        return resultMap;
    }

    @Override
    public Map<String, Map<String, List<String>>> listRecordDataToMap(OffsetDateTime from, OffsetDateTime until, String set, int page, AtomicBoolean hasMore) throws NoRecordsMatchPmhException {
        Map<String, Map<String, List<String>>> resultMap = new HashMap<>();
        Map<String, List<String>> singleRecordMap = new HashMap<>();
        singleRecordMap.put("title", Collections.singletonList("Sample title"));
        singleRecordMap.put("creator", Collections.singletonList("Sample creator"));
        singleRecordMap.put("lastModified", Collections.singletonList("2015-06-17"));
        resultMap.put("oai:organizationName:recordId1", singleRecordMap);
        return resultMap;
    }

    @Override
    public Map<String, String> listIdentifiersToMap(OffsetDateTime from, OffsetDateTime until, String set, int page, AtomicBoolean hasMore) throws NoRecordsMatchPmhException {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("oai:organization:recordId1", "2015-06-17");
        resultMap.put("oai:organization:recordId2", "2015-06-17");
        return resultMap;
    }
}
