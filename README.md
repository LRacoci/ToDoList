# ToDo List

## Features:

* When the app is opened a list of todo must be shown;

* When there isn't entries an empty message must be shown;

* A progress while loading the list must be shown;

* A FloatActionButton must be on right and bottom position to open a new screen where user may create new todo item;

* App must work in landscape and portrait orientation;

* Todo item fields: Title, Description, CreatedDate, Done.

    * Done is marked when user complete the todo item;

    * Description is opcional;

    * Done is false by default;

    * Title must be checked if was typed before save the todo item;

* When a new item is created it must update the list automatically;

* When user click on some item it will complete the item (mark done as true);

* If user long press the item options must be shown with delete option;

## Architecture:

* Develop in Kotlin;

* Use MVVM as architecture;

* Use RoomDatabase to store the todo's;

* Use LiveData to keep the app lifecycle aware;

* Use DataBinding to show data on UI (RecyclerView items);

## References:

An explanation about MVVM: [https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b](https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b).

There is a good Google tutorial how to develop MVVM using Room, LiveData and ViewModel: [https://codelabs.developers.google.com/codelabs/android-room-with-a-view](https://codelabs.developers.google.com/codelabs/android-room-with-a-view).

For DataBinding you can find at: [https://developer.android.com/topic/libraries/data-binding](https://developer.android.com/topic/libraries/data-binding).
