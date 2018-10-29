1) This project is for Tournament scheduler created using Spring Boot

2) Default tournament format is Round Robin (RoundRobinFormat.java)

3) Later any other format can implemented using Interface (TournamentFormat.java)

4) ExceptionController is default to handle all exception in application 

5) TournamentController except following requests

	a) GET request with default teams (response format JSON)
		http://localhost:8080/tournament/schedule

	b) GET request with n teams as a input ( response format JSON)
		http://localhost:8080/tournament?teams="team1","team2"

	c) GET request with teams and City as a input ( response format JSON)
		http://localhost:8080/tournament?teams="team1:City1","team2:City2"

	d) GET request with default teams details ( response in formatted String )
		http://localhost:8080/tournament/default

	e) GET request with n teams as a input ( response in formatted String )
		http://localhost:8080/tournament/detail?teams="team1","team2"

	f) GET request with teams and City as a input ( response in formatted String )
		http://localhost:8080/tournament/detail?teams="team1:City1","team2:City2"

	//jackson parser was not working correctly, i need to work on this 
	g) GET request with teams and City as a json input ( response format JSON)
		http://localhost:8080/tournament/detailwithjson?teams={{"team":"team1","city":"City1"},{"team":"team2","city":"City2"}}

6) For Security (use BASIC AUTH)
	a) Two roles UESR and ADMIN created as default;
	b) user/user and admin/admin are login credential

7) Internationalization is done only on Get Request (this can be further expand for other requests as well)
	http://localhost:8080/tournament/detail?teams="team1","team2"

8) Basic properties for Tournaments using as default, but this can also be also set from using POST, for that we can use @Autowired Tournament Object in TournamentServices.java instead of new method 
//    @Autowired
//    Tournament tournament;

9) Tournament default properties are
Tournament Name 		= "Pro Kabaddi 2018"
Tournament Start Date 		= "07-Oct-d2018"
Max possible matches in a day 	= 2
Maximum match gap for a team	= 1 // to avoid match on continue day