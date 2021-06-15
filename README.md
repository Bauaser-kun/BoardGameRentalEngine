# Board Game Rental

This is the final project from Kodilla JAVA developer plus bootcamp - a REST API for renting boardGames.
Of course I don't realy have Boardgames to rent ;P

This project was turned in the state from commit: https://github.com/Bauaser-kun/BoardGameRentalEngine/commit/158faa2984d8a8c823ea3102b461b27b24d2f951

(If you are the one who will evaluate my project the actual commit you got link to is one commit later, and it consists only of updating link above)

It will always be a stable commit - if something does not build I'm not going to commit it to this repository.
~~if you entered via my github repositories, or googled it or simply deleted commit identifier after checking it then probably you're already here XD.~~

#Running the app
To run this app you should run the main method in **GameBoardRentalApplication** class which is in **Module Frontend**
Also to properly call Board Game Atlas external API you need client_id to be included into URL, in a code I call it from
system environmental variables - so You should get your own account on BGA (it takes about 2 minutes), but I guess You're
not doing it for the sake of checking one project (:wink:) so here is my client id: O8XRGK41rL
The current (hopefully when you check it already upgraded) version is of course without commit identifier: https://github.com/Bauaser-kun/BoardGameRentalEngine

#Other important(?) info
This is a **multi-module** project - so while checking coverage one package should be excluded from coverage scope.
The said package is vaadin (which is only in frontend module and do not contain any business logic but only views 
created in, you guessed, vaadin. Hence, I thought that it is pointless for it to be in tests coverage scope).
The tests also are all created in one module.
There is also commented PopulateDatabaseWithSampleData class in module tests which is used to fill database with some 
board game data to make possible frontend checks. IT SHOULD NEVER BE RUNNED WITH OTHER TESTS! 

I thought about adding authentication, but the deadline came way to close to implement it on time, however the project
**will be** polished further after turning it in (but at slower pace, as I want to create another project at the same time),
so if you feel like it don't hesitate to  check later commits.


