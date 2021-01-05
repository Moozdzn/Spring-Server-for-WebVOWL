package com.webvowl.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webvowl.Server.models.Query;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@RestController
public class EndPoints {
    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(
            value = "/query",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public String postQueryBody(@RequestBody String queryRequest) throws JsonProcessingException {
        System.out.println(queryRequest);
        Query query = mapper.readValue(queryRequest,Query.class);
        SWRLAPI swrlapi = new SWRLAPI();
        String result = (String) swrlapi.executeQuery(query.getQuery(), query.getOwlFileName());
        return result;
    }
    @RequestMapping(
            value = "/owl",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            method = RequestMethod.POST)
    //TODO: UPLOAD ONTOLOGY
    public ResponseEntity<Object> uploadFile(@NotNull @RequestParam("file")MultipartFile file) throws IOException, InterruptedException {
        String pathToFile = "./owl2vowl/" + file.getOriginalFilename();
        System.out.println(file.getOriginalFilename());
        File convertFile = new File(pathToFile);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        TimeUnit.SECONDS.sleep(5);
        File finalFile = new File(pathToFile);
        boolean exists = finalFile.exists();
        System.out.println(exists);
        Process convertOWL2JSON = Runtime.getRuntime().exec("java -jar ./owl2vowl/owl2vowl.jar -file "+pathToFile);
        Integer status = convertOWL2JSON.waitFor();
        String fileName = FilenameUtils.removeExtension(file.getOriginalFilename());
        System.out.println(fileName);
        String content = new String(Files.readAllBytes(Paths.get("./"+fileName+".json")));
        //File json = new File(fileName+".json");
        //json.delete();
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
