package group3.group3_assignment.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class RootController {

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.HEAD })
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Backend is running");
    }

}
