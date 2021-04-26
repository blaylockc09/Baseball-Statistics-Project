# Baseball-Statistics-Project
* This project will consist of 2 teams. See the Final Project Team List file below for team assignments.
* All due dates are listed on the calendar.
* Each team will complete the requirements listed below and use GitHub to track program changes/approvals and collaborate within their team and with the other team. Other collaborations tools such as Discord may also be used in conjunction with GitHub.
* After completion the Batter team will have the Pitcher team review their work and the Pitcher team will have the Batter team review their work. Each team will make suggestions for improvement and assist in testing and resolving any coding and logic errors. 
* Some team stats have been gathered for you to use and can be found in the StatsFiles.zip file below. You will not be able to reproduce everything on the Ohio State summary page, because some of the information is not included in the game stats.  For example, gdp on the summary page means "ground ball into double play".  This is not recorded on the game page.  However, you can enter 2nd base, 3rd base, and home runs because that's included in a sentence after the list of batters on the game page.  We're also not asking you to do anything with the fielding statistics.
* Both teams are expected to deliver a working program that will stand up to bad data being entered. Data entry should be validated using the console program that you have been using for previous projects.
* There should be a main menu.  The user must be able to select from several options including entering data, displaying the report for a game or displaying a report with cumulative stats for a set of games.  The menu displays are required to be readable and usable by someone not familiar with programming or data entry. Make sure all menus items are easily readable and not crammed together.  There must be an appropriate title for the menu and exit and reset buttons be present. Any additional necessary check boxes, input or output fields or other designs are at your discretion. Remember: if a user doesn't know how to use it then all your programming is useless. If you want to add a help button with instructions that would be a plus.

## Batter Team ##

* Create a Batter class for the batting statistics for a player on the team.
* Create a graphical user interface that can be used to collect data about each batter in a game.
* Once the data has been entered, it should be written out to a file identified by the date of the game.  Each batter’s statistics should be added to the file for that date. The format of the file is up to the team to decide, binary, flat or sql-DB it is your decision.
* When the file is read, it should produce a report that lists the statistics for all batters in that game, and calculates the batting average, total bases, slugging percentage, and on-base percentage for each batter. The report should be saved in a file in a format for printing.
* There should also be a program that will read multiple game files and summarize the statistics on each batter for a specified number of games.

