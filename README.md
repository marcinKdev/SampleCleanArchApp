This is a super simple demo app to present very basic approach to clean architecture implementation in Android ecosystem. The app displays a static list of Github Repositories fetch from github api (this precise endpoint: `https://api.github.com/search/repositories?q=kotlin`).

The app is using:
- clean architecture
- Hilt
- Jetpack Compose
- Kotlin Coroutines
- StateFlow in ViewModel to pass the UI state
- very basic Unit + UI testing

It does not have any fancy UI elements, it's rather an architectural showcase.

Regular screen:
![image](https://github.com/user-attachments/assets/11e5beac-c663-48c7-b625-b9614dad3c84)

Error screen:
![image](https://github.com/user-attachments/assets/c5fa9fdc-d51b-4e5c-9185-283873ab683f)

UI tests:
![Screenshot 2025-02-11 at 13 29 07](https://github.com/user-attachments/assets/1e97e78b-09f9-42ad-9407-df48fe4a1c6a)
