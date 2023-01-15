import * as identification_page_component from "../identification-page.component"
// @ponicode
describe("identification_page_component.IdentificationPageComponent.savePassport", () => {
    let inst: any

    beforeEach(() => {
        inst = new identification_page_component.IdentificationPageComponent()
    })

    test("0", () => {
        inst.savePassport()
    })
})
