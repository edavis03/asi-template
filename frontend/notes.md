## First Story decisions

need to move the rendering of teams from app.tsx because as we add more and more components it will be harder and harder
to go back and separate code as the code base grows.

Prob have a teams component and API

A people Component and API

Maybe separate the creation of teams and people from where ethe list is rendered.

going to try without refactoring code base first and see how the code looks

started on app.test and had to move onto create a peopleApiClient test and file because of having to mock out the
getPeople request

peopleApiClient is completed returning to app.test to work on creating a person.

Moving back to my test and re-reading the story. thinking about how am i going to generate the default team of
unallocated

think i am going to continue and write a test to at least get the required fields for making a person then move to the
backend to handle that request.

realized i made my mock too soon will leave it at top of test for now to use later

note to self remember to put mock for team call when app is rendered. Might be a reason to separate out into components
jury still out on that