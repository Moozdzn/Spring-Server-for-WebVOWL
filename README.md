#ADS - Ontology Visualization

# Server API Endpoints
<table>
    <tr>
        <th>Method</th>
        <th>URL</th>
        <th>Request</th>
        <th>Response</th>
    </tr>
    <tr>
        <th>POST</th>
        <th>/query</th>
        <th>{"query": "some_query","owlFileName":"owlName"}</th>
        <th>"query_result"</th>
    </tr>
    <tr>
        <th>POST</th>
        <th>/owl</th>
        <th>{"file": [owl_file]}</th>
        <th><a href="http://visualdataweb.de/webvowl/data/template.json">json template</a></th>
    </tr>
</table>

#Development

## Client
This repository contains solely the necessary client files for building and running docker. For source files and instructions on continuing development, please see the appropriate repository [msnnf-iscteiul/WebVOWL](https://github.com/msnnf-iscteiul/WebVOWL)
## Server
Server developed with [Spring](https://spring.io/) and Java Version 8

Run `mvn clean install` to install all maven dependencies

At the moment `mvn spring-boot:run` produces an error, but you should have no problem running [ServerApplication.java](https://github.com/Moozdzn/Spring-Server-for-WebVOWL/blob/main/Server/src/main/java/com/webvowl/Server/ServerApplication.java) inside your IDE and openning http://localhost:8080 .