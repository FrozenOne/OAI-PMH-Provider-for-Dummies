package com.inqool.oai.provider;

import com.inqool.oai.provider.resource.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * This class is the main servlet listening to HTTP requests. Depending on the verb parameter it delegates it on other resources.
 * @author Lukas Jane (inQool)
 */
@RequestScoped
@Path("/oai-pmh")
public class OaiPmhResource {
    @Inject
    private HttpServletRequest request;

    @Inject
    private IdentifyResource identifyResource;

    @Inject
    private BadVerbResource badVerbResource;

    @Inject
    private ListSetsResource listSetsResource;

    @Inject
    private ListMetadataFormatsResource listMetadataFormatsResource;

    @Inject
    private ListIdentifiersResource listIdentifiersResource;

    @Inject
    private ListRecordsResource listRecordsResource;

    @Inject
    private GetRecordResource getRecordResource;

    @Path("/")
    public VerbResource request(@QueryParam("verb") String string) throws Exception {
        Verb verb;
        try {
            verb = Verb.valueOf(string);
        } catch (IllegalArgumentException | NullPointerException e) {
            return badVerbResource;
        }
        switch (verb) {
            case Identify:
                return identifyResource;
            case ListSets:
                return listSetsResource;
            case ListMetadataFormats:
                return listMetadataFormatsResource;
            case ListIdentifiers:
                return listIdentifiersResource;
            case ListRecords:
                return listRecordsResource;
            case GetRecord:
                return getRecordResource;
            default:
                return badVerbResource;
        }
    }

    public static enum Verb {
        ListRecords,
        GetRecord,
        Identify,
        ListIdentifiers,
        ListMetadataFormats,
        ListSets
    }
}
