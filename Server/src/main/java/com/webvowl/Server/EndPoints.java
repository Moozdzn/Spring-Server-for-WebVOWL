package com.webvowl.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webvowl.Server.models.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EndPoints {
    ObjectMapper mapper = new ObjectMapper();

    Map<Integer, Query> list = new HashMap<>();

    @RequestMapping(
            value = "/query",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public void postQueryBody(@RequestBody String queryRequest) throws JsonProcessingException {
        System.out.println(queryRequest);
        System.out.println(queryRequest);
        Query query = mapper.readValue(queryRequest,Query.class);
        //list.put(query.getId(),query );

        System.out.println(query.getQuery());
    }
    @RequestMapping(
            value = "/owl",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            method = RequestMethod.POST)
    //TODO: UPLOAD ONTOLOGY
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        File convertFile = new File("C:\\Users\\Diogo Work\\Desktop\\Server\\owlUpload\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is uploaded", HttpStatus.OK);
    }
    @GetMapping("/getQuery")
    public Map<Integer,Query> getProblem(){
        return list;
    }
}
