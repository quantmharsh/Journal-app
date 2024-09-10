package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//  it is used for  creating API . we have to write this annotation above class . not inside the class
@RestController
public class MyClass {

   // GetMapping is used for creating get request OR for creating Routes
   // (localhost:8080/hello)
   @GetMapping("hello")
   public String sayHello() {
      return "Hello";
   }

   // localhost:8080/bye
   @GetMapping("bye")
   public String sayBye() {
      return " Bye.";
   }
}