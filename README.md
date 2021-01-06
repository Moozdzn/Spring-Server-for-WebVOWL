# ADS - Ontology Visualization

# Getting started
Clone this repository to your local machine and open cmd in root directory.

Run `docker-compose build` followed by `docker-compose up` and open http://localhost:80 

You can run each container independently:

/webapp
```
cd webapp
docker build -t moozdzn/webapp .
docker run -p 80:80 moozdzn/webapp
```

/Server
```
cd Server
docker build -t moozdzn/spring .
docker run -p 8080:8080 moozdzn/spring
```

Note: If issues arise when loading/uploading ontologies, try disabling CORS on your browser.

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

# Project Architecture

![Project Architecture](https://raw.githubusercontent.com/Moozdzn/Spring-Server-for-WebVOWL/main/Deliverable/Architecture.png)

[SWRLAPI](https://github.com/protegeproject/swrlapi)

[OWL2VOWL](https://github.com/VisualDataWeb/OWL2VOWL)


# Development

## Client
This repository contains solely the necessary client files for building and running docker. For source files and instructions on continuing development, please see the appropriate repository at [msnnf-iscteiul/WebVOWL](https://github.com/msnnf-iscteiul/WebVOWL)
## Server
Server developed with [Spring](https://spring.io/) and Java Version 8

Run `mvn clean install` to install all maven dependencies

At the moment `mvn spring-boot:run` produces an error, but you should have no problem running [ServerApplication.java](https://github.com/Moozdzn/Spring-Server-for-WebVOWL/blob/main/Server/src/main/java/com/webvowl/Server/ServerApplication.java) inside your IDE and openning http://localhost:8080 .
