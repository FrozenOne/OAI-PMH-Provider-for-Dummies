# OAI-PMH-Provider-for-Dummies
Pure Java framework for creating OAI-PMH servers. Extendable. Easy.

If you want to have your own OAI-PMH server and have no idea where to start, you are in the right place.

Use Maven to build a .war file from this project and deploy it on your jboss/wildfly/tomcat java server.
Once it is running, try going to <br>
http://localhost:8080/oai-provider?verb=Identify<br>
http://localhost:8080/oai-provider/?verb=ListRecords&metadataPrefix=oai_dc<br>
to see some sample data coming out from your server.

Then it's time to get hands dirty.
Look at OaiPmhConfiguration class where you can fill in some important info about your server, and then implement the OaiDataSource interface to feed some real data into your OAI-provider. Done!

You may also wish to provide other formats than Dublin Core. For that, implement your own version of FormatDescriptor and add it into the OaiPmhConfiguration.
