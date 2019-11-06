import {TestBed} from '@angular/core/testing'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import {FormsModule} from '@angular/forms'
import {SidebarMapListComponent} from './sidebar-map-list.component'
import {makeRfiListItem} from '../../../test_helpers/utilities'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {RfiService} from '../../services/rfi.service'
import {PrismService} from '../../services/prism.service'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {MockHTMLElement} from "../../../test_helpers/mockHTMLElement";
import Spy = jasmine.Spy;

describe('SidebarListComponent', () => {
    let component: SidebarMapListComponent
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let mockHtmlElement: MockHTMLElement
    let fetchRfiSpy: Spy

    beforeEach(async () => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()
        mockHtmlElement = new MockHTMLElement()

        TestBed.configureTestingModule({
            declarations: [SidebarMapListComponent],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: PrismService, useValue: mockPrismService}
            ],
            imports: [
                FormsModule
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })
        component = TestBed.createComponent(SidebarMapListComponent).componentInstance
        fetchRfiSpy = spyOn(mockRfiService, 'fetchRfi').and.callThrough()
    })

    it('it opens the sidebar', () => {
        const record = makeRfiListItem({
            id: 1,
            title: 'title1',
            justification: 'stuff',
            createdOn: '2018-1-3'
        })

        component.openRecordSidebar(record)
        expect(fetchRfiSpy).toHaveBeenCalledWith(record.id)
    })

    it('sets the length of justification', () => {
        const value1 = 'Lorem Ipsum is simply dummy text of the ' +
            'printing and typesetting industry. Lorem Ipsum has been the ' +
            'industry\'s standard dummy text ever since the 1500s'

        const value1trimmed = component.getTrimmedString(value1, 90)
        expect(value1trimmed.length).toEqual(93)
        expect(value1trimmed).toEqual(`${value1.substr(0, 90)}...`)

        const value2 = 'test'
        expect(component.getTrimmedString(value2, 90)).toEqual(value2)
    })

    it('on changes finds the html element and fires the scroll method', () => {
        spyOn(document, 'getElementById').and.returnValue(mockHtmlElement)
        const htmlElementSpy = spyOn(mockHtmlElement, 'scrollIntoView')
        const changes = {activeRecordIds: {currentValue: ['1']}}

        component.combinedList = []
        component.ngOnChanges(changes)

        expect(htmlElementSpy).toHaveBeenCalledWith({behavior: 'smooth'})
    })

    describe('output', () => {
        it('fires the reveal location event', () => {
            const revealSpy = spyOn(component.revealLocation, 'emit')
            component.fireRevealLocation('1')
            expect(revealSpy).toHaveBeenCalledWith('1')
        })

        it('fires the constrain list to map event', () => {
            const constrainSpy = spyOn(component.constrainResults, 'emit')
            component.doConstrainResults()
            expect(constrainSpy).toHaveBeenCalledWith(true)
        })
    })
})
