# "Мы заботимся" - приложение для благотворительности

**Используемый стек**
(для навигации внутри проекта: [ReviewClass](https://github.com/chepugash/charity-app/blob/develop/app/src/main/java/com/example/charityapp/AgonaReview.kt), навигация внутри репозитория - ниже)

- Multi-Modules
- Clean
- MVVM
- [Retrofit, OkHttp, GsonConverter](https://github.com/chepugash/charity-app/blob/develop/core/common/src/main/java/com/example/common/data/network/NetworkApiCreator.kt)
- [Dagger2](https://github.com/chepugash/charity-app/blob/develop/app/src/main/java/com/example/charityapp/di/app/AppComponent.kt)
- [Room](https://github.com/chepugash/charity-app/blob/develop/core/common/src/main/java/com/example/common/data/storage/AppDatabase.kt)
- [Coroutines, Flow](https://github.com/chepugash/charity-app/blob/develop/features/favourite/src/main/java/com/example/favourite/data/firebase/FirebaseApiImpl.kt)
- [LiveData](https://github.com/chepugash/charity-app/blob/develop/features/favourite/src/main/java/com/example/favourite/presentation/FavouriteViewModel.kt)
- [Jetpack Navigation Component](https://github.com/chepugash/charity-app/blob/develop/app/src/main/java/com/example/charityapp/navigation/Navigator.kt)
- [Firebase Authentication](https://github.com/chepugash/charity-app/blob/develop/core/common/src/main/java/com/example/common/di/modules/NetworkModule.kt)
- [Firebase Firestore](https://github.com/chepugash/charity-app/blob/develop/core/common/src/main/java/com/example/common/di/modules/NetworkModule.kt)
- [Coil](https://github.com/chepugash/charity-app/blob/develop/features/categories/src/main/java/com/example/categories/presentation/adapter/CategoryItemViewHolder.kt)

**Дополнительно**
- Для проекта было создано REST-Api на Spring'e
