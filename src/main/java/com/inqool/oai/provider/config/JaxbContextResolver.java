package com.inqool.oai.provider.config;

import org.openarchives.oai._2.OAIPMH;
import org.openarchives.oai._2.ObjectFactory;
import org.openarchives.oai._2_0.oai_dc.Dc;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Needed for marshalling Dublin Core responses inside OAI-PMH envelopes
 * @author Lukas Jane (inQool)
 */
@Provider
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class JaxbContextResolver implements ContextResolver<JAXBContext> {

    @Override
    public JAXBContext getContext(Class<?> type) {
        if (type == OAIPMH.class) {
            Class[] bindTypes = new Class[]{
                    OAIPMH.class,
                    ObjectFactory.class,
                    Dc.class,
                    org.openarchives.oai._2_0.oai_dc.ObjectFactory.class,
                    org.purl.dc.elements._1.ObjectFactory.class
            };
            try {
                return JAXBContext.newInstance(bindTypes);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}
