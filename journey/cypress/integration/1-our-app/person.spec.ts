describe("people", () => {
  describe("creating a person", () => {
    function addPerson(personName: string) {
      cy.findByLabelText(/person name/i).type(personName)
      cy.findByRole('button', {name: /create person/i}).click()
    }

    it('should update the displayed list of people under the Unallocated Team', () => {
      cy.visit("http://localhost:8080")
      addPerson("Alex Dow")
      cy.findbyu
    });
  })
})