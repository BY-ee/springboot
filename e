[1mdiff --git a/.idea/.gitignore b/.idea/.gitignore[m
[1mnew file mode 100644[m
[1mindex 0000000..13566b8[m
[1m--- /dev/null[m
[1m+++ b/.idea/.gitignore[m
[36m@@ -0,0 +1,8 @@[m
[32m+[m[32m# Default ignored files[m
[32m+[m[32m/shelf/[m
[32m+[m[32m/workspace.xml[m
[32m+[m[32m# Editor-based HTTP Client requests[m
[32m+[m[32m/httpRequests/[m
[32m+[m[32m# Datasource local storage ignored files[m
[32m+[m[32m/dataSources/[m
[32m+[m[32m/dataSources.local.xml[m
[1mdiff --git a/.gitignore b/demo/.gitignore[m
[1msimilarity index 100%[m
[1mrename from .gitignore[m
[1mrename to demo/.gitignore[m
[1mdiff --git a/build.gradle b/demo/build.gradle[m
[1msimilarity index 59%[m
[1mrename from build.gradle[m
[1mrename to demo/build.gradle[m
[1mindex e7a8aec..0a0be91 100644[m
[1m--- a/build.gradle[m
[1m+++ b/demo/build.gradle[m
[36m@@ -4,7 +4,7 @@[m [mplugins {[m
 	id 'io.spring.dependency-management' version '1.1.6'[m
 }[m
 [m
[31m-group = 'com'[m
[32m+[m[32mgroup = 'com.example'[m
 version = '0.0.1-SNAPSHOT'[m
 [m
 java {[m
[36m@@ -13,27 +13,18 @@[m [mjava {[m
 	}[m
 }[m
 [m
[31m-configurations {[m
[31m-	compileOnly {[m
[31m-		extendsFrom annotationProcessor[m
[31m-	}[m
[31m-}[m
[31m-[m
 repositories {[m
 	mavenCentral()[m
 }[m
 [m
 dependencies {[m
[31m-	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'[m
[32m+[m	[32mimplementation 'org.springframework.boot:spring-boot-starter'[m
 	implementation 'org.springframework.boot:spring-boot-starter-web'[m
[31m-	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'[m
[31m-	implementation 'org.liquibase:liquibase-core'[m
[31m-	compileOnly 'org.projectlombok:lombok'[m
[31m-	developmentOnly 'org.springframework.boot:spring-boot-devtools'[m
[31m-	runtimeOnly 'com.oracle.database.jdbc:ojdbc11'[m
[31m-	annotationProcessor 'org.projectlombok:lombok'[m
 	testImplementation 'org.springframework.boot:spring-boot-starter-test'[m
 	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'[m
[32m+[m	[32mimplementation 'com.oracle.database.jdbc:ojdbc6:11.2.0.4'[m
[32m+[m	[32mimplementation 'org.springframework.boot:spring-boot-starter-data-jpa'[m
[32m+[m	[32mimplementation 'org.liquibase:liquibase-core:4.29.1'[m
 }[m
 [m
 tasks.named('test') {[m
[1mdiff --git a/gradle/wrapper/gradle-wrapper.jar b/demo/gradle/wrapper/gradle-wrapper.jar[m
[1msimilarity index 100%[m
[1mrename from gradle/wrapper/gradle-wrapper.jar[m
[1mrename to demo/gradle/wrapper/gradle-wrapper.jar[m
[1mdiff --git a/gradle/wrapper/gradle-wrapper.properties b/demo/gradle/wrapper/gradle-wrapper.properties[m
[1msimilarity index 100%[m
[1mrename from gradle/wrapper/gradle-wrapper.properties[m
[1mrename to demo/gradle/wrapper/gradle-wrapper.properties[m
[1mdiff --git a/gradlew b/demo/gradlew[m
[1msimilarity index 100%[m
[1mrename from gradlew[m
[1mrename to demo/gradlew[m
[1mdiff --git a/gradlew.bat b/demo/gradlew.bat[m
[1msimilarity index 100%[m
[1mrename from gradlew.bat[m
[1mrename to demo/gradlew.bat[m
[1mdiff --git a/demo/settings.gradle b/demo/settings.gradle[m
[1mnew file mode 100644[m
[1mindex 0000000..0a383dd[m
[1m--- /dev/null[m
[1m+++ b/demo/settings.gradle[m
[36m@@ -0,0 +1 @@[m
[32m+[m[32mrootProject.name = 'demo'[m
[1mdiff --git a/demo/src/main/java/com/example/demo/controller/DemoApplication.java b/demo/src/main/java/com/example/demo/controller/DemoApplication.java[m
[1mnew file mode 100644[m
[1mindex 0000000..4dc4c3c[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/java/com/example/demo/controller/DemoApplication.java[m
[36m@@ -0,0 +1,26 @@[m
[32m+[m[32mpackage com.example.demo.controller;[m
[32m+[m
[32m+[m[32mimport org.springframework.boot.SpringApplication;[m
[32m+[m[32mimport org.springframework.boot.autoconfigure.SpringBootApplication;[m
[32m+[m[32mimport org.springframework.stereotype.Controller;[m
[32m+[m[32mimport org.springframework.ui.Model;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.GetMapping;[m
[32m+[m
[32m+[m[32m// 주요 애노테이션: 스프링 부트 애플리케이션을 구성[m
[32m+[m[32m@SpringBootApplication[m
[32m+[m[32mpublic class DemoApplication {[m
[32m+[m
[32m+[m	[32mpublic static void main(String[] args) {[m
[32m+[m		[32mSpringApplication.run(DemoApplication.class, args);[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m// 컨트롤러 메소드: HTTP 요청을 처리하고 응답을 반환[m
[32m+[m	[32m@Controller[m
[32m+[m	[32mpublic class HelloController {[m
[32m+[m		[32m@GetMapping("/Hello")[m
[32m+[m		[32mpublic String Hello(Model model) {[m
[32m+[m			[32mmodel.addAttribute("message", "Hello, Spring Boot!");[m
[32m+[m			[32mreturn "Hello";[m
[32m+[m		[32m}[m
[32m+[m	[32m}[m
[32m+[m[32m}[m
[1mdiff --git a/demo/src/main/java/com/example/demo/controller/HelloWorldApplication.java b/demo/src/main/java/com/example/demo/controller/HelloWorldApplication.java[m
[1mnew file mode 100644[m
[1mindex 0000000..d381372[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/java/com/example/demo/controller/HelloWorldApplication.java[m
[36m@@ -0,0 +1,22 @@[m
[32m+[m[32mpackage com.example.demo.controller;[m
[32m+[m
[32m+[m[32mimport org.springframework.boot.SpringApplication;[m
[32m+[m[32mimport org.springframework.boot.autoconfigure.SpringBootApplication;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.GetMapping;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.RestController;[m
[32m+[m
[32m+[m[32m@SpringBootApplication[m
[32m+[m[32mpublic class HelloWorldApplication {[m
[32m+[m[32m    public static void main(String[] args) {[m
[32m+[m[32m        SpringApplication.run(HelloWorldApplication.class, args);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    // 컨트롤러 정의[m
[32m+[m[32m    @RestController[m
[32m+[m[32m    public class HelloWorldController {[m
[32m+[m[32m        @GetMapping("/Hi")[m
[32m+[m[32m        public String helloWorld() {[m
[32m+[m[32m            return "Hello World!";[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[1mdiff --git a/demo/src/main/java/com/example/demo/controller/PostController.java b/demo/src/main/java/com/example/demo/controller/PostController.java[m
[1mnew file mode 100644[m
[1mindex 0000000..5afa286[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/java/com/example/demo/controller/PostController.java[m
[36m@@ -0,0 +1,8 @@[m
[32m+[m[32mpackage com.example.demo.controller;[m
[32m+[m
[32m+[m[32mimport org.springframework.stereotype.Controller;[m
[32m+[m
[32m+[m[32m@Controller[m
[32m+[m[32mpublic class PostController {[m
[32m+[m[41m    [m
[32m+[m[32m}[m
[1mdiff --git a/demo/src/main/java/com/example/demo/model/Board.java b/demo/src/main/java/com/example/demo/model/Board.java[m
[1mnew file mode 100644[m
[1mindex 0000000..b826a5c[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/java/com/example/demo/model/Board.java[m
[36m@@ -0,0 +1,32 @@[m
[32m+[m[32mpackage com.example.demo.model;[m
[32m+[m
[32m+[m[32mimport jakarta.persistence.Entity;[m
[32m+[m[32mimport jakarta.persistence.Id;[m
[32m+[m
[32m+[m[32m@Entity[m
[32m+[m[32mpublic class Board {[m
[32m+[m[32m    @Id[m
[32m+[m[32m    private Long id;[m
[32m+[m[32m    private String title;[m
[32m+[m[32m    private String content;[m
[32m+[m
[32m+[m[32m    public Board() {[m
[32m+[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public Board(Long id, String title, String content) {[m
[32m+[m[32m        this.id = id;[m
[32m+[m[32m        this.title = title;[m
[32m+[m[32m        this.content = content;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public Long getId() {[m
[32m+[m[32m        return id;[m
[32m+[m[32m    }[m
[32m+[m[32m    public String getTitle() {[m
[32m+[m[32m        return title;[m
[32m+[m[32m    }[m
[32m+[m[32m    public String getContent() {[m
[32m+[m[32m        return content;[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[1mdiff --git a/src/main/java/com/oraclejpa/model/User.java b/demo/src/main/java/com/example/demo/model/User.java[m
[1msimilarity index 95%[m
[1mrename from src/main/java/com/oraclejpa/model/User.java[m
[1mrename to demo/src/main/java/com/example/demo/model/User.java[m
[1mindex e6ce0ab..1dc84a9 100644[m
[1m--- a/src/main/java/com/oraclejpa/model/User.java[m
[1m+++ b/demo/src/main/java/com/example/demo/model/User.java[m
[36m@@ -1,4 +1,4 @@[m
[31m-package com.oraclejpa.model;[m
[32m+[m[32mpackage com.example.demo.model;[m
 [m
 [m
 import jakarta.persistence.Entity;[m
[36m@@ -19,11 +19,9 @@[m [mpublic class User {[m
     public void setId(Long id) {[m
         this.id = id;[m
     }[m
[31m-[m
     public void setName(String name) {[m
         this.name = name;[m
     }[m
[31m-[m
     public void setPassword(String password) {[m
         this.password = password;[m
     }[m
[36m@@ -31,11 +29,9 @@[m [mpublic class User {[m
     public Long getId() {[m
         return id;[m
     }[m
[31m-[m
     public String getName() {[m
         return name;[m
     }[m
[31m-[m
     public String getPassword() {[m
         return password;[m
     }[m
[1mdiff --git a/src/main/java/com/oraclejpa/repository/UserRepository.java b/demo/src/main/java/com/example/demo/repository/UserRepository.java[m
[1msimilarity index 72%[m
[1mrename from src/main/java/com/oraclejpa/repository/UserRepository.java[m
[1mrename to demo/src/main/java/com/example/demo/repository/UserRepository.java[m
[1mindex 5199a4e..54cbcc8 100644[m
[1m--- a/src/main/java/com/oraclejpa/repository/UserRepository.java[m
[1m+++ b/demo/src/main/java/com/example/demo/repository/UserRepository.java[m
[36m@@ -1,6 +1,6 @@[m
[31m-package com.oraclejpa.repository;[m
[32m+[m[32mpackage com.example.demo.repository;[m
 [m
[31m-import com.oraclejpa.model.User;[m
[32m+[m[32mimport com.example.demo.model.User;[m
 import org.springframework.data.jpa.repository.JpaRepository;[m
 import org.springframework.stereotype.Repository;[m
 [m
[1mdiff --git a/demo/src/main/java/com/example/demo/service/UserService.java b/demo/src/main/java/com/example/demo/service/UserService.java[m
[1mnew file mode 100644[m
[1mindex 0000000..c788991[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/java/com/example/demo/service/UserService.java[m
[36m@@ -0,0 +1,14 @@[m
[32m+[m[32mpackage com.example.demo.service;[m
[32m+[m
[32m+[m[32mimport com.example.demo.model.User;[m
[32m+[m[32mimport com.example.demo.repository.UserRepository;[m
[32m+[m[32mimport org.springframework.beans.factory.annotation.Autowired;[m
[32m+[m[32mimport org.springframework.stereotype.Service;[m
[32m+[m
[32m+[m[32m@Service[m
[32m+[m[32mpublic class UserService {[m
[32m+[m[32m    @Autowired[m
[32m+[m[32m    private UserRepository userRepository;[m
[32m+[m
[32m+[m[32m    public void saveUser(User user) {}[m
[32m+[m[32m}[m
[1mdiff --git a/demo/src/main/resources/application.properties b/demo/src/main/resources/application.properties[m
[1mnew file mode 100644[m
[1mindex 0000000..964d3e8[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/resources/application.properties[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32mspring.application.name=demo[m
[32m+[m[32mserver.port=8082[m
[32m+[m[32mspring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe[m
[32m+[m[32mspring.datasource.username=mini[m
[32m+[m[32mspring.datasource.password=2417[m
[32m+[m[32mspring.datasource.driver-class-name=oracle.jdbc.OracleDriver[m
\ No newline at end of file[m
[1mdiff --git a/demo/src/main/resources/db/changelog/db.changelog-master.yaml b/demo/src/main/resources/db/changelog/db.changelog-master.yaml[m
[1mnew file mode 100644[m
[1mindex 0000000..2b4a421[m
[1m--- /dev/null[m
[1m+++ b/demo/src/main/resources/db/changelog/db.changelog-master.yaml[m
[36m@@ -0,0 +1,23 @@[m
[32m+[m[32mdatabaseChangeLog:[m
[32m+[m[32m  - changeSet:[m
[32m+[m[32m      id: 1[m
[32m+[m[32m      author: mini[m
[32m+[m[32m      changes:[m
[32m+[m[32m        - createTable:[m
[32m+[m[32m            tableName: board[m
[32m+[m[32m            tablespace: board_tablespace[m
[32m+[m[32m            columns:[m
[32m+[m[32m              - column:[m
[32m+[m[32m                  name: id[m
[32m+[m[32m                  type: bigint[m
[32m+[m[32m                  constraints:[m
[32m+[m[32m                    primaryKey: true[m
[32m+[m[32m                    nullable: false[m
[32m+[m[32m              - column:[m
[32m+[m[32m                  name: title[m
[32m+[m[32m                  type: varchar(255)[m
[32m+[m[32m                  constraints:[m
[32m+[m[32m                    nullable: false  # 제목은 null이 될 수 없음[m
[32m+[m[32m              - column:[m
[32m+[m[32m                  name: content[m
[32m+[m[32m                  type: text[m
\ No newline at end of file[m
[1mdiff --git a/src/main/structure.txt b/demo/src/main/structure.txt[m
[1msimilarity index 63%[m
[1mrename from src/main/structure.txt[m
[1mrename to demo/src/main/structure.txt[m
[1mindex 2e3372c..abf4c83 100644[m
[1m--- a/src/main/structure.txt[m
[1m+++ b/demo/src/main/structure.txt[m
[36m@@ -5,6 +5,7 @@[m [msrc[m
             └── yourpackage[m
                 ├── Application.java[m
                 ├── model[m
[31m-                ├── repository[m
[31m-                ├── service[m
[32m+[m[32m                │   └── YourEntity.java[m
[32m+[m[32m                └── repository[m
[32m+[m[32m                └── service[m
                 └── controller[m
\ No newline at end of file[m
[1mdiff --git a/src/test/java/com/oraclejpa/OraclejpaApplicationTests.java b/demo/src/test/java/com/example/demo/DemoApplicationTests.java[m
[1msimilarity index 72%[m
[1mrename from src/test/java/com/oraclejpa/OraclejpaApplicationTests.java[m
[1mrename to demo/src/test/java/com/example/demo/DemoApplicationTests.java[m
[1mindex f959fd6..2778a6a 100644[m
[1m--- a/src/test/java/com/oraclejpa/OraclejpaApplicationTests.java[m
[1m+++ b/demo/src/test/java/com/example/demo/DemoApplicationTests.java[m
[36m@@ -1,10 +1,10 @@[m
[31m-package com.oraclejpa;[m
[32m+[m[32mpackage com.example.demo;[m
 [m
 import org.junit.jupiter.api.Test;[m
 import org.springframework.boot.test.context.SpringBootTest;[m
 [m
 @SpringBootTest[m
[31m-class OraclejpaApplicationTests {[m
[32m+[m[32mclass DemoApplicationTests {[m
 [m
 	@Test[m
 	void contextLoads() {[m
[1mdiff --git a/settings.gradle b/settings.gradle[m
[1mdeleted file mode 100644[m
[1mindex d43a262..0000000[m
[1m--- a/settings.gradle[m
[1m+++ /dev/null[m
[36m@@ -1 +0,0 @@[m
[31m-rootProject.name = 'oraclejpa'[m
[1mdiff --git a/src/main/java/com/oraclejpa/OraclejpaApplication.java b/src/main/java/com/oraclejpa/OraclejpaApplication.java[m
[1mdeleted file mode 100644[m
[1mindex 8f5c837..0000000[m
[1m--- a/src/main/java/com/oraclejpa/OraclejpaApplication.java[m
[1m+++ /dev/null[m
[36m@@ -1,13 +0,0 @@[m
[31m-package com.oraclejpa;[m
[31m-[m
[31m-import org.springframework.boot.SpringApplication;[m
[31m-import org.springframework.boot.autoconfigure.SpringBootApplication;[m
[31m-[m
[31m-@SpringBootApplication[m
[31m-public class OraclejpaApplication {[m
[31m-[m
[31m-	public static void main(String[] args) {[m
[31m-		SpringApplication.run(OraclejpaApplication.class, args);[m
[31m-	}[m
[31m-[m
[31m-}[m
[1mdiff --git a/src/main/java/com/oraclejpa/controller/PostController.java b/src/main/java/com/oraclejpa/controller/PostController.java[m
[1mdeleted file mode 100644[m
[1mindex 6ffa68a..0000000[m
[1m--- a/src/main/java/com/oraclejpa/controller/PostController.java[m
[1m+++ /dev/null[m
[36m@@ -1,62 +0,0 @@[m
[31m-package com.oraclejpa.controller;[m
[31m-[m
[31m-import com.oraclejpa.model.Post;[m
[31m-import com.oraclejpa.service.PostService;[m
[31m-import lombok.RequiredArgsConstructor;[m
[31m-import org.springframework.stereotype.Controller;[m
[31m-import org.springframework.ui.Model;[m
[31m-import org.springframework.web.bind.annotation.GetMapping;[m
[31m-import org.springframework.web.bind.annotation.ModelAttribute;[m
[31m-import org.springframework.web.bind.annotation.PostMapping;[m
[31m-[m
[31m-import java.util.List;[m
[31m-[m
[31m-@Controller[m
[31m-@RequiredArgsConstructor[m
[31m-public class PostController {[m
[31m-    private final PostService postService;[m
[31m-[m
[31m-    @GetMapping("/")[m
[31m-    public String index() {[m
[31m-        return "post/index";[m
[31m-    }[m
[31m-[m
[31m-    @GetMapping("/write")[m
[31m-    public String write() {[m
[31m-        return "post/write";[m
[31m-    }[m
[31m-[m
[31m-    @PostMapping("/write")[m
[31m-    public String writePost(@ModelAttribute Post post) {[m
[31m-        postService.save(post);[m
[31m-        return "post/comSave";[m
[31m-    }[m
[31m-[m
[31m-    @GetMapping("/list")[m
[31m-    public String list(Model model) {[m
[31m-        List<Post> dataList = postService.findAll();[m
[31m-        model.addAttribute("dataList", dataList);[m
[31m-        return "post/list";[m
[31m-    }[m
[31m-[m
[31m-    @PostMapping("/list")[m
[31m-    public String listToIndex() {[m
[31m-        return "redirect:/";[m
[31m-    }[m
[31m-[m
[31m-    @PostMapping("/comSave")[m
[31m-    public String comToIndex() {[m
[31m-        return "redirect:/";[m
[31m-    }[m
[31m-[m
[31m-    @GetMapping("/delete")[m
[31m-    public String delete() {[m
[31m-        return "post/delete";[m
[31m-    }[m
[31m-[m
[31m-    @PostMapping("/delete")[m
[31m-    public String deletePostAll() {[m
[31m-        postService.deleteAll();[m
[31m-        return "post/comDelete";[m
[31m-    }[m
[31m-}[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/com/oraclejpa/controller/UserController.java b/src/main/java/com/oraclejpa/controller/UserController.java[m
[1mdeleted file mode 100644[m
[1mindex e9c0adb..0000000[m
[1m--- a/src/main/java/com/oraclejpa/controller/UserController.java[m
[1m+++ /dev/null[m
[36m@@ -1,59 +0,0 @@[m
[31m-package com.oraclejpa.controller;[m
[31m-[m
[31m-import com.oraclejpa.model.User;[m
[31m-import org.springframework.web.bind.annotation.*;[m
[31m-[m
[31m-import java.util.ArrayList;[m
[31m-import java.util.List;[m
[31m-[m
[31m-@RestController[m
[31m-@RequestMapping("/api/users")[m
[31m-public class UserController {[m
[31m-    private final List<User> users = new ArrayList<>();[m
[31m-[m
[31m-    // 사용자 목록 조회[m
[31m-    @GetMapping[m
[31m-    public List<User> getUsers() {[m
[31m-        return users;[m
[31m-    }[m
[31m-[m
[31m-    // 사용자 상세 조회[m
[31m-    @GetMapping("/{id}")[m
[31m-    public User getUser(@PathVariable Long id) {[m
[31m-        return users.stream()[m
[31m-                .filter(user -> user.getId().equals(id))[m
[31m-                .findFirst()[m
[31m-                .orElseThrow(() -> new NotFoundException("User not found"));[m
[31m-    }[m
[31m-[m
[31m-    // 사용자 생성[m
[31m-    @PostMapping[m
[31m-    public User createUser(@RequestBody User user) {[m
[31m-        users.add(user);[m
[31m-        return user;[m
[31m-    }[m
[31m-[m
[31m-    // 사용자 수정[m
[31m-    @PutMapping("/{id}")[m
[31m-    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {[m
[31m-        User user = users.stream()[m
[31m-                .filter(u -> u.getId().equals(id))[m
[31m-                .findFirst()[m
[31m-                .orElseThrow(() -> new NotFoundException("User not found"));[m
[31m-        user.setName(updatedUser.getName());[m
[31m-        user.setPassword(updatedUser.getPassword());[m
[31m-        return user;[m
[31m-    }[m
[31m-[m
[31m-    // 사용자 삭제[m
[31m-    @DeleteMapping("/{id}")[m
[31m-    public void deleteUser(@PathVariable Long id) {[m
[31m-        users.removeIf(user -> user.getId().equals(id));[m
[31m-    }[m
[31m-[m
[31m-    public static class NotFoundException extends RuntimeException {[m
[31m-        public NotFoundException(String message) {[m
[31m-            super(message);[m
[31m-        }[m
[31m-    }[m
[31m-}[m
[1mdiff --git a/src/main/java/com/oraclejpa/model/Post.java b/src/main/java/com/oraclejpa/model/Post.java[m
[1mdeleted file mode 100644[m
[1mindex 877077d..0000000[m
[1m--- a/src/main/java/com/oraclejpa/model/Post.java[m
[1m+++ /dev/null[m
[36m@@ -1,19 +0,0 @@[m
[31m-package com.oraclejpa.model;[m
[31m-[m
[31m-import jakarta.persistence.*;[m
[31m-import lombok.Getter;[m
[31m-import lombok.Setter;[m
[31m-import lombok.ToString;[m
[31m-[m
[31m-@Entity[m
[31m-@Getter[m
[31m-@Setter[m
[31m-@ToString[m
[31m-public class Post {[m
[31m-    @Id[m
[31m-    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")[m
[31m-    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)[m
[31m-    private Long id;[m
[31m-    private String title;[m
[31m-    private String content;[m
[31m-}[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/com/oraclejpa/repository/PostRepository.java b/src/main/java/com/oraclejpa/repository/PostRepository.java[m
[1mdeleted file mode 100644[m
[1mindex 61e7902..0000000[m
[1m--- a/src/main/java/com/oraclejpa/repository/PostRepository.java[m
[1m+++ /dev/null[m
[36m@@ -1,9 +0,0 @@[m
[31m-package com.oraclejpa.repository;[m
[31m-[m
[31m-import com.oraclejpa.model.Post;[m
[31m-import org.springframework.data.jpa.repository.JpaRepository;[m
[31m-import org.springframework.stereotype.Repository;[m
[31m-[m
[31m-@Repository[m
[31m-public interface PostRepository extends JpaRepository<Post, Long> {[m
[31m-}[m
[1mdiff --git a/src/main/java/com/oraclejpa/service/PostService.java b/src/main/java/com/oraclejpa/service/PostService.java[m
[1mdeleted file mode 100644[m
[1mindex 51f78b1..0000000[m
[1m--- a/src/main/java/com/oraclejpa/service/PostService.java[m
[1m+++ /dev/null[m
[36m@@ -1,27 +0,0 @@[m
[31m-package com.oraclejpa.service;[m
[31m-[m
[31m-import com.oraclejpa.model.Post;[m
[31m-import com.oraclejpa.repository.PostRepository;[m
[31m-import lombok.RequiredArgsConstructor;[m
[31m-import org.springframework.stereotype.Service;[m
[31m-import org.springframework.web.bind.annotation.ModelAttribute;[m
[31m-[m
[31m-import java.util.List;[m
[31m-[m
[31m-@Service[m
[31m-@RequiredArgsConstructor[m
[31m-public class PostService {[m
[31m-    private final PostRepository postRepository;[m
[31m-[m
[31m-    public void save(@ModelAttribute Post post) {[m
[31m-        postRepository.save(post);[m
[31m-    }[m
[31m-[m
[31m-    public void deleteAll() {[m
[31m-        postRepository.deleteAll();[m
[31m-    }[m
[31m-[m
[31m-    public List<Post> findAll() {[m
[31m-        return postRepository.findAll();[m
[31m-    }[m
[31m-}[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/com/oraclejpa/service/UserService.java b/src/main/java/com/oraclejpa/service/UserService.java[m
[1mdeleted file mode 100644[m
[1mindex d145894..0000000[m
[1m--- a/src/main/java/com/oraclejpa/service/UserService.java[m
[1m+++ /dev/null[m
[36m@@ -1,17 +0,0 @@[m
[31m-package com.oraclejpa.service;[m
[31m-[m
[31m-import com.oraclejpa.model.User;[m
[31m-import com.oraclejpa.repository.UserRepository;[m
[31m-import lombok.RequiredArgsConstructor;[m
[31m-import org.springframework.beans.factory.annotation.Autowired;[m
[31m-import org.springframework.stereotype.Service;[m
[31m-[m
[31m-@Service[m
[31m-@RequiredArgsConstructor[m
[31m-public class UserService {[m
[31m-    private final UserRepository userRepository;[m
[31m-[m
[31m-    public void saveUser(User user) {[m
[31m-        userRepository.save(user);[m
[31m-    }[m
[31m-}[m
[1mdiff --git a/src/main/resources/application.properties b/src/main/resources/application.properties[m
[1mdeleted file mode 100644[m
[1mindex f237535..0000000[m
[1m--- a/src/main/resources/application.properties[m
[1m+++ /dev/null[m
[36m@@ -1,8 +0,0 @@[m
[31m-spring.application.name=oraclejpa[m
[31m-server.port=8082[m
[31m-spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe[m
[31m-spring.datasource.username=mini[m
[31m-spring.datasource.password=2417[m
[31m-spring.datasource.driver-class-name=oracle.jdbc.OracleDriver[m
[31m-spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.yaml[m
[31m-spring.liquibase.enabled=true[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/db/changelog/db.changelog-master.yaml b/src/main/resources/db/changelog/db.changelog-master.yaml[m
[1mdeleted file mode 100644[m
[1mindex 6f23872..0000000[m
[1m--- a/src/main/resources/db/changelog/db.changelog-master.yaml[m
[1m+++ /dev/null[m
[36m@@ -1,34 +0,0 @@[m
[31m-databaseChangeLog:[m
[31m-  - changeSet:[m
[31m-        id: "createPostSequence"[m
[31m-        author: "mini"[m
[31m-        changes:[m
[31m-          - createSequence:[m
[31m-              sequenceName: "post_seq"[m
[31m-              incrementBy: 1[m
[31m-  - changeSet:[m
[31m-      id: "createPostTable"[m
[31m-      author: "mini"[m
[31m-      changes:[m
[31m-        - createTable:[m
[31m-            tableName: "post"[m
[31m-            columns:[m
[31m-              - column:[m
[31m-                  name: "id"[m
[31m-                  type: "int"[m
[31m-                  constraints:[m
[31m-                    primaryKey: true[m
[31m-                    nullable: false[m
[31m-              - column:[m
[31m-                  name: "title"[m
[31m-                  type: "varchar2(30)"[m
[31m-              - column:[m
[31m-                  name: "content"[m
[31m-                  type: "varchar2(255)"[m
[31m-[m
[31m-#  - changeSet:[m
[31m-#      id: "insertPostIntoTable"[m
[31m-#      author: "mini"[m
[31m-#      changes:[m
[31m-#        - insert:[m
[31m-#            tableName: "post"[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/post/comDelete.html b/src/main/resources/templates/post/comDelete.html[m
[1mdeleted file mode 100644[m
[1mindex df7639c..0000000[m
[1m--- a/src/main/resources/templates/post/comDelete.html[m
[1m+++ /dev/null[m
[36m@@ -1,11 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html lang="en">[m
[31m-<head>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <title>삭제 성공 페이지</title>[m
[31m-</head>[m
[31m-<body>[m
[31m-  <h1>삭제 성공</h1>[m
[31m-  <input type="button" value="Return" onclick="window.location.href='/'">[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/post/comSave.html b/src/main/resources/templates/post/comSave.html[m
[1mdeleted file mode 100644[m
[1mindex 1276401..0000000[m
[1m--- a/src/main/resources/templates/post/comSave.html[m
[1m+++ /dev/null[m
[36m@@ -1,13 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html lang="en">[m
[31m-<head>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <title>Complete</title>[m
[31m-</head>[m
[31m-<body>[m
[31m-    <h1>저장 완료</h1>[m
[31m-    <form th:action="@{/comSave}" method="post">[m
[31m-        <button type="submit">Return</button>[m
[31m-    </form>[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/post/delete.html b/src/main/resources/templates/post/delete.html[m
[1mdeleted file mode 100644[m
[1mindex 9a99468..0000000[m
[1m--- a/src/main/resources/templates/post/delete.html[m
[1m+++ /dev/null[m
[36m@@ -1,13 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html lang="en">[m
[31m-<head>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <title>글 삭제</title>[m
[31m-</head>[m
[31m-<body>[m
[31m-    <form th:action="@{/delete}" method="post">[m
[31m-        <h1>글 삭제 페이지</h1>[m
[31m-        <button type="submit">삭제</button>[m
[31m-    </form>[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/post/index.html b/src/main/resources/templates/post/index.html[m
[1mdeleted file mode 100644[m
[1mindex ff50951..0000000[m
[1m--- a/src/main/resources/templates/post/index.html[m
[1m+++ /dev/null[m
[36m@@ -1,11 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html xmlns:th="http://www.thymeleaf.org">[m
[31m-<head>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <title>Home</title>[m
[31m-</head>[m
[31m-<body>[m
[31m-    <h1>Home</h1>[m
[31m-    <a th:href="@{/write}">글 작성</a> <a th:href="@{/list}">작성글 목록</a>[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/post/list.html b/src/main/resources/templates/post/list.html[m
[1mdeleted file mode 100644[m
[1mindex e8af451..0000000[m
[1m--- a/src/main/resources/templates/post/list.html[m
[1m+++ /dev/null[m
[36m@@ -1,18 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html lang="en">[m
[31m-<head>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <title>출력</title>[m
[31m-</head>[m
[31m-<body>[m
[31m-    <form th:action="@{/list}" method="post">[m
[31m-        <h1>출력</h1>[m
[31m-        <div th:each="post : ${dataList}">[m
[31m-            <div th:text="${post.getId()}"></div>[m
[31m-            <p th:text="'Title: ' + ${post.getTitle()}"></p>[m
[31m-            <p th:text="'Content: ' + ${post.getContent()}"></p>[m
[31m-        </div>[m
[31m-        <input type="button" value="Delete" onclick="window.location.href='delete'"><button type="submit">Return</button>[m
[31m-    </form>[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/post/write.html b/src/main/resources/templates/post/write.html[m
[1mdeleted file mode 100644[m
[1mindex 08af92d..0000000[m
[1m--- a/src/main/resources/templates/post/write.html[m
[1m+++ /dev/null[m
[36m@@ -1,21 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html lang="en">[m
[31m-<head>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <title>글 작성</title>[m
[31m-</head>[m
[31m-<body>[m
[31m-    <h3>글 작성</h3>[m
[31m-    <form th:action="@{/write}" method="post">[m
[31m-        <div>[m
[31m-            <label for="content">Title:</label>[m
[31m-            <textarea id="title" name="title"></textarea>[m
[31m-        </div>[m
[31m-        <div>[m
[31m-            <label for="content">Content:</label>[m
[31m-            <textarea id="content" name="content"></textarea>[m
[31m-        </div>[m
[31m-        <button type="submit">작성</button><input type="button" value="Home" onclick="window.location.href='/'">[m
[31m-    </form>[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/com/oraclejpa/controller/PostController.java b/src/main/java/com/oraclejpa/controller/PostController.java[m
[1mindex a24b5cb..fd40776 100644[m
[1m--- a/src/main/java/com/oraclejpa/controller/PostController.java[m
[1m+++ b/src/main/java/com/oraclejpa/controller/PostController.java[m
[36m@@ -44,9 +44,9 @@[m [mpublic class PostController {[m
     @GetMapping("/lists")[m
     public String list(@RequestParam(value = "page") int page,[m
                        @RequestParam(value = "size") int size, Model model) {[m
[31m-         Page<Post> postPage = postService.getPosts(page, size);[m
[31m-         model.addAttribute("postPage", postPage);[m
[31m-         return "post/lists";[m
[32m+[m[32m        Page<Post> postPage = postService.getPosts(page, size);[m
[32m+[m[32m        model.addAttribute("postPage", postPage);[m
[32m+[m[32m        return "post/lists";[m
     }[m
 [m
     @GetMapping("/list")[m
