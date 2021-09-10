# FLICK [Still under development]
🎬Flick is
an Android app that consumes the TMDB API to get details about upcoming movies, popular movies, top rated movies and shows in general. This app has been built using Kotlin as the base programming language. While building Flick, I am trying to follow the MVVM architecture.

## Why I created this repo:
        1. To understand how to use retrofit to consume an API.
        2. To solidify my knowledge on the MVVM architecture.
        3. Get to use some of the android jetpack components. 

## Contents:
- Screenshots
- Prerequisite
- MVVM
- Retrofit
- Motion Layout

## Screenshots
<p align="center">
<img  src="https://i.ibb.co/br181r9/splash.jpg" height="400px">
<img  src="https://i.ibb.co/ZKbmYny/home.jpg" height="400px">
<img  src="https://i.ibb.co/bFpDY2p/details.jpg" height="400px">
</p>

## Prerequisite
Say you want to use TMDB in your future or upcoming projects, what you'll need is an API key, which is really easy to generate. You will want to visit the site [here](https://developers.themoviedb.org/3/getting-started/introduction) and just follow through the guide to creating your key.

## MVVM and why the pattern
MVVM (Model View ViewModel) is a pattern that primarily helps in separation of concerns for our applications. You see when creating an application, it is best to ensure that there is'nt a mixture of presentation logic and business logic. I like to visualise MVVM as V | VM | M . This is so as to leave the actions for value and data processing to the VM. Presentational tasks are left to the view to handle. Implementing this gets you sort of a miniature front-backend style. That is how I see it. 

The Model I like to think of it as a sort of data handler class. The data in the Model is used in caching and access by say repositories.


## Retrofit
As it is described in its official [documentation](https://square.github.io/retrofit/), retrofit is a type safe http client that generally is there to help us interact with and make network calls in a much more clean and simple manner. Powerful features such as mocking of responses and custom headers come out of the box when dealing with Retrofit. 

### Setup:

 * **Create model classes**

To use retrofit first of all create the model classes (data classes) that your application needs. These will be fleshed up by the JSON body from the network calls made.
 ```
 data class Song(
         val artist:String,
         val runTime: Int,
         val releaseDate: String,
         val albumArt:String
 )
 ```

 * **Create the API service**

Setup an interface to host all the HTTP calls to be performed by your application.

```
interface SongsNetwork{

        @GET(songs/pop)
        suspend fun fetchPopSongs():Response<List<Songs>>

        @GET(songs/rock)
        suspend fun fetchRockSongs():Response<List<Songs>>
}
```

The methods GET, POST, PATCH, PUT, DELETE HEAD and OPTIONS are provided by Retrofit 2 and they do try to do the basic CRUD operations as you would with SQL *in a way*

 * **Provide the instance of Retrofit**

 If you are using dependency injection, you can provide a dependency for the instance of Retrofit in your network module. So simply its just the instrument that will do the literal interal with with the API as you will provide it the base url, converter factory and a client if need be.

``` 
class RetrofitInstance{
                
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GSonConverterFactory.create())
                .build()

        val api = retrofit.create(NetworkService::class.java)

}
```


## Motion Layout


## Still cooking...
I am still in the process of finishing up this app, I am yet to implement the search and favourites screens. Oh! and for the favorites, I will employ Room to make things easier. I have a repository called MyQuotes where I talk about an app I implemented Room with -if interested, please do check it out. 