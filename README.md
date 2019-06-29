# spa-spring-basic
A Basic SpringMVC web application configured for a single page client application.

Why is this so hard? Seriously?!

The end result is really simple but getting to this point is really not. Don't believe me? Well you're here
aren't you. Just try and work it all out yourself before looking at this code, you'll understand the misery.

Anyway, here it is, a skeleton project for a single page web application using SpringMVC for the middle tier.

Key features:

 * The web application is mapped to the root "/" context
 * All static resources are under the "/assets/" path
 * A request for "index.html" will redirect to "/" for a nicer URL in the address bar
 * All web-service API are under an "/api/" path
 * A request for an unknown API will have a catch-all that maps to a BAD_REQUEST response
 * Any other request, including deep-link requests, will map to the single page web application for client
   routing
 * Works with @EnableWebMvc, if you don't use this annotation you will need to set up some other things manually.

The names for the path prefixes used are arbitrary and can be changed to whatever you prefer.

This is a minimal skeleton project, useful things like two-way JSON binding and so on are not fully integrated.

Right now there is no client application integrated, this will come for ReactJS shortly and maybe AngularJS
later (I simply like React way more).

You can run this project from a shell/terminal, simply type:

```
mvn jetty:run
```

Then open your browser at the following URLs to prove it's all configured properly:

```
http://localhost:8080
http://localhost:8080/index.html
http://localhost:8080/api/users
http://localhost:8080/api/version
http://localhost:8080/api/anything-else-does-not-exist
http://localhost:8080/assets/css/index.css
http://localhost:8080/assets/img/star.png
http://localhost:8080/assets/js/app.js
http://localhost:8080/imagine/this/is/client/routing
http://localhost:8080/imagine/this/is/also/client/routing
```

Your single page web application would be expected to be published at "WEB-INF/assets/index.html".

You're welcome.