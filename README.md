## ASI Enablement:
Allocations User Stories Workshop
Kotlin Version .1

## User stories link must use SWF Account for GDrive

https://docs.google.com/document/d/1-J82qQj_apHjn5FgKhSX0MJtbsrd7VjlJt0AVOg0KmY/edit


## or just read these

The simulation
###The prompt
You work in an Army Software Factory which delivers many different software products, has many teams which deliver those products, and many people who create those products.

Many people in the Software Factory need to know who is which team and what the plan is for the future (forming new teams, people joining or leaving the Factory, and people changing teams).

The Program Manager is the person accountable for allocating people to product teams and this is currently done in a spreadsheet that shows who is currently on each team. Sometimes people are listed as being on two teams and sometimes there are people who are not listed anywhere in the spreadsheet because of an error copying them. There’s no simple way for anyone to know who will be on which team in the future.

Your PM expects you to implement the user stories in this order and only work one story at a time. Your tech lead points you to the initial version that she created in GitHub.
The user stories

##Product team list
As a program manager, I want to be able to create teams and see them in a list of teams. It does not need to be easy to create teams because it doesn’t happen often and the team list does not need to be pretty. CreateTeam are just the name of the team.

###Acceptance Criteria
When I create a team
Then I see the team name in the team list

##New CreatePerson
As a program manager, I want to be able to add people to my organization and see them in a team named “Unallocated”. The other teams are not changed.

###Acceptance Criteria0
When I add a person to my organization
Then I see them on a team named “Unallocated"

##Allocate people to teams
As a program manager, I want to be able to move people from “Unallocated” to one of the other teams and see them on their new team.

###Acceptance Criteria
Given that I have at least one team
And at least one person who is unallocated
When I allocate that person to a team
Then I can see them on their new team
And I don’t see them in the “Unallocated” list

##Move between teams
As a program manager, I want to be able to move people from one team to any other team (including Unallocated) and see them on their new team.

###Acceptance Criteria
Given that I have two teams (A and B)
And at least one person on Team A
When I move a person from Team A to Team B
Then I can see them on Team B
And I don’t see them on Team A

###Acceptance Criteria
Given that I have a team named Team A
And at least one person on Team A
When I move that person from Team A to Unallocated
Then I can see that person as Unallocated
And I don’t see them on Team A
##Allocations have a start date
###Acceptance Criteria
Given that I have at least one team
And at least one person who is unallocated
When I allocate that person to a team with a start date
Then I can see the start date next to their name
##Allocations have an end date
###Acceptance Criteria
Given that I have at least one team
And at least one person who is unallocated
When I allocate that person to a team with an end date
Then I can see the end date after their start date