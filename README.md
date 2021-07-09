# FLICK
🎬Flick is
an Android app that consumes the TMDB API to get details about upcoming movies, popular movies, top rated movies and shows in general. This app has been built using Kotlin as the base programming language. While building Flick, I am trying to follow the MVVM architecture.

## Why I created this repo:
        1. To understand how to use retrofit to consume an API.
        2. To solidify my knowledge on the MVVM architecture.
        3. Get to use some of the android jetpack components. 

# Table of Contents
-[Prerequisite](##prerequisite)

## Prerequisite
Say you want to use TMDB in your future or upcoming projects, what you'll need is an API key, which is really easy to generate. You will want to visit the site [here](https://developers.themoviedb.org/3/getting-started/introduction) and just follow through the guide to creating your key.

## MVVM and why that pattern
MVVM (Model View ViewModel) is a pattern that primarily helps in separation of concerns for our applications. You see when creating an application, it is best to ensure that there is'nt a mixture of presentation logic and business logic. I like to visualise MVVM as V | VM | M . This is so as to leave the actions for value and data processing to the VM. Presentational tasks are left to the view to handle. Implementing this gets you sort of a miniature front-backend style. That is how I see it. 

The Model I like to think of it as a sort of data handler class. The data in the Model is used in caching and access by say repositories.


## Retrofit and the Data Source


## Motion Layout


## Still cooking...