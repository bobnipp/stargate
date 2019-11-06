import {RecordFormCreatorService} from "./record-form-creator.service";

describe('RecordFormCreatorService', () => {
    describe('createLinkForm', () => {
        it('creates an empty link when no link provided', () => {
            let service = new RecordFormCreatorService(jasmine.createSpyObj('linkValidator', ['validate']))

            let linkForm = service.createLinkForm()

            expect(linkForm.value).toEqual({
                record1Id: '',
                record2Id: '',
                record1System: '',
                record2System: ''
            })
        })

        it('creates a pre-filled link when link is provided', () => {
            let service = new RecordFormCreatorService(jasmine.createSpyObj('linkValidator', ['validate']))

            let link = {
                record1Id: '123',
                record2Id: 'PRISM 456',
                record1System: 'IMM',
                record2System: 'PRISM'
            };
            let linkForm = service.createLinkForm(link)

            expect(linkForm.value).toEqual(link)
        })
    })
})