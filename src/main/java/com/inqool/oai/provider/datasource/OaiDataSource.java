package com.inqool.oai.provider.datasource;

import com.inqool.oai.provider.exception.IdDoesNotExistPmhException;
import com.inqool.oai.provider.exception.NoRecordsMatchPmhException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Interface specifying a data source from which record data can be loaded.
 * You implement this interface for your source, be it a database, file, or other source.
 * @author Lukas Jane (inQool) 2. 5. 2015.
 */
public interface OaiDataSource {
    /**
     * Gets a single record specified by identifier.
     * @param identifier identifier
     * @return Map containing property names mapped to lists of values. This should contain your format fields and an additional "lastModified" field.
     * @throws com.inqool.oai.provider.exception.IdDoesNotExistPmhException
     */
    Map<String, List<String>> getRecordDataToMap(String identifier) throws IdDoesNotExistPmhException;

    /**
     * Retrieves records between from and until (inclusive), belonging to set, and not all, but only specified page (first page is 0).
     * @param from
     * @param until
     * @param set
     * @param page
     * @param hasMore output parameter, specifying if there are more records and resumption token should be issued
     * @return Map of OAI record identifier -> map of property name -> list of property values.
     * @throws com.inqool.oai.provider.exception.NoRecordsMatchPmhException
     */
    Map<String, Map<String, List<String>>> listRecordDataToMap(OffsetDateTime from, OffsetDateTime until, String set, int page, AtomicBoolean hasMore) throws NoRecordsMatchPmhException;

    /**
     * Same as listRecordDataToMap, but returns only map of identifiers mapped to their last modified time.
     * @param from
     * @param until
     * @param set
     * @param page
     * @param hasMore output parameter, specifying if there are more records and resumption token should be issued
     * @return Map of OAI record identifier -> last modified ISO date string
     * @throws com.inqool.oai.provider.exception.NoRecordsMatchPmhException
     */
    Map<String, String> listIdentifiersToMap(OffsetDateTime from, OffsetDateTime until, String set, int page, AtomicBoolean hasMore) throws NoRecordsMatchPmhException;
}
