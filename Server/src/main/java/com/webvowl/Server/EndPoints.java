package com.webvowl.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webvowl.Server.models.Query;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swrlapi.sqwrl.SQWRLResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public String postQueryBody(@RequestBody String queryRequest) throws JsonProcessingException {
        Query query = mapper.readValue(queryRequest,Query.class);
        SWRLAPI swrlapi = new SWRLAPI();
        Object result = swrlapi.executeQuery(query.getQuery(), query.getOwlFileName());
        String jsonString = mapper.writeValueAsString(result);
        return jsonString;
    }
    @RequestMapping(
            value = "/owl",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            method = RequestMethod.POST)
    //TODO: UPLOAD ONTOLOGY
    public ResponseEntity<Object> uploadFile(@NotNull @RequestParam("file")MultipartFile file) throws IOException {
        String pathToFile = "..\\Server\\owlUpload\\" + file.getOriginalFilename();
        System.out.println(file.getOriginalFilename());
        File convertFile = new File(pathToFile);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        Runtime.getRuntime().exec("java -jar ./owl2vowl/owl2vowl.jar -file "+pathToFile);
        String content = new String(Files.readAllBytes(Paths.get("./PMOEA.json")));
        System.out.println(content);



        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
