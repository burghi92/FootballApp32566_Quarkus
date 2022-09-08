# FootballApp32566 Project
Author: André Burkhart, Matrikelnr. 32566

This project uses Quarkus, the Supersonic Subatomic Java Framework.

With this App you can add, adjust, read or delete Football-Matches and also see the resulting ranks of the teams.


## Running the application in dev mode

You can run the application in dev mode that enables live coding using. Just start Application via Terminal with:

```shell script
./mvnw compile quarkus:dev
```

## Call Matches via URL:

All Matches:
```shell script
http://localhost:8080/quarkus/api/matches
```

Single Match(ID is the parameter):
```shell script
http://localhost:8080/quarkus/api/matches/1
```
## Call ranking via URL:

Call a sorted rank of teams:
```shell script
http://localhost:8080/quarkus/api/stats/rank
```

Call an unsorted rank of teams:
```shell script
http://localhost:8080/quarkus/api/stats/all
```

Call team in first place:
```shell script
http://localhost:8080/quarkus/api/stats/first
```

Call team in last place:
```shell script
http://localhost:8080/quarkus/api/stats/last
```

Call teamstatistik of a specific team (ID is the parameter)
```shell script
http://localhost:8080/quarkus/api/stats/1
```

## Update, Delete and Add Matches via REST-API:

Open file „API_Test_FootballApp_Quarkus.http“ in Intellij and Execute HTTP-Requests with Run-Button:

![grafik](https://user-images.githubusercontent.com/98780769/189171471-28b44e88-fa77-4159-9efe-c5fdd37d8ace.png)

Add one Match:

![grafik](https://user-images.githubusercontent.com/98780769/189171635-8057aa03-2ee4-4166-b5d9-5ec040be56b8.png)

Add list of Matches:

![grafik](https://user-images.githubusercontent.com/98780769/189171677-be223aaa-d01e-49d4-ac6a-b5219c9c5fd7.png)

Delete Match via Parameter ID:

![grafik](https://user-images.githubusercontent.com/98780769/189171730-1de37701-f5fd-4c07-b461-6f061c87cfa5.png)

Update a single Match with new Goal statistic:

![grafik](https://user-images.githubusercontent.com/98780769/189171801-c213f789-90d9-4513-a616-f7ac2b1d6df0.png)
