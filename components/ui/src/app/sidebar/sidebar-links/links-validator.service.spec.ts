import {LinksValidatorService} from "./links-validator.service";
import {RecordFormCreatorService} from "../record-form-creator/record-form-creator.service";
import {makePrismNomination, makeRfi} from "../../../test_helpers/utilities";
import {RfiLink} from "../../models/rfi-link.model";
import {Store, StoreModule} from "@ngrx/store";
import {AppState} from "../../state/appstate";
import {TestBed} from "@angular/core/testing";
import {AgmCoreModule} from "@agm/core";
import {prismNominationsReducer, rfiListReducer} from "../../state/reducers";
import {ReceivedPrismNominationsAction, ReceivedRfisAction} from "../../state/actions";
import {FormControl, FormGroup} from "@angular/forms";

describe('LinksValidatorService', () => {

    let store: Store<AppState>

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [AgmCoreModule.forRoot(),
                StoreModule.forRoot({
                    rfis: rfiListReducer,
                    prismNominations: prismNominationsReducer
                })
            ]
        })
        store = TestBed.get(Store)

        const rfis = [
            makeRfi({id: 'rfi-1'}),
            makeRfi({id: 'rfi-2'}),
            makeRfi({id: 'rfi-3'})
        ]
        store.dispatch(new ReceivedRfisAction(rfis))

        const nominations = [
            makePrismNomination({id: 'prism-1'}),
            makePrismNomination({id: 'prism-2'}),
            makePrismNomination({id: 'prism-3'})
        ]
        store.dispatch(new ReceivedPrismNominationsAction(nominations))
    })

    describe('validate', () => {
        it('returns errors when a link id does not exist', () => {
            let service = new LinksValidatorService(store)
            let link: RfiLink = {
                record1Id: 'rfi-4',
                record2Id: 'rfi-5',
                record1System: '',
                record2System: '',
                title: '',
                status: ''
            }
            let linkForm = new FormGroup({
                record1Id: new FormControl(''),
                record2Id: new FormControl(''),
                record1System: new FormControl(''),
                record2System: new FormControl('')
            })
            linkForm.patchValue(link)

            const result = service.validate(linkForm)

            expect(result).toEqual({linkIdMissing: 'Cannot find ID'})
        })

        it('returns null when the ids exist', () => {
            let service = new LinksValidatorService(store)
            let link: RfiLink = {
                record1Id: 'rfi-1',
                record2Id: 'rfi-2',
                record1System: 'IMM',
                record2System: 'IMM',
                title: '',
                status: ''
            }
            let linkForm = new FormGroup({
                record1Id: new FormControl(''),
                record2Id: new FormControl(''),
                record1System: new FormControl(''),
                record2System: new FormControl('')
            })
            linkForm.patchValue(link)

            const result = service.validate(linkForm)

            expect(result).toBeNull()
        })
    })
})