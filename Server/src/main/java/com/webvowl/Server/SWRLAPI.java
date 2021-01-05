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
import java.io.Serializable;
import java.util.ArrayList;

public class SWRLAPI {
    
    public Serializable executeQuery(String query, String owlFileName){
        // Create OWLOntology instance using the OWLAPI
        OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = null;
        {
            try {
                ontology = ontologyManager.loadOntologyFromOntologyDocument(new File("./owl2vowl/"+owlFileName+".owl"));
            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
            }
        }

        // Create SQWRL query engine using the SWRLAPI
        SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);

        // Create and execute a SQWRL query using the SWRLAPI
        SQWRLResult result = null;

        {
            try {
                result = queryEngine.runSQWRLQuery("q1",query);


                return result.getColumn(0).toString();
            } catch (SWRLParseException e) {
                e.printStackTrace();
            } catch (SQWRLException e) {
                e.printStackTrace();
            }
        }
        return "Malformed";
    }
    

    // Process the SQWRL result

}
