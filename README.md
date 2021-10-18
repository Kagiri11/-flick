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
**1. Domain**

This is the most abstract layer of our application. It defines the business logic of our application and the necessary models of entities.
What goes in the domain layer?
+ **Entity models**: Components that bundle the info that will be used in our application.
+ **Repository Declarations**: Definitions you want implemented by repositories from other layers.
+ **Use cases**: Are objects that have just one action, to do something specific by utilizing the repository.

**2. Data**

This layer acts as the entry points of data that we require in our application. These can either be data from remote infrastructure or data persisted locally.
What goes in the data layer?
+ **Repository implementations**: These implement the repository declared in our domain layer
+ **Service Declarations**: These artifacts are responsible for providing data and are the ones that feed the repository class.

**3. Presentation**

This is the layer that contains UI related business

## Tech Stack.
This project uses many of the popular libraries, plugins and tools of the android ecosystem.

### Libraries.

- [Hilt](https://github.com/google/hilt) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
    - [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - Allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)-Component that allows easier implementation of navigation from simple button clicks to more complex patterns.

- [MotionLayout](https://medium.com/google-developers/introduction-to-motionlayout-part-i-29208674b10d) - Helps create and manage beautiful UI animations.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client
  and supports coroutines out of the box.
- [GSON](https://github.com/square/gson) - JSON Parser,used to parse
  requests on the data layer for Entities and understands Kotlin non-nullable
  and default parameters.
- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Timber](https://github.com/JakeWharton/timber)-Library for easier logging.
- [Material Design](https://material.io/develop/android/docs/getting-started/) - Build awesome beautiful UIs.
- [Glide](https://github.com/bumptech/glide)- Image Library from loading images from the database and cacheing in memory.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines,provides runBlocking coroutine builder used in tests.
- [Truth](https://truth.dev/) - Assertions Library,provides readability as far as assertions are concerned.
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - Web server for testing HTTP clients, verify requests and responses on the TMDB API with the retrofit client.



    