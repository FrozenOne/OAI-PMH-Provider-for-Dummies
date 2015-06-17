package com.inqool.oai.provider;

import com.inqool.oai.provider.exception.NoSetHierarchyPmhException;
import com.inqool.oai.provider.formats.DcFormatDescriptor;
import com.inqool.oai.provider.formats.FormatDescriptor;
import org.openarchives.oai._2.*;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class providing all the custom configuration for the OAI-PMH provider.
 *
 * If you are looking to use the OAI-PMH provider, you should:
 *
 * 1) fill in your repository information to the identify() method
 * 2) implement FormatDescriptor for all metadata formats you wish to support, or just complete the default DcFormatDescriptor template
 * 3) add all supported formats in this class constructor
 *
 * @author Lukas Jane (inQool) 1. 5. 2015.
 */
@ApplicationScoped
public class OaiPmhConfiguration {
    private Map<String, FormatDescriptor> supportedFormats = new HashMap<>();

    /**
     * Add all supported metadata formats here.
     * When request comes, Resource classes use these format descriptors to load data and build the response.
     */
    public OaiPmhConfiguration() {
        addSupportedFormat(new DcFormatDescriptor());
        //addSupportedFormat(new AnotherFormatDescriptor());
    }

    /**
     * Provides basic info about the repository when identify verb is requested
     */
    public IdentifyType identify() {
        IdentifyType identifyType = new IdentifyType();
        //TODO fill actual info
        identifyType.setRepositoryName("IQ ZDO Repository");
        identifyType.getAdminEmails().add("admin@example.com");
        identifyType.setEarliestDatestamp("INSTALL_DATE");    //find earliest modified record and return the time
        identifyType.setDeletedRecord(DeletedRecordType.NO);
        identifyType.setGranularity(GranularityType.YYYY_MM_DD);
        identifyType.setProtocolVersion("2.0");

        final DescriptionType desc = new DescriptionType();
        desc.setAny(new JAXBElement<>(new QName("general"), String.class, "An example repository description"));
        identifyType.getDescriptions().add(desc);
        return identifyType;
    }

    /**
     * List sets, groups into which records are divided in this repository
     * @throws com.inqool.oai.provider.exception.NoSetHierarchyPmhException
     */
    public ListSetsType listSets() throws NoSetHierarchyPmhException {
        throw new NoSetHierarchyPmhException();
    }

    /* Following methods do not need to be changed */

    public void addSupportedFormat(FormatDescriptor formatDescriptor) {
        supportedFormats.put(formatDescriptor.getMetadataPrefix(), formatDescriptor);
    }

    public FormatDescriptor getFormatDescriptor(String name) {
        return supportedFormats.get(name);
    }

    public Collection<FormatDescriptor> listAllFormats() {
        return supportedFormats.values();
    }
}
