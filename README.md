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

MVVM (Model View ViewModel) is a pattern that primarily helps in separation of concerns for our applications. You see when creating an application, it is best to ensure that there is'nt a mixture of presentation logic and business logic. I like to visualise MVVM as V | VM | M . This is so as to leave the actions for value and data processing to the VM. Presentational tasks are left to the view to handle. Implementing this gets you sort of a miniature front-backend style. That is how I see it.
The Model I like to think of it as a sort of data handler class. The data in the Model is used in caching and access by say repositories.

## Still cooking...
I am still in the process of finishing up this app, I am yet to implement the search and favourites screens. Oh! and for the favorites, I will employ Room to make things easier. I have a repository called MyQuotes where I talk about an app I implemented Room with -if interested, please do check it out. 
