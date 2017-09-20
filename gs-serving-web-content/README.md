### ESE 2017 Spring Demo ###
This project intended as demonstration of the Spring framework, for the [2017 ESE course](https://github.com/scg-unibe-ch/ese2017) at University of Bern. It is based on [this GitHub repository](https://github.com/spring-guides/gs-serving-web-content), which corresponds to the guide [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/) by Spring.

The original project was slightly extended and modified to show all components of the MVC pattern.

Usage:
1. Download the latest version of the [Spring Tools Suite (STS)](https://spring.io/tools/sts/all)
2. Import the project into STS as a Maven project. The POM file is located in the folder <code>complete</code> of this repository.
3. Locate the class <code>hello.application.Application</code>. Right-click the class, then click "Run As" -> "Spring Boot App".
4. Once the app has fully booted, point your browser to [http://localhost:8080/greeting](http://localhost:8080/greeting) to see the "Hello World" page. Note: if you set a breakpoint somewhere in the <code>greeting()</code> method in <code>hello.controller.GreetingController</code> and start the application with "Debug As", instead of "Run As", the execution should stop at the breakpoint after you point your browser to the specified URL.
5. Have a look at <code>hello.controller.CatalogueController</code>. It makes use of the model in <code>hello.model</code>, to provide details about a specific product in the catalogue. Point your browser to [http://localhost:8080/catalogue?itemId=1](http://localhost:8080/catalogue?itemId=1). To see the second item in the catalogue. You can change the value if the itemId request parameter (although you should keep it between 0 and 4, since the application does not check the input against the applicable range =)). You can also set a breakpoint in the <code>catalogue()</code> and launch the app in debug mode, to see what's going on inside this controller method.