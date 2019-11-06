import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FilterSearchComponent} from './filter-search.component'
import {FilterService} from '../../services/filter.service'
import {FormsModule} from '@angular/forms'
import {MockFilterService} from "../../../test_helpers/mockFilterService";
import Spy = jasmine.Spy;

describe('FilterSearchComponent', () => {
    let component: FilterSearchComponent
    let fixture: ComponentFixture<FilterSearchComponent>
    let mockFilterService: MockFilterService
    let updateSearchFilterSpy: Spy
    let updateSearchFilterDontApplySpy: Spy

    beforeEach(() => {

        mockFilterService = new MockFilterService()

        TestBed.configureTestingModule({
            declarations: [FilterSearchComponent],
            providers: [
                {provide: FilterService, useValue: mockFilterService}
            ],
            imports: [FormsModule]
        })

        updateSearchFilterSpy = spyOn(mockFilterService, 'updateSearchFilter')
        updateSearchFilterDontApplySpy = spyOn(mockFilterService, 'updateSearchFilterDontApply')
        fixture = TestBed.createComponent(FilterSearchComponent)
        component = fixture.componentInstance
    })

    describe('search', () => {
        it('it sends search term to filter service', () => {
            component.searchInput = 'something'
            component.search()
            expect(updateSearchFilterSpy).toHaveBeenCalledWith('something')
        })
    })

    describe('clearCallback', () => {
        it('clears the search term from the input bar', () => {
            component.searchInput = 'something'
            component.clearCallback()
            expect(component.searchInput).toBe('')
        })

        it('sends a cleared search term to the filter service', () => {
            component.clearCallback()
            expect(updateSearchFilterDontApplySpy).toHaveBeenCalledWith('')
        })
    })
})
