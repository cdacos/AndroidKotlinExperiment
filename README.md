# Android Kotlin Experiments

Simple app that explores:

- Kotlin
- Android Architecture Components (ViewModel, LiveData)
- RecyclerView and DiffUtil
- Dagger 2 for dependency injection

Uses Hacker News API (Firebase) https://github.com/HackerNews/API :

- ThreadPoolExecutor for async behaviour off main thread
- First fetches a list of top 500 story ids
- Then for first 25 stories gets details for each
- Click story title to open a WebView with the original story
- Click comment icon to open a WebView with the HN comment page for that story
