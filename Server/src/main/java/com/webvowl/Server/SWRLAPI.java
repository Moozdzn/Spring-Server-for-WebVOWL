package com.webvowl.Server;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.io.File;

public class SWRLAPI {
    
    static Object executeQuery(String query, String owlFileName){
        // Create OWLOntology instance using the OWLAPI
        OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = null;
        {
            try {
                ontology = ontologyManager.loadOntologyFromOntologyDocument(new File("..\\Server\\owlUpload\\"+owlFileName+".owl"));
            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
            }
        }

        // Create SQWRL query engine using the SWRLAPI
        SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);

        // Create and execute a SQWRL query using the SWRLAPI
        SQWRLResult result;
        {
            try {
                result = queryEngine.runSQWRLQuery("q1",query);
                System.out.println(result);
                return result;

                //if (result.next()) {
                //    System.out.println("Name: " + result.getLiteral("x").getInteger());
                //}
            } catch (SWRLParseException e) {
                e.printStackTrace();
            } catch (SQWRLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
    

    // Process the SQWRL result

}
