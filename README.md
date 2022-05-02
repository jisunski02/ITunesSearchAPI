# ITunesSearchAPI
Simple Android App that displays data from ITunes Search API


![image](https://user-images.githubusercontent.com/49611734/166177385-9fc3bd55-dfd2-44c5-8cf0-c54a92ba086e.png)

As you can see the home screen, it was populated with data from a ITUnes search API URL, particulary movie list, with its title, genre and price and also the image.

Here's the link URL: https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all

Here are the dependencies that i used in developing the app:

 // Third-Party Networking Library
    implementation 'com.android.volley:volley:1.2.1' >> for handling network between the app and the server, parsing of URL and extracting its JSON values
    implementation 'com.google.code.gson:gson:2.8.7' >> takes big part here, use for serializing of objects

    //Image URL parser/loader Library
    implementation 'com.github.bumptech.glide:glide:4.12.0' >> this is for handling images

    //Room Database
    implementation "android.arch.persistence.room:runtime:$room_version" >> this is for offline storage of certain data, coming from the server
    annotationProcessor "android.arch.persistence.room:compiler:$room_version"
    testImplementation "android.arch.persistence.room:testing:$room_version"

Design Patterns Used: MVP

The MVP pattern allows for easier mocking of the view and more efficient unit testing of applications. In the MVP pattern, 
the presenter manipulates the model while simultaneously updating the view.

Everytime you call an API(from a URL), the data is passing to the presenter(handled by a networking library, which is called in a function), then cascade it into the model
and the view will handle the data listing. 

For more info kindly check comments and code internally. Thank You!

Here's the APK link for your reference: https://drive.google.com/file/d/1TNQWZ2iHpQ0KvO3iI21ZvXTuZdh-CyIv/view?usp=sharing

