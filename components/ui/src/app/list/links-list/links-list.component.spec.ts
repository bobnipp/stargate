import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FormsModule} from '@angular/forms'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import {ClickOutsideModule} from 'ng-click-outside'
import {makePrismNomination, makeRfi} from '../../../test_helpers/utilities'
import {LinksListComponent} from './links-list.component';

describe('LinksListComponent', () => {
    let component: LinksListComponent
    let fixture: ComponentFixture<LinksListComponent>

    beforeEach(async () => {

        TestBed.configureTestingModule({
            declarations: [LinksListComponent],
            imports: [
                FormsModule,
                ClickOutsideModule
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        fixture = TestBed.createComponent(LinksListComponent)
        component = fixture.componentInstance
    })

    describe('getRecordsForLinks', () => {
        it('maps rfi and prism links based on the open link', () => {
            component.anchorRecord = makeRfi({
                id: 'rfi1',
                links: [
                    {
                        record1Id: 'rfi1',
                        record2Id: 'rfi2',
                        record1System: 'IMM',
                        record2System: 'IMM',
                        title: '',
                        status: ''
                    },
                    {
                        record1Id: 'rfi3',
                        record2Id: 'rfi1',
                        record1System: 'IMM',
                        record2System: 'IMM',
                        title: '',
                        status: ''
                    },
                    {
                        record1Id: 'rfi1',
                        record2Id: 'nom1',
                        record1System: 'IMM',
                        record2System: 'PRISM',
                        title: '',
                        status: ''
                    },
                    {
                        record1Id: 'nom2',
                        record2Id: 'rfi1',
                        record1System: 'PRISM',
                        record2System: 'IMM',
                        title: '',
                        status: ''
                    }
                ]
            })

            component.rfis = [
                component.anchorRecord,
                makeRfi({id: 'rfi2'}),
                makeRfi({id: 'rfi3'})
            ]

            component.noms = [
                makePrismNomination({id: 'nom1'}),
                makePrismNomination({id: 'nom2'}),
                makePrismNomination({id: 'nom3'}),
            ]

            component.ngOnInit()

            expect(component.linkRecords).toEqual(jasmine.arrayContaining([
                makeRfi({id: 'rfi2'}),
                makeRfi({id: 'rfi3'}),
                makePrismNomination({id: 'nom1'}),
                makePrismNomination({id: 'nom2'}),
            ]))
        })

    })
})
