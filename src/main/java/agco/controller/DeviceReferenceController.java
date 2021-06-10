package agco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceReferenceController {
  @RequestMapping("/")
  public String index() {
    return "Gradle : Hello Spring Boot!";
  }

}
