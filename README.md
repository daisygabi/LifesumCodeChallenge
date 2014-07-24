The attached project is a challenge to search food items provided by Lifesum REST API and the posibility the user can add food items to favorite list, in order to have access to them in 'offline' mode. Using OrmLite(http://ormlite.com/) for the persistence layer and Google’s solution for asynk requests: Volley. The interface is very simple, autocomplete text view when searching for some food items. User can search both in the existing favorite food items or online. The icons are not mine, they are as example only. 

Created the JavaDoc files for the project.
Add the android library from sdk: android-support-v7-appcompat to compile.
Tested it on a tablet as well.

Structure: I have used one activity only, with two adapters for two type of lists. One is the local favorite list and the other is the list that shows the data from endpoint response. If there is no internet connection found, a Toast is shown. Use should be able to delete a favorite item or clear all of them. Usually an application has 3 layers: persistence, busines logica and prezentation. The persistence part is connected to the database(databases). In this case the persistece layer is the one connecting to the local SQLite database. In the business logic, there is the code that consumes a REST API with JSON as response and the presentation part is the single screen the application has. Where user can see exisiting favorited food items and search for new ones as well.

Bug that i know of now: when clearing favorite lists, the newly added items are not shown. The adapter is not notified. The UI needs more polish.

What I would do more:
- save more local information and show them in a different screen
- show icons depending on the food category.
- add details about a favorite food item selected from list
- add exception handling
- better search, don’t show existing favorited food item in the results again
- try retrofit instead of volley. read article that states it’s is faster. A comparison between these two would be fun.
- show loading when user is waiting for the search result. User does not know what i happening
- need to place in correct places the drawables. The "drawable" folder now is just a workaround.(a wrong one)
- configure proguard and refactoring
- would polish the UI

Regarding testing, I have used calabash-android in the project i worked on with Ruby. Creating a ruby script made it more easier to install, test scenarios, uninstall apps. Ruby is fairly easy to use and maintain. Write less, accomplish more. For this project at this time there is no testing. This is something i will try to work on this weekend.

I have used the OrmLite for the first time because I was looking for a new one to try. I have used greenDao before on a project but it became questionable as to why to create a new project beside the Android project and generate the database and table/tables, so i was looking for a new approach to use. This seemed pretty straight forward and easy to implement. I will use ormlite again.
