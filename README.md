# FLICK [Still under development]
🎬Flick is
an Android app that consumes the TMDB API to get details about upcoming movies, popular movies, top rated movies and shows in general. This app has been built using Kotlin as the base programming language. While building Flick, I am trying to follow the MVVM architecture.

## Why I created this repo:
        1. To learn how to structure a software using clean architecture concepts.
        2. To solidify my knowledge on some of the android jetpack components.
        3. To use dependency injection with Koin.
       

## Contents:
- Screenshots
- Prerequisite
- Clean Architecture MVVM
- Dependency injection with Koin

## Screenshots
<p align="center">
<img  src="https://i.ibb.co/br181r9/splash.jpg" height="400px">
<img  src="https://i.ibb.co/ZKbmYny/home.jpg" height="400px">
<img  src="https://i.ibb.co/bFpDY2p/details.jpg" height="400px">
</p>

## Prerequisite
Say you want to use TMDB in your future or upcoming projects, what you'll need is an API key, which is really easy to generate. You will want to visit the site [here](https://developers.themoviedb.org/3/getting-started/introduction) and just follow through the guide to creating your key.

## Clean architecture MVVM
![Clean-Architecture-graph-650x488](https://user-images.githubusercontent.com/59829833/137780349-f60383ba-ae90-415f-801c-fb1871a00e2e.png)

The different parts of this app are represented by different layers of circles as the iamge above depicts. As you move to the center circles, your software artifacts become more abstract.
Inner circles tend to contain the business rules of our application.
A layer should not know about the activities of a layer above it but a layer can understand components of a layer beneath it.

### Layers
 1. **Domain**

This is the most abstract layer of our application. It defines the business logic of our application and the necessary models of entities.
What goes in the domain layer?
+ **Entity models**: Components that bundle the info that will be used in our application.
+ **Repository Declarations**: Defenitions you want implemented by repositories from other layers.


    